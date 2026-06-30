package functionalinterfacesdeep;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO FUNCTIONAL INTERFACES DEEP DIVE EXERCISES
 * =======================================================
 * Comprehensive solutions covering all 43 built-in functional interfaces.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== FUNCTIONAL INTERFACES DEEP DIVE - SOLUTIONS ===\n");
        
        exercise1_Core4Interfaces();
        exercise2_BiVariants();
        exercise3_Operators();
        exercise4_PrimitiveSpecializations();
        exercise5_Composition();
        exercise6_AdvancedPatterns();
        exercise7_RealWorldUsage();
    }

    /**
     * EXERCISE 1: Core 4 Interfaces
     */
    private static void exercise1_Core4Interfaces() {
        System.out.println("EXERCISE 1: Core 4 Interfaces");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. Predicate - test condition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Even numbers: " + 
            numbers.stream().filter(isEven).collect(Collectors.toList()));
        
        // 2. Function - transform
        Function<Integer, String> toHex = n -> Integer.toHexString(n);
        System.out.println("Hex values: " + 
            numbers.stream().map(toHex).limit(5).collect(Collectors.toList()));
        
        // 3. Consumer - side effects
        Consumer<String> printer = s -> System.out.println("  " + s);
        System.out.println("Consuming values:");
        Arrays.asList("A", "B", "C").forEach(printer);
        
        // 4. Supplier - lazy generation
        Supplier<Double> random = Math::random;
        System.out.println("Random value: " + random.get());
        
        // 5. Predicate composition
        Predicate<Integer> greaterThan5 = n -> n > 5;
        Predicate<Integer> combined = isEven.and(greaterThan5);
        System.out.println("Even AND > 5: " + 
            numbers.stream().filter(combined).collect(Collectors.toList()));
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Bi-Variants
     */
    private static void exercise2_BiVariants() {
        System.out.println("EXERCISE 2: Bi-Variants");
        System.out.println("-".repeat(60));
        
        // 1. BiPredicate
        BiPredicate<String, Integer> longerThan = (s, len) -> s.length() > len;
        System.out.println("'Hello' longer than 3? " + longerThan.test("Hello", 3));
        
        // 2. BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.apply(5, 3));
        
        // 3. BiConsumer
        BiConsumer<String, Integer> printRepeat = (str, times) -> {
            for (int i = 0; i < times; i++) System.out.print(str + " ");
            System.out.println();
        };
        printRepeat.accept("Java", 3);
        
        // 4. Compare with single-arg
        Function<Integer, Integer> square = n -> n * n;
        BiFunction<Integer, Integer, Integer> power = (n, exp) -> 
            (int) Math.pow(n, exp);
        System.out.println("Square of 5: " + square.apply(5));
        System.out.println("5 to power 3: " + power.apply(5, 3));
        
        System.out.println();
    }

    /**
     * EXERCISE 3: Operators
     */
    private static void exercise3_Operators() {
        System.out.println("EXERCISE 3: Operators");
        System.out.println("-".repeat(60));
        
        // 1. UnaryOperator
        UnaryOperator<String> toUpper = String::toUpperCase;
        System.out.println("toUpper: " + toUpper.apply("hello"));
        
        // 2. BinaryOperator
        BinaryOperator<Integer> max = Math::max;
        System.out.println("Max of 10, 20: " + max.apply(10, 20));
        
        // 3. IntUnaryOperator
        IntUnaryOperator doubleIt = n -> n * 2;
        System.out.println("Double 5: " + doubleIt.applyAsInt(5));
        
        // 4. IntBinaryOperator
        IntBinaryOperator multiply = (a, b) -> a * b;
        System.out.println("3 * 4 = " + multiply.applyAsInt(3, 4));
        
        // 5. Identity operator
        UnaryOperator<String> identity = UnaryOperator.identity();
        System.out.println("Identity: " + identity.apply("unchanged"));
        
        System.out.println();
    }

    /**
     * EXERCISE 4: Primitive Specializations
     */
    private static void exercise4_PrimitiveSpecializations() {
        System.out.println("EXERCISE 4: Primitive Specializations");
        System.out.println("-".repeat(60));
        
        // 1. IntPredicate vs Predicate<Integer>
        IntPredicate intPred = n -> n > 5;
        Predicate<Integer> boxedPred = n -> n > 5;
        System.out.println("IntPredicate (no boxing): " + intPred.test(10));
        System.out.println("Predicate<Integer> (boxing): " + boxedPred.test(10));
        
        // 2. IntFunction
        IntFunction<String> intToString = i -> "Number: " + i;
        System.out.println(intToString.apply(42));
        
        // 3. ToIntFunction
        ToIntFunction<String> stringLength = String::length;
        System.out.println("Length: " + stringLength.applyAsInt("Java"));
        
        // 4. IntSupplier
        IntSupplier randomInt = () -> (int)(Math.random() * 100);
        System.out.println("Random int: " + randomInt.getAsInt());
        
        // 5. IntConsumer
        IntConsumer printer = n -> System.out.println("Value: " + n);
        printer.accept(100);
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Composition
     */
    private static void exercise5_Composition() {
        System.out.println("EXERCISE 5: Composition");
        System.out.println("-".repeat(60));
        
        // Function composition
        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;
        Function<String, String> combined = trim.andThen(upper);
        
        System.out.println("Composed: " + combined.apply("  hello  "));
        
        // Predicate composition
        Predicate<Integer> positive = n -> n > 0;
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> positiveEven = positive.and(even);
        
        System.out.println("6 is positive and even? " + positiveEven.test(6));
        System.out.println("-3 is positive and even? " + positiveEven.test(-3));
        
        // Consumer composition
        Consumer<String> print1 = s -> System.out.println("Print1: " + s);
        Consumer<String> print2 = s -> System.out.println("Print2: " + s);
        Consumer<String> both = print1.andThen(print2);
        both.accept("Test");
        
        System.out.println();
    }

    /**
     * EXERCISE 6: Advanced Patterns
     */
    private static void exercise6_AdvancedPatterns() {
        System.out.println("EXERCISE 6: Advanced Patterns");
        System.out.println("-".repeat(60));
        
        List<String> items = Arrays.asList("apple", "banana", "cherry");
        
        // Map with Function
        List<Integer> lengths = items.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Lengths: " + lengths);
        
        // Filter with Predicate
        List<String> filtered = items.stream()
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println("Starts with 'a': " + filtered);
        
        // Reduce with BinaryOperator
        Optional<String> concatenated = items.stream()
                .reduce((a, b) -> a + ", " + b);
        System.out.println("Concatenated: " + concatenated.orElse(""));
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Real-world Usage
     */
    private static void exercise7_RealWorldUsage() {
        System.out.println("EXERCISE 7: Real-world Usage");
        System.out.println("-".repeat(60));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 75000),
            new Employee("Bob", 55000),
            new Employee("Charlie", 85000)
        );
        
        // Filter high earners
        Predicate<Employee> highEarner = emp -> emp.salary > 60000;
        System.out.println("High earners:");
        employees.stream()
                .filter(highEarner)
                .forEach(emp -> System.out.println("  " + emp.name));
        
        // Transform to salary list
        Function<Employee, Double> getSalary = emp -> emp.salary;
        List<Double> salaries = employees.stream()
                .map(getSalary)
                .collect(Collectors.toList());
        System.out.println("Salaries: " + salaries);
        
        // Calculate total salary
        BinaryOperator<Double> sumSalaries = Double::sum;
        double total = salaries.stream()
                .reduce(0.0, sumSalaries);
        System.out.println("Total salary budget: $" + total);
        
        System.out.println();
    }

    static class Employee {
        String name;
        double salary;
        
        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
    }
}
