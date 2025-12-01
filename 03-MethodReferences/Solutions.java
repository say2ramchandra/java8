package methodreferences;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * SOLUTIONS - Method References Exercises
 */
public class Solutions {
    
    public static void main(String[] args) {
        exercise1_ConvertLambdaToMethodReference();
        exercise2_StaticMethodReferences();
        exercise5_SortingWithMethodReferences();
    }
    
    private static void exercise1_ConvertLambdaToMethodReference() {
        System.out.println("EXERCISE 1: Convert Lambda to Method Reference");
        System.out.println("-".repeat(60));
        
        // 1. str -> System.out.println(str) becomes System.out::println
        Consumer<String> printer = System.out::println;
        printer.accept("Hello");
        
        // 2. x -> Math.abs(x) becomes Math::abs
        Function<Integer, Integer> absolute = Math::abs;
        System.out.println("Absolute of -5: " + absolute.apply(-5));
        
        // 3. s -> s.length() becomes String::length
        Function<String, Integer> length = String::length;
        System.out.println("Length of 'Java': " + length.apply("Java"));
        
        // 4. x -> Integer.parseInt(x) becomes Integer::parseInt
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("Parsed '123': " + parser.apply("123"));
        
        // 5. () -> new ArrayList<>() becomes ArrayList::new
        Supplier<List<String>> listCreator = ArrayList::new;
        System.out.println("Created list: " + listCreator.get());
        
        System.out.println();
    }
    
    private static void exercise2_StaticMethodReferences() {
        System.out.println("EXERCISE 2: Static Method References");
        System.out.println("-".repeat(60));
        
        // 1. Find max using Math.max
        BinaryOperator<Integer> max = Math::max;
        System.out.println("Max of 10, 20: " + max.apply(10, 20));
        
        // 2. Parse double
        Function<String, Double> parseDouble = Double::parseDouble;
        System.out.println("Parsed '3.14': " + parseDouble.apply("3.14"));
        
        // 3. Compare strings ignoring case
        BiFunction<String, String, Integer> compareIgnoreCase = String::compareToIgnoreCase;
        System.out.println("Compare 'Hello' and 'HELLO': " + compareIgnoreCase.apply("Hello", "HELLO"));
        
        // 4. Current time
        Supplier<Long> currentTime = System::currentTimeMillis;
        System.out.println("Current time: " + currentTime.get());
        
        // 5. Power calculation
        BiFunction<Double, Double, Double> power = Math::pow;
        System.out.println("2^3: " + power.apply(2.0, 3.0));
        
        System.out.println();
    }
    
    private static void exercise5_SortingWithMethodReferences() {
        System.out.println("EXERCISE 5: Sorting with Method References");
        System.out.println("-".repeat(60));
        
        // 1. Sort strings alphabetically
        List<String> strings = new ArrayList<>(Arrays.asList("Zebra", "Apple", "Mango"));
        strings.sort(String::compareTo);
        System.out.println("Sorted strings: " + strings);
        
        // 2. Sort integers
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1));
        numbers.sort(Integer::compareTo);
        System.out.println("Sorted numbers: " + numbers);
        
        // 3. Sort Person by name
        List<PersonEx> people = Arrays.asList(
            new PersonEx("Charlie", 30),
            new PersonEx("Alice", 25),
            new PersonEx("Bob", 35)
        );
        people.sort(Comparator.comparing(PersonEx::getName));
        System.out.println("Sorted by name: " + people);
        
        System.out.println();
    }
    
    static class PersonEx {
        String name;
        int age;
        PersonEx(String name, int age) {
            this.name = name;
            this.age = age;
        }
        String getName() { return name; }
        public String toString() { return name; }
    }
}
