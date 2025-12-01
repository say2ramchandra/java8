package optional;

import java.util.*;

/**
 * Solutions to Optional Class Exercises
 */
public class Solutions {
    public static void main(String[] args) {
        System.out.println("=== OPTIONAL CLASS SOLUTIONS ===\n");
        basicExercises();
        intermediateExercises();
    }
    
    private static void basicExercises() {
        // 1. Create Optionals
        Optional<String> withValue = Optional.of("Hello");
        Optional<String> withNull = Optional.ofNullable(null);
        Optional<String> empty = Optional.empty();
        System.out.println("1. Created: " + withValue + ", " + withNull + ", " + empty);
        
        // 2. Check presence
        System.out.println("2. isPresent: " + withValue.isPresent() + ", " + empty.isPresent());
        
        // 3. orElse
        String value = empty.orElse("Default");
        System.out.println("3. orElse: " + value);
        
        // 4. ifPresent
        System.out.print("4. ifPresent: ");
        withValue.ifPresent(v -> System.out.println(v));
        
        // 5. filter
        Optional<String> filtered = withValue.filter(s -> s.length() > 3);
        System.out.println("5. Filtered: " + filtered);
        System.out.println();
    }
    
    private static void intermediateExercises() {
        // 6. Chain map
        Optional<String> result = Optional.of("  hello  ")
                .map(String::trim)
                .map(String::toUpperCase);
        System.out.println("6. Chained: " + result.get());
        
        // 7. flatMap
        Optional<Integer> parsed = Optional.of("123")
                .flatMap(Solutions::parseInt);
        System.out.println("7. Parsed: " + parsed.orElse(-1));
        
        // 10. Transform String to Integer
        Optional<Integer> length = Optional.of("Java")
                .map(String::length);
        System.out.println("10. Length: " + length.get());
    }
    
    private static Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
