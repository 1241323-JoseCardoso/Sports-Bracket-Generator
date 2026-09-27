# Sports-Bracket-Generator

Sports Bracket Generator is a Java-based application designed to manage single-elimination tournament brackets, handle team registrations, and track round transitions up to the final champion.

The project was developed as a software engineering exercise focusing on domain-driven design (DDD), Test-Driven Development (TDD), reactive round transitions, and clean architecture principles.

---

## Screenshots

### Tournament Bracket Overview

<p align="center">
  <img src="docs/images/bracket-overview.png" alt="Tournament Bracket Overview" width="600"/>
</p>

### Match Result Registration

<p align="center">
  <img src="docs/images/match-registration.png" alt="Match Registration" width="600"/>
</p>

---

## Features

- Create and manage tournament definitions with maximum team limits
- Track tournament session lifecycles (`DRAFTED`, `IN_PROGRESS`, `PAUSED`, `CONCLUDED`)
- Register competing teams while ensuring uniqueness
- Validate power-of-2 team constraints ($2, 4, 8, 16 \dots$) via bitwise operations
- Randomly shuffle teams and dynamically generate first-round matchups
- Record individual match scores and determine winners via `MatchSession`
- Event-driven round transitions: automatically advance to the next round when all active matches finish
- Crown the final tournament champion upon concluding the final match
- Comprehensive unit test suite built with JUnit 5 following TDD principles

---

## Architecture

The project follows Domain-Driven Design (DDD) principles and a layered architecture:

```text
Presentation / API
    ↓
Application
    ↓
Domain
```
### Domain Layer

The domain layer contains all the core business rules, entity invariants, and state transitions.

Current domain classes and objects:

- `TournamentSession` (Aggregate Root)
- `Team` (Aggregate Root)
- `Tournament` (Entity)
- `Match` (Entity)
- `MatchSession` (Entity)
- `TournamentSessionState` (Enumeration)

---

## Domain Overview

The central concept of the project is the `TournamentSession`.

A `Tournament` defines the static setup (code, max team capacity), while a `TournamentSession` manages a specific execution of that tournament.

The session guarantees that:
1. The number of teams is a power of 2 before running (`runTournament()`).
2. Matches are created dynamically round by round without redundant placeholder nodes.
3. Every finished match (`nextRound(match)`) registers its winner in `roundWinners`.
4. When all active round matches complete, `advanceToNextRound()` builds the next round automatically or crowns the champion if only one winner remains.

---

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 21 or newer
- Maven

Check your Java version:

```bash
java -version
```

Check your Maven version:

```bash
mvn -version
```

---

## Running Tests

The project was developed using **TDD (Test-Driven Development)**. To run all unit tests in `BracketTest`:

```bash
mvn test
```

To compile the project:

```bash
mvn compile
```
---

## Documentation

Additional project documentation and diagrams are available in the `docs/` folder:

* [Domain Model Documentation]()
* [Design Class Diagram]()

---

## Learning Goals

This project was created to practice:

* Domain-Driven Design (DDD) & Aggregate Root boundaries
* Test-Driven Development (TDD) with JUnit 5
* Bitwise algorithm validations (Power of 2 check)
* Event-driven state transitions (Reactive round advancement)
* Separation of concerns between domain rules and presentation logic

---

## License

This project is for educational purposes.