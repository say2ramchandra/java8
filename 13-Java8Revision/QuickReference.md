# Java 8 Quick Reference Cheat Sheet

**One-page reference for all Java 8 features**

---

## 🎯 Lambda Expressions

```java
// Syntax variations
() -> expression                           // No params
x -> expression                            // One param
(x, y) -> expression                       // Multiple params
(x, y) -> { statements; return result; }   // Block body

// Examples
Runnable r = () -> System.out.println("Hi");
Function<String, Integer> f = s -> s.length();
BiFunction<Int, Int, Int> add = (a,b) -> a + b;
```

---

## 🎯 Core Functional Interfaces

| Interface | Method | Use Case | Example |
|-----------|--------|----------|---------|
| `Predicate<T>` | `boolean test(T)` | Test condition | `n -> n > 0` |
| `Function<T,R>` | `R apply(T)` | Transform T→R | `s -> s.length()` |
| `Consumer<T>` | `void accept(T)` | Consume input | `s -> print(s)` |
| `Supplier<T>` | `T get()` | Supply value | `() -> new Object()` |
| `BiPredicate<T,U>` | `boolean test(T,U)` | Test 2 inputs | `(s,n) -> s.length()>n` |
| `BiFunction<T,U,R>` | `R apply(T,U)` | Transform 2→1 | `(a,b) -> a+b` |
| `BiConsumer<T,U>` | `void accept(T,U)` | Consume 2 | `(k,v) -> map.put(k,v)` |
| `UnaryOperator<T>` | `T apply(T)` | T→T transform | `s -> s.toUpperCase()` |
| `BinaryOperator<T>` | `T apply(T,T)` | T,T→T | `(a,b) -> Math.max(a,b)` |

### Primitive Specializations (avoid boxing)
- `IntPredicate`, `LongPredicate`, `DoublePredicate`
- `IntFunction<R>`, `IntConsumer`, `IntSupplier`
- `IntUnaryOperator`, `IntBinaryOperator`
- `ToIntFunction<T>`, `ToIntBiFunction<T,U>`
- *(Same for Long and Double)*

---

## 🎯 Method References

```java
// Static method
Integer::parseInt           // x -> Integer.parseInt(x)

// Instance method on object
str::length                 // () -> str.length()

// Instance method on type
String::toUpperCase         // s -> s.toUpperCase()

// Constructor
ArrayList::new              // () -> new ArrayList()
```

---

## 🎯 Streams API

### Creating Streams
```java
collection.stream()
collection.parallelStream()
Stream.of(1, 2, 3)
IntStream.range(0, 10)
Stream.iterate(0, n -> n + 1)
Stream.generate(Math::random)
```

### Intermediate Operations (Lazy)
```java
filter(predicate)           // Filter elements
map(function)               // Transform 1:1
flatMap(function)           // Transform 1:many, flatten
distinct()                  // Remove duplicates
sorted()                    // Sort
sorted(comparator)          // Sort with comparator
limit(n)                    // First n elements
skip(n)                     // Skip n elements
peek(consumer)              // Debug/side effects
```

### Terminal Operations (Eager)
```java
forEach(consumer)           // Iterate
collect(collector)          // Collect to collection
reduce(identity, op)        // Reduce to single value
count()                     // Count elements
anyMatch(predicate)         // Check if any match
allMatch(predicate)         // Check if all match
noneMatch(predicate)        // Check if none match
findFirst()                 // Get first (Optional)
findAny()                   // Get any (Optional)
min(comparator)             // Get minimum
max(comparator)             // Get maximum
toArray()                   // Convert to array
```

### Common Patterns
```java
// Filter-Map-Collect
list.stream()
    .filter(x -> x > 0)
    .map(x -> x * 2)
    .collect(Collectors.toList());

// FlatMap
nestedList.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());

// Reduce
numbers.stream()
    .reduce(0, (a,b) -> a + b);
```

---

## 🎯 Optional

### Creating
```java
Optional.empty()
Optional.of(value)           // NPE if null
Optional.ofNullable(value)   // Safe
```

### Checking
```java
optional.isPresent()         // Has value?
optional.isEmpty()           // Empty? (Java 11+)
```

### Retrieving
```java
optional.get()               // Get value (unsafe!)
optional.orElse(default)     // Get or default
optional.orElseGet(supplier) // Get or compute
optional.orElseThrow()       // Get or throw
```

### Transforming
```java
optional.map(function)       // Transform if present
optional.flatMap(function)   // Transform to Optional
optional.filter(predicate)   // Filter if matches
```

### Best Pattern
```java
return Optional.ofNullable(value)
    .filter(predicate)
    .map(transformer)
    .orElse(defaultValue);
```

---

## 🎯 Collectors

```java
// To Collections
Collectors.toList()
Collectors.toSet()
Collectors.toCollection(TreeSet::new)

// To Map
Collectors.toMap(keyMapper, valueMapper)
Collectors.toMap(k -> k, v -> v, mergeFunction)

// Joining
Collectors.joining()
Collectors.joining(", ")
Collectors.joining(", ", "[", "]")

// Grouping
Collectors.groupingBy(classifier)
Collectors.groupingBy(classifier, downstream)

// Partitioning
Collectors.partitioningBy(predicate)

// Statistics
Collectors.counting()
Collectors.summingInt(mapper)
Collectors.averagingInt(mapper)
Collectors.summarizingInt(mapper)

// Other
Collectors.reducing(identity, op)
Collectors.collectingAndThen(collector, finisher)
Collectors.mapping(mapper, downstream)
```

