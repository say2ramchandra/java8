# Lambda Expressions - Practice Exercises

## Basic Level Exercises

### Exercise 1: Simple Lambda Expressions
Create lambda expressions for the following functional interfaces:
1. A `Runnable` that prints "Hello from Lambda"
2. A `Supplier<Integer>` that returns a random number between 1 and 100
3. A `Consumer<String>` that prints a string in uppercase
4. A `Predicate<Integer>` that checks if a number is even
5. A `Function<String, Integer>` that returns the length of a string

### Exercise 2: Basic Comparators
Write lambda expressions to:
1. Sort a list of strings in alphabetical order
2. Sort a list of strings by length (shortest first)
3. Sort a list of integers in descending order
4. Sort a list of strings in reverse alphabetical order

### Exercise 3: List Operations
Given a list of integers `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`, use lambda expressions with `removeIf()` to:
1. Remove all even numbers
2. Remove all numbers greater than 5
3. Remove all numbers divisible by 3
4. Remove all numbers that are not prime

### Exercise 4: forEach Practice
Create a list of your favorite movies (at least 5) and use lambda expressions with `forEach()` to:
1. Print each movie name
2. Print each movie name with its index (1. Movie1, 2. Movie2, etc.)
3. Print only movies that contain the letter 'a'
4. Print movies in uppercase

---

## Intermediate Level Exercises

### Exercise 5: Student Grade Calculator
Create a `Student` class with fields: `name`, `age`, and `marks` (array of integers).
Write lambda expressions to:
1. Calculate the average marks for each student
2. Filter students who scored above 75% average
3. Sort students by their average marks (highest first)
4. Print student names who failed (average < 40%)

### Exercise 6: String Manipulation
Given a list of strings, write lambda expressions to:
1. Convert all strings to uppercase
2. Filter strings that start with a vowel
3. Remove strings with length less than 3
4. Create a new list with the first character of each string
5. Count how many strings contain the letter 'e'

### Exercise 7: Calculator Operations
Create a map of calculator operations where:
- Key: Operation name (String) - "add", "subtract", "multiply", "divide"
- Value: Lambda expression that performs the operation (BiFunction<Double, Double, Double>)

Test all operations with sample inputs.

### Exercise 8: Custom Functional Interface
1. Create a functional interface `MathOperation` with a method `double operate(double a, double b)`
2. Create lambda expressions for: addition, subtraction, multiplication, division, power, modulo
3. Create a method that takes two numbers and a MathOperation, then returns the result
4. Test with various operations

---

## Advanced Level Exercises

### Exercise 9: Employee Management System
Create an `Employee` class with: `id`, `name`, `department`, `salary`, `yearsOfExperience`.
Create a list of at least 10 employees. Write lambda expressions to:
1. Find all employees in the "IT" department
2. Give a 15% salary hike to employees with more than 5 years of experience
3. Find the employee with the highest salary
4. Calculate the total salary expense for each department
5. Find employees whose name starts with 'A' and salary > 50000
6. Group employees by department and count them

### Exercise 10: Transaction Processing
Create a `Transaction` class with: `id`, `accountNumber`, `amount`, `type` (CREDIT/DEBIT), `date`.
Generate 20 sample transactions. Write lambda expressions to:
1. Calculate total credited amount
2. Calculate total debited amount
3. Find the largest transaction
4. Filter suspicious transactions (amount > 100000)
5. Group transactions by type and sum amounts
6. Find all transactions for a specific account number

### Exercise 11: Thread Operations
1. Create 5 threads using lambda expressions
2. Each thread should print numbers from 1 to 5 with the thread name
3. Use lambda expressions to implement a Runnable that sleeps for random duration
4. Create a thread that monitors and prints when other threads complete

### Exercise 12: Custom Sorting Logic
Create a `Product` class with: `name`, `category`, `price`, `rating`, `stockQuantity`.
Create a list of 15 products. Implement lambda-based sorting for:
1. Sort by price (low to high)
2. Sort by rating (high to low)
3. Sort by name alphabetically
4. Multi-level sort: First by category, then by price within each category
5. Custom sort: In-stock items first, then by rating

