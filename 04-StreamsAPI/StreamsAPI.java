package streamsapi;

import java.util.*;
import java.util.stream.*;

/**
 * STREAMS API IN JAVA 8
 * ======================
 * 
 * WHAT IS THE STREAMS API?
 * ------------------------
 * The Stream API is a powerful abstraction for processing sequences of elements
 * in a declarative way. It enables functional-style operations on collections.
 * 
 * KEY CONCEPTS:
 * -------------
 * - Stream: A sequence of elements supporting sequential and parallel aggregate operations
 * - Pipeline: Chain of stream operations (source → intermediate ops → terminal op)
 * - Lazy Evaluation: Intermediate operations are not executed until a terminal operation is invoked
 * - Non-modifying: Streams don't modify the source collection
 * - One-time use: A stream can only be consumed once
 * 
 * STREAM OPERATIONS:
 * ------------------
 * 1. INTERMEDIATE OPERATIONS (return a stream, lazy):
 *    - filter(Predicate): Filter elements based on condition
 *    - map(Function): Transform each element
 *    - flatMap(Function): Flatten nested structures
 *    - distinct(): Remove duplicates
 *    - sorted(): Sort elements
 *    - peek(Consumer): Perform action without modifying stream (debugging)
 *    - limit(long): Truncate stream to n elements
 *    - skip(long): Skip first n elements
 * 
 * 2. TERMINAL OPERATIONS (trigger execution, produce result):
 *    - forEach(Consumer): Perform action on each element
 *    - collect(Collector): Accumulate elements into collection
 *    - reduce(BinaryOperator): Combine elements into single result
 *    - count(): Count elements
 *    - anyMatch/allMatch/noneMatch(Predicate): Test elements
 *    - findFirst/findAny(): Retrieve element
 *    - min/max(Comparator): Find minimum/maximum
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class StreamsAPI {

    public static void main(String[] args) {
        System.out.println("=== PART 1: UNDERSTANDING STREAMS ===\n");
        demonstrateStreamCreation();
        demonstrateFilteringAndMapping();
        demonstrateFlatMapAndDistinct();
        demonstrateSortingAndLimiting();
        demonstrateTerminalOperations();
        demonstrateReduction();
        
        System.out.println("\n=== PART 2: REAL-WORLD SCENARIOS ===\n");
        realWorldScenario1_SalesAnalysis();
        realWorldScenario2_StudentGrading();
        realWorldScenario3_LogAnalysis();
    }

    /**
     * EXAMPLE 1: Stream Creation
     * --------------------------
     * Different ways to create streams
     */
    private static void demonstrateStreamCreation() {
        System.out.println("1. STREAM CREATION:");
        System.out.println("-".repeat(60));
        
        // From collection
        List<String> list = Arrays.asList("A", "B", "C");
        Stream<String> stream1 = list.stream();
        System.out.println("From list: " + stream1.collect(Collectors.toList()));
        
        // From array
        String[] array = {"X", "Y", "Z"};
        Stream<String> stream2 = Arrays.stream(array);
        System.out.println("From array: " + stream2.collect(Collectors.toList()));
        
        // Using Stream.of()
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
        System.out.println("Stream.of: " + stream3.collect(Collectors.toList()));
        
        // Using Stream.generate() - infinite stream
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);
        System.out.println("Random numbers: " + randomStream.collect(Collectors.toList()));
        
        // Using Stream.iterate() - infinite stream
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("Even numbers: " + evenNumbers.collect(Collectors.toList()));
        
        // Using IntStream, LongStream, DoubleStream
        IntStream intStream = IntStream.range(1, 6);
        System.out.println("IntStream range: " + intStream.boxed().collect(Collectors.toList()));
        
        System.out.println();
    }

    /**
     * EXAMPLE 2: Filtering and Mapping
     * --------------------------------
     * Core stream operations for transformation
     */
    private static void demonstrateFilteringAndMapping() {
        System.out.println("2. FILTERING AND MAPPING:");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter: Get even numbers
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Map: Square each number
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);
        
        // Filter + Map: Square of even numbers
        List<Integer> evenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Even squares: " + evenSquares);
        
        // Map with different type
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);
        
        // Multiple filters
        List<Integer> result = numbers.stream()
                .filter(n -> n > 3)
                .filter(n -> n < 8)
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Filtered (>3, <8, even): " + result);
        
        System.out.println();
    }

    /**
     * EXAMPLE 3: FlatMap and Distinct
     * -------------------------------
     * Handling nested structures and removing duplicates
     */
    private static void demonstrateFlatMapAndDistinct() {
        System.out.println("3. FLATMAP AND DISTINCT:");
        System.out.println("-".repeat(60));
        
        // FlatMap: Flatten nested lists
        List<List<Integer>> nestedList = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        
        List<Integer> flattened = nestedList.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattened);
        
        // FlatMap with strings
        List<String> sentences = Arrays.asList(
            "Hello World",
            "Java Streams",
            "Functional Programming"
        );
        
        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println("All words: " + words);
        
        // Distinct: Remove duplicates
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
        List<Integer> uniqueNumbers = numbersWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique numbers: " + uniqueNumbers);
        
        // FlatMap + Distinct
        List<String> uniqueWords = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique words (lowercase): " + uniqueWords);
        
        System.out.println();
    }

    /**
     * EXAMPLE 4: Sorting and Limiting
     * -------------------------------
     * Ordering and restricting stream elements
     */
    private static void demonstrateSortingAndLimiting() {
        System.out.println("4. SORTING AND LIMITING:");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6);
        
        // Sort in natural order
        List<Integer> sorted = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted: " + sorted);
        
        // Sort in reverse order
        List<Integer> sortedDesc = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Sorted (descending): " + sortedDesc);
        
        // Limit: Get first 5 elements
        List<Integer> limited = numbers.stream()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("First 5: " + limited);
        
        // Skip: Skip first 3 elements
        List<Integer> skipped = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("After skipping 3: " + skipped);
        
        // Combination: Top 3 largest numbers
        List<Integer> top3 = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3: " + top3);
        
        // Sort strings by length
        List<String> words = Arrays.asList("apple", "pie", "banana", "cat");
        List<String> sortedByLength = words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Sorted by length: " + sortedByLength);
        
        System.out.println();
    }

    /**
     * EXAMPLE 5: Terminal Operations
     * ------------------------------
     * Operations that produce final results
     */
    private static void demonstrateTerminalOperations() {
        System.out.println("5. TERMINAL OPERATIONS:");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Count
        long count = numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Count of even numbers: " + count);
        
        // anyMatch: Check if any element matches
        boolean hasEven = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("Has even numbers: " + hasEven);
        
        // allMatch: Check if all elements match
        boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0);
        System.out.println("All positive: " + allPositive);
        
        // noneMatch: Check if no elements match
        boolean noNegative = numbers.stream()
                .noneMatch(n -> n < 0);
        System.out.println("No negative numbers: " + noNegative);
        
        // findFirst: Get first element
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
        System.out.println("First number > 5: " + first.orElse(-1));
        
        // findAny: Get any element (useful in parallel streams)
        Optional<Integer> any = numbers.stream()
                .filter(n -> n > 5)
                .findAny();
        System.out.println("Any number > 5: " + any.orElse(-1));
        
        // min and max
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        System.out.println("Min: " + min.orElse(-1) + ", Max: " + max.orElse(-1));
        
        System.out.println();
    }

    /**
     * EXAMPLE 6: Reduction Operations
     * -------------------------------
     * Combining stream elements into single result
     */
    private static void demonstrateReduction() {
        System.out.println("6. REDUCTION OPERATIONS:");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Sum using reduce
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
        
        // Sum using Integer::sum method reference
        int sum2 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum (method ref): " + sum2);
        
        // Product
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
        
        // Max using reduce
        Optional<Integer> max = numbers.stream()
                .reduce((a, b) -> a > b ? a : b);
        System.out.println("Max: " + max.orElse(-1));
        
        // String concatenation
        List<String> words = Arrays.asList("Hello", "World", "from", "Streams");
        String sentence = words.stream()
                .reduce("", (a, b) -> a + " " + b);
        System.out.println("Sentence:" + sentence);
        
        // Using collectors for common reductions
        int sumCollector = numbers.stream()
                .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum (collector): " + sumCollector);
        
        double average = numbers.stream()
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Average: " + average);
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 1: Sales Data Analysis
     * ------------------------------------------
     */
    private static void realWorldScenario1_SalesAnalysis() {
        System.out.println("REAL-WORLD SCENARIO 1: E-Commerce Sales Analysis");
        System.out.println("-".repeat(60));
        
        List<Sale> sales = Arrays.asList(
            new Sale("Laptop", 999.99, 5, "Electronics"),
            new Sale("Mouse", 29.99, 15, "Electronics"),
            new Sale("Desk", 299.99, 3, "Furniture"),
            new Sale("Chair", 199.99, 7, "Furniture"),
            new Sale("Monitor", 349.99, 4, "Electronics"),
            new Sale("Lamp", 49.99, 10, "Furniture")
        );
        
        // Total revenue
        double totalRevenue = sales.stream()
                .mapToDouble(sale -> sale.price * sale.quantity)
                .sum();
        System.out.printf("\nTotal Revenue: $%.2f\n", totalRevenue);
        
        // Average sale price
        double avgPrice = sales.stream()
                .mapToDouble(sale -> sale.price)
                .average()
                .orElse(0.0);
        System.out.printf("Average Price: $%.2f\n", avgPrice);
        
        // Top 3 products by revenue
        System.out.println("\nTop 3 Products by Revenue:");
        sales.stream()
                .sorted(Comparator.comparingDouble(s -> -s.price * s.quantity))
                .limit(3)
                .forEach(sale -> System.out.printf("  %s: $%.2f\n", 
                        sale.product, sale.price * sale.quantity));
        
        // Revenue by category
        System.out.println("\nRevenue by Category:");
        sales.stream()
                .collect(Collectors.groupingBy(
                    sale -> sale.category,
                    Collectors.summingDouble(sale -> sale.price * sale.quantity)
                ))
                .forEach((category, revenue) -> 
                    System.out.printf("  %s: $%.2f\n", category, revenue));
        
        // Products with quantity > 5
        System.out.println("\nHigh Volume Products (qty > 5):");
        sales.stream()
                .filter(sale -> sale.quantity > 5)
                .forEach(sale -> System.out.println("  " + sale.product));
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 2: Student Grading System
     * ---------------------------------------------
     */
    private static void realWorldScenario2_StudentGrading() {
        System.out.println("REAL-WORLD SCENARIO 2: Student Grading System");
        System.out.println("-".repeat(60));
        
        List<Student> students = Arrays.asList(
            new Student("Alice", Arrays.asList(85, 90, 88, 92)),
            new Student("Bob", Arrays.asList(70, 75, 72, 78)),
            new Student("Charlie", Arrays.asList(95, 98, 96, 94)),
            new Student("Diana", Arrays.asList(60, 65, 62, 68)),
            new Student("Eve", Arrays.asList(88, 85, 90, 87))
        );
        
        // Calculate average for each student
        System.out.println("\nStudent Averages:");
        students.forEach(student -> {
            double avg = student.grades.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
            System.out.printf("  %s: %.2f%%\n", student.name, avg);
        });
        
        // Students with average > 85
        System.out.println("\nHigh Performers (avg > 85%):");
        students.stream()
                .filter(s -> s.grades.stream().mapToInt(Integer::intValue).average().orElse(0) > 85)
                .forEach(s -> System.out.println("  " + s.name));
        
        // Class average
        double classAvg = students.stream()
                .flatMapToInt(s -> s.grades.stream().mapToInt(Integer::intValue))
                .average()
                .orElse(0.0);
        System.out.printf("\nClass Average: %.2f%%\n", classAvg);
        
        // Highest individual score
        int highestScore = students.stream()
                .flatMapToInt(s -> s.grades.stream().mapToInt(Integer::intValue))
                .max()
                .orElse(0);
        System.out.println("Highest Score: " + highestScore);
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 3: Log File Analysis
     * ----------------------------------------
     */
    private static void realWorldScenario3_LogAnalysis() {
        System.out.println("REAL-WORLD SCENARIO 3: Server Log Analysis");
        System.out.println("-".repeat(60));
        
        List<LogEntry> logs = Arrays.asList(
            new LogEntry("ERROR", "Database connection failed", "2024-01-15 10:30"),
            new LogEntry("INFO", "User logged in", "2024-01-15 10:31"),
            new LogEntry("WARN", "High memory usage", "2024-01-15 10:32"),
            new LogEntry("ERROR", "API timeout", "2024-01-15 10:33"),
            new LogEntry("INFO", "Request processed", "2024-01-15 10:34"),
            new LogEntry("ERROR", "File not found", "2024-01-15 10:35"),
            new LogEntry("INFO", "Cache cleared", "2024-01-15 10:36")
        );
        
        // Count by log level
        System.out.println("\nLog Count by Level:");
        logs.stream()
                .collect(Collectors.groupingBy(
                    log -> log.level,
                    Collectors.counting()
                ))
                .forEach((level, count) -> 
                    System.out.printf("  %s: %d\n", level, count));
        
        // All errors
        System.out.println("\nError Messages:");
        logs.stream()
                .filter(log -> log.level.equals("ERROR"))
                .forEach(log -> System.out.println("  " + log.message));
        
        // Check if there are critical errors
        boolean hasCriticalErrors = logs.stream()
                .anyMatch(log -> log.level.equals("ERROR") && 
                               log.message.contains("Database"));
        System.out.println("\nHas Critical Errors: " + hasCriticalErrors);
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================

    static class Sale {
        String product;
        double price;
        int quantity;
        String category;
        
        Sale(String product, double price, int quantity, String category) {
            this.product = product;
            this.price = price;
            this.quantity = quantity;
            this.category = category;
        }
    }

    static class Student {
        String name;
        List<Integer> grades;
        
        Student(String name, List<Integer> grades) {
            this.name = name;
            this.grades = grades;
        }
    }

    static class LogEntry {
        String level;
        String message;
        String timestamp;
        
        LogEntry(String level, String message, String timestamp) {
            this.level = level;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
}
