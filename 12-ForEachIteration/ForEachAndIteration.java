package foreachiteration;

import java.util.*;

/**
 * FOREACH AND ITERATION ENHANCEMENTS
 * ===================================
 * 
 * Java 8 introduces enhanced iteration methods on Iterable and Map.
 * 
 * KEY METHODS:
 * - forEach(Consumer) - Iterate with lambda
 * - forEachOrdered() - Ordered iteration in streams
 * - Map.forEach(BiConsumer) - Iterate map entries
 * - replaceAll(UnaryOperator) - Transform all elements
 * - removeIf(Predicate) - Conditional removal
 * 
 * @author Java 8 Mastery Course
 */
public class ForEachAndIteration {
    
    public static void main(String[] args) {
        System.out.println("=== FOREACH AND ITERATION ENHANCEMENTS ===\n");
        
        // List forEach
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println("forEach:");
        names.forEach(name -> System.out.println("  " + name));
        
        // Method reference
        System.out.println("\nMethod reference:");
        names.forEach(System.out::println);
        
        // Map forEach
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        
        System.out.println("\nMap forEach:");
        scores.forEach((name, score) -> 
            System.out.println(name + ": " + score));
        
        // replaceAll
        List<String> words = new ArrayList<>(Arrays.asList("hello", "world"));
        words.replaceAll(String::toUpperCase);
        System.out.println("\nAfter replaceAll: " + words);
        
        // removeIf
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("After removeIf (even): " + numbers);
        
        // Indexed iteration
        System.out.println("\nIndexed iteration:");
        List<String> items = Arrays.asList("A", "B", "C");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ": " + items.get(i));
        }
        
        // Stream forEach vs forEachOrdered
        System.out.println("\nStream forEach:");
        Arrays.asList(1, 2, 3, 4, 5)
            .parallelStream()
            .forEach(System.out::print);  // May be unordered
        
        System.out.println("\n\nStream forEachOrdered:");
        Arrays.asList(1, 2, 3, 4, 5)
            .parallelStream()
            .forEachOrdered(System.out::print);  // Maintains order
        
        System.out.println();
    }
}
