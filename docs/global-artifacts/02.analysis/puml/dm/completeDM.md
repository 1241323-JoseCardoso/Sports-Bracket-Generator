# Domain Model

## 1. Purpose

This document describes the conceptual domain model of the Calculator project.

The domain model focuses on the main business concepts of the calculator and their responsibilities. It does not describe user interface details or implementation-specific concerns such as JavaFX layouts.

---

## 2. Domain Scope

The calculator domain is responsible for:

- managing the current numeric input;
- storing the value used in a pending operation;
- storing the selected arithmetic operator;
- executing arithmetic operations;
- updating the display value;
- handling invalid operations;
- managing the calculator status.

The domain does not include presentation concepts such as buttons, layouts, JavaFX components or visual styling.

---

## 3. Domain Model Diagram

![Domain Model](\docs\global-artifacts\02.analysis\png\dm\Calculator.png)

PlantUML source:

```text
docs/diagrams/domain-model.puml
```

---

## 4. Main Domain Concepts

| Concept | Type | Responsibility |
| :--- | :--- | :--- |
| `Calculator` | Aggregate Root | Coordinates the calculator state and protects the calculator business rules. |
| `CalculatorInput` | Value Object | Represents the number currently being entered by the user. |
| `DisplayValue` | Value Object | Represents the value currently shown on the calculator display. |
| `Calculation` | Value Object | Represents a completed arithmetic operation. |
| `OperatorType` | Enumeration | Represents the supported arithmetic operators. |
| `CalculatorStatus` | Enumeration | Represents whether the calculator is ready or in an error state. |

---

## 5. Aggregate Root

### Calculator

`Calculator` is the aggregate root of the domain.

It is responsible for coordinating the internal state of the calculator and ensuring that every operation respects the calculator rules.

Main state managed by the aggregate:

- current input;
- stored value;
- pending operator;
- display value;
- calculator status;
- calculation history.

The application layer should interact with the calculator through meaningful actions, instead of directly changing its internal state.

---

## 6. Value Objects

### CalculatorInput

`CalculatorInput` represents the value currently being entered by the user.

It is responsible for input-related rules, such as:

- counting numeric digits;
- tracking whether a decimal separator has already been entered;
- exposing the current raw input value.

### DisplayValue

`DisplayValue` represents the value visible to the user.

Possible display values include:

- the initial value `0`;
- the current input;
- the selected operator;
- the operation result;
- the error value `ERR`.

### Calculation

`Calculation` represents an arithmetic operation that has already been completed.

It contains:

- the first number;
- the second number;
- the selected operator;
- the calculated result.

---

## 7. Enumerations

### OperatorType

Supported operators:

- `ADD`
- `SUBTRACT`
- `MULTIPLY`
- `DIVIDE`

### CalculatorStatus

Supported calculator states:

- `READY`
- `ERR`

---

## 8. Business Rules Represented in the Domain

The domain model supports the following rules:

- The calculator starts with display value `0`.
- The calculator accepts numeric input.
- The calculator accepts decimal input.
- A number may contain only one decimal separator.
- A user-entered number may contain at most 8 numeric digits.
- Extra digits beyond the limit are ignored.
- The calculator supports addition, subtraction, multiplication and division.
- Operations are evaluated sequentially.
- Division by zero results in `ERR`.
- A result whose integer part exceeds 8 digits results in `ERR`.
- When the calculator is in `ERR` state, normal input is ignored.
- The calculator can recover from `ERR` using `C` or `AC`.

---

## 9. Notes

This model intentionally keeps the domain small.

The project follows a DDD-lite approach: domain concepts are explicit, but no heavy framework or infrastructure layer is used.

The model may evolve as implementation progresses.