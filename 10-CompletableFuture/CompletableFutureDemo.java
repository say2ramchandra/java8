package completablefuture;

import java.util.concurrent.*;

/**
 * COMPLETABLEFUTURE AND ASYNCHRONOUS PROGRAMMING
 * ===============================================
 * 
 * CompletableFuture enables asynchronous, non-blocking programming.
 * It represents a future result of an asynchronous computation.
 * 
 * KEY METHODS:
 * - supplyAsync() - Run asynchronously and return result
 * - runAsync() - Run asynchronously without return
 * - thenApply() - Transform result
 * - thenAccept() - Consume result
 * - thenCompose() - Chain dependent futures
 * - thenCombine() - Combine two futures
 * - exceptionally() - Handle errors
 * - allOf(), anyOf() - Combine multiple futures
 * 
 * @author Java 8 Mastery Course
 */
public class CompletableFutureDemo {
    
    public static void main(String[] args) throws Exception {
        System.out.println("=== COMPLETABLEFUTURE ===\n");
        
        // Simple async computation
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Hello from Future!";
        });
        
        System.out.println("Doing other work...");
        System.out.println("Result: " + future.get());
        
        // Chaining operations
        CompletableFuture<String> chain = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);
        System.out.println("\nChained: " + chain.get());
        
        // Combining futures
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        
        CompletableFuture<String> combined = future1.thenCombine(future2, 
                (s1, s2) -> s1 + " " + s2);
        System.out.println("Combined: " + combined.get());
        
        // Error handling
        CompletableFuture<Integer> withError = CompletableFuture
                .supplyAsync(() -> {
                    if (true) throw new RuntimeException("Error!");
                    return 42;
                })
                .exceptionally(ex -> {
                    System.out.println("Caught: " + ex.getMessage());
                    return -1;
                });
        System.out.println("\nWith error handling: " + withError.get());
    }
    
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
