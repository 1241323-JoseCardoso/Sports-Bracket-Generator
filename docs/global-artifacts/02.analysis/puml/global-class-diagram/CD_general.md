# Design Class Diagram

## 1. Purpose

This document describes the implementation-oriented class diagram of the Calculator project.

Unlike the domain model, this diagram may include application and presentation classes, because its purpose is to explain how the system is structured in code.

---

## 2. Design Class Diagram

![Design Class Diagram](\docs\global-artifacts\02.analysis\png\class-diagram\CD_general.png)

PlantUML source:

```text
docs/diagrams/class-diagram.puml
```

---

## 3. Layered Architecture

The project follows a simple layered structure:

```text
Presentation
    ↓
Application
    ↓
Domain
```

Each layer has a specific responsibility.

---

## 4. Presentation Layer

The presentation layer is responsible for user interaction.

Expected responsibilities:

- displaying the calculator interface;
- receiving button click events;
- sending user actions to the controller;
- displaying the updated value returned by the application layer.

The presentation layer must not contain calculation rules.

Expected classes:

| Class | Responsibility |
| :--- | :--- |
| `CalculatorUI` | Displays the calculator and captures user input events. |
| `EntryPad` | Represents the visual group of calculator buttons, if modelled in the UI layer. |

---

## 5. Application Layer

The application layer coordinates user actions and communicates with the domain.

Current/expected classes:

| Class | Responsibility |
| :--- | :--- |
| `CalculatorController` | Receives input events from the UI and calls the service. |
| `CalculatorService` | Coordinates actions and keeps access to the active calculator instance. |

The controller should remain thin. It should not contain calculation rules.

The service may translate external input, such as symbols, into domain concepts such as `OperatorType`.

Example:

```text
"+" → OperatorType.ADD
"-" → OperatorType.SUBTRACT
"*" → OperatorType.MULTIPLY
"/" → OperatorType.DIVIDE
```

---

## 6. Domain Layer

The domain layer contains the calculator state and rules.

Current classes:

| Class | Responsibility |
| :--- | :--- |
| `Calculator` | Aggregate root that manages state and operations. |
| `CalculatorInput` | Represents and validates the current input. |
| `DisplayValue` | Represents the visible display value. |
| `Calculation` | Represents a completed arithmetic operation. |
| `OperatorType` | Enumeration of supported operators. |
| `CalculatorStatus` | Enumeration of calculator states. |

---

## 7. Main Interaction Flow

A typical user interaction follows this flow:

```text
User presses a button
        ↓
CalculatorUI captures the event
        ↓
CalculatorController receives the input
        ↓
CalculatorService coordinates the action
        ↓
Calculator updates its internal state
        ↓
Updated display value is returned to the UI
```

Example flow for numeric input:

```text
User presses "5"
        ↓
Controller receives "5"
        ↓
Service calls the calculator
        ↓
Calculator updates current input
        ↓
Calculator updates display value
        ↓
UI displays "5"
```

---

## 8. Design Decisions

### One Calculator Instance per Session

The calculator is created once when the application starts.

The same calculator instance is reused during the session, allowing the domain object to preserve state between user actions.

### Controller Does Not Receive Calculator as Parameter

The controller does not receive the calculator object in every method call.

Instead:

- the service owns or has access to the active calculator;
- the controller calls the service;
- each method receives only the user action/input.

### DTO Removed for Now

A DTO is not currently necessary because the application only returns a single display string.

If the UI later needs more information, such as status, button availability or error messages, a DTO may be introduced.

---

## 9. Important Implementation Notes

The design should avoid:

- placing calculation rules in the UI;
- passing the calculator object around in every method call;
- creating a new calculator for every user action;
- using UI concepts as domain concepts;
- spreading string-based operator logic across the domain.

The design should prefer:

- meaningful domain methods;
- small application layer methods;
- one active calculator instance;
- rules protected by the domain;
- tests based on observable behaviour.

---

## 10. Known Refactoring Points

The following points should be reviewed as the implementation evolves:

- ensure the digit limit accepts exactly 8 numeric digits;
- ensure error state blocks normal input;
- ensure `C` does not fail when input is empty;
- keep `CalculatorService` in the application layer if it acts as an application service;
- keep presentation classes out of the domain model;
- keep the class diagram synchronized with the implementation.

---

## 11. Notes

This class diagram is expected to evolve during implementation.

The goal is not to create a perfect design upfront, but to keep the code understandable, testable and aligned with the project rules.