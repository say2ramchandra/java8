# Functional Interfaces - Practice Exercises

## Basic Level Exercises

### Exercise 1: Understanding Predicate
Create Predicate instances for the following:
1. Check if a number is odd
2. Check if a string contains the word "Java"
3. Check if a number is within range 10-100 (inclusive)
4. Check if a string length is greater than 10
5. Combine two predicates with AND: number is even AND greater than 50

### Exercise 2: Working with Function
Create Function instances that:
1. Convert a string to its character count
2. Convert an integer to its binary string representation
3. Extract the domain from an email address (e.g., "user@gmail.com" → "gmail.com")
4. Square a number and add 10
5. Chain two functions: trim a string, then convert to uppercase

### Exercise 3: Consumer Practice
Write Consumer instances that:
1. Print a number with "Number: " prefix
2. Add an element to a given ArrayList
3. Modify a Person object's age by incrementing it
4. Log a message with timestamp
5. Chain two consumers: print string length, then print string reversed

### Exercise 4: Supplier Basics
Create Supplier instances that:
1. Return current date and time as string
2. Generate a random boolean value
3. Return a UUID string
4. Supply an empty ArrayList
5. Return PI value (Math.PI)

---

## Intermediate Level Exercises

### Exercise 5: Custom Functional Interfaces
Create the following custom functional interfaces and implement them:
1. `StringProcessor` - takes a string and returns modified string
2. `NumberValidator` - takes a number and returns validation message
3. `DataConverter<T, R>` - generic interface for data conversion
4. `TriPredicate<T, U, V>` - predicate with three parameters
5. `QuadFunction<T, U, V, W, R>` - function with four parameters

### Exercise 6: Predicate Combinations
Given predicates for checking:
- Is adult (age >= 18)
- Has valid email
- Has strong password
- Is premium member

Write code to:
1. Combine all predicates with AND
2. Create predicate: adult OR premium member
3. Negate the adult predicate
4. Check multiple users against combined predicates
5. Create a predicate that passes if at least 2 out of 4 conditions are true

### Exercise 7: Function Chaining
Create a data transformation pipeline using Function chaining:
1. Start with a CSV string: "John,Doe,30,Engineer"
2. Split by comma
3. Convert to Person object
4. Calculate a score based on age and profession
5. Format as JSON string

Implement each step as a separate Function and chain them.

### Exercise 8: BiFunction and BinaryOperator
Create implementations for:
1. BiFunction that calculates distance between two points (x1,y1) and (x2,y2)
2. BiFunction that merges two maps
3. BinaryOperator that finds GCD of two numbers
4. BinaryOperator that concatenates strings with a delimiter
5. BiFunction that creates a formatted address from street and city

---

## Advanced Level Exercises

### Exercise 9: Validation Framework
Build a validation framework using functional interfaces:
1. Create a `ValidationResult` class with success/failure and error messages
2. Create a `Validator<T>` functional interface
3. Implement validators for:
   - Email format
   - Phone number format
   - Credit card number (Luhn algorithm)
   - Strong password (length, uppercase, lowercase, digit, special char)
   - Age range
4. Create a `CompositeValidator` that combines multiple validators
5. Test with a user registration form

### Exercise 10: Expression Evaluator
Create a mathematical expression evaluator:
1. Define functional interfaces for unary and binary operations
2. Implement operations: +, -, *, /, %, ^, sqrt, abs
3. Create an expression parser using functional interfaces
4. Support operator precedence
5. Evaluate expressions like: "2 + 3 * 4", "sqrt(16) + 5"

### Exercise 11: Event System
Design an event-driven system:
1. Create `Event<T>` class with event data
2. Define `EventHandler<T>` functional interface
3. Create `EventBus` that registers handlers and publishes events
4. Implement event filtering using Predicates
5. Create event transformers using Functions
6. Test with different event types: UserLoginEvent, OrderPlacedEvent, ErrorEvent

