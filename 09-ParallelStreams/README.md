# Parallel Streams - Comprehensive Guide

## 📚 Table of Contents
- [Introduction](#introduction)
- [Creating Parallel Streams](#creating-parallel-streams)
- [How Parallel Streams Work](#how-parallel-streams-work)
- [When to Use Parallel Streams](#when-to-use-parallel-streams)
- [Common Pitfalls](#common-pitfalls)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 Introduction

Parallel streams allow you to **process data in parallel** using multiple threads, potentially improving performance for large datasets.

### Sequential vs Parallel

```
┌────────────────────────────────────────────────┐
│     SEQUENTIAL STREAM                          │
├────────────────────────────────────────────────┤
│                                                │
│  [1] → [2] → [3] → [4] → [5]                   │
│   ↓     ↓     ↓     ↓     ↓                    │
│  process in order, one at a time               │
│                                                │
└────────────────────────────────────────────────┘

┌────────────────────────────────────────────────┐
│     PARALLEL STREAM                            │
├────────────────────────────────────────────────┤
│                                                │
│      Thread 1    Thread 2    Thread 3          │
│         ↓           ↓           ↓              │
│        [1,2]      [3,4]        [5]             │
│         ↓           ↓           ↓              │
│      process simultaneously                    │
│                                                │
└────────────────────────────────────────────────┘
```

---

## 🚀 Creating Parallel Streams

```java
// From collection
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> parallel = numbers.parallelStream();

// Convert sequential to parallel
Stream<Integer> sequential = numbers.stream();
Stream<Integer> parallel2 = sequential.parallel();

// Check if parallel
boolean isParallel = stream.isParallel();
```

---

## ⚙️ How Parallel Streams Work

```
┌────────────────────────────────────────────────┐
│      FORK-JOIN FRAMEWORK                       │
├────────────────────────────────────────────────┤
│                                                │
│  1. Split data into chunks                     │
│  2. Process chunks in parallel (fork)          │
│  3. Combine results (join)                     │
│                                                │
│  Uses ForkJoinPool.commonPool()                │
│  Default threads: # of CPU cores               │
│                                                │
└────────────────────────────────────────────────┘
```

### Example

```java
// Sequential
long sum1 = numbers.stream()
    .mapToInt(Integer::intValue)
    .sum();

// Parallel - potentially faster for large datasets
long sum2 = numbers.parallelStream()
    .mapToInt(Integer::intValue)
    .sum();
```

---

## ✅ When to Use Parallel Streams

```
┌────────────────────────────────────────────────┐
│      USE PARALLEL WHEN:                        │
├────────────────────────────────────────────────┤
│                                                │
│  ✓ Large dataset (thousands+ elements)         │
│  ✓ CPU-intensive operations                    │
│  ✓ Independent operations (no shared state)    │
│  ✓ Stateless operations                        │
│  ✓ Measured performance benefit                │
│                                                │
└────────────────────────────────────────────────┘

┌────────────────────────────────────────────────┐
│      AVOID PARALLEL WHEN:                      │
├────────────────────────────────────────────────┤
│                                                │
│  ✗ Small dataset (overhead > benefit)          │
│  ✗ I/O operations (blocked threads)            │
│  ✗ Shared mutable state                        │
│  ✗ Order matters (use forEachOrdered)          │
│  ✗ Boxing/unboxing overhead                    │
│                                                │
└────────────────────────────────────────────────┘
```

---

## ⚠️ Common Pitfalls

### 1. Thread Safety Issues

```java
// ❌ BAD - Not thread-safe
List<Integer> list = new ArrayList<>();
IntStream.range(0, 1000).parallel()
    .forEach(list::add);  // ConcurrentModificationException!

// ✅ GOOD - Thread-safe
List<Integer> list = IntStream.range(0, 1000).parallel()
    .boxed()
    .collect(Collectors.toList());
```

### 2. Stateful Operations

```java
// ❌ BAD - Stateful lambda
int[] sum = {0};
numbers.parallelStream()
    .forEach(n -> sum[0] += n);  // Race condition!

// ✅ GOOD - Use reduce
int total = numbers.parallelStream()
    .reduce(0, Integer::sum);
```

---

## 🎯 Quick Reference Card

```java
// Create parallel stream
list.parallelStream()
stream.parallel()

// Check if parallel
stream.isParallel()

// Convert back to sequential
stream.sequential()

// Ordered operations
stream.parallel().forEachOrdered(System.out::println)
```

---

**Previous Module**: [← Collectors](../08-Collectors/)  
**Next Module**: [CompletableFuture →](../10-CompletableFuture/)
