package defaultstaticmethods;

/**
 * DEFAULT AND STATIC METHODS IN INTERFACES (Java 8)
 * ==================================================
 * 
 * DEFAULT METHODS:
 * ----------------
 * - Allow adding new methods to interfaces without breaking existing implementations
 * - Declared with 'default' keyword
 * - Provide default implementation
 * - Can be overridden by implementing classes
 * - Enable backward compatibility
 * 
 * STATIC METHODS:
 * ---------------
 * - Utility methods related to the interface
 * - Called using interface name (not instance)
 * - Cannot be overridden
 * - Help organize helper methods
 * 
 * @author Java 8 Mastery Course
 */
public class DefaultAndStaticMethods {

    public static void main(String[] args) {
        System.out.println("=== DEFAULT AND STATIC METHODS IN INTERFACES ===\n");
        
        // Using default methods
        Vehicle car = new Car();
        car.start();  // Abstract method
        car.honk();   // Default method
        
        Vehicle bike = new Motorcycle();
        bike.start();
        bike.honk();  // Uses custom implementation
        
        // Using static methods
        System.out.println("\n" + Vehicle.getDescription());
        Vehicle.printInfo();
        
        // Real-world example: Calculator
        Calculator calc = new BasicCalculator();
        System.out.println("\nCalculator Demo:");
        System.out.println("Add: " + calc.add(5, 3));
        System.out.println("Subtract: " + calc.subtract(10, 4));
        System.out.println("Multiply: " + calc.multiply(6, 7));
        System.out.println("Divide: " + calc.divide(20, 4));
        System.out.println("Power: " + calc.power(2, 3));  // Default method
        System.out.println("Sqrt: " + Calculator.sqrt(16));  // Static method
    }

    // Example 1: Vehicle Interface
    interface Vehicle {
        // Abstract method - must be implemented
        void start();
        
        // Default method - can be used or overridden
        default void honk() {
            System.out.println("Generic honk sound!");
        }
        
        default void stop() {
            System.out.println("Vehicle stopping...");
        }
        
        // Static method - utility
        static String getDescription() {
            return "Vehicle interface provides common vehicle operations";
        }
        
        static void printInfo() {
            System.out.println("This is a vehicle interface with default and static methods");
        }
    }

    static class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("Car engine starting...");
        }
        // Uses default honk()
    }

    static class Motorcycle implements Vehicle {
        @Override
        public void start() {
            System.out.println("Motorcycle engine starting...");
        }
        
        @Override
        public void honk() {
            System.out.println("Beep beep!"); // Custom implementation
        }
    }

    // Example 2: Calculator Interface
    interface Calculator {
        // Abstract methods
        int add(int a, int b);
        int subtract(int a, int b);
        
        // Default methods - provide common functionality
        default int multiply(int a, int b) {
            return a * b;
        }
        
        default double divide(double a, double b) {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        }
        
        default double power(double base, double exponent) {
            return Math.pow(base, exponent);
        }
        
        // Static utility methods
        static double sqrt(double value) {
            return Math.sqrt(value);
        }
        
        static boolean isEven(int number) {
            return number % 2 == 0;
        }
    }

    static class BasicCalculator implements Calculator {
        @Override
        public int add(int a, int b) {
            return a + b;
        }
        
        @Override
        public int subtract(int a, int b) {
            return a - b;
        }
        // Inherits multiply(), divide(), power() default methods
    }
}
