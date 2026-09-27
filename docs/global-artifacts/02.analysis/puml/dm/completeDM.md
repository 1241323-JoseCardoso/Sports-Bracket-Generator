# Domain Model

## 1. Purpose

This document describes the conceptual domain model of the Sports Bracket project.

The domain model focuses on the main business concepts of single-elimination tournament management and their responsibilities. It does not describe user interface details or implementation-specific concerns such as JavaFX layouts, REST controllers, or database persistence schemas.

---

## 2. Domain Scope

The sports bracket domain is responsible for:

- managing tournament setup and maximum team capacity;
- managing tournament session life cycle states (`DRAFTED`, `IN_PROGRESS`, `PAUSED`, `CONCLUDED`);
- registering participating teams and preventing duplicates;
- validating that the total number of teams is a valid power of 2 (e.g., 2, 4, 8, 16);
- shuffling teams and dynamically building tournament round matches;
- recording match results and scores via match sessions;
- reactively advancing winning teams to subsequent rounds;
- determining the final tournament champion.

The domain does not include presentation concepts such as UI widgets, REST payloads, API routes, or visual styling.

---

## 3. Domain Model Diagram

![Domain Model](\Sports-Bracket-Generator\docs\global-artifacts\02.analysis\png\dm\domain_model.png)

PlantUML source:

```text
docs/global-artifacts/02.analysis/puml/dm/completeDM.puml
```

---

## 4. Main Domain Concepts

| Concept | Type | Responsibility |
| :--- | :--- | :--- |
| `TournamentSession` | Aggregate Root | Coordinates tournament progress, manages active round matches, and protects lifecycle business rules. |
| `Tournament` | Entity | Represents the tournament definition, including its identification code and capacity limit. |
| `Team` | Aggregate Root | Represents a competing sports team with its identity, name, and historical win/loss stats. |
| `Match` | Entity | Represents a single knockout confrontation between two opposing teams. |
| `MatchSession` | Entity | Tracks real-time game details, score updates, and completion status for a specific match. |
| `TournamentSessionState` | Enumeration | Represents the current operational state of a tournament session. |

---

## 5. Aggregate Root

### TournamentSession

`TournamentSession` is the primary aggregate root of the domain.

It is responsible for coordinating the execution of a tournament bracket and ensuring that every round transition respects knockout tournament rules.

Main state managed by the aggregate:

- associated tournament definition;
- current session state (`DRAFTED`, `IN_PROGRESS`, `PAUSED`, `CONCLUDED`);
- start and end dates;
- registered teams list;
- active round matches;
- current round winners list;
- final tournament champion (`winner`).

The application layer should interact with the tournament through domain actions (e.g., runTournament(), nextRound()), instead of directly mutating its internal collections.

### Team

`Team` is an independent aggregate root representing a participating entity in the system.

Main state managed by the aggregate:

- unique team identifier (`UUID`);
- team name;
- accumulated win and loss records;

---

## 6. Entities

### Tournament

`Tournament` represents the structural configuration of a tournament.

It contains:

- unique identifier (`UUID`);
- unique tournament slug/code;
- maximum allowed number of team.

### Match

`Match` represents a scheduled game within a round.

It contains:

- first opponent (`teamOne`);
- second opponent (`teamSecond`);
- determined winner (`winningTeam`);


### MatchSession

`MatchSession` manages the execution and scorekeeping of an individual match.

It contains:

- reference to the underlying `Match`;
- score map tracking goals/points for each opponent;
- finished status indicator.

---

## 7. Enumerations

### TournamentSessionState

Supported session lifecycle states:

- `DRAFTED`
- `IN_PROGRESS`
- `PAUSED`
- `CONCLUDED`

---

## 8. Business Rules Represented in the Domain

The domain model supports the following rules:

- A tournament session starts in the `DRAFTED` state.
- Teams added to a tournament must be unique; duplicate entries are rejected.
- The total number of teams registered cannot exceed the tournament's `maxTeams` limit.
- A tournament can only start if the team count is at least 2 and equal to a power of 2 ($2, 4, 8, 16 \dots$).
- Teams are randomly shuffled at the start to generate fair first-round matchups.
- Matches are created dynamically round by round without empty placeholder nodes.
- A match score must be updated through `MatchSession` before declaring a winner.
- Matches in a knockout system cannot end in an unresolved draw.
- Round advancement occurs automatically when every match in the active round has a declared winner.
- When a single winning team remains after the final match, the tournament state changes to `CONCLUDED` and the team is crowned champion.

---

## 9. Notes

This model intentionally keeps the domain focused on core knockout bracket rules.

The project follows a DDD-lite approach: aggregate boundaries and invariants are explicitly enforced, but lightweight pure Java domain models are used without heavy framework dependencies.

The model may evolve as tie-breaking rules, seeding configurations, or persistence layers are introduced.