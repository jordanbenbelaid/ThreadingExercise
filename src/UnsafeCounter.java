public class UnsafeCounter {
    int count = 0;

    void increment() {
        count++; // Not thread-safe → race condition
    }
}
