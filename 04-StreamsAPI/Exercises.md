# Streams API - Practice Exercises

## Basic Level

### Exercise 1: Stream Creation
Create streams using different methods:
1. From a list of integers
2. From an array of strings
3. Using Stream.of() with 10 numbers
4. Generate infinite stream of random numbers (limit to 100)
5. Create IntStream from 1 to 100

### Exercise 2: Filter and Map
Given list [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
1. Filter even numbers
2. Filter numbers > 5
3. Map each number to its square
4. Map each number to its string representation
5. Filter and map: square of odd numbers

### Exercise 3: Terminal Operations
Use terminal operations to:
1. Count elements in a stream
2. Find minimum and maximum
3. Check if all elements are positive
4. Check if any element is divisible by 7
5. Find first element greater than 50

### Exercise 4: Collectors
Collect stream results into:
1. List
2. Set
3. Map (group by even/odd)
4. Comma-separated string
5. Statistics summary

## Intermediate Level

### Exercise 5: FlatMap
1. Flatten list of lists [[1,2], [3,4], [5,6]]
2. Split sentences into words
3. Extract all characters from list of strings
4. Flatten nested employee departments
5. Process nested JSON-like structure

### Exercise 6: Sorting and Limiting
Create a stream and:
1. Sort in ascending order
2. Sort in descending order
3. Get top 10 elements
4. Skip first 5, then get next 10
5. Sort by custom comparator

### Exercise 7: Employee Analysis
Given Employee class with name, department, salary:
1. Find average salary by department
2. Get highest paid employee
3. List all unique departments
4. Count employees per department
5. Find employees with salary > average

### Exercise 8: String Operations
Given list of strings:
1. Convert all to uppercase
2. Filter strings longer than 5 characters
3. Remove duplicates
4. Sort alphabetically
5. Join with delimiter

## Advanced Level

### Exercise 9: Complex Grouping
Process sales data to:
1. Group by category, then by price range
2. Calculate total revenue per category
3. Find top-selling product in each category
4. Generate sales report
5. Identify underperforming categories

### Exercise 10: Custom Collectors
Create custom collectors for:
1. Collecting to immutable list
2. Collecting with transformation
3. Collecting with filtering
4. Partitioning with custom logic
5. Custom reduction logic

### Exercise 11: Performance Optimization
1. Compare sequential vs parallel streams
2. Optimize large dataset processing
3. Lazy evaluation demonstration
4. Short-circuit operations
5. Memory-efficient streaming

### Exercise 12: Data Pipeline
Build a complete data processing pipeline:
- Read data from source
- Clean and validate
- Transform
- Aggregate
- Generate report

## Expert Level

### Exercise 13: Multi-threaded Processing
1. Process 1M records using parallel streams
2. Custom ForkJoinPool configuration
3. Thread-safe collectors
4. Performance benchmarking
5. Optimal parallelization strategy

### Exercise 14: Advanced Reductions
1. Implement custom reduction operations
2. Combiner functions for parallel streams
3. Stateful vs stateless operations
4. Reduction with accumulator
5. Complex aggregations

### Exercise 15: Stream Integration
Integrate streams with:
1. Database queries (JDBC)
2. File I/O operations
3. REST API calls
4. Real-time data processing
5. Event stream processing
