package streamsapi;

import java.util.*;
import java.util.stream.*;

/**
 * Solutions to Streams API Exercises
 */
public class Solutions {
    
    public static void main(String[] args) {
        exercise1_StreamCreation();
        exercise2_FilterAndMap();
        exercise7_EmployeeAnalysis();
    }
    
    private static void exercise1_StreamCreation() {
        System.out.println("EXERCISE 1: Stream Creation");
        System.out.println("-".repeat(60));
        
        // 1. From list
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(System.out::print);
        System.out.println();
        
        // 2. From array
        String[] array = {"A", "B", "C"};
        Arrays.stream(array).forEach(System.out::print);
        System.out.println();
        
        // 3. Stream.of()
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(System.out::print);
        System.out.println();
        
        // 4. Infinite stream
        Stream.generate(Math::random).limit(5).forEach(n -> System.out.printf("%.2f ", n));
        System.out.println();
        
        // 5. IntStream range
        IntStream.range(1, 11).forEach(System.out::print);
        System.out.println("\n");
    }
    
    private static void exercise2_FilterAndMap() {
        System.out.println("EXERCISE 2: Filter and Map");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. Even numbers
        System.out.println("Even: " + numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()));
        
        // 2. Numbers > 5
        System.out.println("> 5: " + numbers.stream().filter(n -> n > 5).collect(Collectors.toList()));
        
        // 3. Squares
        System.out.println("Squares: " + numbers.stream().map(n -> n * n).collect(Collectors.toList()));
        
        // 4. To string
        System.out.println("Strings: " + numbers.stream().map(String::valueOf).collect(Collectors.toList()));
        
        // 5. Square of odd
        System.out.println("Odd squares: " + numbers.stream().filter(n -> n % 2 != 0).map(n -> n * n).collect(Collectors.toList()));
        
        System.out.println();
    }
    
    private static void exercise7_EmployeeAnalysis() {
        System.out.println("EXERCISE 7: Employee Analysis");
        System.out.println("-".repeat(60));
        
        List<EmployeeEx> employees = Arrays.asList(
            new EmployeeEx("Alice", "IT", 75000),
            new EmployeeEx("Bob", "HR", 65000),
            new EmployeeEx("Charlie", "IT", 85000),
            new EmployeeEx("Diana", "HR", 70000)
        );
        
        // 1. Average salary by department
        System.out.println("\nAverage Salary by Department:");
        employees.stream()
            .collect(Collectors.groupingBy(
                e -> e.department,
                Collectors.averagingDouble(e -> e.salary)
            ))
            .forEach((dept, avg) -> System.out.printf("  %s: $%.2f\n", dept, avg));
        
        // 2. Highest paid
        employees.stream()
            .max(Comparator.comparingDouble(e -> e.salary))
            .ifPresent(e -> System.out.println("\nHighest paid: " + e.name + " - $" + e.salary));
        
        // 3. Unique departments
        System.out.println("\nDepartments: " + 
            employees.stream().map(e -> e.department).distinct().collect(Collectors.toList()));
        
        System.out.println();
    }
    
    static class EmployeeEx {
        String name, department;
        double salary;
        EmployeeEx(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }
}
