package defaultstaticmethods;

import java.util.*;
import java.util.function.*;

/**
 * SOLUTIONS TO DEFAULT AND STATIC METHODS EXERCISES
 * ==================================================
 * This file contains comprehensive solutions demonstrating default and static methods in interfaces.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== DEFAULT AND STATIC METHODS - EXERCISE SOLUTIONS ===\n");
        
        // Run all exercise solutions
        exercise1_InterfaceWithDefaultMethod();
        exercise2_InterfaceWithStaticMethod();
        exercise3_ImplementInterfaceWithDefault();
        exercise4_OverrideDefaultMethod();
        exercise5_CallStaticMethod();
        exercise6_MultipleDefaultMethods();
        exercise7_DiamondProblem();
        exercise8_CombineAbstractDefaultStatic();
        exercise9_BackwardCompatibility();
        exercise10_UtilityClassEquivalent();
        exercise11_PluginArchitecture();
        exercise12_FluentAPI();
        exercise13_ExtensibleFramework();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Create interface with default method
     * Solution demonstrates basic default method syntax
     */
    private static void exercise1_InterfaceWithDefaultMethod() {
        System.out.println("EXERCISE 1: Interface with Default Method");
        System.out.println("-".repeat(60));
        
        // Interface with default method
        interface Greeting {
            void sayHello();
            
            // Default method - provides default implementation
            default void sayGoodbye() {
                System.out.println("  Goodbye!");
            }
        }
        
        // Implementation
        Greeting greeter = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("  Hello!");
            }
            // No need to implement sayGoodbye() - uses default
        };
        
        System.out.println("Calling methods:");
        greeter.sayHello();       // Implemented
        greeter.sayGoodbye();     // Default implementation
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Create interface with static method
     * Solution demonstrates static methods in interfaces
     */
    private static void exercise2_InterfaceWithStaticMethod() {
        System.out.println("EXERCISE 2: Interface with Static Method");
        System.out.println("-".repeat(60));
        
        interface MathOperations {
            // Static method - utility function
            static int add(int a, int b) {
                return a + b;
            }
            
            static int multiply(int a, int b) {
                return a * b;
            }
            
            static double average(int... numbers) {
                return Arrays.stream(numbers).average().orElse(0);
            }
        }
        
        // Call static methods directly on interface
        System.out.println("5 + 3 = " + MathOperations.add(5, 3));
        System.out.println("5 * 3 = " + MathOperations.multiply(5, 3));
        System.out.println("Average of [1,2,3,4,5] = " + 
                          MathOperations.average(1, 2, 3, 4, 5));
        
        System.out.println();
    }

    /**
     * EXERCISE 3: Implement interface using default method
     * Solution shows how implementations inherit default methods
     */
    private static void exercise3_ImplementInterfaceWithDefault() {
        System.out.println("EXERCISE 3: Implement Interface with Default");
        System.out.println("-".repeat(60));
        
        interface Vehicle {
            String getType();
            
            default void start() {
                System.out.println("  " + getType() + " is starting...");
            }
            
            default void stop() {
                System.out.println("  " + getType() + " is stopping...");
            }
        }
        
        // Implementation uses default methods
        class Car implements Vehicle {
            @Override
            public String getType() {
                return "Car";
            }
            // Inherits start() and stop() default methods
        }
        
        Vehicle car = new Car();
        car.start();
        car.stop();
        
        System.out.println();
    }

    /**
     * EXERCISE 4: Override default method in implementation
     * Solution demonstrates overriding default behavior
     */
    private static void exercise4_OverrideDefaultMethod() {
        System.out.println("EXERCISE 4: Override Default Method");
        System.out.println("-".repeat(60));
        
        interface Logger {
            default void log(String message) {
                System.out.println("  [LOG] " + message);
            }
            
            default void error(String message) {
                System.out.println("  [ERROR] " + message);
            }
        }
        
        // Implementation 1: Uses default
        class SimpleLogger implements Logger {
            // Uses default log() and error()
        }
        
        // Implementation 2: Overrides default
        class CustomLogger implements Logger {
            @Override
            public void log(String message) {
                System.out.println("  [CUSTOM LOG] " + message.toUpperCase());
            }
            
            @Override
            public void error(String message) {
                System.out.println("  [CUSTOM ERROR] *** " + message + " ***");
            }
        }
        
        System.out.println("SimpleLogger (uses defaults):");
        Logger simple = new SimpleLogger();
        simple.log("Info message");
        simple.error("Error message");
        
        System.out.println("\nCustomLogger (overrides defaults):");
        Logger custom = new CustomLogger();
        custom.log("Info message");
        custom.error("Error message");
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Call static method from interface
     * Solution demonstrates accessing static interface methods
     */
    private static void exercise5_CallStaticMethod() {
        System.out.println("EXERCISE 5: Call Static Methods");
        System.out.println("-".repeat(60));
        
        interface StringUtils {
            static boolean isNullOrEmpty(String str) {
                return str == null || str.isEmpty();
            }
            
            static String reverse(String str) {
                return str == null ? null : 
                       new StringBuilder(str).reverse().toString();
            }
            
            static String capitalize(String str) {
                if (isNullOrEmpty(str)) return str;
                return str.substring(0, 1).toUpperCase() + str.substring(1);
            }
        }
        
        // Call static methods
        System.out.println("Is 'hello' null or empty? " + 
                          StringUtils.isNullOrEmpty("hello"));
        System.out.println("Is '' null or empty? " + 
                          StringUtils.isNullOrEmpty(""));
        System.out.println("Reverse 'Java': " + 
                          StringUtils.reverse("Java"));
        System.out.println("Capitalize 'java': " + 
                          StringUtils.capitalize("java"));
        
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 6: Create multiple default methods in one interface
     * Solution demonstrates rich interfaces with multiple defaults
     */
    private static void exercise6_MultipleDefaultMethods() {
        System.out.println("EXERCISE 6: Multiple Default Methods");
        System.out.println("-".repeat(60));
        
        interface Collection {
            void add(String item);
            
            // Multiple default methods
            default void addAll(String... items) {
                for (String item : items) {
                    add(item);
                }
            }
            
            default void addIfNotNull(String item) {
                if (item != null) {
                    add(item);
                }
            }
            
            default void addWithPrefix(String prefix, String item) {
                add(prefix + item);
            }
            
            default void prettyPrint() {
                System.out.println("  Collection contents printed");
            }
        }
        
        class SimpleCollection implements Collection {
            private List<String> items = new ArrayList<>();
            
            @Override
            public void add(String item) {
                items.add(item);
                System.out.println("    Added: " + item);
            }
        }
        
        Collection collection = new SimpleCollection();
        collection.add("Item1");
        collection.addAll("Item2", "Item3", "Item4");
        collection.addIfNotNull("Item5");
        collection.addIfNotNull(null);  // Won't add
        collection.addWithPrefix("[NEW] ", "Item6");
        collection.prettyPrint();
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Resolve diamond problem with default methods
     * Solution demonstrates multiple inheritance conflict resolution
     */
    private static void exercise7_DiamondProblem() {
        System.out.println("EXERCISE 7: Diamond Problem Resolution");
        System.out.println("-".repeat(60));
        
        interface InterfaceA {
            default void show() {
                System.out.println("  InterfaceA::show()");
            }
        }
        
        interface InterfaceB {
            default void show() {
                System.out.println("  InterfaceB::show()");
            }
        }
        
        // Diamond problem: both interfaces have same default method
        class DiamondClass implements InterfaceA, InterfaceB {
            @Override
            public void show() {
                // Must explicitly resolve the conflict
                System.out.println("  DiamondClass::show() - resolving conflict");
                
                // Can call specific interface method
                InterfaceA.super.show();
                InterfaceB.super.show();
            }
        }
        
        DiamondClass obj = new DiamondClass();
        obj.show();
        
        System.out.println();
    }

    /**
     * EXERCISE 8: Combine abstract, default, and static methods
     * Solution demonstrates comprehensive interface design
     */
    private static void exercise8_CombineAbstractDefaultStatic() {
        System.out.println("EXERCISE 8: Combine Abstract, Default, and Static");
        System.out.println("-".repeat(60));
        
        interface Shape {
            // Abstract method - must be implemented
            double area();
            
            // Default method - can be overridden
            default void printArea() {
                System.out.println("  Area: " + area());
            }
            
            default String getType() {
                return "Unknown Shape";
            }
            
            // Static method - utility function
            static double totalArea(Shape... shapes) {
                return Arrays.stream(shapes)
                            .mapToDouble(Shape::area)
                            .sum();
            }
        }
        
        class Circle implements Shape {
            private double radius;
            
            Circle(double radius) {
                this.radius = radius;
            }
            
            @Override
            public double area() {
                return Math.PI * radius * radius;
            }
            
            @Override
            public String getType() {
                return "Circle";
            }
        }
        
        class Rectangle implements Shape {
            private double width, height;
            
            Rectangle(double width, double height) {
                this.width = width;
                this.height = height;
            }
            
            @Override
            public double area() {
                return width * height;
            }
            
            @Override
            public String getType() {
                return "Rectangle";
            }
        }
        
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        
        System.out.println(circle.getType() + ":");
        circle.printArea();
        
        System.out.println(rectangle.getType() + ":");
        rectangle.printArea();
        
        System.out.println("Total area: " + Shape.totalArea(circle, rectangle));
        
        System.out.println();
    }

    /**
     * EXERCISE 9: Use default methods for backward compatibility
     * Solution demonstrates evolving interfaces without breaking code
     */
    private static void exercise9_BackwardCompatibility() {
        System.out.println("EXERCISE 9: Backward Compatibility");
        System.out.println("-".repeat(60));
        
        // Original interface (Version 1)
        interface DataProcessor {
            void process(String data);
        }
        
        // Existing implementation (before adding new methods)
        class LegacyProcessor implements DataProcessor {
            @Override
            public void process(String data) {
                System.out.println("  Processing: " + data);
            }
        }
        
        // Enhanced interface (Version 2) - adds default methods
        interface EnhancedDataProcessor extends DataProcessor {
            // New method with default implementation
            default void preProcess(String data) {
                System.out.println("  Pre-processing: " + data);
            }
            
            default void postProcess(String data) {
                System.out.println("  Post-processing: " + data);
            }
            
            // Backward compatible: old code still works
            default void processAll(String... data) {
                for (String item : data) {
                    preProcess(item);
                    process(item);
                    postProcess(item);
                }
            }
        }
        
        // Old implementation still works!
        class ModernProcessor implements EnhancedDataProcessor {
            @Override
            public void process(String data) {
                System.out.println("  [Modern] Processing: " + data);
            }
            
            // Can optionally override new default methods
            @Override
            public void preProcess(String data) {
                System.out.println("  [Modern] Pre-processing: " + data);
            }
        }
        
        System.out.println("Legacy processor still works:");
        DataProcessor legacy = new LegacyProcessor();
        legacy.process("data1");
        
        System.out.println("\nModern processor with enhancements:");
        EnhancedDataProcessor modern = new ModernProcessor();
        modern.processAll("data2");
        
        System.out.println();
    }

    /**
     * EXERCISE 10: Create utility class equivalent using static methods
     * Solution demonstrates replacing utility classes with interfaces
     */
    private static void exercise10_UtilityClassEquivalent() {
        System.out.println("EXERCISE 10: Utility Methods in Interface");
        System.out.println("-".repeat(60));
        
        // Traditional utility class pattern (pre-Java 8)
        class OldCollectionUtils {
            private OldCollectionUtils() {} // Prevent instantiation
            
            public static boolean isEmpty(List<?> list) {
                return list == null || list.isEmpty();
            }
        }
        
        // Modern approach: interface with static methods
        interface CollectionUtils {
            static <T> boolean isEmpty(List<T> list) {
                return list == null || list.isEmpty();
            }
            
            static <T> boolean isNotEmpty(List<T> list) {
                return !isEmpty(list);
            }
            
            static <T> T firstElement(List<T> list) {
                return isEmpty(list) ? null : list.get(0);
            }
            
            static <T> T lastElement(List<T> list) {
                return isEmpty(list) ? null : list.get(list.size() - 1);
            }
            
            static <T> List<T> safe(List<T> list) {
                return list == null ? Collections.emptyList() : list;
            }
        }
        
        List<String> list = Arrays.asList("A", "B", "C");
        List<String> emptyList = null;
        
        System.out.println("List empty? " + CollectionUtils.isEmpty(list));
        System.out.println("Null list empty? " + CollectionUtils.isEmpty(emptyList));
        System.out.println("First element: " + CollectionUtils.firstElement(list));
        System.out.println("Last element: " + CollectionUtils.lastElement(list));
        System.out.println("Safe list size: " + CollectionUtils.safe(emptyList).size());
        
        System.out.println();
    }

    // ==================== ADVANCED LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 11: Implement plugin architecture using default methods
     * Solution demonstrates extensible plugin system
     */
    private static void exercise11_PluginArchitecture() {
        System.out.println("EXERCISE 11: Plugin Architecture");
        System.out.println("-".repeat(60));
        
        interface Plugin {
            String getName();
            void execute();
            
            // Default plugin lifecycle methods
            default void initialize() {
                System.out.println("  [" + getName() + "] Initializing...");
            }
            
            default void cleanup() {
                System.out.println("  [" + getName() + "] Cleaning up...");
            }
            
            default boolean isEnabled() {
                return true;
            }
            
            default void run() {
                if (isEnabled()) {
                    initialize();
                    execute();
                    cleanup();
                } else {
                    System.out.println("  [" + getName() + "] Plugin disabled");
                }
            }
        }
        
        class LoggingPlugin implements Plugin {
            @Override
            public String getName() {
                return "LoggingPlugin";
            }
            
            @Override
            public void execute() {
                System.out.println("  [" + getName() + "] Logging events...");
            }
        }
        
        class SecurityPlugin implements Plugin {
            @Override
            public String getName() {
                return "SecurityPlugin";
            }
            
            @Override
            public void execute() {
                System.out.println("  [" + getName() + "] Checking security...");
            }
            
            @Override
            public void initialize() {
                System.out.println("  [" + getName() + "] Loading security config...");
            }
        }
        
        List<Plugin> plugins = Arrays.asList(
            new LoggingPlugin(),
            new SecurityPlugin()
        );
        
        System.out.println("Running plugins:");
        plugins.forEach(Plugin::run);
        
        System.out.println();
    }

    /**
     * EXERCISE 12: Create fluent API using default methods
     * Solution demonstrates method chaining with defaults
     */
    private static void exercise12_FluentAPI() {
        System.out.println("EXERCISE 12: Fluent API with Defaults");
        System.out.println("-".repeat(60));
        
        interface FluentSQL {
            String build();
            
            default FluentSQL select(String... columns) {
                return this;
            }
            
            default FluentSQL from(String table) {
                return this;
            }
            
            default FluentSQL where(String condition) {
                return this;
            }
            
            default FluentSQL orderBy(String column) {
                return this;
            }
            
            default FluentSQL limit(int count) {
                return this;
            }
        }
        
        class SQLBuilder implements FluentSQL {
            private StringBuilder sql = new StringBuilder();
            
            @Override
            public SQLBuilder select(String... columns) {
                sql.append("SELECT ").append(String.join(", ", columns));
                return this;
            }
            
            @Override
            public SQLBuilder from(String table) {
                sql.append(" FROM ").append(table);
                return this;
            }
            
            @Override
            public SQLBuilder where(String condition) {
                sql.append(" WHERE ").append(condition);
                return this;
            }
            
            @Override
            public SQLBuilder orderBy(String column) {
                sql.append(" ORDER BY ").append(column);
                return this;
            }
            
            @Override
            public SQLBuilder limit(int count) {
                sql.append(" LIMIT ").append(count);
                return this;
            }
            
            @Override
            public String build() {
                return sql.toString();
            }
        }
        
        String query = new SQLBuilder()
                .select("name", "age", "email")
                .from("users")
                .where("age > 18")
                .orderBy("name")
                .limit(10)
                .build();
        
        System.out.println("Generated SQL:");
        System.out.println("  " + query);
        
        System.out.println();
    }

    /**
     * EXERCISE 13: Build extensible framework with default methods
     * Solution demonstrates framework design with extension points
     */
    private static void exercise13_ExtensibleFramework() {
        System.out.println("EXERCISE 13: Extensible Framework");
        System.out.println("-".repeat(60));
        
        interface EventHandler<T> {
            void handle(T event);
            
            // Extension points with default implementations
            default void beforeHandle(T event) {
                // Hook for preprocessing
            }
            
            default void afterHandle(T event) {
                // Hook for postprocessing
            }
            
            default void onError(T event, Exception e) {
                System.out.println("  [ERROR] " + e.getMessage());
            }
            
            default boolean canHandle(T event) {
                return true;
            }
            
            // Template method pattern
            default void process(T event) {
                if (canHandle(event)) {
                    try {
                        beforeHandle(event);
                        handle(event);
                        afterHandle(event);
                    } catch (Exception e) {
                        onError(event, e);
                    }
                }
            }
        }
        
        class UserEventHandler implements EventHandler<String> {
            @Override
            public void handle(String event) {
                System.out.println("  Handling user event: " + event);
            }
            
            @Override
            public void beforeHandle(String event) {
                System.out.println("  [Before] Validating: " + event);
            }
            
            @Override
            public void afterHandle(String event) {
                System.out.println("  [After] Logging: " + event);
            }
        }
        
        EventHandler<String> handler = new UserEventHandler();
        handler.process("UserLogin");
        
        System.out.println();
    }
}
