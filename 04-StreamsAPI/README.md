# Streams API - Comprehensive Guide

## 📚 Table of Contents
- [Introduction](#introduction)
- [What is a Stream?](#what-is-a-stream)
- [Stream Pipeline Architecture](#stream-pipeline-architecture)
- [Creating Streams](#creating-streams)
- [Intermediate Operations](#intermediate-operations)
- [Terminal Operations](#terminal-operations)
- [Stream Operation Categories](#stream-operation-categories)
- [Advanced Patterns](#advanced-patterns)
- [Performance Considerations](#performance-considerations)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 Introduction

The **Streams API** is one of the most powerful features introduced in Java 8. It provides a functional approach to processing collections of data with operations like filtering, mapping, and reducing.

### Before and After Streams

```java
// ❌ BEFORE (Java 7) - Imperative approach
List<String> names = new ArrayList<>();
for (Person person : people) {
    if (person.getAge() > 18) {
        names.add(person.getName().toUpperCase());
    }
}
Collections.sort(names);
for (String name : names) {
    System.out.println(name);
}

// ✅ AFTER (Java 8) - Declarative approach with Streams
people.stream()
    .filter(p -> p.getAge() > 18)
    .map(Person::getName)
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
```

### Why Streams?

```
┌──────────────────────────────────────────────────┐
│           STREAMS API BENEFITS                   │
├──────────────────────────────────────────────────┤
│                                                  │
│  ✓ DECLARATIVE                                   │
│    Focus on WHAT, not HOW                        │
│                                                  │
│  ✓ COMPOSABLE                                    │
│    Chain operations easily                       │
│                                                  │
│  ✓ LAZY EVALUATION                               │
│    Operations only execute when needed           │
│                                                  │
│  ✓ PARALLEL PROCESSING                           │
│    Easy parallelization with .parallelStream()   │
│                                                  │
│  ✓ READABLE                                      │
│    Code reads like English                       │
│                                                  │
└──────────────────────────────────────────────────┘
```

---

## 🔍 What is a Stream?

A **Stream** is a sequence of elements supporting sequential and parallel aggregate operations.

### Key Characteristics

```
┌────────────────────────────────────────────────────┐
│         STREAM CHARACTERISTICS                     │
├────────────────────────────────────────────────────┤
│                                                    │
│  1. NOT A DATA STRUCTURE                           │
│     Streams don't store data                       │
│     They operate on data from a source             │
│                                                    │
│  2. FUNCTIONAL IN NATURE                           │
│     Operations produce a result                    │
│     Don't modify the source                        │
│                                                    │
│  3. LAZY                                           │
│     Intermediate operations are lazy               │
│     Executed only when terminal operation called   │
│                                                    │
│  4. CONSUMABLE                                     │
│     Can only be traversed once                     │
│     Like an iterator                               │
│                                                    │
│  5. UNBOUNDED                                      │
│     Can be infinite (use with caution!)            │
│                                                    │
└────────────────────────────────────────────────────┘
```

### Stream vs Collection

```
┌──────────────────────────────────────────────────────┐
│        COLLECTION vs STREAM                          │
├──────────────────┬───────────────────────────────────┤
│  Collection      │  Stream                           │
├──────────────────┼───────────────────────────────────┤
│  Data structure  │  Abstraction for processing       │
│  Stores data     │  Computes on demand               │
│  Eager           │  Lazy                             │
│  External loop   │  Internal iteration               │
│  Reusable        │  One-time use                     │
│  Finite          │  Can be infinite                  │
└──────────────────┴───────────────────────────────────┘
```

---

## 🏗️ Stream Pipeline Architecture

A stream pipeline consists of three parts:

```
┌──────────────────────────────────────────────────────────┐
│              STREAM PIPELINE                             │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  ┌──────────┐    ┌─────────────┐    ┌──────────────┐   │
│  │          │    │             │    │              │   │
│  │  SOURCE  │───▶│INTERMEDIATE │───▶│  TERMINAL    │   │
│  │          │    │ OPERATIONS  │    │  OPERATION   │   │
│  │          │    │  (0 or more)│    │   (exactly 1)│   │
│  └──────────┘    └─────────────┘    └──────────────┘   │
│                                                          │
│  Examples:       Examples:          Examples:           │
│  • Collection    • filter()         • collect()         │
│  • Array         • map()            • forEach()         │
│  • Generator     • sorted()         • reduce()          │
│  • I/O channel   • distinct()       • count()           │
│                  • limit()          • findFirst()       │
│                  • skip()           • anyMatch()        │
│                                                          │
└──────────────────────────────────────────────────────────┘
```

### Execution Flow

```
┌─────────────────────────────────────────────────────────┐
│           STREAM EXECUTION FLOW                         │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  List<String> result = people.stream()                  │
│      .filter(p -> p.getAge() > 18)  ← Intermediate      │
│      .map(Person::getName)          ← Intermediate      │
│      .sorted()                      ← Intermediate      │
│      .collect(Collectors.toList()); ← Terminal          │
│                                                         │
│  Execution Steps:                                       │
│  ─────────────────                                      │
│                                                         │
│  1. Create stream from people                           │
│                                                         │
│  2. Define pipeline (intermediate operations)           │
│     - filter, map, sorted are LAZY                      │
│     - Nothing happens yet!                              │
│                                                         │
│  3. Execute terminal operation (collect)                │
│     - NOW the pipeline executes                         │
│     - Data flows through all operations                 │
│     - Result is produced                                │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 🌊 Creating Streams

### From Collections

```java
// List
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> streamFromList = list.stream();

// Set
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
Stream<Integer> streamFromSet = set.stream();

// Map (entries, keys, values)
Map<String, Integer> map = new HashMap<>();
Stream<Map.Entry<String, Integer>> entries = map.entrySet().stream();
Stream<String> keys = map.keySet().stream();
Stream<Integer> values = map.values().stream();
```

### From Arrays

```java
// Using Arrays.stream()
String[] array = {"a", "b", "c"};
Stream<String> streamFromArray = Arrays.stream(array);

// Primitive arrays
int[] intArray = {1, 2, 3, 4, 5};
IntStream intStream = Arrays.stream(intArray);

// Using Stream.of()
Stream<String> stream = Stream.of("a", "b", "c");
Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
```

### From Values

```java
// Stream.of() with varargs
Stream<String> stream = Stream.of("a", "b", "c");

// Empty stream
Stream<String> empty = Stream.empty();

// Single element
Stream<String> single = Stream.of("single");
```

### Infinite Streams

```java
// Stream.iterate() - infinite stream with seed and function
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
// 0, 2, 4, 6, 8, 10, ...

// With limit
Stream<Integer> first10Even = Stream.iterate(0, n -> n + 2)
                                    .limit(10);

// Stream.generate() - infinite stream with Supplier
Stream<Double> randomStream = Stream.generate(Math::random);
Stream<String> helloStream = Stream.generate(() -> "Hello");

// With limit
Stream<Double> first5Random = Stream.generate(Math::random)
                                    .limit(5);
```

### From Ranges (Primitive Streams)

```java
// IntStream
IntStream range = IntStream.range(1, 5);        // 1, 2, 3, 4 (exclusive)
IntStream rangeClosed = IntStream.rangeClosed(1, 5);  // 1, 2, 3, 4, 5 (inclusive)

// LongStream
LongStream longRange = LongStream.range(1L, 1000000L);

// Convert to object stream
Stream<Integer> integerStream = IntStream.range(1, 10).boxed();
```

### From Files

```java
// Read lines from file
try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
    lines.forEach(System.out::println);
}

// List files in directory
try (Stream<Path> paths = Files.list(Paths.get("."))) {
    paths.filter(Files::isRegularFile)
         .forEach(System.out::println);
}

// Walk directory tree
try (Stream<Path> paths = Files.walk(Paths.get("."))) {
    paths.filter(Files::isRegularFile)
         .filter(p -> p.toString().endsWith(".java"))
         .forEach(System.out::println);
}
```

### Stream Builder

```java
Stream.Builder<String> builder = Stream.builder();
builder.add("a");
builder.add("b");
builder.add("c");
Stream<String> stream = builder.build();
```

---

## ⚙️ Intermediate Operations

Intermediate operations return a new stream and are **lazy** (executed only when terminal operation is called).

### Visual Representation

```
┌─────────────────────────────────────────────────────┐
│        INTERMEDIATE OPERATIONS                      │
├─────────────────────────────────────────────────────┤
│                                                     │
│  Stream → [Intermediate Op] → Stream                │
│                                                     │
│  Characteristics:                                   │
│  • Return Stream<T>                                 │
│  • Lazy (not executed immediately)                  │
│  • Can be chained                                   │
│  • Stateless or stateful                            │
│                                                     │
└─────────────────────────────────────────────────────┘
```

### 1. filter() - Select Elements

**Purpose:** Keep elements that match a predicate

```java
// Signature
Stream<T> filter(Predicate<? super T> predicate)

// Examples
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Filter even numbers
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// Result: [2, 4, 6, 8, 10]

// Filter by multiple conditions
List<Integer> result = numbers.stream()
    .filter(n -> n > 3)
    .filter(n -> n < 8)
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// Result: [4, 6]

// Filter objects
List<Person> adults = people.stream()
    .filter(p -> p.getAge() >= 18)
    .collect(Collectors.toList());

// Filter with method reference
List<String> nonEmpty = strings.stream()
    .filter(s -> !s.isEmpty())
    .collect(Collectors.toList());
```

### 2. map() - Transform Elements

**Purpose:** Transform each element to another value

```java
// Signature
<R> Stream<R> map(Function<? super T, ? extends R> mapper)

// Examples
List<String> words = Arrays.asList("hello", "world", "java");

// Transform to lengths
List<Integer> lengths = words.stream()
    .map(String::length)
    .collect(Collectors.toList());
// Result: [5, 5, 4]

// Transform to uppercase
List<String> upper = words.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
// Result: ["HELLO", "WORLD", "JAVA"]

// Extract property
List<String> names = people.stream()
    .map(Person::getName)
    .collect(Collectors.toList());

// Chain transformations
List<Integer> result = words.stream()
    .map(String::trim)
    .map(String::toUpperCase)
    .map(String::length)
    .collect(Collectors.toList());

// Type conversion
List<String> numbers = Arrays.asList("1", "2", "3");
List<Integer> integers = numbers.stream()
    .map(Integer::parseInt)
    .collect(Collectors.toList());
```

### 3. flatMap() - Flatten Nested Structures

**Purpose:** Transform each element to a stream and flatten all streams into one

```java
// Signature
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)

// Visual representation
/*
  map:     [[a,b], [c,d]] → Stream<List<String>>
  flatMap: [[a,b], [c,d]] → [a, b, c, d]
*/

// Examples
List<List<Integer>> nestedList = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4),
    Arrays.asList(5, 6)
);

List<Integer> flat = nestedList.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4, 5, 6]

// Split strings into words
List<String> sentences = Arrays.asList("Hello World", "Java Streams");
List<String> words = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .collect(Collectors.toList());
// Result: ["Hello", "World", "Java", "Streams"]

// Get all books from all authors
List<String> allBooks = authors.stream()
    .flatMap(author -> author.getBooks().stream())
    .map(Book::getTitle)
    .collect(Collectors.toList());

// Flatten Optional
List<Optional<String>> optionals = Arrays.asList(
    Optional.of("a"),
    Optional.empty(),
    Optional.of("b")
);
List<String> values = optionals.stream()
    .flatMap(Optional::stream)  // Java 9+
    .collect(Collectors.toList());
// Result: ["a", "b"]
```

### 4. distinct() - Remove Duplicates

```java
// Examples
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
List<Integer> unique = numbers.stream()
    .distinct()
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4, 5]

// Works with objects (uses equals())
List<Person> uniquePeople = people.stream()
    .distinct()  // Based on Person.equals()
    .collect(Collectors.toList());

// Get unique names
List<String> uniqueNames = people.stream()
    .map(Person::getName)
    .distinct()
    .collect(Collectors.toList());
```

### 5. sorted() - Sort Elements

```java
// Natural order
List<Integer> sorted = numbers.stream()
    .sorted()
    .collect(Collectors.toList());

// Custom comparator
List<String> sortedWords = words.stream()
    .sorted(Comparator.comparing(String::length))
    .collect(Collectors.toList());

// Reverse order
List<Integer> reversed = numbers.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());

// Multiple comparators
List<Person> sorted = people.stream()
    .sorted(Comparator.comparing(Person::getLastName)
                      .thenComparing(Person::getFirstName)
                      .thenComparing(Person::getAge))
    .collect(Collectors.toList());
```

### 6. limit() - Take First N Elements

```java
List<Integer> first5 = numbers.stream()
    .limit(5)
    .collect(Collectors.toList());

// With infinite stream
List<Integer> first10Even = Stream.iterate(0, n -> n + 2)
    .limit(10)
    .collect(Collectors.toList());
// Result: [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
```

### 7. skip() - Skip First N Elements

```java
List<Integer> skipFirst5 = numbers.stream()
    .skip(5)
    .collect(Collectors.toList());

// Pagination
List<Product> page2 = products.stream()
    .skip(20)   // Skip first page (20 items)
    .limit(20)  // Take next 20 items
    .collect(Collectors.toList());
```

### 8. peek() - Debug/Side Effects

**Purpose:** Perform an action on each element without changing it (useful for debugging)

```java
List<Integer> result = numbers.stream()
    .peek(n -> System.out.println("Original: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("After map: " + n))
    .filter(n -> n > 10)
    .peek(n -> System.out.println("After filter: " + n))
    .collect(Collectors.toList());

// Modify state (not recommended, but possible)
List<Person> people = getpeople();
people.stream()
    .peek(p -> p.setProcessed(true))  // Side effect
    .collect(Collectors.toList());
```

---

## 🎯 Terminal Operations

Terminal operations trigger stream execution and produce a result or side-effect. **Stream cannot be used after terminal operation.**

### Visual Representation

```
┌─────────────────────────────────────────────────────┐
│         TERMINAL OPERATIONS                         │
├─────────────────────────────────────────────────────┤
│                                                     │
│  Stream → [Terminal Op] → Result (not a Stream)     │
│                                                     │
│  Characteristics:                                   │
│  • Trigger pipeline execution                       │
│  • Return non-Stream result                         │
│  • Stream cannot be reused after                    │
│  • Short-circuit (some operations)                  │
│                                                     │
└─────────────────────────────────────────────────────┘
```

### 1. collect() - Collect to Collection

```java
// To List
List<String> list = stream.collect(Collectors.toList());

// To Set
Set<String> set = stream.collect(Collectors.toSet());

// To specific implementation
ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));

// To Map
Map<Integer, String> map = people.stream()
    .collect(Collectors.toMap(
        Person::getId,      // Key mapper
        Person::getName     // Value mapper
    ));

// Grouping
Map<Integer, List<Person>> byAge = people.stream()
    .collect(Collectors.groupingBy(Person::getAge));

// Partitioning (boolean key)
Map<Boolean, List<Person>> adults = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));