---

## 🎯 Date Time API

### Core Classes
```java
LocalDate       // Date only
LocalTime       // Time only
LocalDateTime   // Date + Time
ZonedDateTime   // Date + Time + Zone
Instant         // Timestamp (epoch)
Duration        // Time-based amount
Period          // Date-based amount
```

### Creation
```java
LocalDate.now()
LocalDate.of(2024, 3, 30)
LocalDate.parse("2024-03-30")
```

### Manipulation (Immutable!)
```java
date.plusDays(1)
date.plusMonths(1)
date.minusWeeks(2)
date.withYear(2025)
```

### Comparison
```java
date.isBefore(other)
date.isAfter(other)
date.isEqual(other)
```

### Formatting
```java
DateTimeFormatter.ISO_DATE
DateTimeFormatter.ofPattern("dd/MM/yyyy")
date.format(formatter)
```

---

## 🎯 Default & Static Methods

```java
interface MyInterface {
    // Abstract
    void abstractMethod();
    
    // Default (implementation in interface)
    default void defaultMethod() {
        System.out.println("Default");
    }
    
    // Static
    static void staticMethod() {
        System.out.println("Static");
    }
}

// Usage
MyInterface.staticMethod();           // Call static
obj.defaultMethod();                  // Inherited default
InterfaceA.super.defaultMethod();     // Resolve diamond
```

---

## 🎯 Parallel Streams

```java
// Create
list.parallelStream()
stream.parallel()

// Check
stream.isParallel()

// Convert
stream.sequential()

// Ordered parallel
stream.parallelStream().forEachOrdered(...)
```

### When to Use?
✅ Large datasets (>10K elements)  
✅ CPU-intensive operations  
✅ Stateless operations  
✅ ArrayList, arrays (good splitting)  

❌ Small datasets  
❌ I/O operations  
❌ Stateful operations  
❌ LinkedList (poor splitting)  

---

## 🎯 CompletableFuture

### Creating
```java
CompletableFuture.completedFuture(value)
CompletableFuture.supplyAsync(() -> value)
CompletableFuture.runAsync(() -> action)
```

### Transforming
```java
.thenApply(function)         // Transform result
.thenAccept(consumer)        // Consume result
.thenRun(runnable)           // Run after
```

### Combining
```java
cf1.thenCombine(cf2, (a,b) -> ...)    // Combine 2
cf.thenCompose(result -> ...)          // Chain
CompletableFuture.allOf(cf1, cf2, ...) // Wait all
CompletableFuture.anyOf(cf1, cf2, ...) // Wait any
```

### Error Handling
```java
.exceptionally(ex -> defaultValue)
.handle((result, ex) -> ...)
.whenComplete((result, ex) -> ...)
```

---

## 🎯 forEach & Iteration

```java
// Collection forEach
list.forEach(item -> ...)
map.forEach((k,v) -> ...)

// Stream forEach
stream.forEach(item -> ...)
stream.forEachOrdered(item -> ...)  // Ordered parallel

// Modification
list.replaceAll(function)           // Transform all
list.removeIf(predicate)            // Remove matching
```

---

## 🎯 Common Patterns

### Null Safety
```java
// Before Java 8
if (value != null) {
    return value.length();
}
return 0;

// Java 8
return Optional.ofNullable(value)
    .map(String::length)
    .orElse(0);
```

### Collection Processing
```java
// Before Java 8
List<String> result = new ArrayList<>();
for (String s : list) {
    if (s.length() > 3) {
        result.add(s.toUpperCase());
    }
}

// Java 8
List<String> result = list.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

### Grouping
```java
// Before Java 8
Map<String, List<Person>> map = new HashMap<>();
for (Person p : people) {
    map.computeIfAbsent(p.getDept(), k -> new ArrayList<>()).add(p);
}

// Java 8
Map<String, List<Person>> map = people.stream()
    .collect(Collectors.groupingBy(Person::getDept));
```

---

## 🎯 Quick Decision Guide

**Choose Lambda** when you need inline function implementation  
**Choose Method Reference** when lambda just calls one method  
**Choose Stream** for data transformation pipelines  
**Choose Optional** for methods that might not return value  
**Choose Parallel Stream** for large CPU-intensive operations  
**Choose CompletableFuture** for async operations  

---

## 🎯 Common Mistakes to Avoid

❌ `optional.get()` without checking  
❌ Modifying external state in lambdas  
❌ Using parallel streams for small datasets  
❌ Catching exceptions incorrectly in lambdas  
❌ Using `Optional` as field or parameter  
❌ Multiple terminal operations on same stream  
❌ Not understanding lazy evaluation  

---

## 🎯 Performance Tips

1. Use primitive streams (`IntStream`, `LongStream`)
2. Use method references (more optimizable)
3. Prefer `anyMatch`/`findFirst` (short-circuit)
4. Avoid unnecessary boxing/unboxing
5. Consider ArrayList over LinkedList for parallel
6. Measure before using parallel streams

---

**Print this sheet and keep it handy while coding!**

For complete examples, see [Java8Recap.java](Java8Recap.java)
