# Functional Interfaces Deep Dive - Comprehensive Guide

## 📚 Table of Contents
- [All 43 Built-in Functional Interfaces](#all-43-built-in-functional-interfaces)
- [Primitive Specializations](#primitive-specializations)
- [Bi-Variants](#bi-variants)
- [Operators](#operators)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 All 43 Built-in Functional Interfaces

Java 8 provides **43 functional interfaces** in `java.util.function` package.

### Category Overview

```
┌────────────────────────────────────────────────┐
│     FUNCTIONAL INTERFACE CATEGORIES            │
├────────────────────────────────────────────────┤
│                                                │
│  Core (4):                                     │
│    Predicate, Function, Consumer, Supplier     │
│                                                │
│  Bi-Variants (4):                              │
│    BiPredicate, BiFunction, BiConsumer,        │
│    BinaryOperator                              │
│                                                │
│  Operators (2):                                │
│    UnaryOperator, BinaryOperator               │
│                                                │
│  Primitive Specializations (33):               │
│    IntPredicate, IntFunction, IntConsumer...   │
│    LongPredicate, LongFunction...              │
│    DoublePredicate, DoubleFunction...          │
│                                                │
└────────────────────────────────────────────────┘
```

---

## 🔢 Primitive Specializations

### Why Primitive Specializations?

```
┌────────────────────────────────────────────────┐
│     AVOID BOXING OVERHEAD                      │
├────────────────────────────────────────────────┤
│                                                │
│  ❌ Generic version:                           │
│     Function<Integer, Integer>                 │
│     - Autoboxing: int → Integer                │
│     - Unboxing: Integer → int                  │
│     - Performance penalty                      │
│                                                │
│  ✅ Primitive version:                         │
│     IntUnaryOperator                           │
│     - Direct int → int                         │
│     - No boxing/unboxing                       │
│     - Better performance                       │
│                                                │
└────────────────────────────────────────────────┘
```

### Int Variants

```java
// IntPredicate: int → boolean
IntPredicate isEven = n -> n % 2 == 0;

// IntFunction<R>: int → R
IntFunction<String> toString = i -> String.valueOf(i);

// IntConsumer: int → void
IntConsumer printer = System.out::println;

// IntSupplier: () → int
IntSupplier random = () -> (int)(Math.random() * 100);

// IntUnaryOperator: int → int
IntUnaryOperator square = n -> n * n;

// IntBinaryOperator: (int, int) → int
IntBinaryOperator sum = (a, b) -> a + b;

// ToIntFunction<T>: T → int
ToIntFunction<String> length = String::length;

// ToIntBiFunction<T,U>: (T, U) → int
ToIntBiFunction<String, String> compare = String::compareTo;
```

### Long and Double Variants

```java
// Long variants
LongPredicate, LongFunction<R>, LongConsumer, LongSupplier
LongUnaryOperator, LongBinaryOperator
ToLongFunction<T>, ToLongBiFunction<T,U>

// Double variants
DoublePredicate, DoubleFunction<R>, DoubleConsumer, DoubleSupplier
DoubleUnaryOperator, DoubleBinaryOperator
ToDoubleFunction<T>, ToDoubleBiFunction<T,U>
```

---

## 👥 Bi-Variants

### BiPredicate<T, U>

```java
BiPredicate<String, Integer> lengthGreaterThan = 
    (str, len) -> str.length() > len;

boolean result = lengthGreaterThan.test("Hello", 3);  // true
```

### BiFunction<T, U, R>

```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

int sum = add.apply(5, 3);  // 8

// Map merge example
Map<String, Integer> map = new HashMap<>();
map.merge("key", 1, (oldVal, newVal) -> oldVal + newVal);
```

### BiConsumer<T, U>

```java
BiConsumer<String, Integer> printer = 
    (name, age) -> System.out.println(name + " is " + age);

printer.accept("Alice", 30);  // Alice is 30

// Map forEach
Map<String, Integer> map = new HashMap<>();
map.forEach((key, value) -> 
    System.out.println(key + ": " + value)
);
```

---

## ⚙️ Operators

### UnaryOperator<T> extends Function<T, T>

```java
// T → T (same type input and output)
UnaryOperator<String> toUpper = String::toUpperCase;
UnaryOperator<Integer> square = n -> n * n;

String result = toUpper.apply("hello");  // "HELLO"
```

### BinaryOperator<T> extends BiFunction<T, T, T>

```java
// (T, T) → T (same type for both inputs and output)
BinaryOperator<Integer> max = Math::max;
BinaryOperator<String> concat = (a, b) -> a + b;

int maximum = max.apply(10, 20);  // 20

// In reduce
Optional<Integer> sum = numbers.stream()
    .reduce((a, b) -> a + b);

// Static methods
BinaryOperator<Integer> maxOp = BinaryOperator.maxBy(Integer::compareTo);
BinaryOperator<Integer> minOp = BinaryOperator.minBy(Integer::compareTo);
```

---

## 🎯 Quick Reference Card

### Complete List of 43 Interfaces

```java
// CORE (4)
Predicate<T>, Function<T,R>, Consumer<T>, Supplier<T>

// BI-VARIANTS (4)
BiPredicate<T,U>, BiFunction<T,U,R>, BiConsumer<T,U>, BinaryOperator<T>

// OPERATORS (2)
UnaryOperator<T>, BinaryOperator<T>

// INT VARIANTS (9)
IntPredicate, IntFunction<R>, IntConsumer, IntSupplier
IntUnaryOperator, IntBinaryOperator
ToIntFunction<T>, ToIntBiFunction<T,U>, ObjIntConsumer<T>

// LONG VARIANTS (9)
LongPredicate, LongFunction<R>, LongConsumer, LongSupplier
LongUnaryOperator, LongBinaryOperator
ToLongFunction<T>, ToLongBiFunction<T,U>, ObjLongConsumer<T>

// DOUBLE VARIANTS (9)
DoublePredicate, DoubleFunction<R>, DoubleConsumer, DoubleSupplier
DoubleUnaryOperator, DoubleBinaryOperator
ToDoubleFunction<T>, ToDoubleBiFunction<T,U>, ObjDoubleConsumer<T>

// CONVERSION (6)
IntToLongFunction, IntToDoubleFunction
LongToIntFunction, LongToDoubleFunction
DoubleToIntFunction, DoubleToLongFunction
```

### Usage Decision Tree

```
Need to test? → Predicate
Need to transform? → Function
Need to consume? → Consumer
Need to supply? → Supplier

Two parameters? → Add "Bi" prefix
Same input/output type? → Use Operator
Primitive types? → Use primitive variant
```

---

**Previous Module**: [← CompletableFuture](../10-CompletableFuture/)  
**Next Module**: [forEach and Iteration →](../12-ForEachIteration/)
