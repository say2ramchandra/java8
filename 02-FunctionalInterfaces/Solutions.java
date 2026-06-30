package functionalinterfaces;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO FUNCTIONAL INTERFACES EXERCISES
 * ============================================
 * Comprehensive solutions with explanations
 * 
 * @author Java 8 Mastery Course
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== FUNCTIONAL INTERFACES - EXERCISE SOLUTIONS ===\n");
        
        exercise1_UnderstandingPredicate();
        exercise2_WorkingWithFunction();
        exercise3_ConsumerPractice();
        exercise4_SupplierBasics();
        exercise5_CustomFunctionalInterfaces();
        exercise6_PredicateCombinations();
        exercise7_FunctionChaining();
        exercise9_ValidationFramework();
    }

    // ==================== BASIC EXERCISES ====================

    private static void exercise1_UnderstandingPredicate() {
        System.out.println("EXERCISE 1: Understanding Predicate");
        System.out.println("-".repeat(60));
        
        // 1. Check if number is odd
        Predicate<Integer> isOdd = num -> num % 2 != 0;
        System.out.println("Is 7 odd? " + isOdd.test(7));
        System.out.println("Is 8 odd? " + isOdd.test(8));
        
        // 2. Check if string contains "Java"
        Predicate<String> containsJava = str -> str.contains("Java");
        System.out.println("'Java Programming' contains Java? " + containsJava.test("Java Programming"));
        System.out.println("'Python' contains Java? " + containsJava.test("Python"));
        
        // 3. Check if number is within range 10-100
        Predicate<Integer> inRange = num -> num >= 10 && num <= 100;
        System.out.println("Is 50 in range [10,100]? " + inRange.test(50));
        System.out.println("Is 150 in range [10,100]? " + inRange.test(150));
        
        // 4. Check if string length > 10
        Predicate<String> isLong = str -> str.length() > 10;
        System.out.println("'Short' length > 10? " + isLong.test("Short"));
        System.out.println("'This is a long string' length > 10? " + isLong.test("This is a long string"));
        
        // 5. Combine: even AND greater than 50
        Predicate<Integer> isEven = num -> num % 2 == 0;
        Predicate<Integer> greaterThan50 = num -> num > 50;
        Predicate<Integer> evenAndGreaterThan50 = isEven.and(greaterThan50);
        System.out.println("Is 60 even AND > 50? " + evenAndGreaterThan50.test(60));
        System.out.println("Is 40 even AND > 50? " + evenAndGreaterThan50.test(40));
        
        System.out.println();
    }

    private static void exercise2_WorkingWithFunction() {
        System.out.println("EXERCISE 2: Working with Function");
        System.out.println("-".repeat(60));
        
        // 1. String to character count
        Function<String, Integer> charCount = str -> str.length();
        System.out.println("Character count of 'Hello': " + charCount.apply("Hello"));
        
        // 2. Integer to binary string
        Function<Integer, String> toBinary = num -> Integer.toBinaryString(num);
        System.out.println("10 in binary: " + toBinary.apply(10));
        
        // 3. Extract domain from email
        Function<String, String> extractDomain = email -> {
            int atIndex = email.indexOf('@');
            return atIndex >= 0 ? email.substring(atIndex + 1) : "";
        };
        System.out.println("Domain of 'user@gmail.com': " + extractDomain.apply("user@gmail.com"));
        
        // 4. Square and add 10
        Function<Integer, Integer> squareAndAdd10 = num -> num * num + 10;
        System.out.println("5 squared + 10: " + squareAndAdd10.apply(5));
        
        // 5. Chain: trim then uppercase
        Function<String, String> trimAndUpper = ((Function<String, String>) String::trim)
                .andThen(String::toUpperCase);
        System.out.println("'  hello  ' trimmed and upper: '" + trimAndUpper.apply("  hello  ") + "'");
        
        System.out.println();
    }

    private static void exercise3_ConsumerPractice() {
        System.out.println("EXERCISE 3: Consumer Practice");
        System.out.println("-".repeat(60));
        
        // 1. Print with prefix
        Consumer<Integer> printWithPrefix = num -> System.out.println("Number: " + num);
        printWithPrefix.accept(42);
        
        // 2. Add to ArrayList
        List<String> list = new ArrayList<>();
        Consumer<String> addToList = list::add;
        addToList.accept("Item 1");
        addToList.accept("Item 2");
        System.out.println("List after additions: " + list);
        
        // 3. Increment Person's age
        Person person = new Person("John", 25);
        Consumer<Person> incrementAge = p -> p.age++;
        System.out.println("Before: " + person);
        incrementAge.accept(person);
        System.out.println("After: " + person);
        
        // 4. Log with timestamp
        Consumer<String> logWithTimestamp = msg -> 
            System.out.println("[" + System.currentTimeMillis() + "] " + msg);
        logWithTimestamp.accept("Application started");
        
        // 5. Chain: print length, then print reversed
        Consumer<String> printLength = str -> System.out.println("Length: " + str.length());
        Consumer<String> printReversed = str -> 
            System.out.println("Reversed: " + new StringBuilder(str).reverse());
        Consumer<String> combined = printLength.andThen(printReversed);
        combined.accept("Lambda");
        
        System.out.println();
    }

    private static void exercise4_SupplierBasics() {
        System.out.println("EXERCISE 4: Supplier Basics");
        System.out.println("-".repeat(60));
        
        // 1. Current date/time
        Supplier<String> dateTimeSupplier = () -> new Date().toString();
        System.out.println("Current time: " + dateTimeSupplier.get());
        
        // 2. Random boolean
        Supplier<Boolean> randomBoolean = () -> Math.random() > 0.5;
        System.out.println("Random boolean: " + randomBoolean.get());
        
        // 3. UUID string
        Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();
        System.out.println("UUID: " + uuidSupplier.get());
        
        // 4. Empty ArrayList
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;
        System.out.println("New list: " + listSupplier.get());
        
        // 5. PI value
        Supplier<Double> piSupplier = () -> Math.PI;
        System.out.println("PI: " + piSupplier.get());
        
        System.out.println();
    }

    // ==================== INTERMEDIATE EXERCISES ====================

    private static void exercise5_CustomFunctionalInterfaces() {
        System.out.println("EXERCISE 5: Custom Functional Interfaces");
        System.out.println("-".repeat(60));
        
        // 1. StringProcessor
        StringProcessor toUpperCase = str -> str.toUpperCase();
        StringProcessor addPrefix = str -> "Processed: " + str;
        System.out.println(toUpperCase.process("hello"));
        System.out.println(addPrefix.process("data"));
        
        // 2. NumberValidator
        NumberValidator positiveValidator = num -> 
            num > 0 ? "Valid: Positive number" : "Invalid: Must be positive";
        System.out.println(positiveValidator.validate(10));
        System.out.println(positiveValidator.validate(-5));
        
        // 3. DataConverter
        DataConverter<String, Integer> stringToInt = Integer::parseInt;
        DataConverter<Integer, String> intToHex = Integer::toHexString;
        System.out.println("'123' to int: " + stringToInt.convert("123"));
        System.out.println("255 to hex: " + intToHex.convert(255));
        
        // 4. TriPredicate
        TriPredicate<Integer, Integer, Integer> isSumEven = (a, b, c) -> (a + b + c) % 2 == 0;
        System.out.println("Is 1+2+3 even? " + isSumEven.test(1, 2, 3));
        System.out.println("Is 2+2+2 even? " + isSumEven.test(2, 2, 2));
        
        // 5. QuadFunction
        QuadFunction<Integer, Integer, Integer, Integer, Double> average = 
            (a, b, c, d) -> (a + b + c + d) / 4.0;
        System.out.println("Average of 10,20,30,40: " + average.apply(10, 20, 30, 40));
        
        System.out.println();
    }

    private static void exercise6_PredicateCombinations() {
        System.out.println("EXERCISE 6: Predicate Combinations");
        System.out.println("-".repeat(60));
        
        // Define base predicates
        Predicate<User> isAdult = user -> user.age >= 18;
        Predicate<User> hasValidEmail = user -> user.email.contains("@");
        Predicate<User> hasStrongPassword = user -> user.password.length() >= 8;
        Predicate<User> isPremium = user -> user.isPremiumMember;
        
        // 1. Combine all with AND
        Predicate<User> allConditions = isAdult
                .and(hasValidEmail)
                .and(hasStrongPassword)
                .and(isPremium);
        
        // 2. Adult OR Premium
        Predicate<User> adultOrPremium = isAdult.or(isPremium);
        
        // 3. Negate adult
        Predicate<User> isMinor = isAdult.negate();
        
        // Test users
        User user1 = new User("Alice", 25, "alice@email.com", "strong123", true);
        User user2 = new User("Bob", 16, "bob@email.com", "weak", false);
        User user3 = new User("Charlie", 30, "charlie@email.com", "pass123!", false);
        
        System.out.println("User1 passes all conditions: " + allConditions.test(user1));
        System.out.println("User2 is adult OR premium: " + adultOrPremium.test(user2));
        System.out.println("User2 is minor: " + isMinor.test(user2));
        
        System.out.println();
    }

    private static void exercise7_FunctionChaining() {
        System.out.println("EXERCISE 7: Function Chaining");
        System.out.println("-".repeat(60));
        
        // CSV: "John,Doe,30,Engineer"
        String csv = "John,Doe,30,Engineer";
        
        // Step 1: Split by comma
        Function<String, String[]> splitByComma = str -> str.split(",");
        
        // Step 2: Convert to Person
        Function<String[], PersonData> toPerson = parts -> 
            new PersonData(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
        
        // Step 3: Calculate score
        Function<PersonData, Integer> calculateScore = p -> {
            int ageScore = p.age > 25 ? 50 : 30;
            int professionScore = p.profession.equals("Engineer") ? 50 : 30;
            return ageScore + professionScore;
        };
        
        // Step 4: Format as JSON
        Function<Integer, String> toJSON = score -> 
            String.format("{\"score\": %d}", score);
        
        // Chain all functions
        Function<String, String> pipeline = splitByComma
                .andThen(toPerson)
                .andThen(calculateScore)
                .andThen(toJSON);
        
        String result = pipeline.apply(csv);
        System.out.println("Pipeline result: " + result);
        
        System.out.println();
    }

    // ==================== ADVANCED EXERCISES ====================

    private static void exercise9_ValidationFramework() {
        System.out.println("EXERCISE 9: Validation Framework");
        System.out.println("-".repeat(60));
        
        // Create validators
        Validator<String> emailValidator = value -> {
            if (value == null || !value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return new ValidationResult(false, "Invalid email format");
            }
            return new ValidationResult(true, "Email is valid");
        };
        
        Validator<String> phoneValidator = value -> {
            if (value == null || !value.matches("^\\d{10}$")) {
                return new ValidationResult(false, "Phone must be 10 digits");
            }
            return new ValidationResult(true, "Phone is valid");
        };
        
        Validator<String> passwordValidator = value -> {
            if (value == null || value.length() < 8) {
                return new ValidationResult(false, "Password must be at least 8 characters");
            }
            if (!value.matches(".*[A-Z].*")) {
                return new ValidationResult(false, "Password must contain uppercase letter");
            }
            if (!value.matches(".*[a-z].*")) {
                return new ValidationResult(false, "Password must contain lowercase letter");
            }
            if (!value.matches(".*\\d.*")) {
                return new ValidationResult(false, "Password must contain digit");
            }
            return new ValidationResult(true, "Password is strong");
        };
        
        // Test validations
        System.out.println("\nEmail Validation:");
        System.out.println("  'user@example.com': " + emailValidator.validate("user@example.com"));
        System.out.println("  'invalid-email': " + emailValidator.validate("invalid-email"));
        
        System.out.println("\nPhone Validation:");
        System.out.println("  '1234567890': " + phoneValidator.validate("1234567890"));
        System.out.println("  '12345': " + phoneValidator.validate("12345"));
        
        System.out.println("\nPassword Validation:");
        System.out.println("  'Pass123!': " + passwordValidator.validate("Pass123!"));
        System.out.println("  'weak': " + passwordValidator.validate("weak"));
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================

    static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String toString() {
            return name + " (" + age + ")";
        }
    }

    static class User {
        String name;
        int age;
        String email;
        String password;
        boolean isPremiumMember;
        
        User(String name, int age, String email, String password, boolean isPremiumMember) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.password = password;
            this.isPremiumMember = isPremiumMember;
        }
    }

    static class PersonData {
        String firstName, lastName, profession;
        int age;
        PersonData(String firstName, String lastName, int age, String profession) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.profession = profession;
        }
    }

    // Custom Functional Interfaces
    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }

    @FunctionalInterface
    interface NumberValidator {
        String validate(int number);
    }

    @FunctionalInterface
    interface DataConverter<T, R> {
        R convert(T input);
    }

    @FunctionalInterface
    interface TriPredicate<T, U, V> {
        boolean test(T t, U u, V v);
    }

    @FunctionalInterface
    interface QuadFunction<T, U, V, W, R> {
        R apply(T t, U u, V v, W w);
    }

    @FunctionalInterface
    interface Validator<T> {
        ValidationResult validate(T value);
    }

    static class ValidationResult {
        boolean isValid;
        String message;
        
        ValidationResult(boolean isValid, String message) {
            this.isValid = isValid;
            this.message = message;
        }
        
        public String toString() {
            return (isValid ? "✓ " : "✗ ") + message;
        }
    }
}
