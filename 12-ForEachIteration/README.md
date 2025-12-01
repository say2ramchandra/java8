# forEach and Iteration Enhancements - Comprehensive Guide

## 📚 Table of Contents
- [Introduction](#introduction)
- [Iterable.forEach()](#iterableforeach)
- [Map.forEach()](#mapforeach)
- [Stream.forEach()](#streamforeach)
- [forEach vs for-loop](#foreach-vs-for-loop)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 Introduction

Java 8 added the **forEach()** method to `Iterable`, `Map`, and `Stream` interfaces, providing a functional way to iterate over collections.

### Evolution of Iteration

```java
// Traditional for-loop (Java 1.0)
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// Enhanced for-loop (Java 5)
for (String item : list) {
    System.out.println(item);
}

// forEach (Java 8)
list.forEach(System.out::println);

// Stream forEach (Java 8)
list.stream().forEach(System.out::println);
```

---

## 📋 Iterable.forEach()

All collections implement `Iterable`, so they all have `forEach()`.

```java
// List
List<String> list = Arrays.asList("A", "B", "C");
list.forEach(System.out::println);

// Set
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
set.forEach(n -> System.out.println(n * 2));

// With lambda
list.forEach(item -> {
    String upper = item.toUpperCase();
    System.out.println(upper);
});

// With method reference
list.forEach(System.out::println);

// Custom action
List<Person> people = getPeople();
people.forEach(person -> {
    person.setProcessed(true);
    save(person);
});
```

---

## 🗺️ Map.forEach()

Maps have special `forEach()` that accepts `BiConsumer<K, V>`.

```java
Map<String, Integer> map = new HashMap<>();
map.put("Alice", 30);
map.put("Bob", 25);
map.put("Charlie", 35);

// Iterate over entries
map.forEach((key, value) -> 
    System.out.println(key + " is " + value + " years old")
);

// Modify values
map.forEach((key, value) -> 
    map.put(key, value + 1)  // Increment all ages
);

// Complex operations
map.forEach((name, age) -> {
    if (age > 30) {
        System.out.println(name + " is senior");
    }
});
```

---

## 🌊 Stream.forEach()

```java
// Stream forEach
list.stream()
    .filter(s -> s.length() > 3)
    .forEach(System.out::println);

// forEachOrdered - maintains order in parallel streams
list.parallelStream()
    .filter(s -> s.length() > 3)
    .forEachOrdered(System.out::println);  // Ordered output

// Regular forEach in parallel stream - order not guaranteed
list.parallelStream()
    .forEach(System.out::println);  // May print in random order
```

---

## ⚖️ forEach vs for-loop

```
┌────────────────────────────────────────────────┐
│     forEach vs for-loop                        │
├────────────────────────────────────────────────┤
│                                                │
│  forEach:                                      │
│    ✓ More concise                              │
│    ✓ Functional style                          │
│    ✓ Cannot use break/continue                 │
│    ✓ Cannot modify iteration index             │
│    ✗ Harder to debug                           │
│                                                │
│  for-loop:                                     │
│    ✓ Can use break/continue                    │
│    ✓ Can access index                          │
│    ✓ Easier to debug                           │
│    ✗ More verbose                              │
│                                                │
└────────────────────────────────────────────────┘
```

### When to Use What

```java
// ✅ Use forEach for simple iteration
list.forEach(System.out::println);

// ✅ Use for-loop when you need to break early
for (String item : list) {
    if (item.equals("STOP")) break;
    System.out.println(item);
}

// ✅ Use for-loop when you need index
for (int i = 0; i < list.size(); i++) {
    System.out.println(i + ": " + list.get(i));
}

// ✅ Use forEach with streams for complex operations
list.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

---

## 🎯 Quick Reference Card

```java
// Iterable forEach
list.forEach(System.out::println)
list.forEach(item -> process(item))

// Map forEach
map.forEach((k, v) -> System.out.println(k + ": " + v))

// Stream forEach
stream.forEach(System.out::println)
parallelStream.forEachOrdered(System.out::println)

// Common patterns
list.forEach(item -> {
    // multiple statements
});

map.forEach((key, value) -> {
    // process key-value pair
});

list.stream()
    .filter(predicate)
    .map(function)
    .forEach(consumer);
```

---

**Previous Module**: [← Functional Interfaces Deep Dive](../11-FunctionalInterfacesDeepDive/)  
**Main Project**: [← Back to Main README](../README.md)
