# Java 8 Revision Exercises

**Test Your Complete Java 8 Knowledge**

---

## Overview

These exercises test your understanding across **ALL** Java 8 features. Each exercise combines multiple concepts to ensure comprehensive mastery.

**Difficulty Levels:**
- ⭐ Basic - Single concept application
- ⭐⭐ Intermediate - Multiple concepts
- ⭐⭐⭐ Advanced - Complex scenarios

---

## Exercise 1: Complete Pipeline ⭐⭐
**Concepts: Lambdas, Streams, Collectors**

Given a list of employees with name, age, salary, and department:
1. Filter employees with salary > 50000
2. Group by department
3. Calculate average age per department
4. Return map sorted by average age

**Input:**
```java
List<Employee> employees = Arrays.asList(
    new Employee("Alice", 30, 75000, "IT"),
    new Employee("Bob", 25, 45000, "HR"),
    new Employee("Charlie", 35, 85000, "IT"),
    new Employee("David", 28, 55000, "Finance")
);
```

**Expected Output:**
```
{HR=25.0, Finance=28.0, IT=32.5}
```

---

## Exercise 2: Optional Chaining ⭐⭐
**Concepts: Optional, Streams, Method References**

Create a method that:
1. Accepts a list of strings
2. Finds the first string longer than 5 characters
3. Converts to uppercase
4. Returns Optional<String>

Handle all edge cases (null input, empty list, no match).

**Test Cases:**
```java
findLongString(null) → Optional.empty()
findLongString(Arrays.asList()) → Optional.empty()
findLongString(Arrays.asList("hi", "hello")) → Optional.empty()
findLongString(Arrays.asList("hi", "wonderful")) → Optional.of("WONDERFUL")
```

---

## Exercise 3: Custom Collector ⭐⭐⭐
**Concepts: Collectors, Functional Interfaces**

Create a custom collector that:
1. Collects to a comma-separated string
2. Wraps result in square brackets
3. Converts each element to uppercase

**Example:**
```java
Stream.of("java", "python", "c++")
    .collect(customCollector())
// Result: "[JAVA, PYTHON, C++]"
```

---

## Exercise 4: Date Range Generator ⭐⭐
**Concepts: Date Time API, Streams, Lambdas**

Create a method that generates all dates between two dates (inclusive):
```java
List<LocalDate> getDateRange(LocalDate start, LocalDate end)
```

**Filter out weekends** and return only **business days**.

**Example:**
```java
getDateRange(LocalDate.of(2024, 3, 25), LocalDate.of(2024, 3, 29))
// Returns: [2024-03-25, 2024-03-26, 2024-03-27, 2024-03-28, 2024-03-29]
// (excluding weekend if any)
```

---

## Exercise 5: Parallel Processing ⭐⭐⭐
**Concepts: Parallel Streams, Performance**

Compare sequential vs parallel processing:
1. Create a list of 1 million numbers
2. Find sum of squares of even numbers
3. Measure and compare execution times
4. Determine the break-even point

**Questions to answer:**
- At what dataset size does parallel become faster?
- What's the performance difference?
- Is it consistent across runs?

---

## Exercise 6: Async Data Fetching ⭐⭐⭐
**Concepts: CompletableFuture, Streams, Error Handling**

Simulate fetching data from 3 APIs:
1. User service (returns User object)
2. Orders service (returns List<Order>)
3. Recommendations service (returns List<Product>)

**Requirements:**
- Fetch all 3 in parallel
- Combine results into a Dashboard object
- Handle failures gracefully (use defaults)
- Timeout after 2 seconds

```java
class Dashboard {
    User user;
    List<Order> orders;
    List<Product> recommendations;
}
```

---

## Exercise 7: Interface Evolution ⭐⭐
**Concepts: Default Methods, Functional Interfaces**

You have an existing interface `DataProcessor`:
```java
interface DataProcessor {
    String process(String data);
}
```

**Without breaking existing implementations:**
1. Add validation (default method)
2. Add logging (default method)
3. Add utility methods (static methods)
4. Create enhanced version that uses all methods

---

## Exercise 8: Smart Cache ⭐⭐⭐
**Concepts: Optional, CompletableFuture, Collectors**

Implement a cache with these features:
1. `Optional<V> get(K key)` - returns cached value
2. `CompletableFuture<V> getAsync(K key, Supplier<V> loader)` - loads if missing
3. Expiry after 5 seconds
4. Statistics (hits, misses, hit rate)

**Usage:**
```java
SmartCache<String, User> cache = new SmartCache<>();
Optional<User> user = cache.get("user1");
CompletableFuture<User> futureUser = cache.getAsync("user1", () -> db.loadUser("user1"));
```

---

## Exercise 9: Data Transformation Pipeline ⭐⭐⭐
**Concepts: Streams, Collectors, Method References**

Given a CSV string of transactions:
```
"1,Alice,100.50,2024-03-01,IT"
"2,Bob,250.75,2024-03-01,HR"
"3,Alice,150.00,2024-03-02,IT"
```