// Joining strings
String result = words.stream()
    .collect(Collectors.joining(", "));
// Result: "a, b, c"

String withPrefixSuffix = words.stream()
    .collect(Collectors.joining(", ", "[", "]"));
// Result: "[a, b, c]"
```

### 2. forEach() - Perform Action on Each Element

```java
stream.forEach(System.out::println);

people.forEach(person -> {
    System.out.println(person.getName());
    // Perform side effects
});

// forEachOrdered - maintains order (important for parallel streams)
stream.parallel()
      .forEachOrdered(System.out::println);
```

### 3. reduce() - Combine Elements

```java
// Signature
Optional<T> reduce(BinaryOperator<T> accumulator)
T reduce(T identity, BinaryOperator<T> accumulator)

// Sum
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);
// Or using Integer::sum
int sum = numbers.stream()
    .reduce(0, Integer::sum);

// Product
int product = numbers.stream()
    .reduce(1, (a, b) -> a * b);

// Max
Optional<Integer> max = numbers.stream()
    .reduce(Integer::max);

// Min
Optional<Integer> min = numbers.stream()
    .reduce(Integer::min);

// Concatenation
String concatenated = words.stream()
    .reduce("", (a, b) -> a + b);
// Or using String::concat
String result = words.stream()
    .reduce("", String::concat);
