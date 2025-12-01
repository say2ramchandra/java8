# Functional Interfaces - Comprehensive Guide

## 📚 Table of Contents
- [Introduction](#introduction)
- [What is a Functional Interface?](#what-is-a-functional-interface)
- [The @FunctionalInterface Annotation](#the-functionalinterface-annotation)
- [Built-in Functional Interfaces](#built-in-functional-interfaces)
- [Detailed Interface Breakdown](#detailed-interface-breakdown)
- [Method Chaining and Composition](#method-chaining-and-composition)
- [Custom Functional Interfaces](#custom-functional-interfaces)
- [Advanced Patterns](#advanced-patterns)
- [Quick Reference Card](#quick-reference-card)

---

## 🎯 Introduction

Functional interfaces are the **backbone of lambda expressions** in Java 8. They provide the contract that lambda expressions implement, enabling functional programming paradigms in Java.

### Why Functional Interfaces?

**The Problem:**
Lambda expressions need a type. How does Java know what type a lambda is?

**The Solution:**
Functional interfaces provide the type information. A lambda expression is an implementation of a functional interface.

```java
// The functional interface defines the contract
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Lambda provides the implementation
Calculator add = (a, b) -> a + b;
Calculator subtract = (a, b) -> a - b;
```

---

## 🔍 What is a Functional Interface?

A **functional interface** is an interface that contains:
- **Exactly ONE abstract method** (SAM - Single Abstract Method)
- Any number of default methods (optional)
- Any number of static methods (optional)
- Methods from Object class don't count

### Visual Definition

```
┌────────────────────────────────────────────────────────┐
│           FUNCTIONAL INTERFACE ANATOMY                 │
├────────────────────────────────────────────────────────┤
│                                                        │
│  @FunctionalInterface                                  │
│  interface MyFunction {                                │
│                                                        │
│      int apply(int x);  ←── ONE abstract method (SAM) │
│                                                        │
│      default int twice(int x) {  ←── default (OK)     │
│          return apply(x) * 2;                          │
│      }                                                 │
│                                                        │
│      static int zero() {  ←── static (OK)              │
│          return 0;                                     │
│      }                                                 │
│  }                                                     │
│                                                        │
└────────────────────────────────────────────────────────┘
```

### Examples

```java
// ✅ Valid Functional Interfaces
@FunctionalInterface
interface StringProcessor {
    String process(String input);  // Single abstract method
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);  // Single abstract method
    
    default int square(int x) {   // Default methods OK
        return calculate(x, x);
    }
    
    static int zero() {            // Static methods OK
        return 0;
    }
}

// ❌ NOT Functional Interfaces
interface TwoMethods {
    void method1();
    void method2();  // Two abstract methods - NOT functional
}

interface NoMethods {
    // No abstract methods - NOT functional
    default void doSomething() { }
}
```

---

## 📝 The @FunctionalInterface Annotation

### Purpose

The `@FunctionalInterface` annotation is **optional** but highly recommended.

```java
@FunctionalInterface  // Optional but recommended
interface Processor {
    void process(String input);
}
```

### Benefits

```
┌─────────────────────────────────────────────────────┐
│     @FunctionalInterface BENEFITS                   │
├─────────────────────────────────────────────────────┤
│                                                     │
│  1. COMPILE-TIME CHECK                              │
│     - Compiler ensures exactly one abstract method  │
│     - Prevents accidental addition of methods       │
│                                                     │
│  2. DOCUMENTATION                                   │
│     - Clearly indicates lambda compatibility        │
│     - Helps other developers understand intent      │
│                                                     │
│  3. TOOLING SUPPORT                                 │
│     - IDEs provide better autocomplete              │
│     - Better refactoring support                    │
│                                                     │
└─────────────────────────────────────────────────────┘
```

### Example

```java
@FunctionalInterface
interface DataValidator {
    boolean validate(String data);
    
    // ❌ This would cause compile error:
    // boolean anotherMethod(int x);
}

// ✅ Compiler ensures only one abstract method
DataValidator emailValidator = email -> email.contains("@");
```

---

## 🏗️ Built-in Functional Interfaces

Java 8 provides **43 functional interfaces** in the `java.util.function` package. Here are the most important ones:

### The Core Four

```
┌──────────────────────────────────────────────────────────────┐
│                 CORE FUNCTIONAL INTERFACES                   │
├──────────────┬──────────────┬──────────────┬────────────────┤
│              │              │              │                │
│  Predicate   │   Function   │   Consumer   │   Supplier     │
│              │              │              │                │
│  T → boolean │   T → R      │   T → void   │   () → T       │
│              │              │              │                │
│  Test a      │  Transform   │  Accept &    │  Supply a      │
│  condition   │  input to    │  perform     │  value         │
│              │  output      │  action      │                │
│              │              │              │                │
└──────────────┴──────────────┴──────────────┴────────────────┘
```

### Interface Hierarchy

```
Functional Interfaces
│
├── Single Parameter
│   ├── Predicate<T>          : T → boolean
│   ├── Function<T,R>         : T → R
│   ├── Consumer<T>           : T → void
│   ├── UnaryOperator<T>      : T → T (extends Function)
│   └── Supplier<T>           : () → T (no parameters)
│
├── Two Parameters
│   ├── BiPredicate<T,U>      : (T, U) → boolean
│   ├── BiFunction<T,U,R>     : (T, U) → R
│   ├── BiConsumer<T,U>       : (T, U) → void
│   └── BinaryOperator<T>     : (T, T) → T (extends BiFunction)
│
└── Primitive Specializations
    ├── IntPredicate          : int → boolean
    ├── IntFunction<R>        : int → R
    ├── IntConsumer           : int → void
    ├── IntSupplier           : () → int
    ├── IntUnaryOperator      : int → int
    ├── IntBinaryOperator     : (int, int) → int
    └── (Similar for Long, Double...)
```

---

## 📊 Detailed Interface Breakdown

### 1. Predicate\<T> - Testing Conditions

**Signature:** `T → boolean`

**Purpose:** Test if something is true or false

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
    
    // Default methods for composition
    default Predicate<T> and(Predicate<? super T> other) { ... }
    default Predicate<T> or(Predicate<? super T> other) { ... }
    default Predicate<T> negate() { ... }
    
    // Static method
    static <T> Predicate<T> isEqual(Object targetRef) { ... }
}
```

**Visual Representation:**

```
┌─────────────────────────────────────────────────┐
│              PREDICATE<T>                       │
├─────────────────────────────────────────────────┤
│                                                 │
│  Input: T (any type)                            │
│    ↓                                            │
│  [Test Condition]                               │
│    ↓                                            │
│  Output: boolean (true/false)                   │
│                                                 │
│  Example:                                       │
│  ┌─────┐     ┌──────────┐     ┌──────┐        │
│  │  5  │ ──→ │ isEven() │ ──→ │false │        │
│  └─────┘     └──────────┘     └──────┘        │
│                                                 │
└─────────────────────────────────────────────────┘
```

**Common Uses:**

```java
// Filtering
Predicate<Integer> isPositive = n -> n > 0;
Predicate<String> isEmpty = String::isEmpty;
Predicate<Person> isAdult = p -> p.getAge() >= 18;

// Combining predicates
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<Integer> isPositive = n -> n > 0;
Predicate<Integer> isPositiveEven = isPositive.and(isEven);

// In collections
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
       .filter(n -> n > 2)  // Using Predicate
       .forEach(System.out::println);

// Validation
Predicate<String> isValidEmail = email -> 
    email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
```

---

### 2. Function<T, R> - Transformations

**Signature:** `T → R`

**Purpose:** Transform input of type T to output of type R

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
    
    // Composition
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) { ... }
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) { ... }
    
    // Identity
    static <T> Function<T, T> identity() { ... }
}
```

**Visual Representation:**

```
┌─────────────────────────────────────────────────┐
│              FUNCTION<T, R>                     │
├─────────────────────────────────────────────────┤
│                                                 │
│  Input Type: T                                  │
│       ↓                                         │
│  [Transformation Logic]                         │
│       ↓                                         │
│  Output Type: R (can be different from T)       │
│                                                 │
│  Example: String → Integer                      │
│  ┌────────┐    ┌─────────┐    ┌───┐           │
│  │"Hello" │ ──→│ length()│ ──→│ 5 │           │
│  └────────┘    └─────────┘    └───┘           │
│                                                 │
└─────────────────────────────────────────────────┘
```

**Common Uses:**

```java
// Type conversion
Function<String, Integer> toInt = Integer::parseInt;
Function<Integer, String> toString = String::valueOf;

// Extraction
Function<Person, String> getName = Person::getName;
Function<Order, Double> getTotalPrice = Order::getTotalPrice;

// Calculation
Function<Integer, Integer> square = n -> n * n;
Function<Double, Double> addTax = price -> price * 1.1;

// Chaining
Function<String, String> trim = String::trim;
Function<String, String> upper = String::toUpperCase;
Function<String, String> trimAndUpper = trim.andThen(upper);

String result = trimAndUpper.apply("  hello  "); // "HELLO"

// In streams
List<String> names = people.stream()
    .map(Person::getName)  // Using Function
    .collect(Collectors.toList());
```

---

### 3. Consumer\<T> - Actions with Side Effects

**Signature:** `T → void`

**Purpose:** Accept input and perform an action (no return value)

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    
    // Chaining
    default Consumer<T> andThen(Consumer<? super T> after) { ... }
}
```

**Visual Representation:**

```
┌─────────────────────────────────────────────────┐
│              CONSUMER<T>                        │
├─────────────────────────────────────────────────┤
│                                                 │
│  Input: T                                       │
│    ↓                                            │
│  [Perform Action - Side Effect]                 │
│    ↓                                            │
│  Output: void (no return value)                 │
│                                                 │
│  Example:                                       │
│  ┌────────┐    ┌──────────┐                    │
│  │"Hello" │ ──→│  print() │ ──→ [Side Effect]  │
│  └────────┘    └──────────┘      (console)     │
│                                                 │
└─────────────────────────────────────────────────┘
```

**Common Uses:**

```java
// Printing
Consumer<String> print = System.out::println;
Consumer<String> log = s -> logger.info(s);

// Modifying
Consumer<List<String>> addItem = list -> list.add("New Item");
Consumer<Person> incrementAge = p -> p.setAge(p.getAge() + 1);

// Saving
Consumer<User> saveToDb = user -> database.save(user);
Consumer<File> writeToFile = file -> fileWriter.write(file);

// Chaining
Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
Consumer<String> printLength = s -> System.out.println("Length: " + s.length());
Consumer<String> combined = printUpper.andThen(printLength);

combined.accept("hello");
// Output:
// HELLO
// Length: 5

// In collections
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println("Hello, " + name));
```

---

### 4. Supplier\<T> - Value Providers

**Signature:** `() → T`

**Purpose:** Supply/provide a value (no input parameters)

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

**Visual Representation:**

```
┌─────────────────────────────────────────────────┐
│              SUPPLIER<T>                        │
├─────────────────────────────────────────────────┤
│                                                 │
│  Input: (none)                                  │
│    ↓                                            │
│  [Generate/Provide Value]                       │
│    ↓                                            │
│  Output: T                                      │
│                                                 │
│  Example:                                       │
│              ┌──────────┐     ┌────┐           │
│       () ──→ │ random() │ ──→ │0.73│           │
│              └──────────┘     └────┘           │
│                                                 │
└─────────────────────────────────────────────────┘
```

**Common Uses:**

```java
// Generating values
Supplier<Double> random = Math::random;
Supplier<String> uuid = () -> UUID.randomUUID().toString();
Supplier<LocalDateTime> now = LocalDateTime::now;

// Factory methods
Supplier<List<String>> listFactory = ArrayList::new;
Supplier<Map<String, Integer>> mapFactory = HashMap::new;

// Lazy initialization
Supplier<ExpensiveObject> lazyInit = () -> new ExpensiveObject();
// Object only created when get() is called

// Default values
Supplier<String> defaultName = () -> "Unknown";
Supplier<Integer> defaultAge = () -> 0;

// With Optional
String name = Optional.ofNullable(getName())
    .orElseGet(() -> "Default Name");

// In Stream generation
Stream<Double> randomStream = Stream.generate(Math::random);
Stream<Integer> infiniteOnes = Stream.generate(() -> 1);
```

---

### 5. BiFunction<T, U, R> - Two Parameter Functions

**Signature:** `(T, U) → R`

**Purpose:** Transform two inputs to one output

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
    
    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) { ... }
}
```

**Common Uses:**

```java
// Arithmetic
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;

// String operations
BiFunction<String, Integer, String> repeat = (str, times) -> str.repeat(times);
BiFunction<String, String, Integer> compare = String::compareTo;

// Object creation
BiFunction<String, Integer, Person> createPerson = Person::new;

// Combining values
BiFunction<List<Integer>, Integer, List<Integer>> addToList = (list, item) -> {
    list.add(item);
    return list;
};

// In Map operations
Map<String, Integer> map = new HashMap<>();
map.merge("key", 1, (oldVal, newVal) -> oldVal + newVal);
//                   ↑ BiFunction<Integer, Integer, Integer>
```

---

### 6. UnaryOperator\<T> and BinaryOperator\<T>

Special cases where input and output types are the same.

```java
// UnaryOperator<T> extends Function<T, T>
UnaryOperator<String> toUpper = String::toUpperCase;
UnaryOperator<Integer> square = n -> n * n;

// BinaryOperator<T> extends BiFunction<T, T, T>
BinaryOperator<Integer> max = Math::max;
BinaryOperator<String> concat = (s1, s2) -> s1 + s2;

// Static methods
BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compareTo);
BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compareTo);
```

---

## 🔗 Method Chaining and Composition

### Predicate Composition

```java
Predicate<Integer> isPositive = n -> n > 0;
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<Integer> isLessThan100 = n -> n < 100;

// AND composition
Predicate<Integer> positiveEven = isPositive.and(isEven);
// n -> (n > 0) && (n % 2 == 0)

// OR composition
Predicate<Integer> positiveOrEven = isPositive.or(isEven);
// n -> (n > 0) || (n % 2 == 0)

// NEGATE
Predicate<Integer> isNotPositive = isPositive.negate();
// n -> !(n > 0)

// Complex composition
Predicate<Integer> complex = isPositive
    .and(isEven)
    .and(isLessThan100);
// Positive AND even AND less than 100
```

### Function Composition

```java
Function<String, String> trim = String::trim;
Function<String, String> toUpper = String::toUpperCase;
Function<String, Integer> length = String::length;

// andThen: f.andThen(g) means g(f(x))
Function<String, Integer> trimThenLength = trim.andThen(length);
String input = "  hello  ";
int result = trimThenLength.apply(input);  // 5

// compose: f.compose(g) means f(g(x))
Function<String, String> upperThenTrim = trim.compose(toUpper);
// First toUpper, then trim

// Chain multiple
Function<String, Integer> pipeline = trim
    .andThen(toUpper)
    .andThen(length);
```

**Visual Composition:**

```
┌──────────────────────────────────────────────────┐
│         FUNCTION COMPOSITION                     │
├──────────────────────────────────────────────────┤
│                                                  │
│  andThen:  f.andThen(g) = g(f(x))               │
│                                                  │
│     ┌─────┐      ┌─────┐      ┌─────┐          │
│  x──│  f  │──y───│  g  │──z   │     │          │
│     └─────┘      └─────┘      │final│          │
│                                └─────┘          │
│                                                  │
│  compose:  f.compose(g) = f(g(x))               │
│                                                  │
│     ┌─────┐      ┌─────┐      ┌─────┐          │
│  x──│  g  │──y───│  f  │──z   │     │          │
│     └─────┘      └─────┘      │final│          │
│                                └─────┘          │
│                                                  │
└──────────────────────────────────────────────────┘
```

### Consumer Chaining

```java
Consumer<String> print = System.out::println;
Consumer<String> log = s -> logger.info(s);
Consumer<String> save = s -> database.save(s);

// Chain consumers
Consumer<String> printAndLog = print.andThen(log);
Consumer<String> printLogSave = print.andThen(log).andThen(save);

printLogSave.accept("Important message");
// Executes all three in order
```

---

## 🛠️ Custom Functional Interfaces

### When to Create Custom Interfaces

Create custom functional interfaces when:
1. Built-in interfaces don't express domain intent clearly
2. You need more descriptive method names
3. You want to add default/static helper methods

### Example: Banking Domain

```java
@FunctionalInterface
interface AccountValidator {
    boolean validate(Account account);
    
    // Default method for combining validators
    default AccountValidator and(AccountValidator other) {
        return account -> this.validate(account) && other.validate(account);
    }
    
    // Static factory methods
    static AccountValidator minimumBalance(double amount) {
        return account -> account.getBalance() >= amount;
    }
    
    static AccountValidator activeAccount() {
        return account -> account.isActive();
    }
}

// Usage
AccountValidator validator = AccountValidator.minimumBalance(100.0)
    .and(AccountValidator.activeAccount());

if (validator.validate(myAccount)) {
    // Proceed with transaction
}
```

### Example: Data Processing

```java
@FunctionalInterface
interface DataTransformer<T> {
    T transform(T data);
    
    default DataTransformer<T> andThen(DataTransformer<T> next) {
        return data -> next.transform(this.transform(data));
    }
    
    static <T> DataTransformer<T> identity() {
        return data -> data;
    }
}

// Usage
DataTransformer<String> trimmer = String::trim;
DataTransformer<String> capitalizer = String::toUpperCase;
DataTransformer<String> pipeline = trimmer.andThen(capitalizer);

String result = pipeline.transform("  hello  "); // "HELLO"
```

### Example: Three Parameter Function

```java
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

// Usage
TriFunction<Integer, Integer, Integer, Integer> sumThree = 
    (a, b, c) -> a + b + c;

int result = sumThree.apply(1, 2, 3); // 6
```

---

## 🎯 Advanced Patterns

### 1. Currying with Functional Interfaces

```java
// Traditional three-parameter function
TriFunction<Integer, Integer, Integer, Integer> add3 = 
    (a, b, c) -> a + b + c;

// Curried version
Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedAdd =
    a -> b -> c -> a + b + c;

// Usage
int result = curriedAdd.apply(1).apply(2).apply(3); // 6

// Partial application
Function<Integer, Function<Integer, Integer>> add1 = curriedAdd.apply(1);
Function<Integer, Integer> add1and2 = add1.apply(2);
int finalResult = add1and2.apply(3); // 6
```

### 2. Memoization

```java
class Memoizer<T, R> {
    private final Map<T, R> cache = new ConcurrentHashMap<>();
    private final Function<T, R> function;
    
    public Memoizer(Function<T, R> function) {
        this.function = function;
    }
    
    public Function<T, R> memoize() {
        return input -> cache.computeIfAbsent(input, function);
    }
}

// Usage
Function<Integer, Integer> expensiveOperation = n -> {
    // Simulate expensive computation
    try { Thread.sleep(1000); } catch (InterruptedException e) { }
    return n * n;
};

Function<Integer, Integer> memoized = new Memoizer<>(expensiveOperation).memoize();

// First call: takes 1 second
int result1 = memoized.apply(5);
// Second call with same input: instant (cached)
int result2 = memoized.apply(5);
```

### 3. Function Factory

```java
class FunctionFactory {
    // Create validator with custom rule
    public static <T> Predicate<T> createValidator(
            Predicate<T> rule, String errorMessage) {
        return value -> {
            if (!rule.test(value)) {
                throw new ValidationException(errorMessage);
            }
            return true;
        };
    }
    
    // Create transformer with logging
    public static <T, R> Function<T, R> createLoggingTransformer(
            Function<T, R> transformer, String name) {
        return input -> {
            System.out.println(name + ": Processing " + input);
            R result = transformer.apply(input);
            System.out.println(name + ": Result " + result);
            return result;
        };
    }
}

// Usage
Predicate<String> emailValidator = FunctionFactory.createValidator(
    email -> email.contains("@"),
    "Invalid email format"
);

Function<String, Integer> loggingLength = FunctionFactory.createLoggingTransformer(
    String::length,
    "LengthCalculator"
);
```

---

## 🎯 Quick Reference Card

### Functional Interface Selector

```
┌────────────────────────────────────────────────────────┐
│        WHICH FUNCTIONAL INTERFACE TO USE?              │
├────────────────────────────────────────────────────────┤
│                                                        │
│  Need to TEST something?                               │
│    → Predicate<T>                                      │
│      Example: n -> n > 0                               │
│                                                        │
│  Need to TRANSFORM data?                               │
│    → Function<T, R>                                    │
│      Example: s -> s.length()                          │
│                                                        │
│  Need to DO something (side effect)?                   │
│    → Consumer<T>                                       │
│      Example: s -> System.out.println(s)               │
│                                                        │
│  Need to PROVIDE/GENERATE a value?                     │
│    → Supplier<T>                                       │
│      Example: () -> new ArrayList<>()                  │
│                                                        │
│  Working with TWO inputs?                              │
│    - Testing: BiPredicate<T, U>                        │
│    - Transforming: BiFunction<T, U, R>                 │
│    - Acting: BiConsumer<T, U>                          │
│                                                        │
│  Input and Output SAME type?                           │
│    - One input: UnaryOperator<T>                       │
│    - Two inputs: BinaryOperator<T>                     │
│                                                        │
└────────────────────────────────────────────────────────┘
```

### Complete Interface Summary

```java
// PREDICATES (Testing)
Predicate<T>           t -> boolean
BiPredicate<T,U>       (t,u) -> boolean
IntPredicate           int -> boolean

// FUNCTIONS (Transforming)
Function<T,R>          t -> r
BiFunction<T,U,R>      (t,u) -> r
UnaryOperator<T>       t -> t
BinaryOperator<T>      (t,t) -> t
IntFunction<R>         int -> r
ToIntFunction<T>       t -> int

// CONSUMERS (Acting)
Consumer<T>            t -> void
BiConsumer<T,U>        (t,u) -> void
IntConsumer            int -> void

// SUPPLIERS (Providing)
Supplier<T>            () -> t
IntSupplier            () -> int
BooleanSupplier        () -> boolean
```

### Composition Cheat Sheet

```java
// PREDICATE COMPOSITION
p1.and(p2)           // p1 && p2
p1.or(p2)            // p1 || p2
p1.negate()          // !p1

// FUNCTION COMPOSITION
f.andThen(g)         // g(f(x))
f.compose(g)         // f(g(x))

// CONSUMER COMPOSITION
c1.andThen(c2)       // c1(x); c2(x)
```

---

## 🏆 Mastery Checklist

- [ ] Understand what makes an interface "functional"
- [ ] Know the purpose of @FunctionalInterface annotation
- [ ] Master the core four: Predicate, Function, Consumer, Supplier
- [ ] Understand Bi-variants (BiPredicate, BiFunction, BiConsumer)
- [ ] Know when to use UnaryOperator vs BinaryOperator
- [ ] Can compose Predicates with and/or/negate
- [ ] Can chain Functions with andThen/compose
- [ ] Can create custom functional interfaces
- [ ] Understand primitive specializations (IntPredicate, etc.)
- [ ] Know advanced patterns (currying, memoization)

---

**Previous Module**: [← Lambda Expressions](../01-LambdaExpressions/)  
**Next Module**: [Method References →](../03-MethodReferences/)

