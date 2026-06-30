package optional;

import java.util.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO OPTIONAL CLASS EXERCISES
 * ======================================
 * This file contains comprehensive solutions to all Optional exercises.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== OPTIONAL CLASS - EXERCISE SOLUTIONS ===\n");
        
        // Run all exercise solutions
        exercise1_CreateOptionals();
        exercise2_CheckPresence();
        exercise3_OrElseDefault();
        exercise4_IfPresent();
        exercise5_FilterOptional();
        exercise6_ChainMapOperations();
        exercise7_FlatMapExample();
        exercise8_OrElseVsOrElseGet();
        exercise9_OrElseThrow();
        exercise10_TransformTypes();
        exercise11_RepositoryPattern();
        exercise12_NestedOptionals();
        exercise13_ValidationMethod();
        exercise14_ProcessListOfOptionals();
        exercise15_FluentAPIWithOptional();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Create Optional with value, with null, and empty
     * Solution demonstrates the three ways to create Optional objects
     */
    private static void exercise1_CreateOptionals() {
        System.out.println("EXERCISE 1: Create Optional Objects");
        System.out.println("-".repeat(60));
        
        // Method 1: Optional.of() - value MUST NOT be null
        Optional<String> withValue = Optional.of("Hello World");
        System.out.println("Optional.of(\"Hello World\"): " + withValue);
        
        // Method 2: Optional.ofNullable() - value CAN be null
        String nullValue = null;
        Optional<String> withNull = Optional.ofNullable(nullValue);
        System.out.println("Optional.ofNullable(null): " + withNull);
        
        String nonNullValue = "Java 8";
        Optional<String> withNonNull = Optional.ofNullable(nonNullValue);
        System.out.println("Optional.ofNullable(\"Java 8\"): " + withNonNull);
        
        // Method 3: Optional.empty() - explicitly create empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println("Optional.empty(): " + empty);
        
        // Best Practice: Use ofNullable when unsure if value is null
        System.out.println("\nBest Practice: Use ofNullable() for uncertain nullability");
        System.out.println();
    }

    /**
     * EXERCISE 2: Check if Optional contains value using isPresent()
     * Solution demonstrates checking for value presence
     */
    private static void exercise2_CheckPresence() {
        System.out.println("EXERCISE 2: Check Presence with isPresent()");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("I exist!");
        Optional<String> absent = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);
        
        // isPresent() - returns true if value exists
        System.out.println("present.isPresent(): " + present.isPresent());
        System.out.println("absent.isPresent(): " + absent.isPresent());
        System.out.println("nullable.isPresent(): " + nullable.isPresent());
        
        // Practical usage: conditional logic
        if (present.isPresent()) {
            System.out.println("Value found: " + present.get());
        }
        
        if (!absent.isPresent()) {
            System.out.println("No value present in absent Optional");
        }
        
        System.out.println();
    }

    /**
     * EXERCISE 3: Get value with orElse() providing default
     * Solution demonstrates safe value retrieval with defaults
     */
    private static void exercise3_OrElseDefault() {
        System.out.println("EXERCISE 3: Get Value with orElse()");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("Actual Value");
        Optional<String> absent = Optional.empty();
        
        // orElse() - returns value or default
        String value1 = present.orElse("Default Value");
        String value2 = absent.orElse("Default Value");
        
        System.out.println("present.orElse(\"Default\"): " + value1);
        System.out.println("absent.orElse(\"Default\"): " + value2);
        
        // Practical example: configuration values
        Optional<String> configValue = getConfigValue("theme");
        String theme = configValue.orElse("light");
        System.out.println("\nConfiguration theme: " + theme);
        
        // With different types
        Optional<Integer> optionalAge = Optional.empty();
        int age = optionalAge.orElse(18); // Default adult age
        System.out.println("Age (with default): " + age);
        
        System.out.println();
    }

    /**
     * EXERCISE 4: Use ifPresent() to print value if exists
     * Solution demonstrates consuming Optional values conditionally
     */
    private static void exercise4_IfPresent() {
        System.out.println("EXERCISE 4: Use ifPresent()");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("Hello, Optional!");
        Optional<String> absent = Optional.empty();
        
        // ifPresent() - executes action only if value exists
        System.out.print("present.ifPresent(): ");
        present.ifPresent(value -> System.out.println(value));
        
        System.out.print("absent.ifPresent(): ");
        absent.ifPresent(value -> System.out.println(value));
        System.out.println("(nothing printed for absent)");
        
        // Practical usage: logging
        Optional<String> username = Optional.of("Alice");
        username.ifPresent(name -> System.out.println("\nLogged in user: " + name));
        
        // With method reference
        List<String> messages = new ArrayList<>();
        Optional.of("Important message").ifPresent(messages::add);
        System.out.println("Messages collected: " + messages);
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Filter Optional based on condition
     * Solution demonstrates filtering Optional values
     */
    private static void exercise5_FilterOptional() {
        System.out.println("EXERCISE 5: Filter Optional");
        System.out.println("-".repeat(60));
        
        Optional<String> value = Optional.of("Hello");
        
        // filter() - keeps value only if predicate matches
        Optional<String> filtered1 = value.filter(s -> s.length() > 3);
        Optional<String> filtered2 = value.filter(s -> s.length() > 10);
        
        System.out.println("Original: " + value);
        System.out.println("Filtered (length > 3): " + filtered1);
        System.out.println("Filtered (length > 10): " + filtered2);
        
        // Practical example: age validation
        Optional<Integer> age = Optional.of(25);
        Optional<Integer> adultAge = age.filter(a -> a >= 18);
        adultAge.ifPresent(a -> System.out.println("\nAdult age verified: " + a));
        
        // Chaining filters
        Optional<String> email = Optional.of("user@example.com");
        Optional<String> validEmail = email
                .filter(e -> e.contains("@"))
                .filter(e -> e.length() > 5);
        System.out.println("Valid email: " + validEmail.orElse("Invalid"));
        
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 6: Chain map() operations on Optional
     * Solution demonstrates transforming Optional values
     */
    private static void exercise6_ChainMapOperations() {
        System.out.println("EXERCISE 6: Chain map() Operations");
        System.out.println("-".repeat(60));
        
        Optional<String> input = Optional.of("  hello world  ");
        
        // Chain multiple transformations
        Optional<String> result = input
                .map(String::trim)           // Remove whitespace
                .map(String::toUpperCase)    // Convert to uppercase
                .map(s -> s.replace(" ", "_")); // Replace spaces
        
        System.out.println("Original: \"  hello world  \"");
        System.out.println("After chaining: " + result.get());
        
        // Practical example: processing user input
        Optional<String> userInput = Optional.of("  Java 8 Features  ");
        String processed = userInput
                .map(String::trim)
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("\\s+", "-"))
                .orElse("default-slug");
        System.out.println("\nProcessed slug: " + processed);
        
        // Empty Optional propagation
        Optional<String> empty = Optional.empty();
        Optional<String> emptyResult = empty
                .map(String::trim)
                .map(String::toUpperCase);
        System.out.println("Empty after chaining: " + emptyResult);
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Use flatMap() with method returning Optional
     * Solution demonstrates flattening nested Optionals
     */
    private static void exercise7_FlatMapExample() {
        System.out.println("EXERCISE 7: Use flatMap()");
        System.out.println("-".repeat(60));
        
        Optional<String> validNumber = Optional.of("123");
        Optional<String> invalidNumber = Optional.of("abc");
        
        // flatMap() - prevents Optional<Optional<Integer>>
        Optional<Integer> parsed1 = validNumber.flatMap(Solutions::parseInteger);
        Optional<Integer> parsed2 = invalidNumber.flatMap(Solutions::parseInteger);
        
        System.out.println("Parsing \"123\": " + parsed1.orElse(-1));
        System.out.println("Parsing \"abc\": " + parsed2.orElse(-1));
        
        // Comparison: map() vs flatMap()
        System.out.println("\nmap() vs flatMap():");
        Optional<Optional<Integer>> nested = validNumber.map(Solutions::parseInteger);
        System.out.println("map() result type: Optional<Optional<Integer>>");
        Optional<Integer> flat = validNumber.flatMap(Solutions::parseInteger);
        System.out.println("flatMap() result type: Optional<Integer>");
        
        // Practical example: user lookup chain
        Optional<String> userId = Optional.of("user123");
        Optional<User> user = userId.flatMap(Solutions::findUserById);
        user.ifPresent(u -> System.out.println("\nFound user: " + u.name));
        
        System.out.println();
    }

    /**
     * EXERCISE 8: Compare orElse() vs orElseGet() performance
     * Solution demonstrates the difference and performance implications
     */
    private static void exercise8_OrElseVsOrElseGet() {
        System.out.println("EXERCISE 8: orElse() vs orElseGet()");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("Value");
        Optional<String> absent = Optional.empty();
        
        // orElse() - ALWAYS evaluates default value
        System.out.println("Testing orElse():");
        String result1 = present.orElse(expensiveOperation());
        System.out.println("Result: " + result1);
        
        // orElseGet() - LAZILY evaluates default value (only if absent)
        System.out.println("\nTesting orElseGet():");
        String result2 = present.orElseGet(() -> expensiveOperation());
        System.out.println("Result: " + result2);
        
        // Performance comparison
        System.out.println("\n--- Performance Test ---");
        
        System.out.println("Present value with orElse():");
        long start = System.nanoTime();
        present.orElse(expensiveOperation());
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");
        
        System.out.println("\nPresent value with orElseGet():");
        start = System.nanoTime();
        present.orElseGet(() -> expensiveOperation());
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");
        
        System.out.println("\n✓ Key Difference: orElseGet() is more efficient for expensive defaults");
        System.out.println();
    }

    /**
     * EXERCISE 9: Use orElseThrow() with custom exception
     * Solution demonstrates throwing exceptions for absent values
     */
    private static void exercise9_OrElseThrow() {
        System.out.println("EXERCISE 9: Use orElseThrow()");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("Value exists");
        Optional<String> absent = Optional.empty();
        
        // orElseThrow() with default exception (Java 10+)
        try {
            String value = present.orElseThrow();
            System.out.println("Present value: " + value);
        } catch (NoSuchElementException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        // orElseThrow() with custom exception
        try {
            String value = absent.orElseThrow(() -> 
                new IllegalArgumentException("Value is required!"));
        } catch (IllegalArgumentException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
        }
        
        // Practical example: user authentication
        Optional<String> authToken = Optional.empty();
        try {
            String token = authToken.orElseThrow(() -> 
                new SecurityException("Authentication required"));
        } catch (SecurityException e) {
            System.out.println("\nSecurity exception: " + e.getMessage());
        }
        
        // Domain-specific exception
        Optional<User> user = Optional.empty();
        try {
            User u = user.orElseThrow(() -> 
                new UserNotFoundException("User not found in database"));
        } catch (UserNotFoundException e) {
            System.out.println("Domain exception: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * EXERCISE 10: Transform Optional<String> to Optional<Integer>
     * Solution demonstrates type transformation with map()
     */
    private static void exercise10_TransformTypes() {
        System.out.println("EXERCISE 10: Transform Optional Types");
        System.out.println("-".repeat(60));
        
        // String to Integer transformation
        Optional<String> stringOpt = Optional.of("Hello");
        Optional<Integer> lengthOpt = stringOpt.map(String::length);
        System.out.println("String \"Hello\" -> length: " + lengthOpt.get());
        
        // Multiple transformations
        Optional<String> text = Optional.of("Java Programming");
        Optional<Integer> wordCount = text
                .map(s -> s.split(" "))
                .map(arr -> arr.length);
        System.out.println("Word count: " + wordCount.get());
        
        // String to custom object
        Optional<String> name = Optional.of("Alice");
        Optional<User> userOpt = name.map(n -> new User(n, 25));
        userOpt.ifPresent(u -> System.out.println("Created user: " + u));
        
        // Integer to String
        Optional<Integer> number = Optional.of(42);
        Optional<String> formatted = number.map(n -> "The answer is " + n);
        System.out.println("Formatted: " + formatted.get());
        
        // Practical example: parsing and validating
        Optional<String> input = Optional.of("100");
        Optional<Integer> validatedNumber = input
                .flatMap(Solutions::parseInteger)
                .filter(n -> n > 0 && n <= 100);
        System.out.println("Validated (0-100): " + validatedNumber.orElse(-1));
        
        System.out.println();
    }

    // ==================== ADVANCED LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 11: Implement repository pattern returning Optional
     * Solution demonstrates using Optional in repository/DAO pattern
     */
    private static void exercise11_RepositoryPattern() {
        System.out.println("EXERCISE 11: Repository Pattern with Optional");
        System.out.println("-".repeat(60));
        
        UserRepository repository = new UserRepository();
        
        // Find existing user
        Optional<User> user1 = repository.findById("user123");
        user1.ifPresentOrElse(
            u -> System.out.println("Found: " + u),
            () -> System.out.println("User not found")
        );
        
        // Find non-existing user
        Optional<User> user2 = repository.findById("user999");
        user2.ifPresentOrElse(
            u -> System.out.println("Found: " + u),
            () -> System.out.println("User not found")
        );
        
        // Find by email
        Optional<User> user3 = repository.findByEmail("alice@example.com");
        String userName = user3.map(u -> u.name).orElse("Guest");
        System.out.println("\nUser name: " + userName);
        
        // Chaining repository operations
        String department = repository.findById("user123")
                .flatMap(repository::getDepartment)
                .orElse("Unknown");
        System.out.println("Department: " + department);
        
        System.out.println();
    }

    /**
     * EXERCISE 12: Handle nested Optionals using flatMap
     * Solution demonstrates dealing with nested Optional structures
     */
    private static void exercise12_NestedOptionals() {
        System.out.println("EXERCISE 12: Handle Nested Optionals");
        System.out.println("-".repeat(60));
        
        // Problem: Optional<Optional<T>>
        Optional<User> user = Optional.of(new User("Bob", 30));
        
        // Wrong way: creates Optional<Optional<String>>
        // Optional<Optional<String>> nested = user.map(User::getEmail);
        
        // Right way: flatMap flattens the structure
        Optional<String> email = user.flatMap(User::getEmail);
        System.out.println("Email: " + email.orElse("No email"));
        
        // Chaining multiple levels
        Optional<String> city = user
                .flatMap(User::getAddress)
                .flatMap(Address::getCity);
        System.out.println("City: " + city.orElse("Unknown"));
        
        // Practical example: deep navigation
        Optional<User> userOpt = Optional.of(new User("Charlie", 28));
        String postalCode = userOpt
                .flatMap(User::getAddress)
                .flatMap(Address::getPostalCode)
                .orElse("00000");
        System.out.println("Postal code: " + postalCode);
        
        System.out.println();
    }

    /**
     * EXERCISE 13: Create validation method returning Optional
     * Solution demonstrates validation patterns with Optional
     */
    private static void exercise13_ValidationMethod() {
        System.out.println("EXERCISE 13: Validation with Optional");
        System.out.println("-".repeat(60));
        
        // Email validation
        Optional<String> validEmail1 = validateEmail("user@example.com");
        Optional<String> validEmail2 = validateEmail("invalid-email");
        
        System.out.println("Valid email: " + validEmail1.orElse("Invalid"));
        System.out.println("Invalid email: " + validEmail2.orElse("Invalid"));
        
        // Age validation
        Optional<Integer> validAge1 = validateAge(25);
        Optional<Integer> validAge2 = validateAge(-5);
        
        validAge1.ifPresent(age -> System.out.println("\nValidated age: " + age));
        System.out.println("Invalid age result: " + validAge2.orElse(-1));
        
        // Password validation
        String password1 = "SecurePass123!";
        String password2 = "weak";
        
        validatePassword(password1).ifPresentOrElse(
            pwd -> System.out.println("\nPassword accepted: " + "*".repeat(pwd.length())),
            () -> System.out.println("Password rejected")
        );
        
        validatePassword(password2).ifPresentOrElse(
            pwd -> System.out.println("Password accepted: " + pwd),
            () -> System.out.println("Password rejected (too weak)")
        );
        
        System.out.println();
    }

    /**
     * EXERCISE 14: Process list of Optionals efficiently
     * Solution demonstrates working with collections of Optionals
     */
    private static void exercise14_ProcessListOfOptionals() {
        System.out.println("EXERCISE 14: Process List of Optionals");
        System.out.println("-".repeat(60));
        
        // List of Optionals
        List<Optional<String>> optionals = Arrays.asList(
            Optional.of("Apple"),
            Optional.empty(),
            Optional.of("Banana"),
            Optional.empty(),
            Optional.of("Cherry")
        );
        
        // Extract present values
        List<String> presentValues = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Present values: " + presentValues);
        
        // Better approach with flatMap (Java 9+)
        List<String> values = optionals.stream()
                .flatMap(opt -> opt.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
        System.out.println("Using flatMap: " + values);
        
        // Count present values
        long count = optionals.stream()
                .filter(Optional::isPresent)
                .count();
        System.out.println("Count of present values: " + count);
        
        // Process with default values
        List<String> withDefaults = optionals.stream()
                .map(opt -> opt.orElse("DEFAULT"))
                .collect(Collectors.toList());
        System.out.println("With defaults: " + withDefaults);
        
        System.out.println();
    }

    /**
     * EXERCISE 15: Build fluent API using Optional chaining
     * Solution demonstrates creating fluent interfaces with Optional
     */
    private static void exercise15_FluentAPIWithOptional() {
        System.out.println("EXERCISE 15: Fluent API with Optional");
        System.out.println("-".repeat(60));
        
        // Fluent configuration builder
        ConfigBuilder config = new ConfigBuilder()
                .withHost("localhost")
                .withPort(8080)
                .withUsername("admin")
                .build();
        
        String host = config.getHost().orElse("default-host");
        Integer port = config.getPort().orElse(80);
        String username = config.getUsername().orElse("guest");
        String password = config.getPassword().orElse("no-password-set");
        
        System.out.println("Configuration:");
        System.out.println("  Host: " + host);
        System.out.println("  Port: " + port);
        System.out.println("  Username: " + username);
        System.out.println("  Password: " + password);
        
        // Query builder example
        String query = QueryBuilder.builder()
                .select("name", "age")
                .from("users")
                .where("age > 18")
                .orderBy("name")
                .build()
                .orElse("SELECT * FROM users");
        
        System.out.println("\nGenerated query: " + query);
        
        System.out.println();
    }

    // ==================== HELPER METHODS AND CLASSES ====================

    private static Optional<String> getConfigValue(String key) {
        // Simulated configuration lookup
        return Optional.empty();
    }

    private static String expensiveOperation() {
        System.out.println("  [Expensive operation executed!]");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Expensive result";
    }

    private static Optional<Integer> parseInteger(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static Optional<User> findUserById(String id) {
        if (id.equals("user123")) {
            return Optional.of(new User("Alice", 25));
        }
        return Optional.empty();
    }

    private static Optional<String> validateEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            return Optional.of(email);
        }
        return Optional.empty();
    }

    private static Optional<Integer> validateAge(int age) {
        if (age >= 0 && age <= 150) {
            return Optional.of(age);
        }
        return Optional.empty();
    }

    private static Optional<String> validatePassword(String password) {
        if (password != null && password.length() >= 8 && 
            password.matches(".*[A-Z].*") && 
            password.matches(".*[0-9].*")) {
            return Optional.of(password);
        }
        return Optional.empty();
    }

    // ==================== DOMAIN CLASSES ====================

    static class User {
        String name;
        int age;
        
        User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        Optional<String> getEmail() {
            // Simulated: some users don't have emails
            return Math.random() > 0.5 ? 
                Optional.of(name.toLowerCase() + "@example.com") : 
                Optional.empty();
        }
        
        Optional<Address> getAddress() {
            return Math.random() > 0.3 ? 
                Optional.of(new Address()) : 
                Optional.empty();
        }
        
        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + "}";
        }
    }

    static class Address {
        Optional<String> getCity() {
            return Optional.of("New York");
        }
        
        Optional<String> getPostalCode() {
            return Optional.of("10001");
        }
    }

    static class UserRepository {
        private Map<String, User> database = new HashMap<>();
        
        UserRepository() {
            database.put("user123", new User("Alice", 25));
            database.put("user456", new User("Bob", 30));
        }
        
        Optional<User> findById(String id) {
            return Optional.ofNullable(database.get(id));
        }
        
        Optional<User> findByEmail(String email) {
            return database.values().stream()
                    .filter(u -> u.getEmail().map(e -> e.equals(email)).orElse(false))
                    .findFirst();
        }
        
        Optional<String> getDepartment(User user) {
            return Optional.of("Engineering");
        }
    }

    static class UserNotFoundException extends Exception {
        UserNotFoundException(String message) {
            super(message);
        }
    }

    static class ConfigBuilder {
        private Optional<String> host = Optional.empty();
        private Optional<Integer> port = Optional.empty();
        private Optional<String> username = Optional.empty();
        private Optional<String> password = Optional.empty();
        
        ConfigBuilder withHost(String host) {
            this.host = Optional.of(host);
            return this;
        }
        
        ConfigBuilder withPort(int port) {
            this.port = Optional.of(port);
            return this;
        }
        
        ConfigBuilder withUsername(String username) {
            this.username = Optional.of(username);
            return this;
        }
        
        ConfigBuilder withPassword(String password) {
            this.password = Optional.of(password);
            return this;
        }
        
        ConfigBuilder build() {
            return this;
        }
        
        Optional<String> getHost() {
            return host;
        }
        
        Optional<Integer> getPort() {
            return port;
        }
        
        Optional<String> getUsername() {
            return username;
        }
        
        Optional<String> getPassword() {
            return password;
        }
    }

    static class QueryBuilder {
        private StringBuilder query = new StringBuilder();
        
        static QueryBuilder builder() {
            return new QueryBuilder();
        }
        
        QueryBuilder select(String... columns) {
            query.append("SELECT ").append(String.join(", ", columns));
            return this;
        }
        
        QueryBuilder from(String table) {
            query.append(" FROM ").append(table);
            return this;
        }
        
        QueryBuilder where(String condition) {
            query.append(" WHERE ").append(condition);
            return this;
        }
        
        QueryBuilder orderBy(String column) {
            query.append(" ORDER BY ").append(column);
            return this;
        }
        
        Optional<String> build() {
            return query.length() > 0 ? 
                Optional.of(query.toString()) : 
                Optional.empty();
        }
    }
}
