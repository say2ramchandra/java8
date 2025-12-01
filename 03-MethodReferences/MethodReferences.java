package methodreferences;

import java.util.*;
import java.util.function.*;

/**
 * METHOD REFERENCES IN JAVA 8
 * ===========================
 * 
 * WHAT ARE METHOD REFERENCES?
 * ---------------------------
 * Method references are a shorthand notation of a lambda expression to call a method.
 * They make code more readable when a lambda expression only calls an existing method.
 * 
 * SYNTAX: ClassName::methodName or object::methodName
 * 
 * TYPES OF METHOD REFERENCES:
 * ---------------------------
 * 1. Reference to a Static Method
 *    Syntax: ClassName::staticMethodName
 *    Example: Integer::parseInt
 *    Lambda equivalent: str -> Integer.parseInt(str)
 * 
 * 2. Reference to an Instance Method of a Particular Object
 *    Syntax: objectReference::instanceMethodName
 *    Example: System.out::println
 *    Lambda equivalent: x -> System.out.println(x)
 * 
 * 3. Reference to an Instance Method of an Arbitrary Object of a Particular Type
 *    Syntax: ClassName::instanceMethodName
 *    Example: String::toUpperCase
 *    Lambda equivalent: str -> str.toUpperCase()
 * 
 * 4. Reference to a Constructor
 *    Syntax: ClassName::new
 *    Example: ArrayList::new
 *    Lambda equivalent: () -> new ArrayList()
 * 
 * BENEFITS:
 * ---------
 * - More concise than lambda expressions
 * - Improved code readability
 * - Reusability of existing methods
 * - Better IDE support and refactoring
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class MethodReferences {

    public static void main(String[] args) {
        System.out.println("=== PART 1: UNDERSTANDING METHOD REFERENCES ===\n");
        demonstrateStaticMethodReferences();
        demonstrateInstanceMethodReferences();
        demonstrateArbitraryObjectMethodReferences();
        demonstrateConstructorReferences();
        
        System.out.println("\n=== PART 2: REAL-WORLD SCENARIOS ===\n");
        realWorldScenario1_DataProcessing();
        realWorldScenario2_ObjectFactory();
        realWorldScenario3_SortingAndComparison();
    }

    /**
     * EXAMPLE 1: Static Method References
     * -----------------------------------
     * Reference to static methods of a class
     */
    private static void demonstrateStaticMethodReferences() {
        System.out.println("1. STATIC METHOD REFERENCES:");
        System.out.println("-".repeat(60));
        
        // Lambda expression
        Function<String, Integer> lambdaParser = str -> Integer.parseInt(str);
        System.out.println("Lambda: " + lambdaParser.apply("123"));
        
        // Method reference - more concise
        Function<String, Integer> methodRefParser = Integer::parseInt;
        System.out.println("Method Ref: " + methodRefParser.apply("456"));
        
        // Static method reference with Math class
        BinaryOperator<Integer> max = Math::max;
        System.out.println("Max of 10 and 20: " + max.apply(10, 20));
        
        UnaryOperator<Double> sqrt = Math::sqrt;
        System.out.println("Square root of 16: " + sqrt.apply(16.0));
        
        // Custom static method reference
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
        numbers.stream()
                .map(Integer::parseInt)  // Static method reference
                .forEach(System.out::println);
        
        System.out.println();
    }

    /**
     * EXAMPLE 2: Instance Method References (Particular Object)
     * ---------------------------------------------------------
     * Reference to instance method of a specific object
     */
    private static void demonstrateInstanceMethodReferences() {
        System.out.println("2. INSTANCE METHOD REFERENCES (PARTICULAR OBJECT):");
        System.out.println("-".repeat(60));
        
        // Reference to System.out.println
        Consumer<String> printer = System.out::println;
        printer.accept("Hello from method reference!");
        
        // Reference to instance method of custom object
        Printer myPrinter = new Printer(">>> ");
        Consumer<String> customPrinter = myPrinter::print;
        customPrinter.accept("Custom printer message");
        
        // Using with collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println("\nPrinting names:");
        names.forEach(System.out::println);  // Instance method reference
        
        // String instance method
        String prefix = "Hello, ";
        Function<String, String> greeter = prefix::concat;
        System.out.println(greeter.apply("World"));
        
        System.out.println();
    }

    /**
     * EXAMPLE 3: Instance Method References (Arbitrary Object)
     * --------------------------------------------------------
     * Reference to instance method of an arbitrary object of a particular type
     */
    private static void demonstrateArbitraryObjectMethodReferences() {
        System.out.println("3. INSTANCE METHOD REFERENCES (ARBITRARY OBJECT):");
        System.out.println("-".repeat(60));
        
        // String::toUpperCase
        List<String> words = Arrays.asList("java", "python", "javascript");
        
        // Lambda expression
        words.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);
        
        System.out.println("\nUsing method reference:");
        words.stream()
                .map(String::toUpperCase)  // Method reference
                .forEach(System.out::println);
        
        // String::length
        List<Integer> lengths = words.stream()
                .map(String::length)
                .toList();
        System.out.println("\nWord lengths: " + lengths);
        
        // Sorting with method reference
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        names.sort(String::compareToIgnoreCase);
        System.out.println("\nSorted names: " + names);
        
        // Custom class method reference
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 20)
        );
        
        people.stream()
                .map(Person::getName)  // Instance method reference
                .forEach(System.out::println);
        
        System.out.println();
    }

    /**
     * EXAMPLE 4: Constructor References
     * ---------------------------------
     * Reference to constructors
     */
    private static void demonstrateConstructorReferences() {
        System.out.println("4. CONSTRUCTOR REFERENCES:");
        System.out.println("-".repeat(60));
        
        // ArrayList constructor reference
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();
        list.add("Item 1");
        System.out.println("Created list: " + list);
        
        // HashMap constructor reference
        Supplier<Map<String, Integer>> mapSupplier = HashMap::new;
        Map<String, Integer> map = mapSupplier.get();
        map.put("One", 1);
        System.out.println("Created map: " + map);
        
        // Custom class constructor reference
        Function<String, Person> personCreator = name -> new Person(name, 0);
        // Equivalent method reference
        BiFunction<String, Integer, Person> personFactory = Person::new;
        
        Person p1 = personFactory.apply("Alice", 25);
        Person p2 = personFactory.apply("Bob", 30);
        System.out.println("Created: " + p1);
        System.out.println("Created: " + p2);
        
        // Array constructor reference
        IntFunction<String[]> arrayCreator = String[]::new;
        String[] array = arrayCreator.apply(5);
        System.out.println("Created array of length: " + array.length);
        
        // Using constructor reference with streams
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Person> persons = names.stream()
                .map(name -> new Person(name, 0))  // Lambda
                .toList();
        
        System.out.println("\nCreated persons: " + persons);
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 1: Data Processing Pipeline
     * -----------------------------------------------
     * Using method references in data transformation
     */
    private static void realWorldScenario1_DataProcessing() {
        System.out.println("REAL-WORLD SCENARIO 1: CSV Data Processing");
        System.out.println("-".repeat(60));
        
        List<String> csvData = Arrays.asList(
            "1,John Doe,john@email.com,30",
            "2,Jane Smith,jane@email.com,25",
            "3,Bob Johnson,bob@email.com,35"
        );
        
        System.out.println("\nProcessing CSV data:");
        List<Employee> employees = csvData.stream()
                .map(String::trim)                    // Method reference
                .map(line -> line.split(","))
                .map(Employee::fromArray)              // Static method reference
                .toList();
        
        employees.forEach(System.out::println);       // Instance method reference
        
        // Extract and process emails
        System.out.println("\nEmail addresses:");
        employees.stream()
                .map(Employee::getEmail)               // Instance method reference
                .map(String::toUpperCase)              // Method reference
                .forEach(System.out::println);
        
        // Calculate statistics
        double avgAge = employees.stream()
                .mapToInt(Employee::getAge)            // Method reference
                .average()
                .orElse(0.0);
        System.out.println("\nAverage age: " + avgAge);
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 2: Object Factory Pattern
     * ---------------------------------------------
     * Using constructor references for object creation
     */
    private static void realWorldScenario2_ObjectFactory() {
        System.out.println("REAL-WORLD SCENARIO 2: Vehicle Factory");
        System.out.println("-".repeat(60));
        
        // Factory using constructor references
        VehicleFactory factory = new VehicleFactory();
        
        // Register vehicle constructors
        factory.register("Car", Car::new);
        factory.register("Truck", Truck::new);
        factory.register("Motorcycle", Motorcycle::new);
        
        // Create vehicles
        System.out.println("\nCreating vehicles:");
        Vehicle car = factory.create("Car", "Toyota Camry");
        Vehicle truck = factory.create("Truck", "Ford F-150");
        Vehicle motorcycle = factory.create("Motorcycle", "Harley Davidson");
        
        car.start();
        truck.start();
        motorcycle.start();
        
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 3: Sorting and Comparison
     * ---------------------------------------------
     * Using method references for sorting
     */
    private static void realWorldScenario3_SortingAndComparison() {
        System.out.println("REAL-WORLD SCENARIO 3: Product Sorting");
        System.out.println("-".repeat(60));
        
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99, 4.5),
            new Product("Mouse", 29.99, 4.8),
            new Product("Keyboard", 79.99, 4.3),
            new Product("Monitor", 299.99, 4.6)
        );
        
        // Sort by price using method reference
        System.out.println("\nSorted by price:");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(System.out::println);
        
        // Sort by rating (descending)
        System.out.println("\nSorted by rating (desc):");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getRating).reversed())
                .forEach(System.out::println);
        
        // Sort by name
        System.out.println("\nSorted by name:");
        products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .forEach(System.out::println);
        
        // Multi-level sorting: rating desc, then price asc
        System.out.println("\nSorted by rating (desc), then price (asc):");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getRating).reversed()
                        .thenComparingDouble(Product::getPrice))
                .forEach(System.out::println);
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================

    static class Printer {
        private String prefix;
        Printer(String prefix) { this.prefix = prefix; }
        void print(String message) {
            System.out.println(prefix + message);
        }
    }

    static class Person {
        private String name;
        private int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        String getName() { return name; }
        int getAge() { return age; }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }

    static class Employee {
        private int id;
        private String name;
        private String email;
        private int age;
        
        Employee(int id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }
        
        static Employee fromArray(String[] parts) {
            return new Employee(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Integer.parseInt(parts[3])
            );
        }
        
        String getEmail() { return email; }
        int getAge() { return age; }
        
        @Override
        public String toString() {
            return String.format("Employee[id=%d, name=%s, email=%s, age=%d]",
                    id, name, email, age);
        }
    }

    interface Vehicle {
        void start();
    }

    static class Car implements Vehicle {
        private String model;
        Car(String model) { this.model = model; }
        public void start() { System.out.println("Car " + model + " is starting..."); }
    }

    static class Truck implements Vehicle {
        private String model;
        Truck(String model) { this.model = model; }
        public void start() { System.out.println("Truck " + model + " is starting..."); }
    }

    static class Motorcycle implements Vehicle {
        private String model;
        Motorcycle(String model) { this.model = model; }
        public void start() { System.out.println("Motorcycle " + model + " is starting..."); }
    }

    static class VehicleFactory {
        private Map<String, Function<String, Vehicle>> creators = new HashMap<>();
        
        void register(String type, Function<String, Vehicle> creator) {
            creators.put(type, creator);
        }
        
        Vehicle create(String type, String model) {
            Function<String, Vehicle> creator = creators.get(type);
            if (creator == null) {
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
            }
            return creator.apply(model);
        }
    }

    static class Product {
        private String name;
        private double price;
        private double rating;
        
        Product(String name, double price, double rating) {
            this.name = name;
            this.price = price;
            this.rating = rating;
        }
        
        String getName() { return name; }
        double getPrice() { return price; }
        double getRating() { return rating; }
        
        @Override
        public String toString() {
            return String.format("%s - $%.2f (%.1f★)", name, price, rating);
        }
    }
}
