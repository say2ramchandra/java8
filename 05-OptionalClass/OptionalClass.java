package optional;

import java.util.*;
import java.util.stream.*;

/**
 * OPTIONAL CLASS IN JAVA 8
 * =========================
 * 
 * WHAT IS OPTIONAL?
 * -----------------
 * Optional is a container object that may or may not contain a non-null value.
 * It's designed to help avoid NullPointerException and make code more readable.
 * 
 * WHY USE OPTIONAL?
 * -----------------
 * - Explicit null-handling in API
 * - Encourages developers to think about absence of values
 * - Reduces NullPointerException errors
 * - Makes code more expressive and safer
 * - Functional-style programming for null checks
 * 
 * CREATING OPTIONAL:
 * ------------------
 * 1. Optional.of(value) - Creates Optional with non-null value (throws NPE if null)
 * 2. Optional.ofNullable(value) - Creates Optional that may contain null
 * 3. Optional.empty() - Creates empty Optional
 * 
 * KEY METHODS:
 * ------------
 * - isPresent() - Returns true if value present
 * - isEmpty() - Returns true if value absent (Java 11+)
 * - get() - Returns value (throws if empty)
 * - orElse(T) - Returns value or default
 * - orElseGet(Supplier) - Returns value or computed default
 * - orElseThrow() - Returns value or throws exception
 * - ifPresent(Consumer) - Executes action if value present
 * - map(Function) - Transforms value if present
 * - flatMap(Function) - Transforms to another Optional
 * - filter(Predicate) - Filters value
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class OptionalClass {

    public static void main(String[] args) {
        System.out.println("=== PART 1: UNDERSTANDING OPTIONAL ===\n");
        demonstrateCreatingOptionals();
        demonstrateCheckingPresence();
        demonstrateGettingValues();
        demonstrateOptionalOperations();
        
        System.out.println("\n=== PART 2: REAL-WORLD SCENARIOS ===\n");
        realWorldScenario1_UserRepository();
        realWorldScenario2_ConfigurationManager();
        realWorldScenario3_DataProcessing();
    }

    /**
     * EXAMPLE 1: Creating Optional Objects
     */
    private static void demonstrateCreatingOptionals() {
        System.out.println("1. CREATING OPTIONAL OBJECTS:");
        System.out.println("-".repeat(60));
        
        // Optional.of() - value must not be null
        Optional<String> optional1 = Optional.of("Hello");
        System.out.println("Optional.of(\"Hello\"): " + optional1);
        
        // Optional.ofNullable() - value can be null
        String nullableValue = null;
        Optional<String> optional2 = Optional.ofNullable(nullableValue);
        System.out.println("Optional.ofNullable(null): " + optional2);
        
        String nonNullValue = "World";
        Optional<String> optional3 = Optional.ofNullable(nonNullValue);
        System.out.println("Optional.ofNullable(\"World\"): " + optional3);
        
        // Optional.empty() - explicitly empty
        Optional<String> optional4 = Optional.empty();
        System.out.println("Optional.empty(): " + optional4);
        
        System.out.println();
    }

    /**
     * EXAMPLE 2: Checking Presence
     */
    private static void demonstrateCheckingPresence() {
        System.out.println("2. CHECKING PRESENCE:");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("Value");
        Optional<String> absent = Optional.empty();
        
        // isPresent()
        System.out.println("present.isPresent(): " + present.isPresent());
        System.out.println("absent.isPresent(): " + absent.isPresent());
        
        // isEmpty() - Java 11+
        System.out.println("absent.isEmpty(): " + absent.isEmpty());
        
        // ifPresent() - Execute action if present
        present.ifPresent(val -> System.out.println("Value is: " + val));
        absent.ifPresent(val -> System.out.println("This won't print"));
        
        // ifPresentOrElse() - Java 9+
        System.out.println("\nUsing ifPresentOrElse:");
        present.ifPresentOrElse(
            val -> System.out.println("Found: " + val),
            () -> System.out.println("Not found")
        );
        
        absent.ifPresentOrElse(
            val -> System.out.println("Found: " + val),
            () -> System.out.println("Not found")
        );
        
        System.out.println();
    }

    /**
     * EXAMPLE 3: Getting Values
     */
    private static void demonstrateGettingValues() {
        System.out.println("3. GETTING VALUES:");
        System.out.println("-".repeat(60));
        
        Optional<String> present = Optional.of("Hello");
        Optional<String> absent = Optional.empty();
        
        // get() - Use with caution (throws if empty)
        System.out.println("get() on present: " + present.get());
        
        // orElse() - Returns default value
        String result1 = absent.orElse("Default Value");
        System.out.println("orElse() on absent: " + result1);
        
        // orElseGet() - Computes default value using Supplier
        String result2 = absent.orElseGet(() -> {
            System.out.println("  Computing default...");
            return "Computed Default";
        });
        System.out.println("orElseGet() result: " + result2);
        
        // orElseThrow() - Throws exception if empty
        try {
            absent.orElseThrow(() -> new IllegalStateException("Value not present!"));
        } catch (IllegalStateException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        
        // Comparison: orElse vs orElseGet
        System.out.println("\norElse vs orElseGet demonstration:");
        Optional<String> opt = Optional.of("Exists");
        
        System.out.println("With orElse:");
        opt.orElse(getDefaultValue());  // Always executes
        
        System.out.println("With orElseGet:");
        opt.orElseGet(() -> getDefaultValue());  // Only executes if empty
        
        System.out.println();
    }

    private static String getDefaultValue() {
        System.out.println("  getDefaultValue() called");
        return "Default";
    }

    /**
     * EXAMPLE 4: Optional Operations
     */
    private static void demonstrateOptionalOperations() {
        System.out.println("4. OPTIONAL OPERATIONS:");
        System.out.println("-".repeat(60));
        
        Optional<String> optional = Optional.of("hello world");
        
        // map() - Transform value
        Optional<String> uppercase = optional.map(String::toUpperCase);
        System.out.println("map(toUpperCase): " + uppercase.get());
        
        Optional<Integer> length = optional.map(String::length);
        System.out.println("map(length): " + length.get());
        
        // Chaining map operations
        Optional<String> result = optional
                .map(String::trim)
                .map(String::toUpperCase)
                .map(s -> s.replace(" ", "_"));
        System.out.println("Chained maps: " + result.get());
        
        // filter() - Filter value
        Optional<String> filtered = optional.filter(s -> s.length() > 5);
        System.out.println("filter(length > 5): " + filtered);
        
        Optional<String> filteredOut = optional.filter(s -> s.length() > 100);
        System.out.println("filter(length > 100): " + filteredOut);
        
        // flatMap() - When function returns Optional
        Optional<String> nested = Optional.of("123");
        Optional<Integer> parsed = nested.flatMap(OptionalClass::parseInteger);
        System.out.println("flatMap(parseInteger): " + parsed);
        
        // or() - Return alternative Optional (Java 9+)
        Optional<String> empty = Optional.empty();
        Optional<String> alternative = empty.or(() -> Optional.of("Alternative"));
        System.out.println("or() with alternative: " + alternative.get());
        
        System.out.println();
    }

    private static Optional<Integer> parseInteger(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * REAL-WORLD SCENARIO 1: User Repository
     */
    private static void realWorldScenario1_UserRepository() {
        System.out.println("REAL-WORLD SCENARIO 1: User Repository");
        System.out.println("-".repeat(60));
        
        UserRepository repo = new UserRepository();
        
        // Find existing user
        System.out.println("\nSearching for user 'alice':");
        Optional<User> user1 = repo.findByUsername("alice");
        user1.ifPresentOrElse(
            user -> System.out.println("  Found: " + user),
            () -> System.out.println("  User not found")
        );
        
        // Find non-existing user
        System.out.println("\nSearching for user 'xyz':");
        Optional<User> user2 = repo.findByUsername("xyz");
        user2.ifPresentOrElse(
            user -> System.out.println("  Found: " + user),
            () -> System.out.println("  User not found")
        );
        
        // Get email with default
        System.out.println("\nGetting email for 'bob':");
        String email = repo.findByUsername("bob")
                .map(User::getEmail)
                .orElse("no-email@example.com");
        System.out.println("  Email: " + email);
        
        // Check if user is admin
        System.out.println("\nChecking if 'alice' is admin:");
        boolean isAdmin = repo.findByUsername("alice")
                .filter(User::isAdmin)
                .isPresent();
        System.out.println("  Is admin: " + isAdmin);
        
        // Get user or create default
        System.out.println("\nGet or create guest user:");
        User guestUser = repo.findByUsername("guest")
                .orElseGet(() -> new User("guest", "guest@example.com", false));
        System.out.println("  User: " + guestUser);
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 2: Configuration Manager
     */
    private static void realWorldScenario2_ConfigurationManager() {
        System.out.println("REAL-WORLD SCENARIO 2: Configuration Manager");
        System.out.println("-".repeat(60));
        
        ConfigManager config = new ConfigManager();
        
        // Get configuration with defaults
        String dbHost = config.get("database.host").orElse("localhost");
        System.out.println("\nDatabase Host: " + dbHost);
        
        int port = config.get("database.port")
                .flatMap(OptionalClass::parseInteger)
                .orElse(3306);
        System.out.println("Database Port: " + port);
        
        // Get and transform
        String appName = config.get("app.name")
                .map(String::toUpperCase)
                .orElse("UNKNOWN");
        System.out.println("App Name: " + appName);
        
        // Chain operations
        config.get("api.timeout")
                .flatMap(OptionalClass::parseInteger)
                .filter(timeout -> timeout > 0 && timeout < 300)
                .ifPresentOrElse(
                    timeout -> System.out.println("API Timeout (valid): " + timeout + "s"),
                    () -> System.out.println("API Timeout: Using default 30s")
                );
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 3: Data Processing
     */
    private static void realWorldScenario3_DataProcessing() {
        System.out.println("REAL-WORLD SCENARIO 3: Order Processing");
        System.out.println("-".repeat(60));
        
        List<Order> orders = Arrays.asList(
            new Order("ORD001", Optional.of("PROMO10"), 100.0),
            new Order("ORD002", Optional.empty(), 50.0),
            new Order("ORD003", Optional.of("SAVE20"), 200.0),
            new Order("ORD004", Optional.empty(), 75.0)
        );
        
        System.out.println("\nProcessing orders:");
        orders.forEach(order -> {
            double finalPrice = order.getDiscountCode()
                    .map(code -> applyDiscount(order.getAmount(), code))
                    .orElse(order.getAmount());
            
            System.out.printf("  %s: $%.2f -> $%.2f %s\n",
                    order.getId(),
                    order.getAmount(),
                    finalPrice,
                    order.getDiscountCode().isPresent() ? "(discount applied)" : "");
        });
        
        // Total with discounts
        double total = orders.stream()
                .mapToDouble(order -> order.getDiscountCode()
                        .map(code -> applyDiscount(order.getAmount(), code))
                        .orElse(order.getAmount()))
                .sum();
        System.out.printf("\nTotal: $%.2f\n", total);
        
        System.out.println();
    }

    private static double applyDiscount(double amount, String code) {
        if (code.equals("PROMO10")) return amount * 0.9;
        if (code.equals("SAVE20")) return amount * 0.8;
        return amount;
    }

    // ==================== HELPER CLASSES ====================

    static class User {
        private String username;
        private String email;
        private boolean admin;

        User(String username, String email, boolean admin) {
            this.username = username;
            this.email = email;
            this.admin = admin;
        }

        String getUsername() { return username; }
        String getEmail() { return email; }
        boolean isAdmin() { return admin; }

        @Override
        public String toString() {
            return username + " (" + email + ")" + (admin ? " [Admin]" : "");
        }
    }

    static class UserRepository {
        private List<User> users = Arrays.asList(
            new User("alice", "alice@example.com", true),
            new User("bob", "bob@example.com", false),
            new User("charlie", "charlie@example.com", false)
        );

        Optional<User> findByUsername(String username) {
            return users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst();
        }
    }

    static class ConfigManager {
        private Map<String, String> config = Map.of(
            "database.host", "prod-db.example.com",
            "app.name", "MyApp",
            "api.timeout", "60"
        );

        Optional<String> get(String key) {
            return Optional.ofNullable(config.get(key));
        }
    }

    static class Order {
        private String id;
        private Optional<String> discountCode;
        private double amount;

        Order(String id, Optional<String> discountCode, double amount) {
            this.id = id;
            this.discountCode = discountCode;
            this.amount = amount;
        }

        String getId() { return id; }
        Optional<String> getDiscountCode() { return discountCode; }
        double getAmount() { return amount; }
    }
}
