package java8revision;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * JAVA 8 COMPLETE REVISION - ALL FEATURES IN ONE PLACE
 * 
 * This comprehensive demo covers ALL major Java 8 features:
 * 1. Lambda Expressions
 * 2. Functional Interfaces
 * 3. Method References
 * 4. Streams API
 * 5. Optional Class
 * 6. Default & Static Methods
 * 7. Date Time API
 * 8. Collectors
 * 9. Parallel Streams
 * 10. CompletableFuture
 * 11. Functional Interfaces Deep Dive
 * 12. forEach Iteration
 * 
 * Run this file to see all concepts in action!
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Java8Recap {

    public static void main(String[] args) {
        System.out.println("\n" + repeat("=", 80));
        System.out.println("    JAVA 8 COMPLETE REVISION - ALL FEATURES DEMONSTRATION");
        System.out.println(repeat("=", 80) + "\n");

        lambdaExpressions();
        functionalInterfaces();
        methodReferences();
        streamsAPI();
        optionalClass();
        defaultStaticMethods();
        dateTimeAPI();
        collectors();
        parallelStreams();
        completableFuture();
        functionalInterfacesDeepDive();
        forEachIteration();

        System.out.println("\n" + repeat("=", 80));
        System.out.println("    REVISION COMPLETE! You've covered all Java 8 features.");
        System.out.println(repeat("=", 80) + "\n");
    }
    
    // Utility method for Java 8 compatibility (repeat() is Java 11+)
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    // ========================================================================
    // 1. LAMBDA EXPRESSIONS
    // ========================================================================
    private static void lambdaExpressions() {
        printSection("1. LAMBDA EXPRESSIONS");

        // No parameters
        Runnable r = () -> System.out.println("   Hello from lambda!");
        r.run();

        // One parameter
        Consumer<String> printer = msg -> System.out.println("   " + msg);
        printer.accept("Single parameter lambda");

        // Multiple parameters
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("   5 + 3 = " + add.apply(5, 3));

        // Block body
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> {
            int result = a * b;
            return result;
        };
        System.out.println("   5 * 3 = " + multiply.apply(5, 3));

        // Lambda with collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("   Hello, " + name));

        System.out.println();
    }

    // ========================================================================
    // 2. FUNCTIONAL INTERFACES
    // ========================================================================
    private static void functionalInterfaces() {
        printSection("2. FUNCTIONAL INTERFACES");

        // Predicate - test condition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("   Is 4 even? " + isEven.test(4));

        // Function - transform
        Function<String, Integer> length = String::length;
        System.out.println("   Length of 'Java': " + length.apply("Java"));

        // Consumer - side effect
        Consumer<String> print = s -> System.out.println("   Consuming: " + s);
        print.accept("Consumer example");

        // Supplier - provide value
        Supplier<Double> random = Math::random;
        System.out.println("   Random value: " + random.get());

        // Custom functional interface
        Calculator calc = (a, b) -> a * b;
        System.out.println("   Custom Calculator 6 * 7 = " + calc.calculate(6, 7));

        System.out.println();
    }

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    // ========================================================================
    // 3. METHOD REFERENCES
    // ========================================================================
    private static void methodReferences() {
        printSection("3. METHOD REFERENCES");

        List<String> words = Arrays.asList("apple", "banana", "cherry");

        // Static method reference
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("   Parsed '42': " + parser.apply("42"));

        // Instance method reference (on object)
        String str = "Hello";
        Supplier<Integer> lengthSupplier = str::length;
        System.out.println("   Length of 'Hello': " + lengthSupplier.get());

        // Instance method reference (arbitrary object)
        words.stream()
                .map(String::toUpperCase)
                .forEach(w -> System.out.println("   " + w));

        // Constructor reference
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        System.out.println("   Created new list: " + newList.getClass().getSimpleName());

        System.out.println();
    }

    // ========================================================================
    // 4. STREAMS API
    // ========================================================================
    private static void streamsAPI() {
        printSection("4. STREAMS API");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("   Even numbers: " + evens);

        // Map
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("   Squares: " + squares);

        // Reduce
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("   Sum: " + sum);

        // FlatMap
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );
        List<Integer> flattened = nested.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("   Flattened: " + flattened);

        // Match operations
        boolean anyGreaterThan5 = numbers.stream().anyMatch(n -> n > 5);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        System.out.println("   Any > 5? " + anyGreaterThan5);
        System.out.println("   All positive? " + allPositive);

        // Sorted and distinct
        List<Integer> result = Arrays.asList(5, 2, 8, 2, 9, 1).stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("   Distinct & Sorted: " + result);

        System.out.println();
    }

    // ========================================================================
    // 5. OPTIONAL CLASS
    // ========================================================================
    private static void optionalClass() {
        printSection("5. OPTIONAL CLASS");

        // Creating Optionals
        Optional<String> empty = Optional.empty();
        Optional<String> notEmpty = Optional.of("Java");
        Optional<String> nullable = Optional.ofNullable(null);

        // isPresent and get (old style - avoid)
        if (notEmpty.isPresent()) {
            System.out.println("   Value present: " + notEmpty.get());
        }

        // ifPresent (better)
        notEmpty.ifPresent(val -> System.out.println("   Using ifPresent: " + val));

        // orElse
        String value1 = empty.orElse("default");
        System.out.println("   orElse on empty: " + value1);

        // orElseGet (lazy)
        String value2 = empty.orElseGet(() -> "computed default");
        System.out.println("   orElseGet on empty: " + value2);

        // map
        Optional<Integer> length = notEmpty.map(String::length);
        System.out.println("   Mapped to length: " + length.get());

        // filter
        Optional<String> filtered = notEmpty.filter(s -> s.startsWith("J"));
        System.out.println("   Filtered (starts with J): " + filtered.get());

        // flatMap
        Optional<String> upper = notEmpty.flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("   FlatMapped to upper: " + upper.get());

        System.out.println();
    }

    // ========================================================================
    // 6. DEFAULT & STATIC METHODS
    // ========================================================================
    private static void defaultStaticMethods() {
        printSection("6. DEFAULT & STATIC METHODS");

        Vehicle car = new Car();
        car.start();
        car.stop();  // Default method

        // Static method
        int result = MathOperations.add(5, 3);
        System.out.println("   Static method result: " + result);

        // Diamond problem resolution
        Diamond d = new Diamond();
        d.print();

        System.out.println();
    }

    interface Vehicle {
        void start();

        default void stop() {
            System.out.println("   Vehicle stopped (default method)");
        }
    }

    static class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("   Car starting");
        }
    }

    interface MathOperations {
        static int add(int a, int b) {
            return a + b;
        }
    }

    interface InterfaceA {
        default void print() {
            System.out.println("   Interface A");
        }
    }

    interface InterfaceB {
        default void print() {
            System.out.println("   Interface B");
        }
    }

    static class Diamond implements InterfaceA, InterfaceB {
        @Override
        public void print() {
            System.out.println("   Diamond class resolves conflict");
            InterfaceA.super.print();  // Explicit selection
        }
    }

    // ========================================================================
    // 7. DATE TIME API
    // ========================================================================
    private static void dateTimeAPI() {
        printSection("7. DATE TIME API");

        // Current date and time
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("   Today: " + today);
        System.out.println("   Now: " + now);
        System.out.println("   DateTime: " + dateTime);

        // Creating specific dates
        LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 15);
        System.out.println("   Birthday: " + birthday);

        // Manipulation (immutable)
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextMonth = today.plusMonths(1);
        System.out.println("   Tomorrow: " + tomorrow);
        System.out.println("   Next month: " + nextMonth);

        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatted = today.format(formatter);
        System.out.println("   Formatted: " + formatted);

        // Period and Duration
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);
        Period period = Period.between(start, end);
        System.out.println("   Period: " + period.getMonths() + " months, " + period.getDays() + " days");

        LocalTime time1 = LocalTime.of(10, 0);
        LocalTime time2 = LocalTime.of(12, 30);
        Duration duration = Duration.between(time1, time2);
        System.out.println("   Duration: " + duration.toHours() + " hours");

        System.out.println();
    }

    // ========================================================================
    // 8. COLLECTORS
    // ========================================================================
    private static void collectors() {
        printSection("8. COLLECTORS");

        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "IT"),
                new Person("Bob", 25, "HR"),
                new Person("Charlie", 30, "IT"),
                new Person("David", 35, "Finance")
        );

        // toList
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("   Names: " + names);

        // toSet
        Set<Integer> ages = people.stream()
                .map(Person::getAge)
                .collect(Collectors.toSet());
        System.out.println("   Unique ages: " + ages);

        // toMap
        Map<String, Integer> nameToAge = people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("   Name to Age map: " + nameToAge);

        // joining
        String joined = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("   Joined names: " + joined);

        // groupingBy
        Map<String, List<Person>> byDept = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("   Grouped by department: " + byDept.keySet());

        // partitioningBy
        Map<Boolean, List<Person>> partition = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 28));
        System.out.println("   Age > 28: " + partition.get(true).size() + " people");

        // counting
        long count = people.stream().collect(Collectors.counting());
        System.out.println("   Total count: " + count);

        // averagingInt
        double avgAge = people.stream()
                .collect(Collectors.averagingInt(Person::getAge));
        System.out.println("   Average age: " + avgAge);

        System.out.println();
    }

    static class Person {
        private String name;
        private int age;
        private String department;

        public Person(String name, int age, String department) {
            this.name = name;
            this.age = age;
            this.department = department;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    // ========================================================================
    // 9. PARALLEL STREAMS
    // ========================================================================
    private static void parallelStreams() {
        printSection("9. PARALLEL STREAMS");

        List<Integer> numbers = IntStream.range(1, 1001)
                .boxed()
                .collect(Collectors.toList());

        // Sequential stream
        long start = System.currentTimeMillis();
        long sumSeq = numbers.stream()
                .mapToLong(n -> n * n)
                .sum();
        long seqTime = System.currentTimeMillis() - start;
        System.out.println("   Sequential sum: " + sumSeq + " (Time: " + seqTime + "ms)");

        // Parallel stream
        start = System.currentTimeMillis();
        long sumPar = numbers.parallelStream()
                .mapToLong(n -> n * n)
                .sum();
        long parTime = System.currentTimeMillis() - start;
        System.out.println("   Parallel sum: " + sumPar + " (Time: " + parTime + "ms)");

        // Check if parallel
        boolean isParallel = numbers.parallelStream().isParallel();
        System.out.println("   Is parallel? " + isParallel);

        // Convert between sequential and parallel
        long count = numbers.stream()
                .parallel()  // Make parallel
                .filter(n -> n % 2 == 0)
                .sequential()  // Back to sequential
                .count();
        System.out.println("   Even numbers count: " + count);

        System.out.println();
    }

    // ========================================================================
    // 10. COMPLETABLEFUTURE
    // ========================================================================
    private static void completableFuture() {
        printSection("10. COMPLETABLEFUTURE");

        // supplyAsync
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "Hello";
        });

        // thenApply
        CompletableFuture<String> future2 = future1.thenApply(s -> s + " World");

        // thenAccept
        future2.thenAccept(s -> System.out.println("   Result: " + s));

        // Combining futures
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "Java");
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "8");

        CompletableFuture<String> combined = cf1.thenCombine(cf2, (a, b) -> a + " " + b);
        System.out.println("   Combined: " + combined.join());

        // allOf
        CompletableFuture<Void> all = CompletableFuture.allOf(cf1, cf2);
        all.join();
        System.out.println("   All futures completed");

        // Error handling
        CompletableFuture<String> withError = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Error!");
            return "Success";
        }).exceptionally(ex -> {
            System.out.println("   Handled error: " + ex.getMessage());
            return "Default";
        });

        System.out.println("   Error handling result: " + withError.join());

        System.out.println();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ========================================================================
    // 11. FUNCTIONAL INTERFACES DEEP DIVE
    // ========================================================================
    private static void functionalInterfacesDeepDive() {
        printSection("11. FUNCTIONAL INTERFACES DEEP DIVE");

        // BiPredicate
        BiPredicate<String, Integer> longerThan = (s, len) -> s.length() > len;
        System.out.println("   'Hello' longer than 3? " + longerThan.test("Hello", 3));

        // BiFunction
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        System.out.println("   5 * 4 = " + multiply.apply(5, 4));

        // BiConsumer
        BiConsumer<String, Integer> printer = (name, age) ->
                System.out.println("   " + name + " is " + age + " years old");
        printer.accept("Alice", 30);

        // UnaryOperator
        UnaryOperator<String> toUpper = String::toUpperCase;
        System.out.println("   Upper: " + toUpper.apply("java"));

        // BinaryOperator
        BinaryOperator<Integer> max = Math::max;
        System.out.println("   Max of 10, 20: " + max.apply(10, 20));

        // Primitive specializations
        IntPredicate isPositive = n -> n > 0;
        System.out.println("   Is 5 positive? " + isPositive.test(5));

        IntFunction<String> intToString = i -> "Number: " + i;
        System.out.println("   " + intToString.apply(42));

        ToIntFunction<String> stringLength = String::length;
        System.out.println("   Length of 'Java': " + stringLength.applyAsInt("Java"));

        // Composition
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> positive = n -> n > 0;
        Predicate<Integer> evenAndPositive = even.and(positive);
        System.out.println("   Is 4 even and positive? " + evenAndPositive.test(4));

        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;
        Function<String, String> combined = trim.andThen(upper);
        System.out.println("   Composed: " + combined.apply("  hello  "));

        System.out.println();
    }

    // ========================================================================
    // 12. FOREACH ITERATION
    // ========================================================================
    private static void forEachIteration() {
        printSection("12. FOREACH ITERATION");

        List<String> items = new ArrayList<>(Arrays.asList("Java", "Python", "C++"));

        // forEach on List
        System.out.println("   List forEach:");
        items.forEach(item -> System.out.println("     - " + item));

        // forEach on Map
        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 8);
        map.put("Python", 3);
        System.out.println("   Map forEach:");
        map.forEach((k, v) -> System.out.println("     " + k + " -> " + v));

        // replaceAll
        items.replaceAll(String::toUpperCase);
        System.out.println("   After replaceAll: " + items);

        // removeIf
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("   After removeIf (even): " + numbers);

        // Stream forEach
        System.out.println("   Stream forEach:");
        Arrays.asList("A", "B", "C").stream()
                .forEach(s -> System.out.println("     " + s));

        // forEachOrdered (maintains order in parallel)
        System.out.println("   Parallel forEachOrdered:");
        Arrays.asList(1, 2, 3, 4, 5).parallelStream()
                .forEachOrdered(n -> System.out.print("     " + n));
        System.out.println();

        System.out.println();
    }

    // ========================================================================
    // UTILITY METHODS
    // ========================================================================
    private static void printSection(String title) {
        System.out.println(repeat("-", 80));
        System.out.println(title);
        System.out.println(repeat("-", 80));
    }
}