---

## Expert Level Exercises

### Exercise 13: E-Commerce Order Processing Pipeline
Create classes: `Customer`, `Order`, `OrderItem`, `Product`.
- `Customer`: id, name, email, loyaltyPoints
- `Order`: orderId, customer, items (List<OrderItem>), status, orderDate
- `OrderItem`: product, quantity, discount
- `Product`: productId, name, price, category

Write lambda expressions to:
1. Calculate total order value including discounts
2. Apply loyalty discount based on customer points (>1000 points = 5% off)
3. Find top 5 customers by total purchase value
4. Calculate average order value by customer
5. Find most popular product category
6. Generate invoice for an order (formatted string with all details)

### Exercise 14: Banking System
Create a banking system with:
- `Account`: accountNumber, holderName, balance, accountType (SAVINGS/CURRENT)
- `Transaction`: date, type, amount, description

Implement lambda expressions for:
1. Process monthly interest (3% for SAVINGS, 0% for CURRENT)
2. Validate transactions (ensure sufficient balance before debit)
3. Generate monthly statement
4. Calculate total interest paid to all accounts
5. Find accounts with balance below minimum (10000 for SAVINGS, 25000 for CURRENT)
6. Implement transaction categorization and reporting

### Exercise 15: Data Validation Framework
Create a generic validation framework using lambda expressions.
1. Create a `Validator<T>` functional interface with method `boolean validate(T object)`
2. Create a `ValidationRule<T>` class that stores a validator lambda and error message
3. Create a `ValidationEngine<T>` that can:
   - Add multiple validation rules using lambdas
   - Validate an object against all rules
   - Return list of validation errors
4. Test with:
   - User registration (validate email, password strength, age)
   - Product input (validate price > 0, name not empty, stock >= 0)
   - Order validation (total amount, item count, customer details)

### Exercise 16: Event-Driven System
Design an event-driven notification system:
1. Create different event types: LOGIN, LOGOUT, PURCHASE, PASSWORD_CHANGE
2. Create an EventManager that allows registering lambda-based event handlers
3. Each event can have multiple handlers (observers)
4. Implement priority-based handler execution using lambdas
5. Add support for filtering events before processing
6. Test with scenarios:
   - Send email on purchase
   - Log all login attempts
   - Alert security team on password changes
   - Update user statistics on logout

### Exercise 17: Advanced Collection Operations
Given a complex data structure (List of Maps of Lists), use lambda expressions to:
1. Flatten the structure
2. Remove duplicates
3. Transform and aggregate data
4. Perform multi-level grouping
5. Calculate complex statistics

Example structure:
```java
List<Map<String, List<Integer>>> complexData
```

### Exercise 18: Performance Optimization Challenge
Create a large dataset (1 million records) of sensor readings:
- `SensorReading`: sensorId, timestamp, temperature, humidity, location

Use lambda expressions to:
1. Find all anomalies (temperature > 100 or humidity < 10)
2. Calculate hourly averages for each sensor
3. Identify sensors with consistent high readings
4. Compare lambda-based vs traditional loop performance
5. Optimize using appropriate functional approaches

---

## Bonus Challenges

### Exercise 19: Lambda Expression Chaining
Create a text processing pipeline using lambda chaining:
1. Read text input
2. Convert to lowercase
3. Remove special characters
4. Split into words
5. Remove stop words
6. Count word frequency
7. Sort by frequency
8. Return top 10 words

Implement this using chained lambda expressions.

### Exercise 20: Custom Comparator Factory
Create a `ComparatorFactory` class that generates Comparator lambdas:
1. Method to create comparator for any field using reflection
2. Method to create reversed comparator
3. Method to create null-safe comparator
4. Method to chain multiple comparators
5. Test with various classes and scenarios

---

## Tips for Practice:
- Start with basic exercises and gradually move to advanced ones
- Write clean, readable lambda expressions
- Add comments explaining your logic
- Test edge cases (null values, empty lists, etc.)
- Compare lambda solutions with traditional approaches
- Measure and compare performance where applicable
- Focus on making code more readable and maintainable
