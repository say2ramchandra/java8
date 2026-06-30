package completablefuture;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * SOLUTIONS TO COMPLETABLEFUTURE EXERCISES
 * =========================================
 * This file contains comprehensive solutions demonstrating CompletableFuture for async programming.
 * 
 * @author Java 8 Mastery Course
 * @version 1.0
 */
public class Solutions {

    public static void main(String[] args) throws Exception {
        System.out.println("=== COMPLETABLEFUTURE - EXERCISE SOLUTIONS ===\n");
        
        // Run all exercise solutions
        exercise1_CreatingFutures();
        exercise2_BasicTransformations();
        exercise3_CombiningFutures();
        exercise4_ErrorHandling();
        exercise5_ExecutionControl();
        exercise6_ChainedOperations();
        exercise7_ParallelExecution();
        exercise8_TimeoutHandling();
        exercise9_RealWorldAsync();
        exercise10_ComplexPipelines();
    }

    // ==================== BASIC LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 1: Creating CompletableFutures
     * Solution demonstrates different ways to create CompletableFutures
     */
    private static void exercise1_CreatingFutures() {
        System.out.println("EXERCISE 1: Creating CompletableFutures");
        System.out.println("-".repeat(60));
        
        // 1. Completed future
        CompletableFuture<String> completed = CompletableFuture.completedFuture("Done");
        System.out.println("Completed future: " + completed.join());
        
        // 2. supplyAsync - with return value
        CompletableFuture<Integer> supply = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return 42;
        });
        System.out.println("Supply async: " + supply.join());
        
        // 3. runAsync - no return value
        CompletableFuture<Void> run = CompletableFuture.runAsync(() -> {
            System.out.println("  Running async task...");
            sleep(50);
        });
        run.join();
        
        // 4. Manual completion
        CompletableFuture<String> manual = new CompletableFuture<>();
        manual.complete("Manually completed");
        System.out.println("Manual: " + manual.join());
        
        System.out.println();
    }

    /**
     * EXERCISE 2: Basic Transformations
     * Solution demonstrates transforming CompletableFuture results
     */
    private static void exercise2_BasicTransformations() {
        System.out.println("EXERCISE 2: Basic Transformations");
        System.out.println("-".repeat(60));
        
        // 1. thenApply - transform result
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);
        System.out.println("thenApply: " + future.join());
        
        // 2. thenAccept - consume result
        CompletableFuture.supplyAsync(() -> "Result")
                .thenAccept(result -> System.out.println("thenAccept: " + result))
                .join();
        
        // 3. thenRun - run after completion
        CompletableFuture.supplyAsync(() -> 100)
                .thenRun(() -> System.out.println("thenRun: Task completed"))
                .join();
        
        // 4. Async variants
        CompletableFuture<String> async = CompletableFuture
                .supplyAsync(() -> "Async")
                .thenApplyAsync(s -> s + " Processing");
        System.out.println("Async variant: " + async.join());
        
        System.out.println();
    }

    /**
     * EXERCISE 3: Combining Futures
     * Solution demonstrates combining multiple CompletableFutures
     */
    private static void exercise3_CombiningFutures() {
        System.out.println("EXERCISE 3: Combining Futures");
        System.out.println("-".repeat(60));
        
        // 1. thenCombine - combine two futures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        
        String combined = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2).join();
        System.out.println("thenCombine: " + combined);
        
        // 2. thenCompose - dependent futures
        CompletableFuture<String> composed = CompletableFuture
                .supplyAsync(() -> "User123")
                .thenCompose(userId -> fetchUserData(userId));
        System.out.println("thenCompose: " + composed.join());
        
        // 3. allOf - wait for all
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "Task1";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            sleep(50);
            return "Task2";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            sleep(75);
            return "Task3";
        });
        
        CompletableFuture<Void> allDone = CompletableFuture.allOf(f1, f2, f3);
        allDone.join();
        System.out.println("allOf results: " + f1.join() + ", " + f2.join() + ", " + f3.join());
        
        // 4. anyOf - first to complete
        CompletableFuture<Object> firstDone = CompletableFuture.anyOf(f1, f2, f3);
        System.out.println("anyOf (first): " + firstDone.join());
        
        System.out.println();
    }

    /**
     * EXERCISE 4: Error Handling
     * Solution demonstrates error handling in CompletableFutures
     */
    private static void exercise4_ErrorHandling() {
        System.out.println("EXERCISE 4: Error Handling");
        System.out.println("-".repeat(60));
        
        // 1. exceptionally - recover from error
        CompletableFuture<Integer> withError = CompletableFuture
                .supplyAsync(() -> {
                    if (true) throw new RuntimeException("Error occurred");
                    return 100;
                })
                .exceptionally(ex -> {
                    System.out.println("Caught: " + ex.getMessage());
                    return -1;
                });
        System.out.println("exceptionally result: " + withError.join());
        
        // 2. handle - handle both success and error
        CompletableFuture<String> handled = CompletableFuture
                .supplyAsync(() -> {
                    if (Math.random() > 0.5) throw new RuntimeException("Random error");
                    return "Success";
                })
                .handle((result, exception) -> {
                    if (exception != null) {
                        return "Recovered from: " + exception.getMessage();
                    }
                    return result;
                });
        System.out.println("handle result: " + handled.join());
        
        // 3. whenComplete - side effect on completion
        CompletableFuture.supplyAsync(() -> "Data")
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.out.println("whenComplete: Error - " + exception.getMessage());
                    } else {
                        System.out.println("whenComplete: Success - " + result);
                    }
                })
                .join();
        
        System.out.println();
    }

    /**
     * EXERCISE 5: Execution Control
     * Solution demonstrates controlling execution with custom executors
     */
    private static void exercise5_ExecutionControl() {
        System.out.println("EXERCISE 5: Execution Control");
        System.out.println("-".repeat(60));
        
        // 1. Custom executor
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        CompletableFuture<String> customExec = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("  Running in: " + Thread.currentThread().getName());
                    return "Custom executor result";
                }, executor);
        
        System.out.println("Custom executor: " + customExec.join());
        
        // 2. Async suffix methods use ForkJoinPool
        CompletableFuture<String> defaultPool = CompletableFuture
                .supplyAsync(() -> "Default")
                .thenApplyAsync(s -> {
                    System.out.println("  Async in: " + Thread.currentThread().getName());
                    return s + " pool";
                });
        System.out.println("Default pool: " + defaultPool.join());
        
        executor.shutdown();
        System.out.println();
    }

    // ==================== INTERMEDIATE LEVEL SOLUTIONS ====================

    /**
     * EXERCISE 6: Chained Operations
     * Solution demonstrates complex chaining
     */
    private static void exercise6_ChainedOperations() {
        System.out.println("EXERCISE 6: Chained Operations");
        System.out.println("-".repeat(60));
        
        // Complex chain
        CompletableFuture<String> chain = CompletableFuture
                .supplyAsync(() -> "user123")
                .thenApply(userId -> "Data for " + userId)
                .thenApply(String::toUpperCase)
                .thenCompose(data -> CompletableFuture.supplyAsync(() -> data + " - Processed"))
                .thenApply(result -> result + " - Final");
        
        System.out.println("Chained result: " + chain.join());
        System.out.println();
    }

    /**
     * EXERCISE 7: Parallel Execution
     * Solution demonstrates parallel async tasks
     */
    private static void exercise7_ParallelExecution() {
        System.out.println("EXERCISE 7: Parallel Execution");
        System.out.println("-".repeat(60));
        
        long start = System.currentTimeMillis();
        
        // Execute multiple tasks in parallel
        List<CompletableFuture<Integer>> futures = IntStream.range(1, 6)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                    sleep(100);
                    return i * i;
                }))
                .collect(Collectors.toList());
        
        // Wait for all and collect results
        List<Integer> results = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        
        long time = System.currentTimeMillis() - start;
        System.out.println("Results: " + results);
        System.out.println("Time (parallel): " + time + "ms (~100ms, not 500ms)");
        System.out.println();
    }

    /**
     * EXERCISE 8: Timeout Handling
     * Solution demonstrates handling timeouts
     */
    private static void exercise8_TimeoutHandling() {
        System.out.println("EXERCISE 8: Timeout Handling");
        System.out.println("-".repeat(60));
        
        try {
            // Simulate slow operation with timeout
            CompletableFuture<String> future = CompletableFuture
                    .supplyAsync(() -> {
                        sleep(2000);  // 2 seconds
                        return "Slow result";
                    })
                    .orTimeout(1, TimeUnit.SECONDS)  // 1 second timeout
                    .exceptionally(ex -> "Timeout! Using default value");
            
            System.out.println("Result: " + future.join());
        } catch (Exception e) {
            System.out.println("Caught timeout exception");
        }
        
        System.out.println();
    }

    /**
     * EXERCISE 9: Real-world Async Scenario
     * Solution demonstrates practical async usage
     */
    private static void exercise9_RealWorldAsync() {
        System.out.println("EXERCISE 9: Real-world Async Scenario");
        System.out.println("-".repeat(60));
        
        // Simulate fetching data from multiple services
        CompletableFuture<String> userService = CompletableFuture
                .supplyAsync(() -> {
                    sleep(100);
                    return "User: Alice";
                });
        
        CompletableFuture<String> orderService = CompletableFuture
                .supplyAsync(() -> {
                    sleep(150);
                    return "Orders: 5";
                });
        
        CompletableFuture<String> recommendationService = CompletableFuture
                .supplyAsync(() -> {
                    sleep(120);
                    return "Recommendations: 10";
                });
        
        // Combine all results
        CompletableFuture<String> dashboard = userService
                .thenCombine(orderService, (user, orders) -> user + ", " + orders)
                .thenCombine(recommendationService, (combined, recs) -> combined + ", " + recs);
        
        System.out.println("Dashboard: " + dashboard.join());
        System.out.println();
    }

    /**
     * EXERCISE 10: Complex Pipelines
     * Solution demonstrates complex async pipelines
     */
    private static void exercise10_ComplexPipelines() {
        System.out.println("EXERCISE 10: Complex Pipelines");
        System.out.println("-".repeat(60));
        
        // Multi-stage pipeline with error handling
        CompletableFuture<String> pipeline = CompletableFuture
                .supplyAsync(() -> "input")
                .thenApply(input -> {
                    System.out.println("  Stage 1: Validate " + input);
                    return input.toUpperCase();
                })
                .thenCompose(validated -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("  Stage 2: Process " + validated);
                    sleep(50);
                    return validated + "-PROCESSED";
                }))
                .thenApply(processed -> {
                    System.out.println("  Stage 3: Transform " + processed);
                    return processed + "-FINAL";
                })
                .exceptionally(ex -> {
                    System.out.println("  Error in pipeline: " + ex.getMessage());
                    return "ERROR";
                })
                .thenApply(result -> {
                    System.out.println("  Stage 4: Finalize " + result);
                    return result;
                });
        
        System.out.println("Pipeline result: " + pipeline.join());
        System.out.println();
    }

    // ==================== HELPER METHODS ====================

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static CompletableFuture<String> fetchUserData(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(50);
            return "User data for " + userId;
        });
    }
}
