import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // ---------------- Part 1: Runnable Demo ----------------
        System.out.println("=== Runnable Computation Demo ===");
        Thread r1 = new Thread(new ComputationRunnable("Thread-A", 10));
        Thread r2 = new Thread(new ComputationRunnable("Thread-B", 20));
        r1.start();
        r2.start();
        r1.join();
        r2.join();

        // ---------------- Part 2: ExecutorService Demo ----------------
        System.out.println("\n=== ExecutorService Computation Demo ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new ComputationTask("Task-1", 5));
        executor.submit(new ComputationTask("Task-2", 7));
        executor.submit(new ComputationTask("Task-3", 10));
        executor.submit(new ComputationTask("Task-4", 12));
        executor.submit(new ComputationTask("Task-5", 15));
        executor.shutdown();
        while (!executor.isTerminated()) {
            // wait for tasks
        }

        // ---------------- Part 3: Race Condition (Unsafe Counter) ----------------
        System.out.println("\n=== Unsafe Counter (Race Condition) ===");
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        };

        Thread u1 = new Thread(unsafeTask);
        Thread u2 = new Thread(unsafeTask);
        u1.start();
        u2.start();
        u1.join();
        u2.join();
        System.out.println("Expected: 2000, Actual: " + unsafeCounter.count);

        // ---------------- Part 4: Synchronized Counter ----------------
        System.out.println("\n=== Synchronized Counter ===");
        SyncCounter syncCounter = new SyncCounter();
        Runnable syncTask = () -> {
            for (int i = 0; i < 1000; i++) {
                syncCounter.increment();
            }
        };

        Thread s1 = new Thread(syncTask);
        Thread s2 = new Thread(syncTask);
        s1.start();
        s2.start();
        s1.join();
        s2.join();
        System.out.println("Expected: 2000, Actual: " + syncCounter.count);

        // ---------------- Part 5: AtomicInteger Counter ----------------
        System.out.println("\n=== AtomicInteger Counter ===");
        AtomicInteger atomicCounter = new AtomicInteger(0);
        Runnable atomicTask = () -> {
            for (int i = 0; i < 1000; i++) {
                atomicCounter.incrementAndGet();
            }
        };

        Thread a1 = new Thread(atomicTask);
        Thread a2 = new Thread(atomicTask);
        a1.start();
        a2.start();
        a1.join();
        a2.join();
        System.out.println("Expected: 2000, Actual: " + atomicCounter.get());

        System.out.println("\n=== All Demos Completed ===");
    }
}
