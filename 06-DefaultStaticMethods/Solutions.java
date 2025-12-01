package defaultstaticmethods;

/**
 * Solutions to Default and Static Methods Exercises
 */
public class Solutions {
    public static void main(String[] args) {
        System.out.println("=== SOLUTIONS ===\n");
        
        // 1. Interface with default method
        Greeting greeter = new EnglishGreeting();
        greeter.sayHello();
        greeter.sayGoodbye();  // Default method
        
        // 5. Call static method
        System.out.println(Greeting.getDefaultLanguage());
    }
    
    interface Greeting {
        void sayHello();
        
        // 1. Default method
        default void sayGoodbye() {
            System.out.println("Goodbye!");
        }
        
        // 2. Static method
        static String getDefaultLanguage() {
            return "English";
        }
    }
    
    static class EnglishGreeting implements Greeting {
        @Override
        public void sayHello() {
            System.out.println("Hello!");
        }
        // 3. Uses default sayGoodbye()
    }
    
    static class SpanishGreeting implements Greeting {
        @Override
        public void sayHello() {
            System.out.println("Hola!");
        }
        
        // 4. Override default method
        @Override
        public void sayGoodbye() {
            System.out.println("Adiós!");
        }
    }
}
