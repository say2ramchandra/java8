# Parallel Streams - Practice Exercises

## Basic Level Exercises

### Exercise 1: Creating Parallel Streams
Create parallel streams in different ways:
1. Convert sequential stream to parallel using `.parallel()`
2. Create parallel stream from collection using `.parallelStream()`
3. Check if a stream is parallel using `.isParallel()`
4. Convert parallel stream back to sequential
5. Create parallel stream from array

### Exercise 2: Basic Parallel Operations
Given a large list of integers (1 to 1,000,000):
1. Calculate sum using sequential stream
2. Calculate sum using parallel stream
3. Compare execution times
4. Find maximum value with parallel stream
5. Count elements matching a condition

### Exercise 3: Performance Comparison
Compare sequential vs parallel for:
1. Filtering large dataset
2. Mapping transformation
3. Reduction operations
4. Sorting operations
5. Collecting to list

### Exercise 4: When to Use Parallel Streams
Identify appropriate use cases:
1. Process 1 million numbers - calculate squares
2. Process 100 strings - convert to uppercase
3. Large file processing with I/O operations
4. CPU-intensive mathematical calculations
5. Small dataset operations (< 1000 elements)

### Exercise 5: Parallel Stream Characteristics
Understand parallel stream behavior:
1. Observe order of execution (may be non-deterministic)
2. Use `.forEachOrdered()` to maintain order
3. Compare `.forEach()` vs `.forEachOrdered()` in parallel
4. Understand work-stealing in ForkJoinPool
5. Check available parallelism level

---

## Intermediate Level Exercises

### Exercise 6: Thread Safety Issues
Identify and fix issues:
1. Unsafe shared mutable state in parallel stream
2. Using non-thread-safe collections
3. Race conditions in parallel operations
4. Safe alternatives using concurrent collections
5. Using reduction instead of shared state

### Exercise 7: Stateful Operations
Handle stateful operations correctly:
1. Avoid `limit()` with unordered parallel streams
2. Use `findAny()` vs `findFirst()` appropriately
3. Understanding `skip()` in parallel contexts
4. Proper use of `sorted()` in parallel streams
5. Stateless vs stateful operation performance

### Exercise 8: Custom ForkJoinPool
Work with custom thread pools:
1. Create custom ForkJoinPool with specific parallelism
2. Execute parallel stream in custom pool
3. Compare default vs custom pool performance
4. Set parallelism level programmatically
5. Monitor pool statistics

### Exercise 9: Reduction Operations
Safe parallel reductions:
1. Use `reduce()` with identity and combiner
2. Use `collect()` with proper concurrent collector
3. Parallel `groupingByConcurrent()`
4. Calculate statistics in parallel
5. Combine parallel sub-results correctly

### Exercise 10: Performance Optimization
Optimize parallel stream performance:
1. Choose optimal data structure (ArrayList vs LinkedList)
2. Size threshold for parallel vs sequential
3. Minimize boxing/unboxing overhead
4. Use primitive streams when possible
5. Profile and measure actual performance gains

---

## Advanced Level Exercises

### Exercise 11: Complex Parallel Aggregations
Implement complex operations:
1. Parallel word count from multiple texts
2. Parallel calculation of statistical metrics
3. Parallel grouping and aggregation
4. Parallel distinct element counting
5. Parallel custom accumulation

### Exercise 12: Avoiding Common Pitfalls
Fix problematic parallel code:
1. Shared ArrayList modification (use concurrent alternative)
2. Non-associative reduction operations
3. Order-dependent operations in parallel context
4. Side-effects in lambda expressions
5. Synchronization overhead

### Exercise 13: Parallel Stream Patterns
Implement advanced patterns:
1. Map-reduce pattern in parallel
2. Parallel filtering with complex predicates
3. Parallel batch processing
4. Parallel data partitioning
5. Parallel pipeline optimization

### Exercise 14: Benchmarking Framework
Create benchmarking utilities:
1. Measure sequential vs parallel execution time
2. Test scalability with varying data sizes
3. Measure speedup ratio
4. Calculate Amdahl's law limitations
5. Profile parallel stream overhead

### Exercise 15: Real-world Parallel Processing
Solve practical problems:
1. Process large CSV file in parallel
2. Parallel image processing filters
3. Parallel data validation and transformation
4. Parallel log file analysis
5. Parallel computation of aggregates from multiple sources

---

## Performance Guidelines

### When Parallel Streams Are Beneficial
✓ Large datasets (typically > 10,000 elements)
✓ CPU-intensive operations
✓ Independent, stateless operations
✓ Operations with high computational cost
✓ Splitting operations (not sequential dependencies)

### When to Avoid Parallel Streams
✗ Small datasets (overhead > benefit)
✗ I/O-bound operations (disk/network)
✗ Ordered operations requiring sequence
✗ Shared mutable state
✗ Simple operations (iteration cost low)

### Best Practices
- Measure! Don't assume parallel is faster
- Use primitive streams to avoid boxing
- Prefer `.parallelStream()` over `.parallel()`
- Ensure operations are stateless and thread-safe
- Use appropriate data structures (splittable)
- Be aware of the common ForkJoinPool size
- Consider using custom ForkJoinPool for isolation

### Data Structure Splitability
**Good for parallel:**
- ArrayList
- Array
- IntStream.range
- HashMap
- HashSet

**Poor for parallel:**
- LinkedList
- Streams from iterate()
- Streams with limit()
- BufferedReader lines()

### Performance Formula
```
Speedup = Time(Sequential) / Time(Parallel)
Efficiency = Speedup / Number_of_Cores
```

Ideal speedup ≈ number of cores, but:
- Overhead reduces actual speedup
- Amdahl's Law limits parallel portions
- Contention and synchronization reduce efficiency
