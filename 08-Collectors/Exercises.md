# Collectors and Reduction - Practice Exercises

## Basic Level Exercises

### Exercise 1: Basic Collection
Use Collectors to:
1. Collect stream of integers to a List
2. Collect stream of strings to a Set
3. Collect stream of integers to a TreeSet (sorted)
4. Collect to ArrayList specifically (not just List)
5. Count elements in a stream

### Exercise 2: String Operations with Collectors
Given a list of strings `["Java", "Python", "C++", "JavaScript", "Ruby"]`:
1. Join all strings with comma separator
2. Join with custom delimiter and prefix/suffix
3. Calculate total length of all strings (summingInt)
4. Find the average length of strings
5. Get statistics (count, sum, min, max, average) of string lengths

### Exercise 3: toMap Collector
Given a list of Person objects with (id, name, age):
1. Create Map<Integer, String> with id as key and name as value
2. Create Map<String, Person> with name as key
3. Handle duplicate keys using merge function
4. Create TreeMap instead of HashMap
5. Create Map<Integer, Integer> with id as key and age as value

### Exercise 4: Grouping Basics
Given a list of employees with (name, department, salary):
1. Group employees by department
2. Group numbers by even/odd (true/false)
3. Group strings by their first letter
4. Group persons by age ranges (0-18, 19-30, 31-50, 50+)
5. Count how many employees in each department

### Exercise 5: Partitioning
Given a list of integers `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`:
1. Partition into even and odd numbers
2. Partition strings by length > 5
3. Partition persons by age >= 18
4. Count elements in each partition
5. Get the sum of each partition

---

## Intermediate Level Exercises

### Exercise 6: Advanced Grouping
Given a list of students with (name, subject, score):
1. Group students by subject, then collect their names
2. Group by subject, then calculate average score
3. Group by subject, then find max score
4. Group by subject, then count students
5. Group by subject, get student with highest score

### Exercise 7: Multi-level Grouping
Given a list of products with (category, brand, price):
1. Group by category, then by brand
2. Group by price range, then by category
3. Group by category, then partition by price > 100
4. Group by category, then get average price per brand
5. Group by brand, then count products per category

### Exercise 8: Downstream Collectors
Given a list of transactions with (type, amount, date):
1. Group by type, sum amounts
2. Group by type, collect amounts to list
3. Group by type, find max amount
4. Group by type, get average amount
5. Group by type, count transactions

### Exercise 9: Mapping and Filtering
Given a list of employees:
1. Collect employee names to a List
2. Collect names of employees with salary > 50000
3. Collect unique departments
4. Collect names in uppercase
5. Collect department names sorted

### Exercise 10: Custom Reduction
Given a list of numbers:
1. Use reducing() to find sum
2. Use reducing() to find product
3. Use reducing() to find max
4. Use reducing() to concatenate strings with custom logic
5. Calculate weighted average using reducing()

---

## Advanced Level Exercises

### Exercise 11: Complex Grouping Scenarios
Given a list of orders with (customer, product, quantity, price):
1. Group by customer, calculate total order value
2. Group by product, sum quantities sold
3. Group by customer, find most purchased product
4. Group by customer, get list of unique products
5. Group by customer, calculate average order value

### Exercise 12: Teeing Collector (Java 12+)
Given a list of numbers:
1. Calculate both sum and count in one pass
2. Get both min and max simultaneously
3. Collect statistics (average and count) together
4. Calculate mean and standard deviation
5. Get top 3 and bottom 3 elements simultaneously

### Exercise 13: Collectors.collectingAndThen()
Given a list of employees:
1. Collect to list, then make it unmodifiable
2. Group by department, then count and convert to single value
3. Collect names, then join with comma
4. Find max salary, then format as currency
5. Group by department, get average salary, format result

### Exercise 14: Custom Collector Implementation
Create custom collectors for:
1. Collecting to comma-separated string
2. Collecting to immutable set
3. Finding mode (most frequent element)
4. Calculating median
5. Creating frequency map

### Exercise 15: Performance Optimization
Given a large stream of data:
1. Compare sequential vs parallel collection
2. Use appropriate primitive collectors (IntStream collectors)
3. Optimize memory usage with proper collectors
4. Benchmark different grouping strategies
5. Analyze collector performance characteristics

---

## Expert Level Exercises

### Exercise 16: Financial Data Analysis
Given a list of transactions with (account, type, amount, timestamp):
1. Calculate total deposits and withdrawals per account
2. Find accounts with negative balance
3. Calculate daily transaction summaries
4. Identify suspicious patterns (large withdrawals)
5. Generate account statements with running balance

### Exercise 17: E-commerce Analytics
Given a list of orders with (customer, product, category, quantity, price, date):
1. Calculate revenue by category and time period
2. Find top 10 customers by total spending
3. Calculate product popularity (sales count)
4. Identify seasonal trends in categories
5. Calculate customer lifetime value

### Exercise 18: Multi-criteria Grouping
Given employee data:
1. Group by department and seniority level
2. Group by location, department, and role
3. Create salary distribution by multiple dimensions
4. Calculate averages across multiple grouping levels
5. Build hierarchical data structure from flat list

### Exercise 19: Data Transformation Pipeline
Given raw data stream:
1. Clean, validate, group, and aggregate in one pipeline
2. Apply multiple transformations with downstream collectors
3. Handle missing/invalid data during collection
4. Generate multiple reports from single stream
5. Create nested data structures efficiently

### Exercise 20: Custom Collector with Mutable State
Implement advanced custom collectors:
1. Collector that maintains order statistics
2. Collector that tracks unique values and duplicates
3. Collector that builds tree structure
4. Collector with custom combiner for parallel streams
5. Collector that generates summary statistics with custom metrics

---

## Hints and Tips

### Basic Collectors Quick Reference
```java
Collectors.toList()
Collectors.toSet()
Collectors.toMap(keyMapper, valueMapper)
Collectors.joining(delimiter)
Collectors.counting()
Collectors.summingInt(mapper)
Collectors.averagingDouble(mapper)
```

### Grouping Quick Reference
```java
Collectors.groupingBy(classifier)
Collectors.groupingBy(classifier, downstream)
Collectors.partitioningBy(predicate)
Collectors.partitioningBy(predicate, downstream)
```

### Advanced Collectors
```java
Collectors.reducing(identity, accumulator)
Collectors.collectingAndThen(downstream, finisher)
Collectors.mapping(mapper, downstream)
Collectors.filtering(predicate, downstream)
```

### Performance Tips
- Use primitive collectors when possible (summarizingInt vs summarizingDouble)
- Consider parallel streams for large datasets
- Be aware of boxing/unboxing overhead
- Use appropriate downstream collectors
- Test collector performance with realistic data