### Exercise 12: Caching with Suppliers
Implement a memoization/caching system:
1. Create a `MemoizedSupplier<T>` that caches the result of a Supplier
2. Create a `MemoizedFunction<T, R>` that caches function results
3. Implement time-based cache expiration
4. Add cache statistics (hits, misses)
5. Test with expensive operations (e.g., factorial, fibonacci)

---

## Expert Level Exercises

### Exercise 13: DSL for Database Queries
Create a Domain-Specific Language (DSL) for building database queries:
1. Use functional interfaces to build query components:
   - `WhereClause`: Predicate-based filtering
   - `SelectClause`: Function for column selection
   - `OrderByClause`: Comparator for sorting
2. Create fluent API: `query().select(...).where(...).orderBy(...)`
3. Support AND/OR combinations in WHERE clauses
4. Implement JOIN operations using BiFunction
5. Generate SQL string from the query object

### Exercise 14: Reactive Data Pipeline
Build a reactive data processing pipeline:
1. Create `Publisher<T>` that emits data
2. Create `Subscriber<T>` that consumes data
3. Implement transformations using Function
4. Add filtering using Predicate
5. Support error handling using Consumer<Exception>
6. Implement backpressure mechanism
7. Test with streaming data source

### Exercise 15: Rule Engine
Implement a business rule engine:
1. Create `Rule<T>` class with condition (Predicate) and action (Consumer)
2. Create `RuleEngine<T>` that evaluates and executes rules
3. Support rule priorities
4. Implement rule chaining (if rule A passes, evaluate rule B)
5. Add conflict resolution strategies
6. Create rules for:
   - Loan approval system
   - Discount calculation
   - Fraud detection

### Exercise 16: Type-Safe Builder Pattern
Create a type-safe builder using functional interfaces:
1. Build a `Person` object with required and optional fields
2. Use functional interfaces to enforce build order
3. Prevent invalid states at compile time
4. Support nested builders for complex objects
5. Implement validation at each step using Predicates
6. Example usage:
```java
Person person = PersonBuilder
    .start()
    .withName("John")
    .withAge(30)
    .withEmail("john@example.com")
    .build();
```

### Exercise 17: Lazy Evaluation Framework
Create a framework for lazy evaluation:
1. Create `Lazy<T>` wrapper using Supplier
2. Implement lazy List, Map, and Set
3. Support lazy transformations (map, filter)
4. Implement lazy evaluation for expensive computations
5. Add caching to avoid re-computation
6. Test performance with large datasets

### Exercise 18: Functional Error Handling
Implement functional error handling without exceptions:
1. Create `Result<T, E>` type (Either success or error)
2. Use Function for transformations that might fail
3. Implement `map`, `flatMap`, `filter` operations
4. Create error recovery strategies using Supplier
5. Chain operations with proper error propagation
6. Test with file I/O and parsing operations

---

## Bonus Challenges

### Exercise 19: Custom Collectors Framework
Create a framework for custom data collection:
1. Define functional interfaces for collection operations
2. Implement collectors for:
   - Grouping with custom key extractor (Function)
   - Filtering during collection (Predicate)
   - Transformation during collection (Function)
   - Custom reduction (BinaryOperator)
3. Support parallel collection
4. Test with large datasets

### Exercise 20: Functional State Machine
Design a state machine using functional interfaces:
1. Define states and transitions
2. Use Predicate for transition conditions
3. Use Consumer for state entry/exit actions
4. Use Function for state transitions
5. Implement event-driven state changes
6. Create a traffic light simulation
7. Create an order processing workflow

---

## Tips for Practice:
- Always annotate your custom functional interfaces with `@FunctionalInterface`
- Use built-in functional interfaces when possible
- Practice method reference syntax alongside lambdas
- Consider thread safety for stateful functional interfaces
- Write unit tests for your functional code
- Experiment with partial application and currying
- Compare functional vs. imperative approaches
- Profile performance for different implementations
