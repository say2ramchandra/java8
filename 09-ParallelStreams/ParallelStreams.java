package parallelstreams;

import java.util.*;
import java.util.stream.*;

/**
 * PARALLEL STREAMS IN JAVA 8
 * ===========================
 * 
 * Parallel streams divide the data into multiple chunks and process them concurrently
 * using the Fork/Join framework, utilizing multiple CPU cores.
 * 
 * CREATING PARALLEL STREAMS:
 * - collection.parallelStream()
 * - stream.parallel()
 * 
 * WHEN TO USE:
 * - Large datasets
 * - CPU-intensive operations
 * - Independent operations
 * 
 * WHEN NOT TO USE:
 * - Small datasets (overhead > benefit)
 * - Order-dependent operations
 * - I/O-bound operations
 * 
 * @author Java 8 Mastery Course
 */
public class ParallelStreams {
    
    public static void main(String[] args) {
        System.out.println("=== PARALLEL STREAMS ===\n");
        
        List<Integer> numbers = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toList());
        
        // Sequential stream
        long start = System.currentTimeMillis();
        long sumSeq = numbers.stream()
                .mapToLong(n -> (long) n * n)
                .sum();
        long seqTime = System.currentTimeMillis() - start;
        System.out.println("Sequential sum: " + sumSeq + " (Time: " + seqTime + "ms)");
        
        // Parallel stream
        start = System.currentTimeMillis();
        long sumPar = numbers.parallelStream()
                .mapToLong(n -> (long) n * n)
                .sum();
        long parTime = System.currentTimeMillis() - start;
        System.out.println("Parallel sum: " + sumPar + " (Time: " + parTime + "ms)");
        
        // Demonstration with large dataset
        System.out.println("\n Processing 10 million numbers:");
        List<Integer> largeData = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        // Sequential
        start = System.currentTimeMillis();
        long count1 = largeData.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Sequential: " + (System.currentTimeMillis() - start) + "ms");
        
        // Parallel
        start = System.currentTimeMillis();
        long count2 = largeData.parallelStream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Parallel: " + (System.currentTimeMillis() - start) + "ms");
    }
}