Create a pipeline that:
1. Parses each line to Transaction object
2. Groups by date
3. Calculates total amount per date
4. Finds the date with highest total
5. Returns Optional<LocalDate>

---

## Exercise 10: Recursive Stream Processing ⭐⭐⭐
**Concepts: Streams, FlatMap, Recursion**

Given a tree structure:
```java
class Node {
    String name;
    List<Node> children;
}
```

Create a method that:
1. Flattens the entire tree
2. Collects all names
3. Returns sorted unique names

**Example:**
```
Root
├── Child1
│   ├── GrandChild1
│   └── GrandChild2
└── Child2
```

Should return: `[Child1, Child2, GrandChild1, GrandChild2, Root]`

---

## Exercise 11: Functional Composition ⭐⭐
**Concepts: Function, Predicate, Composition**

Create a validation framework:
```java
class Validator<T> {
    Validator<T> validate(Predicate<T> rule, String message);
    boolean isValid(T value);
    List<String> getErrors(T value);
}
```

**Usage:**
```java
Validator<String> emailValidator = new Validator<String>()
    .validate(s -> s.contains("@"), "Must contain @")
    .validate(s -> s.length() > 5, "Too short")
    .validate(s -> !s.startsWith(" "), "Cannot start with space");

emailValidator.isValid("test@mail.com"); // true
emailValidator.getErrors("bad"); // ["Must contain @", "Too short"]
```

---

## Exercise 12: Time-based Processing ⭐⭐
**Concepts: Date Time API, Streams, Duration**

Given a list of events with timestamp:
```java
class Event {
    String name;
    LocalDateTime timestamp;
}
```

Create methods to:
1. Find events in last 24 hours
2. Group events by hour of day
3. Calculate average time between events
4. Find the busiest hour

---

## Exercise 13: Multi-level Grouping ⭐⭐⭐
**Concepts: Collectors, Streams**

Given employees with: name, department, level, salary

Create a report structure:
```
Map<String, Map<String, DoubleSummaryStatistics>>
```

Where:
- First level: Group by department
- Second level: Group by level (Junior/Senior/Lead)
- Value: Statistics of salaries (count, sum, min, max, average)

---

## Exercise 14: Reactive Pipeline ⭐⭐⭐
**Concepts: CompletableFuture, Streams, Error Handling**

Create a reactive pipeline that:
1. Fetches list of IDs
2. For each ID, fetches details (parallel, max 5 concurrent)
3. Enriches data with additional info
4. Filters invalid entries
5. Returns CompletableFuture<List<Result>>

Handle partial failures (some IDs might fail).

---

## Exercise 15: Performance Optimizer ⭐⭐⭐
**Concepts: Parallel Streams, Collectors, Performance**

Given a large dataset, create a method that:
1. Automatically decides sequential vs parallel
2. Based on dataset size and operation type
3. Measures actual performance
4. Adapts for future calls

**Considerations:**
- Dataset size
- Operation complexity
- Available processors
- Historical performance data

---

## Bonus Challenge: Complete System ⭐⭐⭐

Build a mini reporting system combining ALL concepts:

**Requirements:**
1. Load data asynchronously (CompletableFuture)
2. Process with streams (filter, map, reduce)
3. Handle missing data (Optional)
4. Support parallel processing
5. Generate time-based reports (Date Time API)
6. Provide grouping and statistics (Collectors)
7. Use functional interfaces for customization
8. Implement with default methods in interfaces

**Real-world scenario:**
Sales reporting system that analyzes transactions, generates insights, and provides customizable reports.

---

## Solution Guidelines

### For Each Exercise:
1. ✅ Write clean, readable code
2. ✅ Use appropriate functional interfaces
3. ✅ Add error handling
4. ✅ Include test cases
5. ✅ Comment complex logic
6. ✅ Consider performance

### Code Quality Checklist:
- [ ] Uses lambdas where appropriate
- [ ] Uses method references when possible
- [ ] Proper null handling with Optional
- [ ] Efficient stream operations
- [ ] Thread-safe if using parallel streams
- [ ] Proper exception handling
- [ ] Well-named variables and methods
- [ ] Comprehensive test coverage

---

## Testing Your Solutions

Run each solution with:
- **Valid** inputs
- **Edge cases** (null, empty, extreme values)
- **Large datasets** (performance)
- **Concurrent scenarios** (if applicable)

---

## Learning Tips

1. **Start Simple**: Begin with basic exercises
2. **Combine Concepts**: Try to use multiple features together
3. **Refactor**: Write imperative first, then convert to functional
4. **Benchmark**: Compare different approaches
5. **Debug**: Use `peek()` to debug stream pipelines
6. **Read Docs**: Java 8 API documentation is excellent

---

## Additional Practice Ideas

1. Convert existing imperative code to functional style
2. Implement design patterns using lambdas
3. Create utility libraries with streams
4. Build reactive services with CompletableFuture
5. Optimize database queries with parallel processing

---

**Good luck! Master these exercises and you'll be a Java 8 expert! 🚀**

Solutions provided in [RevisionSolutions.java](RevisionSolutions.java)
