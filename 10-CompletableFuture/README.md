# CompletableFuture - Comprehensive Guide

## 📚 Table of Contents
- [Introduction](#introduction)
- [Creating CompletableFuture](#creating-completablefuture)
- [Transformation Methods](#transformation-methods)
- [Combining Futures](#combining-futures)
- [Error Handling](#error-handling)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 Introduction

**CompletableFuture** is a powerful tool for **asynchronous programming**. It represents a future result of an asynchronous computation.

### Future vs CompletableFuture

```
┌────────────────────────────────────────────────┐
│     FUTURE (Java 5)                            │
├────────────────────────────────────────────────┤
│                                                │
│  ✗ Blocking get()                              │
│  ✗ No chaining                                 │
│  ✗ No combining                                │
│  ✗ No exception handling                       │
│                                                │
└────────────────────────────────────────────────┘

┌────────────────────────────────────────────────┐
│     COMPLETABLEFUTURE (Java 8)                 │
├────────────────────────────────────────────────┤
│                                                │
│  ✓ Non-blocking callbacks                      │
│  ✓ Chaining (thenApply, thenCompose)           │
│  ✓ Combining (thenCombine, allOf)              │
│  ✓ Exception handling (exceptionally)          │
│  ✓ Manual completion                           │
│                                                │
└────────────────────────────────────────────────┘
```

---

## 🏗️ Creating CompletableFuture

```java
// Completed future
CompletableFuture<String> future = CompletableFuture.completedFuture("Hello");

// Run async (no return value)
CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
    System.out.println("Running async task");
});

// Supply async (with return value)
CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
    return "Result";
});

// With custom executor
ExecutorService executor = Executors.newFixedThreadPool(10);
CompletableFuture<String> cf = CompletableFuture.supplyAsync(
    () -> "Result",
    executor
);
```

---

## 🔄 Transformation Methods

### thenApply() - Transform Result

```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenApply(String::toUpperCase);

System.out.println(future.get());  // "HELLO WORLD"
```

### thenAccept() - Consume Result

```java
CompletableFuture.supplyAsync(() -> "Hello")
    .thenAccept(System.out::println);  // Prints: Hello
```

### thenRun() - Run After Completion

```java
CompletableFuture.supplyAsync(() -> "Task")
    .thenRun(() -> System.out.println("Done!"));
```

---

## 🔗 Combining Futures

### thenCompose() - Chain Dependent Futures

```java
CompletableFuture<User> userFuture = getUserById(123);

CompletableFuture<Order> orderFuture = userFuture
    .thenCompose(user -> getOrderByUser(user));
```

### thenCombine() - Combine Independent Futures

```java
CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

CompletableFuture<Integer> combined = future1.thenCombine(
    future2,
    (a, b) -> a + b
);

System.out.println(combined.get());  // 30
```

### allOf() - Wait for All

```java
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "A");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "B");
CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "C");

CompletableFuture<Void> allFutures = CompletableFuture.allOf(f1, f2, f3);

allFutures.join();  // Wait for all to complete
```

### anyOf() - Wait for First

```java
CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(f1, f2, f3);

System.out.println(firstCompleted.get());  // First one to finish
```

---

## 🛡️ Error Handling

### exceptionally() - Handle Exceptions

```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> {
        if (true) throw new RuntimeException("Error!");
        return "Success";
    })
    .exceptionally(ex -> {
        System.out.println("Exception: " + ex.getMessage());
        return "Default Value";
    });
```

### handle() - Handle Both Success and Failure

```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Result")
    .handle((result, ex) -> {
        if (ex != null) {
            return "Error occurred";
        }
        return result;
    });
```

---

## 🎯 Quick Reference Card

```java
// Creation
CompletableFuture.completedFuture(value)
CompletableFuture.runAsync(runnable)
CompletableFuture.supplyAsync(supplier)

// Transformation
.thenApply(function)      // Transform
.thenAccept(consumer)     // Consume
.thenRun(runnable)        // Run action

// Chaining
.thenCompose(function)    // Flatten nested futures
.thenCombine(other, fn)   // Combine two futures

// Waiting
.allOf(futures...)        // Wait for all
.anyOf(futures...)        // Wait for first

// Error handling
.exceptionally(function)  // Handle error
.handle(biFunction)       // Handle success and error

// Completion
.get()                    // Blocking
.join()                   // Blocking (unchecked)
.getNow(defaultValue)     // Non-blocking
```

---

**Previous Module**: [← Parallel Streams](../09-ParallelStreams/)  
**Next Module**: [Functional Interfaces Deep Dive →](../11-FunctionalInterfacesDeepDive/)
