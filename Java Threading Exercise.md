# Java Threading Exercise

---

## Part 1: Runnable Computation

1. Create a class `ComputationRunnable` that implements `Runnable`.
2. The constructor should accept:
   - `String name` → thread name
   - `int n` → the number up to which the thread will sum numbers
3. The `run()` method should:
   - Sum numbers from 1 to `n`
   - Print the result like:  
     `Thread-A computed sum 1..10 = 55`
4. In `main()`, create **two threads** with different `n` values and start them.
5. Wait for threads to finish using `join()`.


```java
class ComputationRunnable implements Runnable {
    private String name;
    private int n;

    public ComputationRunnable(String name, int n) {
        this.name = name;
        this.n = n;
    }

    @Override
    public void run() {
        // TODO: Compute sum from 1 to n and print
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ComputationRunnable("Thread-A", 10));
        Thread t2 = new Thread(new ComputationRunnable("Thread-B", 20));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
```

---

## Part 2: ExecutorService Computation

1. Create a class `ComputationTask` that implements `Runnable`.
2. The constructor should accept:
   - `String taskName` → task name
   - `int n` → the number to compute factorial
3. The `run()` method should:
   - Compute factorial of `n` (1×2×…×n)
   - Print: `Task-1 computed factorial of 5 = 120`
4. In `main()`, create an `ExecutorService` with 3 threads.
5. Submit **5 tasks** with different `n` values.
6. Shut down the executor and wait for all tasks to finish.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ComputationTask implements Runnable {
    private String taskName;
    private int n;

    public ComputationTask(String taskName, int n) {
        this.taskName = taskName;
        this.n = n;
    }

    @Override
    public void run() {
        // TODO: Compute factorial of n and print
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new ComputationTask("Task-1", 5));
        executor.submit(new ComputationTask("Task-2", 7));
        // TODO: Submit remaining tasks
        executor.shutdown();
    }
}
```

---

## Part 3: Race Condition (Unsafe Counter)

1. Create an `UnsafeCounter` with an `int count` and `increment()` method.
2. Create **two threads**, each incrementing the counter 1000 times.
3. Start threads and wait for them to finish.
4. Print the final count.  
   - Question: Is the count always 2000? Why or why not?

```java
class UnsafeCounter {
    int count = 0;

    void increment() {
        count++; // Not thread-safe
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.count);
    }
}
```

---

## Part 4: Synchronized Counter

1. Create a `SynchronizedCounter` with a **synchronized** `increment()` method.
2. Repeat the same steps as Part 3.
3. The final count should always be 2000.

```java
class SynchronizedCounter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.count);
    }
}
```

---

## Part 5: AtomicInteger Counter

1. Create an `AtomicInteger` counter.
2. Create two threads, each incrementing 1000 times using `incrementAndGet()`.
3. Start threads and wait for them to finish.
4. The final count should always be 2000.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.get());
    }
}
```
