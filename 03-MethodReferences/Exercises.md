# Method References - Practice Exercises

## Basic Level

### Exercise 1: Convert Lambda to Method Reference
Convert these lambda expressions to method references:
1. `str -> System.out.println(str)`
2. `x -> Math.abs(x)`
3. `s -> s.length()`
4. `x -> Integer.parseInt(x)`
5. `() -> new ArrayList<>()`

### Exercise 2: Static Method References
Use static method references for:
1. Find max of two numbers using Math.max
2. Parse double from string
3. Compare two strings ignoring case
4. Get current time in milliseconds
5. Calculate power of a number

### Exercise 3: Instance Method References
Create method references for:
1. String trim method
2. String toLowerCase method
3. List add method
4. StringBuilder append method
5. Random nextInt method

### Exercise 4: Constructor References
Use constructor references to create:
1. ArrayList
2. HashSet
3. StringBuilder
4. String array of size n
5. Custom Person object

## Intermediate Level

### Exercise 5: Sorting with Method References
Sort these using method references:
1. List of strings alphabetically
2. List of integers in natural order
3. List of Person objects by name
4. List of products by price
5. List of employees by salary (descending)

### Exercise 6: Stream Operations
Use method references in streams to:
1. Convert list of strings to uppercase
2. Filter non-null values
3. Extract IDs from list of objects
4. Calculate sum of product prices
5. Group employees by department

### Exercise 7: Comparator Chaining
Create comparators using method references:
1. Compare Person by age, then by name
2. Compare Product by category, then by price
3. Compare Student by grade (desc), then by name
4. Null-safe comparator for optional fields
5. Case-insensitive string comparator

### Exercise 8: Method Reference Types
Identify and implement all four types:
1. Static method reference for validation
2. Instance method reference for logging
3. Arbitrary object method reference for transformation
4. Constructor reference for object creation

## Advanced Level

### Exercise 9: Factory Pattern
Implement a factory using constructor references:
- Support multiple product types
- Register constructors dynamically
- Handle parameterized constructors
- Implement error handling
- Test with 5 different product types

### Exercise 10: Builder Pattern
Create a builder using method references:
- Fluent API for object construction
- Validation at each step
- Support for optional fields
- Method chaining
- Build final immutable object

### Exercise 11: Strategy Pattern
Implement strategy pattern with method references:
- Multiple sorting strategies
- Payment processing strategies
- Discount calculation strategies
- Use method references for strategy selection

### Exercise 12: Custom Functional Operations
Create utility class with methods for:
- List transformations
- Filtering operations
- Reduction operations
- Use these methods as method references

## Expert Level

### Exercise 13: Generic Factory
Create a generic object factory:
- Type-safe constructor references
- Support for multiple constructor parameters
- Dependency injection
- Lazy initialization
- Caching

### Exercise 14: Reflection and Method References
- Dynamically create method references using reflection
- Convert method names to method references
- Invoke methods using method references
- Handle different method signatures

### Exercise 15: Performance Comparison
Compare performance:
- Lambda vs Method Reference
- Different method reference types
- With large datasets (1M+ elements)
- Memory consumption analysis