```

### 4. count() - Count Elements

```java
long count = stream.count();

long adultsCount = people.stream()
    .filter(p -> p.getAge() >= 18)
    .count();
```

### 5. anyMatch(), allMatch(), noneMatch() - Test Elements

```java
// anyMatch - at least one matches
boolean hasAdult = people.stream()
    .anyMatch(p -> p.getAge() >= 18);

// allMatch - all match
boolean allAdults = people.stream()
    .allMatch(p -> p.getAge() >= 18);

// noneMatch - none match
boolean noMinors = people.stream()
    .noneMatch(p -> p.getAge() < 18);
```

### 6. findFirst(), findAny() - Find Element

```java
// findFirst - first element (order matters)
Optional<String> first = words.stream()
    .findFirst();

Optional<Person> firstAdult = people.stream()
    .filter(p -> p.getAge() >= 18)
    .findFirst();

// findAny - any element (useful in parallel streams)
Optional<String> any = words.parallelStream()
    .findAny();
```

### 7. min(), max() - Find Min/Max

```java
Optional<Integer> min = numbers.stream()
    .min(Integer::compareTo);

Optional<Integer> max = numbers.stream()
    .max(Integer::compareTo);

Optional<Person> youngest = people.stream()
    .min(Comparator.comparing(Person::getAge));

