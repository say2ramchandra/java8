package foreachiteration;

import java.util.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO FOREACH AND ITERATION EXERCISES
 * ============================================
 * Comprehensive solutions for modern iteration patterns.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== FOREACH AND ITERATION - SOLUTIONS ===\n");
        
        exercise1_BasicForEach();
        exercise2_MapForEach();
        exercise3_ReplaceAll();
        exercise4_RemoveIf();
        exercise5_StreamForEach();
        exercise6_AdvancedPatterns();
        exercise7_Performance();
    }

    /**
     * EXERCISE 1: Basic forEach
     */
    private static void exercise1_BasicForEach() {
        System.out.println("EXERCISE 1: Basic forEach");
        System.out.println("-".repeat(60));
        
        // 1. forEach on List
        List<String> list = Arrays.asList("Java", "Python", "C++");
        System.out.println("List forEach:");
        list.forEach(item -> System.out.println("  " + item));
        
        // 2. forEach on Set
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\nSet forEach:");
        set.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 3. Method reference
        System.out.println("\nMethod reference:");
        list.forEach(System.out::println);
        
        // 4. With lambda
        System.out.println("\nWith transformation:");
        list.forEach(item -> System.out.println("  Language: " + item.toUpperCase()));
        
        // 5. Indexed forEach (manual)
        System.out.println("\nIndexed:");
        IntStream.range(0, list.size())
                .forEach(i -> System.out.println("  [" + i + "] " + list.get(i)));
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Map forEach
     */
    private static void exercise2_MapForEach() {
        System.out.println("EXERCISE 2: Map forEach");
        System.out.println("-".repeat(60));
        
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Alice", 95);
        grades.put("Bob", 87);
        grades.put("Charlie", 92);
        
        // 1. forEach on entries
        System.out.println("Student grades:");
        grades.forEach((name, grade) -> 
            System.out.println("  " + name + ": " + grade));
        
        // 2. Filter and process
        System.out.println("\nHigh achievers (>= 90):");
        grades.forEach((name, grade) -> {
            if (grade >= 90) {
                System.out.println("  " + name + ": " + grade);
            }
        });
        
        // 3. Modify during iteration (safe with forEach)
        System.out.println("\nIncreasing all grades by 5:");
        grades.replaceAll((name, grade) -> grade + 5);
        grades.forEach((name, grade) -> 
            System.out.println("  " + name + ": " + grade));
        
        System.out.println();
    }

    /**
     * EXERCISE 3: replaceAll
     */
    private static void exercise3_ReplaceAll() {
        System.out.println("EXERCISE 3: replaceAll");
        System.out.println("-".repeat(60));
        
        // 1. Transform all elements
        List<String> words = new ArrayList<>(Arrays.asList("hello", "world", "java"));
        System.out.println("Original: " + words);
        
        words.replaceAll(String::toUpperCase);
        System.out.println("After replaceAll: " + words);
        
        // 2. Conditional replacement
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\nOriginal numbers: " + numbers);
        
        numbers.replaceAll(n -> n % 2 == 0 ? n * 2 : n);
        System.out.println("After replacing even (doubled): " + numbers);
        
        // 3. Complex transformation
        List<String> names = new ArrayList<>(Arrays.asList("alice", "bob", "charlie"));
        names.replaceAll(name -> name.substring(0, 1).toUpperCase() + name.substring(1));
        System.out.println("\nCapitalized: " + names);
        
        System.out.println();
    }

    /**
     * EXERCISE 4: removeIf
     */
    private static void exercise4_RemoveIf() {
        System.out.println("EXERCISE 4: removeIf");
        System.out.println("-".repeat(60));
        
        // 1. Remove matching elements
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original: " + numbers);
        
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("After removeIf (even): " + numbers);
        
        // 2. Multiple conditions
        List<String> items = new ArrayList<>(Arrays.asList("apple", "banana", "a", "avocado", "cherry"));
        System.out.println("\nOriginal items: " + items);
        
        items.removeIf(s -> s.length() < 3);
        System.out.println("After removeIf (length < 3): " + items);
        
        // 3. removeIf vs filter
        List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        data.removeIf(n -> n > 3);  // Modifies original
        System.out.println("\nremoveIf modifies original: " + data);
        
        List<Integer> data2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> filtered = data2.stream()
                .filter(n -> n <= 3)
                .collect(Collectors.toList());  // Creates new list
        System.out.println("filter creates new: " + filtered);
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Stream forEach
     */
    private static void exercise5_StreamForEach() {
        System.out.println("EXERCISE 5: Stream forEach");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. Sequential forEach
        System.out.println("Sequential forEach:");
        numbers.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 2 Parallel forEach (order may vary)
        System.out.println("\nParallel forEach (order may vary):");
        numbers.parallelStream().forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 3. forEachOrdered (maintains order)
        System.out.println("\nParallel forEachOrdered (order maintained):");
        numbers.parallelStream().forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();
        
        // 4. Early termination alternatives
        System.out.println("\nEarly termination with findFirst:");
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
        first.ifPresent(n -> System.out.println("  First number > 5: " + n));
        
        System.out.println();
    }

    /**
     * EXERCISE 6: Advanced Patterns
     */
    private static void exercise6_AdvancedPatterns() {
        System.out.println("EXERCISE 6: Advanced Patterns");
        System.out.println("-".repeat(60));
        
        // 1. Nested forEach
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        
        System.out.println("Nested forEach:");
        nested.forEach(innerList -> {
            innerList.forEach(n -> System.out.print(n + " "));
            System.out.println();
        });
        
        // 2. forEach with side effects (collect results)
        List<Integer> squares = new ArrayList<>();
        Arrays.asList(1, 2, 3, 4, 5).forEach(n -> squares.add(n * n));
        System.out.println("\nCollected squares: " + squares);
        
        // 3. Exception handling
        System.out.println("\nException handling:");
        Arrays.asList("1", "2", "abc", "4").forEach(s -> {
            try {
                int number = Integer.parseInt(s);
                System.out.println("  Parsed: " + number);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid: " + s);
            }
        });
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Performance
     */
    private static void exercise7_Performance() {
        System.out.println("EXERCISE 7: Performance Comparison");
        System.out.println("-".repeat(60));
        
        List<Integer> largeList = IntStream.range(0, 1_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        // Traditional for loop
        long start = System.currentTimeMillis();
        int sum1 = 0;
        for (int i = 0; i < largeList.size(); i++) {
            sum1 += largeList.get(i);
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Traditional for: " + time1 + "ms");
        
        // Enhanced for
        start = System.currentTimeMillis();
        int sum2 = 0;
        for (Integer n : largeList) {
            sum2 += n;
        }
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Enhanced for: " + time2 + "ms");
        
        // forEach
        start = System.currentTimeMillis();
        final int[] sum3 = {0};
        largeList.forEach(n -> sum3[0] += n);
        long time3 = System.currentTimeMillis() - start;
        System.out.println("forEach: " + time3 + "ms");
        
        // Stream (best for this operation)
        start = System.currentTimeMillis();
        int sum4 = largeList.stream().mapToInt(Integer::intValue).sum();
        long time4 = System.currentTimeMillis() - start;
        System.out.println("Stream sum: " + time4 + "ms");
        
        System.out.println();
    }
}
