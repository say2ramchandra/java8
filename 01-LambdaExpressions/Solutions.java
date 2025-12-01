package lambdaexpressions;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.time.*;

/**
 * SOLUTIONS TO LAMBDA EXPRESSIONS EXERCISES
 * =========================================
 * This file contains step-by-step solutions to all exercises from Exercises.md
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== LAMBDA EXPRESSIONS - EXERCISE SOLUTIONS ===\n");
        
        // Run solutions for each exercise
        exercise1_SimpleLambdaExpressions();
        exercise2_BasicComparators();
        exercise3_ListOperations();
        exercise4_ForEachPractice();
        exercise5_StudentGradeCalculator();
        exercise6_StringManipulation();
        exercise7_CalculatorOperations();
        exercise8_CustomFunctionalInterface();
        exercise9_EmployeeManagementSystem();
        exercise10_TransactionProcessing();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Simple Lambda Expressions
     * Solution demonstrates basic lambda syntax for common functional interfaces
     */
    private static void exercise1_SimpleLambdaExpressions() {
        System.out.println("EXERCISE 1: Simple Lambda Expressions");
        System.out.println("-".repeat(60));
        
        // 1. Runnable that prints "Hello from Lambda"
        Runnable runnable = () -> System.out.println("Hello from Lambda");
        runnable.run();
        
        // 2. Supplier that returns random number between 1 and 100
        Supplier<Integer> randomSupplier = () -> (int)(Math.random() * 100) + 1;
        System.out.println("Random number: " + randomSupplier.get());
        
        // 3. Consumer that prints string in uppercase
        Consumer<String> uppercaseConsumer = str -> System.out.println(str.toUpperCase());
        uppercaseConsumer.accept("hello world");
        
        // 4. Predicate that checks if number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));
        
        // 5. Function that returns length of string
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Lambda': " + stringLength.apply("Lambda"));
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Basic Comparators
     * Solution shows various sorting techniques using lambda expressions
     */
    private static void exercise2_BasicComparators() {
        System.out.println("EXERCISE 2: Basic Comparators");
        System.out.println("-".repeat(60));
        
        // Sample data
        List<String> strings = Arrays.asList("Zebra", "Apple", "Mango", "Banana", "Cat");
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        
        // 1. Sort strings alphabetically
        List<String> alphabetical = new ArrayList<>(strings);
        alphabetical.sort((s1, s2) -> s1.compareTo(s2));
        // Or more concisely: alphabetical.sort(String::compareTo);
        System.out.println("Alphabetical: " + alphabetical);
        
        // 2. Sort strings by length (shortest first)
        List<String> byLength = new ArrayList<>(strings);
        byLength.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("By length: " + byLength);
        
        // 3. Sort integers in descending order
        List<Integer> descending = new ArrayList<>(numbers);
        descending.sort((n1, n2) -> n2.compareTo(n1)); // Reversed comparison
        System.out.println("Descending: " + descending);
        
        // 4. Sort strings in reverse alphabetical order
        List<String> reverseAlpha = new ArrayList<>(strings);
        reverseAlpha.sort((s1, s2) -> s2.compareTo(s1));
        System.out.println("Reverse alphabetical: " + reverseAlpha);
        
        System.out.println();
    }

    /**
     * EXERCISE 3: List Operations
     * Solution demonstrates using removeIf() with lambda expressions
     */
    private static void exercise3_ListOperations() {
        System.out.println("EXERCISE 3: List Operations");
        System.out.println("-".repeat(60));
        
        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. Remove all even numbers
        List<Integer> noEvens = new ArrayList<>(original);
        noEvens.removeIf(num -> num % 2 == 0);
        System.out.println("After removing evens: " + noEvens);
        
        // 2. Remove all numbers greater than 5
        List<Integer> max5 = new ArrayList<>(original);
        max5.removeIf(num -> num > 5);
        System.out.println("After removing > 5: " + max5);
        
        // 3. Remove all numbers divisible by 3
        List<Integer> notDivBy3 = new ArrayList<>(original);
        notDivBy3.removeIf(num -> num % 3 == 0);
        System.out.println("After removing divisible by 3: " + notDivBy3);
        
        // 4. Remove all numbers that are not prime
        List<Integer> onlyPrimes = new ArrayList<>(original);
        onlyPrimes.removeIf(num -> !isPrime(num));
        System.out.println("Only primes: " + onlyPrimes);
        
        System.out.println();
    }

    /**
     * Helper method to check if a number is prime
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
     * EXERCISE 4: forEach Practice
     * Solution shows various ways to use forEach with lambda expressions
     */
    private static void exercise4_ForEachPractice() {
        System.out.println("EXERCISE 4: forEach Practice");
        System.out.println("-".repeat(60));
        
        List<String> movies = Arrays.asList(
            "The Matrix", "Inception", "Interstellar", 
            "The Godfather", "Pulp Fiction"
        );
        
        // 1. Print each movie name
        System.out.println("All movies:");
        movies.forEach(movie -> System.out.println("  - " + movie));
        
        // 2. Print with index
        System.out.println("\nMovies with index:");
        final int[] index = {1}; // Using array to modify in lambda
        movies.forEach(movie -> System.out.println("  " + index[0]++ + ". " + movie));
        
        // Alternative approach using IntStream
        System.out.println("\nMovies with index (alternative):");
        IntStream.range(0, movies.size())
                .forEach(i -> System.out.println("  " + (i + 1) + ". " + movies.get(i)));
        
        // 3. Print only movies containing 'a'
        System.out.println("\nMovies containing 'a':");
        movies.stream()
                .filter(movie -> movie.toLowerCase().contains("a"))
                .forEach(movie -> System.out.println("  - " + movie));
        
        // 4. Print in uppercase
        System.out.println("\nMovies in uppercase:");
        movies.forEach(movie -> System.out.println("  - " + movie.toUpperCase()));
        
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 5: Student Grade Calculator
     * Solution demonstrates working with custom objects and lambda expressions
     */
    private static void exercise5_StudentGradeCalculator() {
        System.out.println("EXERCISE 5: Student Grade Calculator");
        System.out.println("-".repeat(60));
        
        // Create sample students
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, new int[]{85, 90, 88, 92}),
            new Student("Bob", 21, new int[]{65, 70, 68, 72}),
            new Student("Charlie", 19, new int[]{95, 98, 96, 94}),
            new Student("Diana", 20, new int[]{45, 50, 48, 52}),
            new Student("Eve", 22, new int[]{78, 82, 80, 85}),
            new Student("Frank", 21, new int[]{30, 35, 32, 38})
        );
        
        // 1. Calculate and print average marks for each student
        System.out.println("Student Averages:");
        students.forEach(student -> {
            double avg = Arrays.stream(student.marks).average().orElse(0.0);
            System.out.printf("  %s: %.2f%%\n", student.name, avg);
        });
        
        // 2. Filter students who scored above 75%
        System.out.println("\nStudents with >75% average:");
        students.stream()
                .filter(student -> {
                    double avg = Arrays.stream(student.marks).average().orElse(0.0);
                    return avg > 75;
                })
                .forEach(student -> System.out.println("  - " + student.name));
        
        // 3. Sort students by average marks (highest first)
        System.out.println("\nStudents sorted by average (highest first):");
        students.stream()
                .sorted((s1, s2) -> {
                    double avg1 = Arrays.stream(s1.marks).average().orElse(0.0);
                    double avg2 = Arrays.stream(s2.marks).average().orElse(0.0);
                    return Double.compare(avg2, avg1); // Descending order
                })
                .forEach(student -> {
                    double avg = Arrays.stream(student.marks).average().orElse(0.0);
                    System.out.printf("  %s: %.2f%%\n", student.name, avg);
                });
        
        // 4. Print students who failed (average < 40%)
        System.out.println("\nFailed students (average < 40%):");
        students.stream()
                .filter(student -> {
                    double avg = Arrays.stream(student.marks).average().orElse(0.0);
                    return avg < 40;
                })
                .forEach(student -> System.out.println("  - " + student.name));
        
        System.out.println();
    }

    /**
     * EXERCISE 6: String Manipulation
     * Solution shows various string operations using lambda expressions
     */
    private static void exercise6_StringManipulation() {
        System.out.println("EXERCISE 6: String Manipulation");
        System.out.println("-".repeat(60));
        
        List<String> strings = Arrays.asList(
            "apple", "Banana", "cherry", "date", "Elderberry", 
            "fig", "grape", "it", "I", "Jackfruit"
        );
        
        // 1. Convert all strings to uppercase
        System.out.println("Uppercase:");
        List<String> uppercase = strings.stream()
                .map(str -> str.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("  " + uppercase);
        
        // 2. Filter strings starting with a vowel
        System.out.println("\nStrings starting with vowel:");
        strings.stream()
                .filter(str -> "AEIOUaeiou".indexOf(str.charAt(0)) >= 0)
                .forEach(str -> System.out.println("  - " + str));
        
        // 3. Remove strings with length less than 3
        System.out.println("\nStrings with length >= 3:");
        List<String> filtered = strings.stream()
                .filter(str -> str.length() >= 3)
                .collect(Collectors.toList());
        System.out.println("  " + filtered);
        
        // 4. Create new list with first character of each string
        System.out.println("\nFirst characters:");
        List<String> firstChars = strings.stream()
                .map(str -> String.valueOf(str.charAt(0)))
                .collect(Collectors.toList());
        System.out.println("  " + firstChars);
        
        // 5. Count strings containing letter 'e'
        long countWithE = strings.stream()
                .filter(str -> str.toLowerCase().contains("e"))
                .count();
        System.out.println("\nStrings containing 'e': " + countWithE);
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Calculator Operations
     * Solution demonstrates using Map with lambda expressions
     */
    private static void exercise7_CalculatorOperations() {
        System.out.println("EXERCISE 7: Calculator Operations");
        System.out.println("-".repeat(60));
        
        // Create map of calculator operations
        Map<String, BiFunction<Double, Double, Double>> calculator = new HashMap<>();
        
        // Add operations using lambda expressions
        calculator.put("add", (a, b) -> a + b);
        calculator.put("subtract", (a, b) -> a - b);
        calculator.put("multiply", (a, b) -> a * b);
        calculator.put("divide", (a, b) -> b != 0 ? a / b : Double.NaN);
        calculator.put("power", (a, b) -> Math.pow(a, b));
        calculator.put("modulo", (a, b) -> a % b);
        
        // Test all operations
        double num1 = 15.0;
        double num2 = 3.0;
        
        System.out.println("Testing with num1 = " + num1 + ", num2 = " + num2);
        System.out.println();
        
        calculator.forEach((operation, function) -> {
            double result = function.apply(num1, num2);
            System.out.printf("%s: %.2f\n", operation, result);
        });
        
        System.out.println();
    }

    /**
     * EXERCISE 8: Custom Functional Interface
     * Solution shows creating and using custom functional interfaces
     */
    private static void exercise8_CustomFunctionalInterface() {
        System.out.println("EXERCISE 8: Custom Functional Interface");
        System.out.println("-".repeat(60));
        
        // Create lambda expressions for various math operations
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;
        MathOperation power = (a, b) -> Math.pow(a, b);
        MathOperation modulo = (a, b) -> a % b;
        
        // Test operations using the executor method
        double x = 10.0;
        double y = 3.0;
        
        System.out.println("Testing with x = " + x + ", y = " + y);
        System.out.println("Addition: " + executeOperation(x, y, addition));
        System.out.println("Subtraction: " + executeOperation(x, y, subtraction));
        System.out.println("Multiplication: " + executeOperation(x, y, multiplication));
        System.out.println("Division: " + executeOperation(x, y, division));
        System.out.println("Power: " + executeOperation(x, y, power));
        System.out.println("Modulo: " + executeOperation(x, y, modulo));
        
        System.out.println();
    }

    /**
     * Helper method that executes a MathOperation
     */
    private static double executeOperation(double a, double b, MathOperation operation) {
        return operation.operate(a, b);
    }

    // ==================== ADVANCED LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 9: Employee Management System
     * Solution demonstrates complex filtering, transformation, and aggregation
     */
    private static void exercise9_EmployeeManagementSystem() {
        System.out.println("EXERCISE 9: Employee Management System");
        System.out.println("-".repeat(60));
        
        // Create employee list
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice Anderson", "IT", 75000, 6),
            new Employee(2, "Bob Brown", "HR", 55000, 3),
            new Employee(3, "Charlie Chen", "IT", 85000, 8),
            new Employee(4, "Diana Davis", "Finance", 70000, 5),
            new Employee(5, "Eve Evans", "IT", 65000, 2),
            new Employee(6, "Frank Foster", "HR", 50000, 7),
            new Employee(7, "Grace Green", "Finance", 80000, 6),
            new Employee(8, "Henry Hill", "IT", 90000, 10),
            new Employee(9, "Ivy Irving", "Finance", 72000, 4),
            new Employee(10, "Andrew Adams", "IT", 68000, 3)
        );
        
        // 1. Find all employees in IT department
        System.out.println("1. IT Department Employees:");
        employees.stream()
                .filter(emp -> emp.department.equals("IT"))
                .forEach(emp -> System.out.println("   " + emp));
        
        // 2. Give 15% salary hike to employees with >5 years experience
        System.out.println("\n2. Employees getting 15% hike (>5 years exp):");
        employees.stream()
                .filter(emp -> emp.yearsOfExperience > 5)
                .forEach(emp -> {
                    double oldSalary = emp.salary;
                    double newSalary = oldSalary * 1.15;
                    System.out.printf("   %s: $%.2f -> $%.2f\n", 
                            emp.name, oldSalary, newSalary);
                });
        
        // 3. Find employee with highest salary
        System.out.println("\n3. Highest Paid Employee:");
        employees.stream()
                .max((e1, e2) -> Double.compare(e1.salary, e2.salary))
                .ifPresent(emp -> System.out.println("   " + emp));
        
        // 4. Calculate total salary expense by department
        System.out.println("\n4. Total Salary by Department:");
        Map<String, Double> salaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    emp -> emp.department,
                    Collectors.summingDouble(emp -> emp.salary)
                ));
        salaryByDept.forEach((dept, total) -> 
                System.out.printf("   %s: $%.2f\n", dept, total));
        
        // 5. Find employees: name starts with 'A' AND salary > 50000
        System.out.println("\n5. Employees (name starts with 'A' AND salary > $50,000):");
        employees.stream()
                .filter(emp -> emp.name.startsWith("A") && emp.salary > 50000)
                .forEach(emp -> System.out.println("   " + emp));
        
        // 6. Group and count employees by department
        System.out.println("\n6. Employee Count by Department:");
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    emp -> emp.department,
                    Collectors.counting()
                ));
        countByDept.forEach((dept, count) -> 
                System.out.printf("   %s: %d employees\n", dept, count));
        
        System.out.println();
    }

    /**
     * EXERCISE 10: Transaction Processing
     * Solution demonstrates complex data analysis using lambda expressions
     */
    private static void exercise10_TransactionProcessing() {
        System.out.println("EXERCISE 10: Transaction Processing");
        System.out.println("-".repeat(60));
        
        // Generate sample transactions
        List<Transaction> transactions = Arrays.asList(
            new Transaction(1, "ACC001", 5000, "CREDIT", LocalDate.now().minusDays(5)),
            new Transaction(2, "ACC002", 15000, "DEBIT", LocalDate.now().minusDays(4)),
            new Transaction(3, "ACC001", 2500, "DEBIT", LocalDate.now().minusDays(3)),
            new Transaction(4, "ACC003", 125000, "CREDIT", LocalDate.now().minusDays(2)),
            new Transaction(5, "ACC002", 8000, "CREDIT", LocalDate.now().minusDays(2)),
            new Transaction(6, "ACC001", 3000, "CREDIT", LocalDate.now().minusDays(1)),
            new Transaction(7, "ACC004", 50000, "DEBIT", LocalDate.now().minusDays(1)),
            new Transaction(8, "ACC003", 7500, "DEBIT", LocalDate.now()),
            new Transaction(9, "ACC002", 150000, "CREDIT", LocalDate.now()),
            new Transaction(10, "ACC001", 1200, "DEBIT", LocalDate.now())
        );
        
        // 1. Calculate total credited amount
        double totalCredit = transactions.stream()
                .filter(t -> t.type.equals("CREDIT"))
                .mapToDouble(t -> t.amount)
                .sum();
        System.out.printf("1. Total Credited Amount: $%.2f\n", totalCredit);
        
        // 2. Calculate total debited amount
        double totalDebit = transactions.stream()
                .filter(t -> t.type.equals("DEBIT"))
                .mapToDouble(t -> t.amount)
                .sum();
        System.out.printf("\n2. Total Debited Amount: $%.2f\n", totalDebit);
        
        // 3. Find largest transaction
        System.out.println("\n3. Largest Transaction:");
        transactions.stream()
                .max((t1, t2) -> Double.compare(t1.amount, t2.amount))
                .ifPresent(t -> System.out.println("   " + t));
        
        // 4. Filter suspicious transactions (amount > 100000)
        System.out.println("\n4. Suspicious Transactions (> $100,000):");
        transactions.stream()
                .filter(t -> t.amount > 100000)
                .forEach(t -> System.out.println("   " + t));
        
        // 5. Group transactions by type and sum amounts
        System.out.println("\n5. Total Amount by Transaction Type:");
        Map<String, Double> amountByType = transactions.stream()
                .collect(Collectors.groupingBy(
                    t -> t.type,
                    Collectors.summingDouble(t -> t.amount)
                ));
        amountByType.forEach((type, total) -> 
                System.out.printf("   %s: $%.2f\n", type, total));
        
        // 6. Find all transactions for a specific account
        String targetAccount = "ACC001";
        System.out.println("\n6. Transactions for " + targetAccount + ":");
        transactions.stream()
                .filter(t -> t.accountNumber.equals(targetAccount))
                .forEach(t -> System.out.println("   " + t));
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================

    /**
     * Student class for Exercise 5
     */
    static class Student {
        String name;
        int age;
        int[] marks;

        Student(String name, int age, int[] marks) {
            this.name = name;
            this.age = age;
            this.marks = marks;
        }
    }

    /**
     * Custom functional interface for Exercise 8
     */
    @FunctionalInterface
    interface MathOperation {
        double operate(double a, double b);
    }

    /**
     * Employee class for Exercise 9
     */
    static class Employee {
        int id;
        String name;
        String department;
        double salary;
        int yearsOfExperience;

        Employee(int id, String name, String department, double salary, int yearsOfExperience) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.yearsOfExperience = yearsOfExperience;
        }

        @Override
        public String toString() {
            return String.format("%s (%s) - $%.2f, %d years exp", 
                    name, department, salary, yearsOfExperience);
        }
    }

    /**
     * Transaction class for Exercise 10
     */
    static class Transaction {
        int id;
        String accountNumber;
        double amount;
        String type; // CREDIT or DEBIT
        LocalDate date;

        Transaction(int id, String accountNumber, double amount, String type, LocalDate date) {
            this.id = id;
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.type = type;
            this.date = date;
        }

        @Override
        public String toString() {
            return String.format("ID:%d, %s, %s $%.2f on %s", 
                    id, accountNumber, type, amount, date);
        }
    }
}
