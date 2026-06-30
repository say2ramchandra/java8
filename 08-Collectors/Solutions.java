package collectors;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO COLLECTORS AND REDUCTION EXERCISES
 * ================================================
 * This file contains comprehensive solutions demonstrating the Collectors API.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== COLLECTORS AND REDUCTION - EXERCISE SOLUTIONS ===\n");
        
        // Run all exercise solutions
        exercise1_BasicCollection();
        exercise2_StringOperations();
        exercise3_ToMapCollector();
        exercise4_GroupingBasics();
        exercise5_Partitioning();
        exercise6_AdvancedGrouping();
        exercise7_MultiLevelGrouping();
        exercise8_DownstreamCollectors();
        exercise9_MappingAndFiltering();
        exercise10_CustomReduction();
        exercise11_ComplexGrouping();
        exercise12_CollectingAndThen();
        exercise13_CustomCollectorUsage();
        exercise14_DataAnalysis();
        exercise15_PracticalApplications();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Basic Collection
     * Solution demonstrates fundamental collectors
     */
    private static void exercise1_BasicCollection() {
        System.out.println("EXERCISE 1: Basic Collection");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6);
        
        // 1. Collect to List
        List<Integer> list = numbers.stream()
                .collect(Collectors.toList());
        System.out.println("To List: " + list);
        
        // 2. Collect to Set (removes duplicates)
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        Set<Integer> set = withDuplicates.stream()
                .collect(Collectors.toSet());
        System.out.println("To Set: " + set);
        
        // 3. Collect to TreeSet (sorted)
        TreeSet<Integer> treeSet = numbers.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("To TreeSet (sorted): " + treeSet);
        
        // 4. Collect to ArrayList specifically
        ArrayList<Integer> arrayList = numbers.stream()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("To ArrayList: " + arrayList);
        
        // 5. Count elements
        long count = numbers.stream()
                .collect(Collectors.counting());
        System.out.println("Count: " + count);
        
        System.out.println();
    }

    /**
     * EXERCISE 2: String Operations with Collectors
     * Solution demonstrates string-specific collectors
     */
    private static void exercise2_StringOperations() {
        System.out.println("EXERCISE 2: String Operations");
        System.out.println("-".repeat(60));
        
        List<String> languages = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");
        
        // 1. Join with comma
        String joined = languages.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);
        
        // 2. Join with custom delimiter and prefix/suffix
        String custom = languages.stream()
                .collect(Collectors.joining(" | ", "[", "]"));
        System.out.println("Custom join: " + custom);
        
        // 3. Sum of lengths
        int totalLength = languages.stream()
                .collect(Collectors.summingInt(String::length));
        System.out.println("Total length: " + totalLength);
        
        // 4. Average length
        double avgLength = languages.stream()
                .collect(Collectors.averagingInt(String::length));
        System.out.println("Average length: " + avgLength);
        
        // 5. Statistics
        IntSummaryStatistics stats = languages.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println("Statistics: " + stats);
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Max: " + stats.getMax());
        System.out.println("  Average: " + stats.getAverage());
        
        System.out.println();
    }

    /**
     * EXERCISE 3: toMap Collector
     * Solution demonstrates map creation from streams
     */
    private static void exercise3_ToMapCollector() {
        System.out.println("EXERCISE 3: toMap Collector");
        System.out.println("-".repeat(60));
        
        List<Person> persons = Arrays.asList(
            new Person(1, "Alice", 25),
            new Person(2, "Bob", 30),
            new Person(3, "Charlie", 35)
        );
        
        // 1. Map<Integer, String> - id to name
        Map<Integer, String> idToName = persons.stream()
                .collect(Collectors.toMap(
                    Person::getId,
                    Person::getName
                ));
        System.out.println("ID to Name: " + idToName);
        
        // 2. Map<String, Person> - name to person
        Map<String, Person> nameToPerson = persons.stream()
                .collect(Collectors.toMap(
                    Person::getName,
                    p -> p
                ));
        System.out.println("Name to Person: " + nameToPersonToString(nameToPerson));
        
        // 3. Handle duplicates with merge function
        List<Person> withDuplicates = Arrays.asList(
            new Person(1, "Alice", 25),
            new Person(1, "Alice", 30)  // Duplicate id
        );
        Map<Integer, Person> merged = withDuplicates.stream()
                .collect(Collectors.toMap(
                    Person::getId,
                    p -> p,
                    (existing, replacement) -> existing  // Keep first
                ));
        System.out.println("With duplicates (keep first): " + merged.size() + " entries");
        
        // 4. Create TreeMap
        TreeMap<Integer, String> treeMap = persons.stream()
                .collect(Collectors.toMap(
                    Person::getId,
                    Person::getName,
                    (a, b) -> a,
                    TreeMap::new
                ));
        System.out.println("TreeMap: " + treeMap);
        
        // 5. Map<Integer, Integer> - id to age
        Map<Integer, Integer> idToAge = persons.stream()
                .collect(Collectors.toMap(
                    Person::getId,
                    Person::getAge
                ));
        System.out.println("ID to Age: " + idToAge);
        
        System.out.println();
    }

    /**
     * EXERCISE 4: Grouping Basics
     * Solution demonstrates basic groupingBy operations
     */
    private static void exercise4_GroupingBasics() {
        System.out.println("EXERCISE 4: Grouping Basics");
        System.out.println("-".repeat(60));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 65000),
            new Employee("Charlie", "IT", 85000),
            new Employee("Diana", "HR", 70000),
            new Employee("Eve", "Sales", 60000)
        );
        
        // 1. Group by department
        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("By Department:");
        byDept.forEach((dept, emps) -> 
            System.out.println("  " + dept + ": " + emps.size() + " employees"));
        
        // 2. Group numbers by even/odd
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0));
        System.out.println("\nEven/Odd grouping:");
        System.out.println("  Even: " + evenOdd.get(true));
        System.out.println("  Odd: " + evenOdd.get(false));
        
        // 3. Group strings by first letter
        List<String> words = Arrays.asList("Apple", "Banana", "Avocado", "Cherry", "Apricot");
        Map<Character, List<String>> byFirstLetter = words.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println("\nBy First Letter:");
        byFirstLetter.forEach((letter, list) -> 
            System.out.println("  " + letter + ": " + list));
        
        // 4. Group by age ranges
        List<Person> persons = Arrays.asList(
            new Person(1, "Child", 10),
            new Person(2, "Teen", 17),
            new Person(3, "Young Adult", 25),
            new Person(4, "Adult", 40),
            new Person(5, "Senior", 65)
        );
        Map<String, List<Person>> byAgeRange = persons.stream()
                .collect(Collectors.groupingBy(p -> {
                    int age = p.getAge();
                    if (age <= 18) return "0-18";
                    if (age <= 30) return "19-30";
                    if (age <= 50) return "31-50";
                    return "50+";
                }));
        System.out.println("\nBy Age Range:");
        byAgeRange.forEach((range, list) -> 
            System.out.println("  " + range + ": " + list.size() + " persons"));
        
        // 5. Count employees per department
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.counting()
                ));
        System.out.println("\nEmployee count by department:");
        countByDept.forEach((dept, count) -> 
            System.out.println("  " + dept + ": " + count));
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Partitioning
     * Solution demonstrates partitioningBy operations
     */
    private static void exercise5_Partitioning() {
        System.out.println("EXERCISE 5: Partitioning");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. Partition into even and odd
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even: " + evenOdd.get(true));
        System.out.println("Odd: " + evenOdd.get(false));
        
        // 2. Partition strings by length
        List<String> words = Arrays.asList("Java", "Python", "C", "JavaScript", "Go", "Ruby");
        Map<Boolean, List<String>> byLength = words.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 5));
        System.out.println("\nLength > 5: " + byLength.get(true));
        System.out.println("Length <= 5: " + byLength.get(false));
        
        // 3. Partition persons by age
        List<Person> persons = Arrays.asList(
            new Person(1, "Minor", 15),
            new Person(2, "Adult1", 25),
            new Person(3, "Adult2", 35)
        );
        Map<Boolean, List<Person>> adults = persons.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
        System.out.println("\nAdults: " + adults.get(true).size());
        System.out.println("Minors: " + adults.get(false).size());
        
        // 4. Count in each partition
        Map<Boolean, Long> countByEven = numbers.stream()
                .collect(Collectors.partitioningBy(
                    n -> n % 2 == 0,
                    Collectors.counting()
                ));
        System.out.println("\nCount:");
        System.out.println("  Even numbers: " + countByEven.get(true));
        System.out.println("  Odd numbers: " + countByEven.get(false));
        
        // 5. Sum of each partition
        Map<Boolean, Integer> sumByEven = numbers.stream()
                .collect(Collectors.partitioningBy(
                    n -> n % 2 == 0,
                    Collectors.summingInt(Integer::intValue)
                ));
        System.out.println("\nSum:");
        System.out.println("  Even sum: " + sumByEven.get(true));
        System.out.println("  Odd sum: " + sumByEven.get(false));
        
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 6: Advanced Grouping
     * Solution demonstrates complex grouping operations
     */
    private static void exercise6_AdvancedGrouping() {
        System.out.println("EXERCISE 6: Advanced Grouping");
        System.out.println("-".repeat(60));
        
        List<Student> students = Arrays.asList(
            new Student("Alice", "Math", 95),
            new Student("Bob", "Math", 85),
            new Student("Charlie", "Science", 90),
            new Student("Diana", "Science", 88),
            new Student("Eve", "Math", 92)
        );
        
        // 1. Group by subject, collect names
        Map<String, List<String>> namesBySubject = students.stream()
                .collect(Collectors.groupingBy(
                    Student::getSubject,
                    Collectors.mapping(Student::getName, Collectors.toList())
                ));
        System.out.println("Students by subject:");
        namesBySubject.forEach((subject, names) -> 
            System.out.println("  " + subject + ": " + names));
        
        // 2. Group by subject, calculate average score
        Map<String, Double> avgBySubject = students.stream()
                .collect(Collectors.groupingBy(
                    Student::getSubject,
                    Collectors.averagingDouble(Student::getScore)
                ));
        System.out.println("\nAverage scores:");
        avgBySubject.forEach((subject, avg) -> 
            System.out.printf("  %s: %.2f\n", subject, avg));
        
        // 3. Group by subject, find max score
        Map<String, Optional<Integer>> maxBySubject = students.stream()
                .collect(Collectors.groupingBy(
                    Student::getSubject,
                    Collectors.mapping(Student::getScore, 
                                      Collectors.maxBy(Integer::compareTo))
                ));
        System.out.println("\nMax scores:");
        maxBySubject.forEach((subject, max) -> 
            System.out.println("  " + subject + ": " + max.orElse(0)));
        
        // 4. Group by subject, count students
        Map<String, Long> countBySubject = students.stream()
                .collect(Collectors.groupingBy(
                    Student::getSubject,
                    Collectors.counting()
                ));
        System.out.println("\nStudent count:");
        countBySubject.forEach((subject, count) -> 
            System.out.println("  " + subject + ": " + count));
        
        // 5. Group by subject, get student with highest score
        Map<String, Optional<Student>> topBySubject = students.stream()
                .collect(Collectors.groupingBy(
                    Student::getSubject,
                    Collectors.maxBy(Comparator.comparingInt(Student::getScore))
                ));
        System.out.println("\nTop students:");
        topBySubject.forEach((subject, student) -> 
            student.ifPresent(s -> System.out.println("  " + subject + ": " + s.getName() + " (" + s.getScore() + ")")));
        
        System.out.println();
    }

    /**
     * EXERCISE 7: Multi-level Grouping
     * Solution demonstrates nested grouping
     */
    private static void exercise7_MultiLevelGrouping() {
        System.out.println("EXERCISE 7: Multi-level Grouping");
        System.out.println("-".repeat(60));
        
        List<Product> products = Arrays.asList(
            new Product("Electronics", "Sony", 599.99),
            new Product("Electronics", "Samsung", 799.99),
            new Product("Clothing", "Nike", 89.99),
            new Product("Clothing", "Adidas", 79.99),
            new Product("Electronics", "Sony", 399.99)
        );
        
        // 1. Group by category, then by brand
        Map<String, Map<String, List<Product>>> byCategoryThenBrand = products.stream()
                .collect(Collectors.groupingBy(
                    Product::getCategory,
                    Collectors.groupingBy(Product::getBrand)
                ));
        System.out.println("By Category → Brand:");
        byCategoryThenBrand.forEach((category, brandMap) -> {
            System.out.println("  " + category + ":");
            brandMap.forEach((brand, prods) -> 
                System.out.println("    " + brand + ": " + prods.size() + " products"));
        });
        
        // 2. Group by price range, then by category
        Map<String, Map<String, List<Product>>> byPriceThenCategory = products.stream()
                .collect(Collectors.groupingBy(
                    p -> p.getPrice() > 100 ? "Premium" : "Budget",
                    Collectors.groupingBy(Product::getCategory)
                ));
        System.out.println("\nBy Price Range → Category:");
        byPriceThenCategory.forEach((range, catMap) -> {
            System.out.println("  " + range + ":");
            catMap.forEach((cat, prods) -> 
                System.out.println("    " + cat + ": " + prods.size()));
        });
        
        // 3. Group by category, partition by price
        Map<String, Map<Boolean, List<Product>>> byCategoryPartitioned = products.stream()
                .collect(Collectors.groupingBy(
                    Product::getCategory,
                    Collectors.partitioningBy(p -> p.getPrice() > 100)
                ));
        System.out.println("\nBy Category, partitioned by price > 100:");
        byCategoryPartitioned.forEach((cat, partMap) -> {
            System.out.println("  " + cat + ":");
            System.out.println("    Premium: " + partMap.get(true).size());
            System.out.println("    Budget: " + partMap.get(false).size());
        });
        
        System.out.println();
    }

    /**
     * EXERCISE 8: Downstream Collectors
     * Solution demonstrates using downstream collectors
     */
    private static void exercise8_DownstreamCollectors() {
        System.out.println("EXERCISE 8: Downstream Collectors");
        System.out.println("-".repeat(60));
        
        List<Transaction> transactions = Arrays.asList(
            new Transaction("Deposit", 1000.0),
            new Transaction("Withdrawal", 500.0),
            new Transaction("Deposit", 2000.0),
            new Transaction("Withdrawal", 300.0),
            new Transaction("Deposit", 1500.0)
        );
        
        // 1. Group by type, sum amounts
        Map<String, Double> sumByType = transactions.stream()
                .collect(Collectors.groupingBy(
                    Transaction::getType,
                    Collectors.summingDouble(Transaction::getAmount)
                ));
        System.out.println("Sum by type:");
        sumByType.forEach((type, sum) -> 
            System.out.printf("  %s: $%.2f\n", type, sum));
        
        // 2. Group by type, collect amounts
        Map<String, List<Double>> amountsByType = transactions.stream()
                .collect(Collectors.groupingBy(
                    Transaction::getType,
                    Collectors.mapping(Transaction::getAmount, Collectors.toList())
                ));
        System.out.println("\nAmounts by type:");
        amountsByType.forEach((type, amounts) -> 
            System.out.println("  " + type + ": " + amounts));
        
        // 3. Group by type, find max amount
        Map<String, Optional<Double>> maxByType = transactions.stream()
                .collect(Collectors.groupingBy(
                    Transaction::getType,
                    Collectors.mapping(Transaction::getAmount, 
                                      Collectors.maxBy(Double::compareTo))
                ));
        System.out.println("\nMax amount by type:");
        maxByType.forEach((type, max) -> 
            System.out.printf("  %s: $%.2f\n", type, max.orElse(0.0)));
        
        // 4. Group by type, average amount
        Map<String, Double> avgByType = transactions.stream()
                .collect(Collectors.groupingBy(
                    Transaction::getType,
                    Collectors.averagingDouble(Transaction::getAmount)
                ));
        System.out.println("\nAverage amount by type:");
        avgByType.forEach((type, avg) -> 
            System.out.printf("  %s: $%.2f\n", type, avg));
        
        // 5. Group by type, count transactions
        Map<String, Long> countByType = transactions.stream()
                .collect(Collectors.groupingBy(
                    Transaction::getType,
                    Collectors.counting()
                ));
        System.out.println("\nCount by type:");
        countByType.forEach((type, count) -> 
            System.out.println("  " + type + ": " + count));
        
        System.out.println();
    }

    /**
     * EXERCISE 9: Mapping and Filtering
     * Solution demonstrates transforming during collection
     */
    private static void exercise9_MappingAndFiltering() {
        System.out.println("EXERCISE 9: Mapping and Filtering");
        System.out.println("-".repeat(60));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 45000),
            new Employee("Charlie", "IT", 85000),
            new Employee("Diana", "Sales", 55000),
            new Employee("Eve", "IT", 65000)
        );
        
        // 1. Collect names
        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("All names: " + names);
        
        // 2. Names of high earners
        List<String> highEarners = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("High earners (>50k): " + highEarners);
        
        // 3. Unique departments
        Set<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
        System.out.println("Departments: " + departments);
        
        // 4. Names in uppercase
        List<String> upperNames = employees.stream()
                .map(Employee::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperNames);
        
        // 5. Sorted department names
        List<String> sortedDepts = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted departments: " + sortedDepts);
        
        System.out.println();
    }

    /**
     * EXERCISE 10: Custom Reduction
     * Solution demonstrates using reducing() collector
     */
    private static void exercise10_CustomReduction() {
        System.out.println("EXERCISE 10: Custom Reduction");
        System.out.println("-".repeat(60));
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // 1. Sum using reducing
        Optional<Integer> sum = numbers.stream()
                .collect(Collectors.reducing((a, b) -> a + b));
        System.out.println("Sum: " + sum.orElse(0));
        
        // 2. Product using reducing
        Optional<Integer> product = numbers.stream()
                .collect(Collectors.reducing((a, b) -> a * b));
        System.out.println("Product: " + product.orElse(1));
        
        // 3. Max using reducing
        Optional<Integer> max = numbers.stream()
                .collect(Collectors.reducing(Integer::max));
        System.out.println("Max: " + max.orElse(0));
        
        // 4. Concatenate strings
        List<String> words = Arrays.asList("Java", "is", "awesome");
        String concatenated = words.stream()
                .collect(Collectors.reducing("", (a, b) -> a + " " + b)).trim();
        System.out.println("Concatenated: " + concatenated);
        
        // 5. Weighted average
        List<WeightedValue> values = Arrays.asList(
            new WeightedValue(90, 0.3),
            new WeightedValue(85, 0.5),
            new WeightedValue(95, 0.2)
        );
        double weightedAvg = values.stream()
                .collect(Collectors.reducing(
                    0.0,
                    v -> v.value * v.weight,
                    Double::sum
                ));
        System.out.printf("Weighted average: %.2f\n", weightedAvg);
        
        System.out.println();
    }

    // ==================== ADVANCED LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 11: Complex Grouping Scenarios
     * Solution demonstrates real-world grouping usage
     */
    private static void exercise11_ComplexGrouping() {
        System.out.println("EXERCISE 11: Complex Grouping Scenarios");
        System.out.println("-".repeat(60));
        
        List<Order> orders = Arrays.asList(
            new Order("Customer1", "Product A", 2, 50.0),
            new Order("Customer1", "Product B", 1, 30.0),
            new Order("Customer2", "Product A", 3, 50.0),
            new Order("Customer2", "Product C", 1, 75.0),
            new Order("Customer1", "Product A", 1, 50.0)
        );
        
        // 1. Total order value per customer
        Map<String, Double> totalByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                    Order::getCustomer,
                    Collectors.summingDouble(o -> o.getQuantity() * o.getPrice())
                ));
        System.out.println("Total order value by customer:");
        totalByCustomer.forEach((customer, total) -> 
            System.out.printf("  %s: $%.2f\n", customer, total));
        
        // 2. Quantities sold per product
        Map<String, Integer> qtyByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                    Order::getProduct,
                    Collectors.summingInt(Order::getQuantity)
                ));
        System.out.println("\nQuantities sold:");
        qtyByProduct.forEach((product, qty) -> 
            System.out.println("  " + product + ": " + qty));
        
        // 3. Most purchased product per customer
        Map<String, Optional<Map.Entry<String, Integer>>> topProductByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                    Order::getCustomer,
                    Collectors.collectingAndThen(
                        Collectors.groupingBy(
                            Order::getProduct,
                            Collectors.summingInt(Order::getQuantity)
                        ),
                        map -> map.entrySet().stream()
                                 .max(Map.Entry.comparingByValue())
                    )
                ));
        System.out.println("\nTop product per customer:");
        topProductByCustomer.forEach((customer, entry) -> 
            entry.ifPresent(e -> System.out.println("  " + customer + ": " + e.getKey() + " (" + e.getValue() + " units)")));
        
        System.out.println();
    }

    /**
     * EXERCISE 12: collectingAndThen
     * Solution demonstrates post-processing collected results
     */
    private static void exercise12_CollectingAndThen() {
        System.out.println("EXERCISE 12: collectingAndThen");
        System.out.println("-".repeat(60));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 65000),
            new Employee("Charlie", "IT", 85000)
        );
        
        // 1. Collect to unmodifiable list
        List<String> unmodifiableNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    Collections::unmodifiableList
                ));
        System.out.println("Unmodifiable list: " + unmodifiableNames);
        
        // 2. Group and count, then get total departments
        Integer deptCount = employees.stream()
                .collect(Collectors.collectingAndThen(
                    Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                    ),
                    Map::size
                ));
        System.out.println("Number of departments: " + deptCount);
        
        // 3. Collect names to comma-separated string
        String joined = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> String.join(", ", list)
                ));
        System.out.println("Joined names: " + joined);
        
        // 4. Find max salary and format
        String maxSalaryFormatted = employees.stream()
                .collect(Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                    opt -> opt.map(e -> String.format("$%.2f (%s)", e.getSalary(), e.getName()))
                              .orElse("No employees")
                ));
        System.out.println("Max salary: " + maxSalaryFormatted);
        
        System.out.println();
    }

    /**
     * EXERCISE 13: Custom Collector Usage
     * Solution demonstrates practical custom collector scenarios
     */
    private static void exercise13_CustomCollectorUsage() {
        System.out.println("EXERCISE 13: Custom Collector Usage");
        System.out.println("-".repeat(60));
        
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
        
        // Frequency map
        Map<String, Long> frequency = words.stream()
                .collect(Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()
                ));
        System.out.println("Frequency map: " + frequency);
        
        // Find mode (most frequent)
        Optional<Map.Entry<String, Long>> mode = frequency.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        mode.ifPresent(e -> System.out.println("Mode: " + e.getKey() + " (appears " + e.getValue() + " times)"));
        
        // Statistics collector
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        DoubleSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingDouble(Integer::doubleValue));
        System.out.println("\nStatistics:");
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Average: " + stats.getAverage());
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Max: " + stats.getMax());
        
        System.out.println();
    }

    /**
     * EXERCISE 14: Data Analysis
     * Solution demonstrates analytical use cases
     */
    private static void exercise14_DataAnalysis() {
        System.out.println("EXERCISE 14: Data Analysis");
        System.out.println("-".repeat(60));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 65000),
            new Employee("Charlie", "IT", 85000),
            new Employee("Diana", "Sales", 70000),
            new Employee("Eve", "IT", 95000),
            new Employee("Frank", "HR", 55000)
        );
        
        // Department statistics
        Map<String, DoubleSummaryStatistics> statsByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.summarizingDouble(Employee::getSalary)
                ));
        
        System.out.println("Salary statistics by department:");
        statsByDept.forEach((dept, stat) -> {
            System.out.println("  " + dept + ":");
            System.out.printf("    Average: $%.2f\n", stat.getAverage());
            System.out.printf("    Min: $%.2f\n", stat.getMin());
            System.out.printf("    Max: $%.2f\n", stat.getMax());
            System.out.println("    Count: " + stat.getCount());
        });
        
        System.out.println();
    }

    /**
     * EXERCISE 15: Practical Applications
     * Solution demonstrates real-world scenarios
     */
    private static void exercise15_PracticalApplications() {
        System.out.println("EXERCISE 15: Practical Applications");
        System.out.println("-".repeat(60));
        
        List<Student> students = Arrays.asList(
            new Student("Alice", "Math", 95),
            new Student("Bob", "Math", 85),
            new Student("Charlie", "Science", 90),
            new Student("Diana", "Science", 88),
            new Student("Eve", "Math", 78),
            new Student("Frank", "Science", 92)
        );
        
        // Grade distribution
        Map<String, Map<String, Long>> gradeDistribution = students.stream()
                .collect(Collectors.groupingBy(
                    Student::getSubject,
                    Collectors.groupingBy(
                        s -> {
                            int score = s.getScore();
                            if (score >= 90) return "A";
                            if (score >= 80) return "B";
                            if (score >= 70) return "C";
                            return "F";
                        },
                        Collectors.counting()
                    )
                ));
        
        System.out.println("Grade distribution by subject:");
        gradeDistribution.forEach((subject, grades) -> {
            System.out.println("  " + subject + ":");
            grades.forEach((grade, count) -> 
                System.out.println("    " + grade + ": " + count + " students"));
        });
        
        System.out.println();
    }

    // ==================== HELPER CLASSES ====================

    static class Person {
        int id;
        String name;
        int age;
        
        Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
        
        int getId() { return id; }
        String getName() { return name; }
        int getAge() { return age; }
    }

    static class Employee {
        String name, department;
        double salary;
        
        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        
        String getName() { return name; }
        String getDepartment() { return department; }
        double getSalary() { return salary; }
    }

    static class Student {
        String name, subject;
        int score;
        
        Student(String name, String subject, int score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }
        
        String getName() { return name; }
        String getSubject() { return subject; }
        int getScore() { return score; }
    }

    static class Product {
        String category, brand;
        double price;
        
        Product(String category, String brand, double price) {
            this.category = category;
            this.brand = brand;
            this.price = price;
        }
        
        String getCategory() { return category; }
        String getBrand() { return brand; }
        double getPrice() { return price; }
    }

    static class Transaction {
        String type;
        double amount;
        
        Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }
        
        String getType() { return type; }
        double getAmount() { return amount; }
    }

    static class Order {
        String customer, product;
        int quantity;
        double price;
        
        Order(String customer, String product, int quantity, double price) {
            this.customer = customer;
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }
        
        String getCustomer() { return customer; }
        String getProduct() { return product; }
        int getQuantity() { return quantity; }
        double getPrice() { return price; }
    }

    static class WeightedValue {
        double value, weight;
        
        WeightedValue(double value, double weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    private static String nameToPersonToString(Map<String, Person> map) {
        return map.keySet().toString();
    }
}
