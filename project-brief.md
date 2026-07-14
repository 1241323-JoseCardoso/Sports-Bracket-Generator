# Sports Bracket Generator

## 1. Project Vision

Sports Bracket Generator is an application that automates the creation and management of elimination tournament brackets.

The goal of this project is to allow users to create a tournament, register competing teams, generate a tournament bracket and track the progress of each match until a final winner is determined.

The project focuses on applying software engineering concepts such as domain modelling, state management, validation rules, automated testing and persistence.

The application should provide a clear visualization of the tournament progression while keeping the tournament logic independent from the presentation layer.

---

## 2. MVP (Minimum Viable Product)

The first version should support:

- Create a tournament;
- Define tournament name;
- Define tournament start and end dates;
- Register participating teams;
- Validate tournament information;
- Generate an elimination bracket;
- Display tournament rounds;
- Register match winners;
- Automatically advance winners to the next round;
- Determine the final tournament winner.

---

## 3. Out of Scope

The following features are not part of the initial version:

- Online multiplayer tournaments;
- User accounts;
- Authentication;
- Live match updates;
- Notifications;
- Tournament streaming integration.

---

## 4. Features

### Tournament Management

- Create a new tournament;
- Define tournament information;
- Validate tournament configuration;
- View tournament details.

### Team Management

- Add teams to a tournament;
- Remove teams before the tournament starts;
- Display participating teams.

### Bracket Generation

- Automatically generate tournament rounds;
- Create matches based on the number of participating teams;
- Display the tournament structure.

### Match Management

- Register match results;
- Define the winner of each match;
- Advance winners automatically;
- Track completed and pending matches.

### Tournament Completion

- Detect when the tournament has ended;
- Display the final winner;
- Show tournament history.

### Persistence

- Save tournament data;
- Load previous tournaments;
- Preserve tournament progress between application sessions.

---

## 5. User Stories

### US01 - Create Tournament

**As a User, I want to create a tournament, so that I can manage its participants and matches.**

#### Acceptance Criteria:

- AC1: User can enter the tournament name.
- AC2: User can define the tournament start date.
- AC3: User can define the tournament end date.
- AC4: The tournament cannot be created with invalid information.

### US02 - Validate Tournament Dates

**As a User, I want the system to validate tournament dates, so that invalid tournaments cannot be created.**

#### Acceptance Criteria:

- AC1: The start date must be valid.
- AC2: The end date must be valid.
- AC3: The end date cannot occur before the start date.
- AC4: The user receives a warning when dates are invalid.

### US03 - Register Teams

**As a User, I want to add teams to the tournament, so that they can participate in the competition.**

#### Acceptance Criteria:

- AC1: User can add a team name.
- AC2: The tournament stores the registered teams.
- AC3: Duplicate teams cannot be added.
- AC4: Teams cannot be modified after the tournament starts.

### US04 - Generate Tournament Bracket

**As a User, I want the system to generate a tournament bracket, so that I do not need to manually create the match structure.**

#### Acceptance Criteria:

- AC1: The bracket is generated based on the registered teams.
- AC2: Each match contains two participants.
- AC3: Winners advance to the next round.
- AC4: The bracket continues until a final winner is determined.

### US05 - View Tournament Progress

**As a User, I want to see the current tournament bracket, so that I can follow the progress of each team.**

#### Acceptance Criteria:

- AC1: The user can see all tournament rounds.
- AC2: Completed matches show their winner.
- AC3: Pending matches show the teams that will compete.
- AC4: The final winner is displayed when the tournament ends.

### US06 - Register Match Result

**As a User, I want to register the result of a match, so that the tournament can continue.**

#### Acceptance Criteria:

- AC1: The user can select the match winner.
- AC2: The winner advances automatically.
- AC3: A completed match cannot be modified without confirmation.
- AC4: A match cannot be completed without a valid winner.

### US07 - Complete Tournament

**As a User, I want the system to determine the tournament winner, so that I know who won the competition.**

#### Acceptance Criteria:

- AC1: The tournament ends when the final match is completed.
- AC2: The winner is displayed.
- AC3: The tournament status changes to completed.

### US08 - Save Tournament

**As a User, I want my tournaments to be saved, so that I can continue managing them later.**

#### Acceptance Criteria:

- AC1: Tournament data can be saved.
- AC2: Saved tournaments can be loaded.
- AC3: Match results are preserved.
- AC4: Tournament progress is preserved.

### US09 - View Tournament History

**As a User, I want to view previous tournaments, so that I can check completed competitions.**

#### Acceptance Criteria:

- AC1: Completed tournaments are stored.
- AC2: The winner of previous tournaments can be viewed.
- AC3: Tournament information remains available after closing the application.

---

## 6. Business Rules

### Tournament Rules

- A tournament must have a name.
- A tournament must have valid dates.
- A tournament must contain a valid number of teams.
- A tournament cannot start without enough participants.
- A tournament cannot be modified after it begins.

### Team Rules

- Each team must have a unique name inside a tournament.
- A team can only participate once in the same tournament.

### Bracket Rules

- The tournament follows an elimination format.
- Each match eliminates one participant.
- The winner advances to the next round.
- The tournament finishes when only one participant remains.

### Match Rules

- A match must contain participants.
- A match can only have one winner.
- A completed match cannot automatically be replayed.
- Winners are automatically placed into the next round.

---

## 7. Bonus Features

Possible future improvements:

### Ranking System

The system can maintain a ranking of teams based on performance.

Examples:

- tournament victories;
- match wins;
- points earned.

The ranking structure can be optimized using an ordered data structure.

### Team Statistics

The system can track:

- number of tournaments played;
- wins;
- losses;
- historical performance.

### Advanced Tournament Formats

Support additional formats:

- group stages;
- round robin;
- double elimination.

### Export Tournament

Allow users to export:

- tournament brackets;
- results;
- statistics.

---

## 8. Learning Goals

This project is intended to practice:

- Domain modelling;
- Object-oriented design;
- State management;
- Data structures and algorithms;
- Tree-based structures;
- Validation rules;
- Automated testing;
- Persistence;
- User interface development;
- Software architecture.

---

## 9. Project Evolution

The project should evolve incrementally:

### Version 1

- Create tournaments;
- Register teams;
- Generate brackets;
- Manage matches.

### Version 2

- Improve user interface;
- Add persistence;
- Add tournament history.

### Version 3

- Add ranking;
- Add statistics;
- Add advanced tournament features.

---

## Backend:

Java + Spring Boot

## Frontend:

React + TypeScript

## Persistência:

PostgreSQL

## Testes:

JUnit + Mockito + talvez Testcontainers