Optional<Person> oldest = people.stream()
    .max(Comparator.comparing(Person::getAge));
```

### 8. toArray() - Convert to Array

```java
// Object array
Object[] array = stream.toArray();

// Typed array
String[] stringArray = stream.toArray(String[]::new);

Integer[] intArray = numbers.stream()
    .toArray(Integer[]::new);
```

---

## 📊 Stream Operation Categories

```
┌──────────────────────────────────────────────────────────┐
│        STREAM OPERATIONS CLASSIFICATION                  │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  INTERMEDIATE (return Stream)                            │
│  ──────────────────────────────                          │
│                                                          │
│  Stateless:                                              │
│    filter, map, flatMap, peek                            │
│    • Don't need to know about other elements             │
│    • Process each element independently                  │
│                                                          │
│  Stateful:                                               │
│    distinct, sorted, limit, skip                         │
│    • Need to know about other elements                   │
│    • May need to process entire stream                   │
│                                                          │
│  TERMINAL (return result)                                │
│  ────────────────────────                                │
│                                                          │
│  Short-circuiting:                                       │
│    anyMatch, allMatch, noneMatch, findFirst, findAny     │
│    • May not process entire stream                       │
│    • Return as soon as result is determined              │
│                                                          │
│  Non-short-circuiting:                                   │
│    forEach, collect, reduce, count, min, max             │
│    • Process entire stream                               │
│    • Return final result                                 │
│                                                          │
└──────────────────────────────────────────────────────────┘
```

---

## 🎯 Quick Reference Card

### Stream Pipeline Template

```java
source.stream()
    .filter(...)        // Keep matching elements
    .map(...)           // Transform elements
    .sorted(...)        // Sort elements
    .limit(...)         // Take first N
    .collect(...);      // Collect result
```

### Common Patterns

```java
// Filter and collect
List<T> filtered = list.stream()
    .filter(predicate)
    .collect(Collectors.toList());

// Transform and collect
List<R> transformed = list.stream()
    .map(function)
    .collect(Collectors.toList());

// Count matching
long count = list.stream()
    .filter(predicate)
    .count();

// Find first matching
Optional<T> first = list.stream()
    .filter(predicate)
    .findFirst();

// Check if any match
boolean exists = list.stream()
    .anyMatch(predicate);

// Sum numbers
int sum = numbers.stream()
    .reduce(0, Integer::sum);

// Group by property
Map<K, List<V>> grouped = list.stream()
    .collect(Collectors.groupingBy(classifier));
```

---

**Previous Module**: [← Method References](../03-MethodReferences/)  
**Next Module**: [Optional Class →](../05-OptionalClass/)

