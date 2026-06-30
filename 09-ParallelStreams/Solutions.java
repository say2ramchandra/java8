package parallelstreams;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO PARALLEL STREAMS EXERCISES
 * ========================================
 * This file contains comprehensive solutions demonstrating parallel stream processing.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== PARALLEL STREAMS - EXERCISE SOLUTIONS ===\n");
        
        // Run all exercise solutions
        exercise1_CreatingParallelStreams();
        exercise2_BasicParallelOperations();
        exercise3_PerformanceComparison();
        exercise4_WhenToUseParallel();
        exercise5_ParallelCharacteristics();
        exercise6_ThreadSafetyIssues();
        exercise7_StatefulOperations();
        exercise8_SafeReduction();
        exercise9_PerformanceOptimization();
        exercise10_ComplexAggregations();
        exercise11_AvoidingPitfalls();
        exercise12_RealWorldProcessing();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Creating Parallel Streams
     * Solution demonstrates different ways to create parallel streams
     */
    private static void exercise1_CreatingParallelStreams() {
        System.out.println("EXERCISE 1: Creating Parallel Streams");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // 1. Sequential to parallel
        Stream<Integer> sequential = numbers.stream();
        Stream<Integer> parallel = sequential.parallel();
        System.out.println("Sequential stream converted to parallel");
        
        // 2. parallelStream() from collection
        Stream<Integer> parallelStream = numbers.parallelStream();
        System.out.println("Parallel stream from collection");
        
        // 3. Check if parallel
        boolean isParallel = numbers.parallelStream().isParallel();
        System.out.println("Is parallel? " + isParallel);
        
        boolean isSequential = numbers.stream().isParallel();
        System.out.println("Is sequential parallel? " + isSequential);
        
        // 4. Convert back to sequential
        Stream<Integer> backToSequential = numbers.parallelStream().sequential();
        System.out.println("Converted back to sequential");
        
        // 5. From array
        Integer[] array = {1, 2, 3, 4, 5};
        Stream<Integer> arrayParallel = Arrays.stream(array).parallel();
        System.out.println("Parallel stream from array");
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Basic Parallel Operations
     * Solution demonstrates basic parallel operations with performance measurement
     */
    private static void exercise2_BasicParallelOperations() {
        System.out.println("EXERCISE 2: Basic Parallel Operations");
        System.out.println("-".repeat(60));
        
        // Create large list
        List<Integer> numbers = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        // 1. Sequential sum
        long startSeq = System.currentTimeMillis();
        long sumSeq = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long timeSeq = System.currentTimeMillis() - startSeq;
        System.out.println("Sequential sum: " + sumSeq + " (Time: " + timeSeq + "ms)");
        
        // 2. Parallel sum
        long startPar = System.currentTimeMillis();
        long sumPar = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long timePar = System.currentTimeMillis() - startPar;
        System.out.println("Parallel sum: " + sumPar + " (Time: " + timePar + "ms)");
        
        // 3. Compare times
        if (timePar > 0) {
            System.out.printf("Speedup: %.2fx\n", (double) timeSeq / timePar);
        }
        
        // 4. Find max with parallel
        Optional<Integer> max = numbers.parallelStream()
                .max(Integer::compareTo);
        System.out.println("Max value: " + max.orElse(0));
        
        // 5. Count elements matching condition
        long count = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Even numbers count: " + count);
        
        System.out.println();
    }

    /**
     * EXERCISE 3: Performance Comparison
     * Solution compares sequential vs parallel for different operations
     */
    private static void exercise3_PerformanceComparison() {
        System.out.println("EXERCISE 3: Performance Comparison");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        // 1. Filtering
        System.out.println("1. Filtering (n > 5000000):");
        benchmarkOperation(
            "Sequential",
            () -> numbers.stream().filter(n -> n > 5_000_000).count()
        );
        benchmarkOperation(
            "Parallel",
            () -> numbers.parallelStream().filter(n -> n > 5_000_000).count()
        );
        
        // 2. Mapping
        System.out.println("\n2. Mapping (square all numbers):");
        benchmarkOperation(
            "Sequential",
            () -> numbers.stream().map(n -> n * n).count()
        );
        benchmarkOperation(
            "Parallel",
            () -> numbers.parallelStream().map(n -> n * n).count()
        );
        
        // 3. Reduction
        System.out.println("\n3. Reduction (sum):");
        benchmarkOperation(
            "Sequential",
            () -> numbers.stream().reduce(0, Integer::sum)
        );
        benchmarkOperation(
            "Parallel",
            () -> numbers.parallelStream().reduce(0, Integer::sum)
        );
        
        System.out.println();
    }

    /**
     * EXERCISE 4: When to Use Parallel Streams
     * Solution demonstrates appropriate use cases
     */
    private static void exercise4_WhenToUseParallel() {
        System.out.println("EXERCISE 4: When to Use Parallel Streams");
        System.out.println("-".repeat(60));
        
        // 1. Large dataset - GOOD for parallel
        System.out.println("1. Process 1 million numbers (squares):");
        List<Integer> large = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        long start = System.currentTimeMillis();
        large.stream().map(n -> n * n).count();
        System.out.println("  Sequential: " + (System.currentTimeMillis() - start) + "ms");
        
        start = System.currentTimeMillis();
        large.parallelStream().map(n -> n * n).count();
        System.out.println("  Parallel: " + (System.currentTimeMillis() - start) + "ms (Good choice for large data)");
        
        // 2. Small dataset - BAD for parallel
        System.out.println("\n2. Process 100 strings (uppercase):");
        List<String> small = IntStream.range(1, 101)
                .mapToObj(i -> "string" + i)
                .collect(Collectors.toList());
        
        start = System.currentTimeMillis();
        small.stream().map(String::toUpperCase).count();
        long seqTime = System.currentTimeMillis() - start;
        System.out.println("  Sequential: " + seqTime + "ms");
        
        start = System.currentTimeMillis();
        small.parallelStream().map(String::toUpperCase).count();
        long parTime = System.currentTimeMillis() - start;
        System.out.println("  Parallel: " + parTime + "ms (Overhead too high for small data)");
        
        // 3. CPU-intensive - GOOD for parallel
        System.out.println("\n3. CPU-intensive calculations:");
        List<Integer> cpuIntensive = IntStream.rangeClosed(1, 100_000)
                .boxed()
                .collect(Collectors.toList());
        
        start = System.currentTimeMillis();
        cpuIntensive.stream()
                .map(Solutions::expensiveCalculation)
                .count();
        System.out.println("  Sequential: " + (System.currentTimeMillis() - start) + "ms");
        
        start = System.currentTimeMillis();
        cpuIntensive.parallelStream()
                .map(Solutions::expensiveCalculation)
                .count();
        System.out.println("  Parallel: " + (System.currentTimeMillis() - start) + "ms (Good for CPU-bound)");
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Parallel Stream Characteristics
     * Solution demonstrates parallel stream behavior
     */
    private static void exercise5_ParallelCharacteristics() {
        System.out.println("EXERCISE 5: Parallel Stream Characteristics");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. Non-deterministic order
        System.out.println("1. Parallel forEach (order may vary):");
        numbers.parallelStream().forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 2. forEachOrdered maintains order
        System.out.println("\n2. Parallel forEachOrdered (order maintained):");
        numbers.parallelStream().forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();
        
        // 3. Available parallelism
        int parallelism = ForkJoinPool.commonPool().getParallelism();
        System.out.println("\n3. Common ForkJoinPool parallelism: " + parallelism);
        System.out.println("   (Usually equals number of CPU cores - 1)");
        
        // 4. Check active threads
        int activeThreads = ForkJoinPool.commonPool().getActiveThreadCount();
        System.out.println("   Active threads: " + activeThreads);
        
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 6: Thread Safety Issues
     * Solution demonstrates and fixes thread safety problems
     */
    private static void exercise6_ThreadSafetyIssues() {
        System.out.println("EXERCISE 6: Thread Safety Issues");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toList());
        
        // ❌ UNSAFE: Shared mutable state
        System.out.println("UNSAFE: Using shared ArrayList");
        List<Integer> unsafeList = new ArrayList<>();
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .forEach(unsafeList::add);  // Race condition!
        System.out.println("   Expected: 500, Got: " + unsafeList.size() + " (may vary due to race condition)");
        
        // ✓ SAFE: Use concurrent collection
        System.out.println("\nSAFE: Using ConcurrentLinkedQueue");
        Queue<Integer> safeQueue = new ConcurrentLinkedQueue<>();
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .forEach(safeQueue::add);
        System.out.println("   Expected: 500, Got: " + safeQueue.size() + " (always correct)");
        
        // ✓ SAFE: Use collect instead
        System.out.println("\nSAFE: Using collect()");
        List<Integer> safeList = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("   Expected: 500, Got: " + safeList.size() + " (always correct)");
        
        // ✓ SAFE: Use reduction
        System.out.println("\nSAFE: Using reduction");
        int sum = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .reduce(0, Integer::sum);
        System.out.println("   Sum of even numbers: " + sum);
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Stateful Operations
     * Solution demonstrates handling stateful operations
     */
    private static void exercise7_StatefulOperations() {
        System.out.println("EXERCISE 7: Stateful Operations");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());
        
        // 1. findFirst vs findAny
        System.out.println("1. findFirst() - guarantees first element:");
        Optional<Integer> first = numbers.parallelStream()
                .filter(n -> n > 10)
                .findFirst();
        System.out.println("   Found: " + first.orElse(0));
        
        System.out.println("\n2. findAny() - any matching element (faster in parallel):");
        Optional<Integer> any = numbers.parallelStream()
                .filter(n -> n > 10)
                .findAny();
        System.out.println("   Found: " + any.orElse(0) + " (may vary)");
        
        // 2. sorted() in parallel
        System.out.println("\n3. sorted() works but may lose parallel benefit:");
        List<Integer> sorted = numbers.parallelStream()
                .sorted()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("   First 5 sorted: " + sorted);
        
        // 3. limit() with ordered vs unordered
        System.out.println("\n4. limit() with ordered stream:");
        List<Integer> limited = numbers.parallelStream()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("   Limited (ordered): " + limited);
        
        System.out.println("\n5. limit() with unordered (potentially faster):");
        List<Integer> unorderedLimited = numbers.parallelStream()
                .unordered()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("   Limited (unordered): " + unorderedLimited + " (order may vary)");
        
        System.out.println();
    }

    /**
     * EXERCISE 8: Safe Reduction
     * Solution demonstrates proper reduction in parallel
     */
    private static void exercise8_SafeReduction() {
        System.out.println("EXERCISE 8: Safe Reduction Operations");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        
        // 1. reduce() with identity and combiner
        int sum = numbers.parallelStream()
                .reduce(
                    0,                    // identity
                    Integer::sum,         // accumulator
                    Integer::sum          // combiner for parallel
                );
        System.out.println("1. Sum using reduce: " + sum);
        
        // 2. collect() with concurrent collector
        Set<Integer> uniqueDigits = numbers.parallelStream()
                .map(n -> n % 10)
                .collect(Collectors.toSet());
        System.out.println("\n2. Unique last digits: " + uniqueDigits);
        
        // 3. groupingByConcurrent
        Map<Boolean, List<Integer>> evenOdd = numbers.parallelStream()
                .collect(Collectors.groupingByConcurrent(n -> n % 2 == 0));
        System.out.println("\n3. Grouped by even/odd:");
        System.out.println("   Even count: " + evenOdd.get(true).size());
        System.out.println("   Odd count: " + evenOdd.get(false).size());
        
        // 4. Statistics in parallel
        IntSummaryStatistics stats = numbers.parallelStream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("\n4. Statistics:");
        System.out.println("   Count: " + stats.getCount());
        System.out.println("   Sum: " + stats.getSum());
        System.out.println("   Average: " + stats.getAverage());
        System.out.println("   Min: " + stats.getMin());
        System.out.println("   Max: " + stats.getMax());
        
        System.out.println();
    }

    /**
     * EXERCISE 9: Performance Optimization
     * Solution demonstrates optimization techniques
     */
    private static void exercise9_PerformanceOptimization() {
        System.out.println("EXERCISE 9: Performance Optimization");
        System.out.println("-".repeat(60));
        
        int size = 1_000_000;
        
        // 1. ArrayList vs LinkedList
        System.out.println("1. ArrayList vs LinkedList:");
        
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) arrayList.add(i);
        
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) linkedList.add(i);
        
        long start = System.currentTimeMillis();
        arrayList.parallelStream().filter(n -> n % 2 == 0).count();
        System.out.println("   ArrayList parallel: " + (System.currentTimeMillis() - start) + "ms (Good)");
        
        start = System.currentTimeMillis();
        linkedList.parallelStream().filter(n -> n % 2 == 0).count();
        System.out.println("   LinkedList parallel: " + (System.currentTimeMillis() - start) + "ms (Poor splitting)");
        
        // 2. Primitive streams (avoid boxing)
        System.out.println("\n2. Boxing vs Primitive streams:");
        
        start = System.currentTimeMillis();
        IntStream.range(0, size)
                .parallel()
                .filter(n -> n % 2 == 0)
                .sum();
        System.out.println("   IntStream (primitive): " + (System.currentTimeMillis() - start) + "ms (Good)");
        
        start = System.currentTimeMillis();
        Stream.iterate(0, n -> n + 1)
                .limit(size)
                .parallel()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("   Stream<Integer> (boxed): " + (System.currentTimeMillis() - start) + "ms (Boxing overhead)");
        
        System.out.println();
    }

    // ==================== ADVANCED LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 10: Complex Aggregations
     * Solution demonstrates complex parallel aggregations
     */
    private static void exercise10_ComplexAggregations() {
        System.out.println("EXERCISE 10: Complex Aggregations");
        System.out.println("-".repeat(60));
        
        // Parallel word count
        List<String> texts = Arrays.asList(
            "the quick brown fox jumps over the lazy dog",
            "the lazy dog sleeps under the tree",
            "the quick fox runs fast"
        );
        
        Map<String, Long> wordCount = texts.parallelStream()
                .flatMap(text -> Arrays.stream(text.split(" ")))
                .collect(Collectors.groupingByConcurrent(
                    word -> word,
                    Collectors.counting()
                ));
        
        System.out.println("Word frequencies:");
        wordCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue()));
        
        System.out.println();
    }

    /**
     * EXERCISE 11: Avoiding Pitfalls
     * Solution shows common mistakes and fixes
     */
    private static void exercise11_AvoidingPitfalls() {
        System.out.println("EXERCISE 11: Avoiding Common Pitfalls");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        
        // Non-associative operation
        System.out.println("Non-associative operation (string concatenation):");
        String result = numbers.parallelStream()
                .map(String::valueOf)
                .reduce("", (a, b) -> a + b);  // May produce incorrect order
        System.out.println("   Result length: " + result.length() + " (order not guaranteed in parallel)");
        
        // Correct approach
        System.out.println("\nCorrect: Use joining() for strings:");
        String correct = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("   Result length: " + correct.length() + " (correct)");
        
        System.out.println();
    }

    /**
     * EXERCISE 12: Real-world Processing
     * Solution demonstrates practical parallel scenarios
     */
    private static void exercise12_RealWorldProcessing() {
        System.out.println("EXERCISE 12: Real-world Parallel Processing");
        System.out.println("-".repeat(60));
        
        // Simulate log file analysis
        List<LogEntry> logs = generateLogEntries(10000);
        
        // Analyze in parallel
        Map<String, Long> errorsByType = logs.parallelStream()
                .filter(log -> log.level.equals("ERROR"))
                .collect(Collectors.groupingByConcurrent(
                    log -> log.message,
                    Collectors.counting()
                ));
        
        System.out.println("Error analysis (parallel processing):");
        System.out.println("  Total unique error types: " + errorsByType.size());
        System.out.println("  Top 3 errors:");
        errorsByType.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(e -> System.out.println("    " + e.getKey() + ": " + e.getValue()));
        
        // Calculate statistics
        DoubleSummaryStatistics stats = logs.parallelStream()
                .collect(Collectors.summarizingDouble(log -> log.responseTime));
        
        System.out.println("\nResponse time statistics:");
        System.out.printf("  Average: %.2fms\n", stats.getAverage());
        System.out.printf("  Max: %.2fms\n", stats.getMax());
        
        System.out.println();
    }

    // ==================== HELPER METHODS ====================

    private static void benchmarkOperation(String name, Supplier<?> operation) {
        long start = System.currentTimeMillis();
        operation.get();
        long time = System.currentTimeMillis() - start;
        System.out.printf("   %s: %dms\n", name, time);
    }

    private static int expensiveCalculation(int n) {
        // Simulate expensive computation
        double result = 0;
        for (int i = 0; i < 1000; i++) {
            result += Math.sqrt(n) * Math.sin(n);
        }
        return (int) result;
    }

    private static List<LogEntry> generateLogEntries(int count) {
        String[] levels = {"INFO", "WARN", "ERROR"};
        String[] messages = {"Timeout", "Connection failed", "Invalid input", "Success"};
        Random random = new Random();
        
        return IntStream.range(0, count)
                .mapToObj(i -> new LogEntry(
                    levels[random.nextInt(levels.length)],
                    messages[random.nextInt(messages.length)],
                    random.nextDouble() * 1000
                ))
                .collect(Collectors.toList());
    }

    static class LogEntry {
        String level;
        String message;
        double responseTime;
        
        LogEntry(String level, String message, double responseTime) {
            this.level = level;
            this.message = message;
            this.responseTime = responseTime;
        }
    }
}
