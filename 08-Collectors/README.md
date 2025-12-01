# Collectors - Comprehensive Guide

## 📚 Table of Contents
- [Introduction](#introduction)
- [Basic Collectors](#basic-collectors)
- [Grouping and Partitioning](#grouping-and-partitioning)
- [Advanced Collectors](#advanced-collectors)
- [Custom Collectors](#custom-collectors)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 Introduction

**Collectors** are powerful tools for accumulating stream elements into collections, aggregating values, and transforming data. They are terminal operations used with the `collect()` method.

### Basic Concept

```
┌────────────────────────────────────────────────┐
│         COLLECTOR FLOW                         │
├────────────────────────────────────────────────┤
│                                                │
│  Stream Elements → Collector → Result          │
│                                                │
│  Example:                                      │
│  [1,2,3,4,5] → toList() → List[1,2,3,4,5]     │
│                                                │
└────────────────────────────────────────────────┘
```

---

## 📦 Basic Collectors

### toList() - Collect to List

```java
List<String> list = stream.collect(Collectors.toList());

// Example
List<String> names = people.stream()
    .map(Person::getName)
    .collect(Collectors.toList());
```

### toSet() - Collect to Set

```java
Set<String> set = stream.collect(Collectors.toSet());

// Remove duplicates
Set<Integer> uniqueAges = people.stream()
    .map(Person::getAge)
    .collect(Collectors.toSet());
```

### toCollection() - Specific Collection

```java
// ArrayList
ArrayList<String> arrayList = stream
    .collect(Collectors.toCollection(ArrayList::new));

// TreeSet (sorted)
TreeSet<String> treeSet = stream
    .collect(Collectors.toCollection(TreeSet::new));

// LinkedList
LinkedList<String> linkedList = stream
    .collect(Collectors.toCollection(LinkedList::new));
```

### toMap() - Collect to Map

```java
// Key and value extractors
Map<Integer, String> map = people.stream()
    .collect(Collectors.toMap(
        Person::getId,      // Key
        Person::getName     // Value
    ));

// With merge function (handle duplicates)
Map<String, Person> byName = people.stream()
    .collect(Collectors.toMap(
        Person::getName,
        person -> person,
        (existing, replacement) -> existing  // Keep first
    ));

// With specific Map implementation
TreeMap<Integer, String> treeMap = people.stream()
    .collect(Collectors.toMap(
        Person::getId,
        Person::getName,
        (a, b) -> a,
        TreeMap::new
    ));
```

### joining() - String Concatenation

```java
// Simple join
String joined = strings.stream()
    .collect(Collectors.joining());
// "abcdef"

// With delimiter
String csv = strings.stream()
    .collect(Collectors.joining(", "));
// "a, b, c, d, e, f"

// With prefix and suffix
String bracketed = strings.stream()
    .collect(Collectors.joining(", ", "[", "]"));
// "[a, b, c, d, e, f]"

// Practical example
String names = people.stream()
    .map(Person::getName)
    .collect(Collectors.joining(", ", "Names: ", "."));
// "Names: Alice, Bob, Charlie."
```

---

## 📊 Grouping and Partitioning

### groupingBy() - Group by Classifier

```java
// Simple grouping
Map<Integer, List<Person>> byAge = people.stream()
    .collect(Collectors.groupingBy(Person::getAge));
// {25=[Person(Alice,25)], 30=[Person(Bob,30), Person(Charlie,30)]}

// Group by condition
Map<String, List<Person>> byAgeGroup = people.stream()
    .collect(Collectors.groupingBy(p -> 
        p.getAge() < 18 ? "Minor" : "Adult"
    ));

// Nested grouping
Map<String, Map<Integer, List<Person>>> byCountryThenAge = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCountry,
        Collectors.groupingBy(Person::getAge)
    ));

// Group and count
Map<Integer, Long> ageCount = people.stream()
    .collect(Collectors.groupingBy(
        Person::getAge,
        Collectors.counting()
    ));

// Group and sum
Map<String, Integer> totalAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.summingInt(Person::getAge)
    ));

// Group and collect names
Map<Integer, List<String>> namesByAge = people.stream()
    .collect(Collectors.groupingBy(
        Person::getAge,
        Collectors.mapping(
            Person::getName,
            Collectors.toList()
        )
    ));
```

### partitioningBy() - Binary Classification

```java
// Split into two groups
Map<Boolean, List<Person>> partitioned = people.stream()
    .collect(Collectors.partitioningBy(
        p -> p.getAge() >= 18
    ));
// {true=[adults...], false=[minors...]}

List<Person> adults = partitioned.get(true);
List<Person> minors = partitioned.get(false);

// Partition and count
Map<Boolean, Long> count = people.stream()
    .collect(Collectors.partitioningBy(
        p -> p.getAge() >= 18,
        Collectors.counting()
    ));
// {true=150, false=50}
```

---

## 🔥 Advanced Collectors

### counting()

```java
long count = stream.collect(Collectors.counting());
// Same as: stream.count()

// With grouping
Map<String, Long> countByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.counting()
    ));
```

### summingInt/Long/Double()

```java
int totalAge = people.stream()
    .collect(Collectors.summingInt(Person::getAge));

double totalSalary = employees.stream()
    .collect(Collectors.summingDouble(Employee::getSalary));
```

### averagingInt/Long/Double()

```java
double avgAge = people.stream()
    .collect(Collectors.averagingInt(Person::getAge));

double avgSalary = employees.stream()
    .collect(Collectors.averagingDouble(Employee::getSalary));
```

### summarizingInt/Long/Double()

```java
IntSummaryStatistics stats = people.stream()
    .collect(Collectors.summarizingInt(Person::getAge));

long count = stats.getCount();
int sum = stats.getSum();
double avg = stats.getAverage();
int min = stats.getMin();
int max = stats.getMax();

System.out.println(stats);
// IntSummaryStatistics{count=5, sum=125, min=20, average=25.0, max=35}
```

### maxBy() and minBy()

```java
Optional<Person> oldest = people.stream()
    .collect(Collectors.maxBy(
        Comparator.comparing(Person::getAge)
    ));

Optional<Person> youngest = people.stream()
    .collect(Collectors.minBy(
        Comparator.comparing(Person::getAge)
    ));
```

### mapping()

```java
// Extract and collect
List<String> names = people.stream()
    .collect(Collectors.mapping(
        Person::getName,
        Collectors.toList()
    ));

// With grouping
Map<Integer, Set<String>> namesByAge = people.stream()
    .collect(Collectors.groupingBy(
        Person::getAge,
        Collectors.mapping(
            Person::getName,
            Collectors.toSet()
        )
    ));
```

### reducing()

```java
// Sum with reduce
Optional<Integer> sum = numbers.stream()
    .collect(Collectors.reducing(Integer::sum));

// With identity
Integer sum2 = numbers.stream()
    .collect(Collectors.reducing(0, Integer::sum));

// With mapper and reducer
Integer totalAge = people.stream()
    .collect(Collectors.reducing(
        0,                    // Identity
        Person::getAge,       // Mapper
        Integer::sum          // Reducer
    ));
```

---

## 🎯 Quick Reference Card

```java
// To Collections
Collectors.toList()
Collectors.toSet()
Collectors.toCollection(TreeSet::new)
Collectors.toMap(keyMapper, valueMapper)

// Strings
Collectors.joining()
Collectors.joining(", ")
Collectors.joining(", ", "[", "]")

// Grouping
Collectors.groupingBy(classifier)
Collectors.partitioningBy(predicate)

// Statistics
Collectors.counting()
Collectors.summingInt(mapper)
Collectors.averagingDouble(mapper)
Collectors.summarizingInt(mapper)

// Min/Max
Collectors.maxBy(comparator)
Collectors.minBy(comparator)

// Transformation
Collectors.mapping(mapper, downstream)
Collectors.reducing(identity, reducer)
```

---

**Previous Module**: [← Date Time API](../07-DateTimeAPI/)  
**Next Module**: [Parallel Streams →](../09-ParallelStreams/)
