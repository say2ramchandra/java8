package functionalinterfaces;

import java.util.*;
import java.util.function.*;

/**
 * FUNCTIONAL INTERFACES IN JAVA 8
 * ================================
 * 
 * WHAT IS A FUNCTIONAL INTERFACE?
 * -------------------------------
 * A functional interface is an interface that contains exactly ONE abstract method.
 * It can have multiple default or static methods, but only one abstract method.
 * This single abstract method represents the contract that lambda expressions implement.
 * 
 * WHY ARE THEY IMPORTANT?
 * -----------------------
 * - Enable lambda expressions and method references
 * - Foundation of functional programming in Java
 * - Provide type safety for lambda expressions
 * - Make code more readable and maintainable
 * 
 * @FunctionalInterface ANNOTATION:
 * --------------------------------
 * - Optional but recommended annotation
 * - Compiler enforces that interface has exactly one abstract method
 * - Generates compilation error if interface doesn't meet requirements
 * - Serves as documentation for developers
 * 
 * BUILT-IN FUNCTIONAL INTERFACES (java.util.function package):
 * ------------------------------------------------------------
 * 1. Predicate<T>      - Tests a condition, returns boolean
 * 2. Function<T, R>    - Transforms input to output
 * 3. Consumer<T>       - Accepts input, returns nothing (void)
 * 4. Supplier<T>       - Provides output, takes no input
 * 5. UnaryOperator<T>  - Special Function where input and output are same type
 * 6. BinaryOperator<T> - Takes two inputs of same type, returns same type
 * 7. BiPredicate<T, U> - Tests condition with two inputs
 * 8. BiFunction<T, U, R> - Transforms two inputs to output
 * 9. BiConsumer<T, U>  - Accepts two inputs, returns nothing
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class FunctionalInterfaces {

    public static void main(String[] args) {
        System.out.println("=== PART 1: UNDERSTANDING FUNCTIONAL INTERFACES ===\n");
        demonstratePredicates();
        demonstrateFunctions();
        demonstrateConsumers();
        demonstrateSuppliers();
        demonstrateOperators();
        demonstrateBiFunctionalInterfaces();
        
        System.out.println("\n=== PART 2: REAL-WORLD SCENARIOS ===\n");
        realWorldScenario1_InputValidation();
        realWorldScenario2_DataPipeline();
        realWorldScenario3_ConfigurationManager();
    }

    /**
     * EXAMPLE 1: Predicate<T> Interface
     * ---------------------------------
     * Represents a boolean-valued function of one argument
     * Method: boolean test(T t)
     */
    private static void demonstratePredicates() {
        System.out.println("1. PREDICATE<T> INTERFACE:");
        System.out.println("-".repeat(60));
        
        // Basic predicate - check if number is positive
        Predicate<Integer> isPositive = num -> num > 0;
        System.out.println("Is 5 positive? " + isPositive.test(5));
        System.out.println("Is -3 positive? " + isPositive.test(-3));
        
        // String predicate - check if string is empty
        Predicate<String> isEmpty = str -> str.isEmpty();
        System.out.println("Is '' empty? " + isEmpty.test(""));
        System.out.println("Is 'Hello' empty? " + isEmpty.test("Hello"));
        
        // Combining predicates with AND
        Predicate<Integer> isEven = num -> num % 2 == 0;
        Predicate<Integer> isGreaterThan10 = num -> num > 10;
        Predicate<Integer> evenAndGreaterThan10 = isEven.and(isGreaterThan10);
        System.out.println("Is 12 even AND > 10? " + evenAndGreaterThan10.test(12));
        System.out.println("Is 8 even AND > 10? " + evenAndGreaterThan10.test(8));
        
        // Combining predicates with OR
        Predicate<String> startsWithA = str -> str.startsWith("A");
        Predicate<String> endsWithZ = str -> str.endsWith("Z");
        Predicate<String> startsWithAorEndsWithZ = startsWithA.or(endsWithZ);
        System.out.println("Apple starts with A OR ends with Z? " + 
                          startsWithAorEndsWithZ.test("Apple"));
        
        // Negating predicates
        Predicate<Integer> isNotPositive = isPositive.negate();
        System.out.println("Is -5 NOT positive? " + isNotPositive.test(-5));
        
        System.out.println();
    }

    /**
     * EXAMPLE 2: Function<T, R> Interface
     * -----------------------------------
     * Represents a function that accepts one argument and produces a result
     * Method: R apply(T t)
     */
    private static void demonstrateFunctions() {
        System.out.println("2. FUNCTION<T, R> INTERFACE:");
        System.out.println("-".repeat(60));
        
        // Convert string to uppercase
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        System.out.println("Uppercase: " + toUpperCase.apply("hello"));
        
        // Get string length
        Function<String, Integer> getLength = str -> str.length();
        System.out.println("Length of 'Lambda': " + getLength.apply("Lambda"));
        
        // Parse string to integer
        Function<String, Integer> parseInteger = str -> Integer.parseInt(str);
        System.out.println("Parsed '123': " + parseInteger.apply("123"));
        
        // Function composition with andThen
        Function<Integer, Integer> multiplyBy2 = num -> num * 2;
        Function<Integer, Integer> add10 = num -> num + 10;
        Function<Integer, Integer> multiplyThenAdd = multiplyBy2.andThen(add10);
        System.out.println("5 * 2 + 10 = " + multiplyThenAdd.apply(5)); // (5*2) + 10 = 20
        
        // Function composition with compose
        Function<Integer, Integer> addThenMultiply = multiplyBy2.compose(add10);
        System.out.println("(5 + 10) * 2 = " + addThenMultiply.apply(5)); // (5+10) * 2 = 30
        
        // Chain multiple functions
        Function<String, String> trimAndUpper = ((Function<String, String>) String::trim)
                .andThen(String::toUpperCase);
        System.out.println("Trim and upper: '" + trimAndUpper.apply("  hello  ") + "'");
        
        System.out.println();
    }

    /**
     * EXAMPLE 3: Consumer<T> Interface
     * --------------------------------
     * Represents an operation that accepts a single input and returns no result
     * Method: void accept(T t)
     */
    private static void demonstrateConsumers() {
        System.out.println("3. CONSUMER<T> INTERFACE:");
        System.out.println("-".repeat(60));
        
        // Simple consumer - print to console
        Consumer<String> printer = message -> System.out.println("Message: " + message);
        printer.accept("Hello, Consumer!");
        
        // Consumer that modifies a list
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        Consumer<List<String>> addName = list -> list.add("Diana");
        System.out.println("Before: " + names);
        addName.accept(names);
        System.out.println("After: " + names);
        
        // Chaining consumers with andThen
        Consumer<String> printUpperCase = str -> System.out.println("Upper: " + str.toUpperCase());
        Consumer<String> printLength = str -> System.out.println("Length: " + str.length());
        Consumer<String> printBoth = printUpperCase.andThen(printLength);
        printBoth.accept("Lambda");
        
        // Consumer with complex logic
        Consumer<Integer> analyzeNumber = num -> {
            System.out.println("Analyzing: " + num);
            System.out.println("  Even: " + (num % 2 == 0));
            System.out.println("  Positive: " + (num > 0));
            System.out.println("  Prime: " + isPrime(num));
        };
        analyzeNumber.accept(17);
        
        System.out.println();
    }

    /**
     * EXAMPLE 4: Supplier<T> Interface
     * --------------------------------
     * Represents a supplier of results (produces output without input)
     * Method: T get()
     */
    private static void demonstrateSuppliers() {
        System.out.println("4. SUPPLIER<T> INTERFACE:");
        System.out.println("-".repeat(60));
        
        // Supply current timestamp
        Supplier<Long> timestampSupplier = () -> System.currentTimeMillis();
        System.out.println("Current timestamp: " + timestampSupplier.get());
        
        // Supply random number
        Supplier<Integer> randomSupplier = () -> (int) (Math.random() * 100);
        System.out.println("Random number: " + randomSupplier.get());
        
        // Supply default value
        Supplier<String> defaultName = () -> "Unknown User";
        System.out.println("Default name: " + defaultName.get());
        
        // Supplier with complex object creation
        Supplier<List<String>> listSupplier = () -> {
            List<String> list = new ArrayList<>();
            list.add("Item 1");
            list.add("Item 2");
            list.add("Item 3");
            return list;
        };
        System.out.println("Supplied list: " + listSupplier.get());
        
        // Lazy evaluation example
        Supplier<String> expensiveOperation = () -> {
            System.out.println("  Performing expensive operation...");
            return "Result of expensive operation";
        };
        System.out.println("Supplier created (not yet executed)");
        System.out.println("Now calling get(): " + expensiveOperation.get());
        
        System.out.println();
    }

    /**
     * EXAMPLE 5: Operator Interfaces
     * ------------------------------
     * UnaryOperator<T> and BinaryOperator<T>
     */
    private static void demonstrateOperators() {
        System.out.println("5. OPERATOR INTERFACES:");
        System.out.println("-".repeat(60));
        
        // UnaryOperator - input and output are same type
        UnaryOperator<Integer> square = num -> num * num;
        System.out.println("Square of 5: " + square.apply(5));
        
        UnaryOperator<String> addExclamation = str -> str + "!";
        System.out.println("Add exclamation: " + addExclamation.apply("Hello"));
        
        // BinaryOperator - two inputs and output are same type
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.apply(5, 3));
        
        BinaryOperator<String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println("Concat: " + concat.apply("Hello", "World"));
        
        // BinaryOperator with minBy and maxBy
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compareTo);
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compareTo);
        System.out.println("Max of 10 and 20: " + max.apply(10, 20));
        System.out.println("Min of 10 and 20: " + min.apply(10, 20));
        
        System.out.println();
    }

    /**
     * EXAMPLE 6: Bi-Functional Interfaces
     * -----------------------------------
     * BiPredicate, BiFunction, BiConsumer
     */
    private static void demonstrateBiFunctionalInterfaces() {
        System.out.println("6. BI-FUNCTIONAL INTERFACES:");
        System.out.println("-".repeat(60));
        
        // BiPredicate<T, U> - test with two arguments
        BiPredicate<String, Integer> isLongerThan = (str, length) -> str.length() > length;
        System.out.println("Is 'Lambda' longer than 5? " + isLongerThan.test("Lambda", 5));
        
        BiPredicate<Integer, Integer> isSumEven = (a, b) -> (a + b) % 2 == 0;
        System.out.println("Is sum of 3 and 5 even? " + isSumEven.test(3, 5));
        
        // BiFunction<T, U, R> - two inputs, one output
        BiFunction<String, Integer, String> repeat = (str, times) -> str.repeat(times);
        System.out.println("Repeat 'Hi' 3 times: " + repeat.apply("Hi", 3));
        
        BiFunction<Integer, Integer, Double> average = (a, b) -> (a + b) / 2.0;
        System.out.println("Average of 10 and 15: " + average.apply(10, 15));
        
        // BiConsumer<T, U> - two inputs, no output
        BiConsumer<String, Integer> printMultiple = (str, count) -> {
            System.out.print(str + " printed " + count + " times: ");
            for (int i = 0; i < count; i++) {
                System.out.print(str + " ");
            }
            System.out.println();
        };
        printMultiple.accept("Java", 3);
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 1: Input Validation Framework
     * -------------------------------------------------
     * Using functional interfaces to build a flexible validation system
     */
    private static void realWorldScenario1_InputValidation() {
        System.out.println("REAL-WORLD SCENARIO 1: User Registration Validation");
        System.out.println("-".repeat(60));
        
        // Define validation rules using Predicates
        Predicate<String> emailValidator = email -> 
            email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        
        Predicate<String> passwordValidator = password -> 
            password != null && password.length() >= 8 && 
            password.matches(".*[A-Z].*") && // Has uppercase
            password.matches(".*[a-z].*") && // Has lowercase
            password.matches(".*\\d.*");      // Has digit
        
        Predicate<Integer> ageValidator = age -> 
            age != null && age >= 18 && age <= 120;
        
        Predicate<String> usernameValidator = username ->
            username != null && username.length() >= 3 && 
            username.matches("^[a-zA-Z0-9_]+$");
        
        // Test user data
        System.out.println("\nValidating User 1:");
        validateUser("john_doe", "john@email.com", "Pass123!", 25,
                    usernameValidator, emailValidator, passwordValidator, ageValidator);
        
        System.out.println("\nValidating User 2 (invalid data):");
        validateUser("jd", "invalid-email", "weak", 15,
                    usernameValidator, emailValidator, passwordValidator, ageValidator);
        
        System.out.println();
    }

    /**
     * Helper method for user validation
     */
    private static void validateUser(String username, String email, String password, Integer age,
                                     Predicate<String> usernameVal, Predicate<String> emailVal,
                                     Predicate<String> passwordVal, Predicate<Integer> ageVal) {
        
        System.out.println("Username: " + username + " - " + 
                          (usernameVal.test(username) ? "✓ Valid" : "✗ Invalid"));
        System.out.println("Email: " + email + " - " + 
                          (emailVal.test(email) ? "✓ Valid" : "✗ Invalid"));
        System.out.println("Password: " + password + " - " + 
                          (passwordVal.test(password) ? "✓ Valid" : "✗ Invalid"));
        System.out.println("Age: " + age + " - " + 
                          (ageVal.test(age) ? "✓ Valid" : "✗ Invalid"));
    }

    /**
     * REAL-WORLD SCENARIO 2: Data Transformation Pipeline
     * ---------------------------------------------------
     * Using Function interface to create data processing pipeline
     */
    private static void realWorldScenario2_DataPipeline() {
        System.out.println("REAL-WORLD SCENARIO 2: Sales Data Processing");
        System.out.println("-".repeat(60));
        
        // Define transformation functions
        Function<String, String> trimWhitespace = String::trim;
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String[]> splitBySemicolon = str -> str.split(";");
        Function<String[], SalesRecord> parseRecord = parts -> 
            new SalesRecord(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
        Function<SalesRecord, Double> calculateRevenue = record -> 
            record.price * record.quantity;
        
        // Sample raw data
        List<String> rawData = Arrays.asList(
            "  Laptop;899.99;5  ",
            "Mouse;25.50;15",
            "  KEYBOARD;75.00;8  ",
            "Monitor;299.99;3"
        );
        
        System.out.println("\nProcessing sales data:");
        rawData.forEach(raw -> {
            // Create processing pipeline
            Function<String, SalesRecord> parsePipeline = 
                trimWhitespace.andThen(splitBySemicolon).andThen(parseRecord);
            
            SalesRecord record = parsePipeline.apply(raw);
            double revenue = calculateRevenue.apply(record);
            
            System.out.printf("  %s: %d units × $%.2f = $%.2f\n", 
                            record.product, record.quantity, record.price, revenue);
        });
        
        // Calculate total revenue
        double totalRevenue = rawData.stream()
            .map(trimWhitespace.andThen(splitBySemicolon).andThen(parseRecord))
            .mapToDouble(calculateRevenue::apply)
            .sum();
        
        System.out.printf("\nTotal Revenue: $%.2f\n", totalRevenue);
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 3: Configuration Manager
     * --------------------------------------------
     * Using Supplier for lazy initialization and default values
     */
    private static void realWorldScenario3_ConfigurationManager() {
        System.out.println("REAL-WORLD SCENARIO 3: Application Configuration Manager");
        System.out.println("-".repeat(60));
        
        // Configuration with default suppliers
        ConfigurationManager config = new ConfigurationManager();
        
        // Set suppliers for configuration values
        config.setDefault("app.name", () -> "MyApplication");
        config.setDefault("app.version", () -> "1.0.0");
        config.setDefault("database.host", () -> "localhost");
        config.setDefault("database.port", () -> "5432");
        config.setDefault("max.connections", () -> "10");
        config.setDefault("timeout.seconds", () -> "30");
        
        // Get configuration values (lazy evaluation)
        System.out.println("\nConfiguration Values:");
        System.out.println("  App Name: " + config.get("app.name"));
        System.out.println("  Version: " + config.get("app.version"));
        System.out.println("  DB Host: " + config.get("database.host"));
        System.out.println("  DB Port: " + config.get("database.port"));
        
        // Override with custom value
        config.setValue("database.host", "production-db.example.com");
        System.out.println("\nAfter override:");
        System.out.println("  DB Host: " + config.get("database.host"));
        
        // Accessing non-existent key returns null
        System.out.println("  Non-existent: " + config.get("non.existent.key"));
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================

    /**
     * Helper method to check if number is prime
     */
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    /**
     * Sales Record class for data pipeline scenario
     */
    static class SalesRecord {
        String product;
        double price;
        int quantity;

        SalesRecord(String product, double price, int quantity) {
            this.product = product;
            this.price = price;
            this.quantity = quantity;
        }
    }

    /**
     * Configuration Manager using Supplier for lazy initialization
     */
    static class ConfigurationManager {
        private Map<String, Supplier<String>> defaults = new HashMap<>();
        private Map<String, String> values = new HashMap<>();

        void setDefault(String key, Supplier<String> supplier) {
            defaults.put(key, supplier);
        }

        void setValue(String key, String value) {
            values.put(key, value);
        }

        String get(String key) {
            // First check if value is explicitly set
            if (values.containsKey(key)) {
                return values.get(key);
            }
            // Then check if default supplier exists
            if (defaults.containsKey(key)) {
                return defaults.get(key).get(); // Lazy evaluation here!
            }
            // Return null if key not found
            return null;
        }
    }
}
