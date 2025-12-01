package functionalinterfacesdeep;

import java.util.function.*;

/**
 * PREDICATE, FUNCTION, CONSUMER, SUPPLIER - DEEP DIVE
 * ====================================================
 * 
 * Deep exploration of the core functional interfaces in java.util.function
 * 
 * @author Java 8 Mastery Course
 */
public class FunctionalInterfacesDeepDive {
    
    public static void main(String[] args) {
        System.out.println("=== FUNCTIONAL INTERFACES DEEP DIVE ===\n");
        
        // Predicate - boolean test
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> combined = isPositive.and(isEven);
        System.out.println("4 is positive AND even: " + combined.test(4));
        
        // Function - transformation
        Function<String, Integer> length = String::length;
        Function<Integer, Integer> square = n -> n * n;
        Function<String, Integer> lengthSquared = length.andThen(square);
        System.out.println("Length of 'Java' squared: " + lengthSquared.apply("Java"));
        
        // Consumer - side effects
        Consumer<String> printer = System.out::println;
        Consumer<String> logger = s -> System.out.println("[LOG] " + s);
        Consumer<String> both = printer.andThen(logger);
        both.accept("Message");
        
        // Supplier - provide values
        Supplier<Double> random = Math::random;
        System.out.println("\nRandom: " + random.get());
        
        // BiPredicate
        BiPredicate<String, Integer> longerThan = (s, len) -> s.length() > len;
        System.out.println("\n'Hello' longer than 3: " + longerThan.test("Hello", 3));
        
        // BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3: " + add.apply(5, 3));
        
        // BiConsumer
        BiConsumer<String, Integer> printWithCount = (s, count) -> {
            for (int i = 0; i < count; i++) {
                System.out.print(s + " ");
            }
            System.out.println();
        };
        printWithCount.accept("Java", 3);
        
        // UnaryOperator
        UnaryOperator<String> toUpper = String::toUpperCase;
        System.out.println("\nUpper: " + toUpper.apply("hello"));
        
        // BinaryOperator
        BinaryOperator<Integer> max = Math::max;
        System.out.println("Max of 10, 20: " + max.apply(10, 20));
    }
}
