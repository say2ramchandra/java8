package collectors;

import java.util.*;
import java.util.stream.*;

/**
 * COLLECTORS AND REDUCTION OPERATIONS
 * ====================================
 * 
 * Collectors are used to accumulate elements from a stream into a collection or other data structure.
 * The Collectors utility class provides many pre-built collectors.
 * 
 * COMMON COLLECTORS:
 * - toList(), toSet(), toMap() - Collect to collections
 * - joining() - Concatenate strings
 * - counting() - Count elements
 * - summingInt/Long/Double() - Sum numeric values
 * - averagingInt/Long/Double() - Calculate average
 * - groupingBy() - Group elements
 * - partitioningBy() - Partition into two groups
 * - reducing() - Custom reduction
 * 
 * @author Java 8 Mastery Course
 */
public class CollectorsAndReduction {
    
    public static void main(String[] args) {
        System.out.println("=== COLLECTORS AND REDUCTION OPERATIONS ===\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Collect to List
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Collect to Set
        Set<Integer> uniqueNumbers = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println("Unique numbers: " + uniqueNumbers);
        
        // Joining strings
        String joined = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);
        
        // Counting
        long count = numbers.stream()
                .filter(n -> n > 5)
                .collect(Collectors.counting());
        System.out.println("Count > 5: " + count);
        
        // Summing
        int sum = numbers.stream()
                .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum: " + sum);
        
        // Averaging
        double avg = numbers.stream()
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Average: " + avg);
        
        // Grouping by
        Map<Boolean, List<Integer>> grouped = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0));
        System.out.println("Grouped by even/odd: " + grouped);
        
        // Partitioning by
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n > 5));
        System.out.println("Partitioned by > 5: " + partitioned);
        
        // Example with objects
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 65000),
            new Employee("Charlie", "IT", 85000),
            new Employee("Diana", "HR", 70000)
        );
        
        // Group by department
        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(e -> e.department));
        System.out.println("\nEmployees by department:");
        byDept.forEach((dept, emps) -> {
            System.out.println(dept + ": " + emps.size() + " employees");
        });
        
        // Average salary by department
        Map<String, Double> avgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                    e -> e.department,
                    Collectors.averagingDouble(e -> e.salary)
                ));
        System.out.println("\nAverage salary by department:");
        avgSalary.forEach((dept, avg2) -> 
            System.out.printf("%s: $%.2f\n", dept, avg2));
    }
    
    static class Employee {
        String name, department;
        double salary;
        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }
}
