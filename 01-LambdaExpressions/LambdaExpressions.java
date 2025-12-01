package lambdaexpressions;

import java.util.*;
import java.util.function.*;

/**
 * LAMBDA EXPRESSIONS IN JAVA 8
 * ============================
 * 
 * Lambda expressions are a powerful feature introduced in Java 8 that enable functional programming.
 * They provide a clear and concise way to represent one method interface using an expression.
 * 
 * WHAT IS A LAMBDA EXPRESSION?
 * ---------------------------
 * A lambda expression is an anonymous function (a function without a name) that can be passed around
 * as if it were an object and executed on demand.
 * 
 * SYNTAX:
 * -------
 * (parameters) -> expression
 * OR
 * (parameters) -> { statements; }
 * 
 * COMPONENTS:
 * -----------
 * 1. Parameter List: Zero or more parameters, enclosed in parentheses
 * 2. Arrow Token: ->
 * 3. Body: Either a single expression or a block of statements
 * 
 * BENEFITS:
 * ---------
 * - Reduces code verbosity
 * - Enables functional programming
 * - Makes code more readable and maintainable
 * - Facilitates parallel programming
 * - Eliminates the need for anonymous inner classes in many cases
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class LambdaExpressions {

    public static void main(String[] args) {
        System.out.println("=== PART 1: UNDERSTANDING LAMBDA EXPRESSIONS ===\n");
        demonstrateBasicSyntax();
        demonstrateDifferentTypes();
        demonstrateWithCollections();
        
        System.out.println("\n=== PART 2: REAL-WORLD SCENARIOS ===\n");
        realWorldScenario1_EmployeeFiltering();
        realWorldScenario2_EventHandling();
        realWorldScenario3_DataTransformation();
    }

    /**
     * EXAMPLE 1: Basic Lambda Syntax
     * ------------------------------
     * Demonstrates different ways to write lambda expressions
     */
    private static void demonstrateBasicSyntax() {
        System.out.println("1. BASIC LAMBDA SYNTAX:");
        System.out.println("-".repeat(50));
        
        // Traditional approach using anonymous class
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional: Running in a thread");
            }
        };
        
        // Lambda expression - concise and clean
        Runnable lambdaRunnable = () -> System.out.println("Lambda: Running in a thread");
        
        traditionalRunnable.run();
        lambdaRunnable.run();
        
        // Lambda with single parameter (parentheses optional)
        Consumer<String> print = message -> System.out.println("Message: " + message);
        print.accept("Hello, Lambda!");
        
        // Lambda with multiple parameters
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.apply(5, 3));
        
        // Lambda with block body
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> {
            System.out.println("Subtracting " + b + " from " + a);
            return a - b;
        };
        System.out.println("Result: " + subtract.apply(10, 4));
        System.out.println();
    }

    /**
     * EXAMPLE 2: Different Types of Lambda Expressions
     * ------------------------------------------------
     * Shows various lambda expression patterns
     */
    private static void demonstrateDifferentTypes() {
        System.out.println("2. DIFFERENT TYPES OF LAMBDA EXPRESSIONS:");
        System.out.println("-".repeat(50));
        
        // Type 1: No parameters
        Supplier<String> greeting = () -> "Hello, World!";
        System.out.println("No params: " + greeting.get());
        
        // Type 2: Single parameter with type inference
        Function<String, Integer> length = str -> str.length();
        System.out.println("Single param: Length of 'Java' = " + length.apply("Java"));
        
        // Type 3: Single parameter with explicit type
        Function<String, String> uppercase = (String s) -> s.toUpperCase();
        System.out.println("Explicit type: " + uppercase.apply("lambda"));
        
        // Type 4: Multiple parameters
        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() > len;
        System.out.println("Multiple params: Is 'Lambda' longer than 5? " + 
                          checkLength.test("Lambda", 5));
        
        // Type 5: Lambda with complex body
        BinaryOperator<Integer> complexOperation = (x, y) -> {
            int sum = x + y;
            int product = x * y;
            return sum * product;
        };
        System.out.println("Complex operation: " + complexOperation.apply(3, 4));
        System.out.println();
    }

    /**
     * EXAMPLE 3: Lambda Expressions with Collections
     * ---------------------------------------------
     * Shows how lambdas simplify collection operations
     */
    private static void demonstrateWithCollections() {
        System.out.println("3. LAMBDA EXPRESSIONS WITH COLLECTIONS:");
        System.out.println("-".repeat(50));
        
        List<String> languages = Arrays.asList("Java", "Python", "C++", "JavaScript", "Go");
        
        // Traditional forEach with anonymous class
        System.out.println("Traditional approach:");
        languages.forEach(new Consumer<String>() {
            @Override
            public void accept(String lang) {
                System.out.println("  - " + lang);
            }
        });
        
        // Lambda expression - much cleaner
        System.out.println("\nLambda approach:");
        languages.forEach(lang -> System.out.println("  - " + lang));
        
        // Sorting with lambda
        List<String> sortedLanguages = new ArrayList<>(languages);
        sortedLanguages.sort((a, b) -> a.compareTo(b));
        System.out.println("\nSorted: " + sortedLanguages);
        
        // Filtering with lambda (using removeIf)
        List<String> filtered = new ArrayList<>(languages);
        filtered.removeIf(lang -> lang.length() < 4);
        System.out.println("Filtered (length >= 4): " + filtered);
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 1: Employee Filtering and Processing
     * --------------------------------------------------------
     * Demonstrates using lambdas for filtering and processing employee data
     */
    private static void realWorldScenario1_EmployeeFiltering() {
        System.out.println("REAL-WORLD SCENARIO 1: Employee Management System");
        System.out.println("-".repeat(50));
        
        // Create sample employee data
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 75000, 28),
            new Employee("Bob", "Marketing", 65000, 35),
            new Employee("Charlie", "Engineering", 85000, 32),
            new Employee("Diana", "HR", 60000, 29),
            new Employee("Eve", "Engineering", 95000, 40),
            new Employee("Frank", "Marketing", 70000, 26)
        );
        
        // Filter employees by department using lambda
        System.out.println("\nEngineering Department:");
        employees.stream()
                .filter(emp -> emp.department.equals("Engineering"))
                .forEach(emp -> System.out.println("  " + emp));
        
        // Filter by salary range
        System.out.println("\nEmployees earning more than $70,000:");
        employees.stream()
                .filter(emp -> emp.salary > 70000)
                .forEach(emp -> System.out.println("  " + emp.name + ": $" + emp.salary));
        
        // Complex filtering with multiple conditions
        System.out.println("\nSenior Engineering staff (age > 30, dept = Engineering):");
        employees.stream()
                .filter(emp -> emp.age > 30 && emp.department.equals("Engineering"))
                .forEach(emp -> System.out.println("  " + emp));
        
        // Calculate average salary using lambda
        double avgSalary = employees.stream()
                .mapToDouble(emp -> emp.salary)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage Salary: $" + String.format("%.2f", avgSalary));
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 2: Event Handling System
     * -------------------------------------------
     * Simulates an event-driven system using lambda expressions
     */
    private static void realWorldScenario2_EventHandling() {
        System.out.println("REAL-WORLD SCENARIO 2: Event Handling System");
        System.out.println("-".repeat(50));
        
        // Create an event processor
        EventProcessor processor = new EventProcessor();
        
        // Register event handlers using lambdas
        processor.onUserLogin(user -> {
            System.out.println("  [LOGIN] User '" + user + "' logged in at " + new Date());
            System.out.println("  [SECURITY] Logging IP address and session details...");
        });
        
        processor.onUserLogout(user -> {
            System.out.println("  [LOGOUT] User '" + user + "' logged out");
            System.out.println("  [CLEANUP] Cleaning up session data...");
        });
        
        processor.onError((errorCode, message) -> {
            System.out.println("  [ERROR] Code: " + errorCode);
            System.out.println("  [ERROR] Message: " + message);
            System.out.println("  [ERROR] Sending notification to admin...");
        });
        
        // Trigger events
        System.out.println("\nTriggering Events:");
        processor.triggerLogin("john.doe@company.com");
        processor.triggerError(500, "Database connection failed");
        processor.triggerLogout("john.doe@company.com");
        System.out.println();
    }

    /**
     * REAL-WORLD SCENARIO 3: Data Transformation Pipeline
     * --------------------------------------------------
     * Shows how lambdas enable elegant data transformation
     */
    private static void realWorldScenario3_DataTransformation() {
        System.out.println("REAL-WORLD SCENARIO 3: E-Commerce Order Processing");
        System.out.println("-".repeat(50));
        
        // Sample order data
        List<Order> orders = Arrays.asList(
            new Order("ORD001", "Alice", 150.00, "PENDING"),
            new Order("ORD002", "Bob", 75.00, "SHIPPED"),
            new Order("ORD003", "Charlie", 200.00, "PENDING"),
            new Order("ORD004", "Diana", 50.00, "DELIVERED"),
            new Order("ORD005", "Eve", 300.00, "PENDING")
        );
        
        // Transformation 1: Apply discount to pending orders over $100
        System.out.println("\nApplying 10% discount to pending orders over $100:");
        orders.stream()
                .filter(order -> order.status.equals("PENDING") && order.amount > 100)
                .forEach(order -> {
                    double discount = order.amount * 0.10;
                    double newAmount = order.amount - discount;
                    System.out.println("  " + order.orderId + ": $" + order.amount + 
                                     " -> $" + String.format("%.2f", newAmount) + 
                                     " (saved $" + String.format("%.2f", discount) + ")");
                });
        
        // Transformation 2: Generate shipping labels
        System.out.println("\nGenerating shipping labels for pending orders:");
        orders.stream()
                .filter(order -> order.status.equals("PENDING"))
                .map(order -> "SHIP TO: " + order.customerName + 
                            " | ORDER: " + order.orderId + 
                            " | AMOUNT: $" + order.amount)
                .forEach(label -> System.out.println("  " + label));
        
        // Transformation 3: Calculate total revenue by status
        System.out.println("\nRevenue Summary:");
        Map<String, Double> revenueByStatus = new HashMap<>();
        orders.forEach(order -> {
            revenueByStatus.merge(order.status, order.amount, (old, val) -> old + val);
        });
        revenueByStatus.forEach((status, total) -> 
            System.out.println("  " + status + ": $" + String.format("%.2f", total)));
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================
    
    /**
     * Employee class for demonstration purposes
     */
    static class Employee {
        String name;
        String department;
        double salary;
        int age;

        Employee(String name, String department, double salary, int age) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " (" + department + ", Age: " + age + ", $" + salary + ")";
        }
    }

    /**
     * Order class for e-commerce scenario
     */
    static class Order {
        String orderId;
        String customerName;
        double amount;
        String status;

        Order(String orderId, String customerName, double amount, String status) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.amount = amount;
            this.status = status;
        }
    }

    /**
     * Event Processor for event handling scenario
     */
    static class EventProcessor {
        private Consumer<String> loginHandler;
        private Consumer<String> logoutHandler;
        private BiConsumer<Integer, String> errorHandler;

        void onUserLogin(Consumer<String> handler) {
            this.loginHandler = handler;
        }

        void onUserLogout(Consumer<String> handler) {
            this.logoutHandler = handler;
        }

        void onError(BiConsumer<Integer, String> handler) {
            this.errorHandler = handler;
        }

        void triggerLogin(String user) {
            if (loginHandler != null) loginHandler.accept(user);
        }

        void triggerLogout(String user) {
            if (logoutHandler != null) logoutHandler.accept(user);
        }

        void triggerError(int code, String message) {
            if (errorHandler != null) errorHandler.accept(code, message);
        }
    }
}
