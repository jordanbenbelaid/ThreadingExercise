public class SyncCounter {
    int count = 0;

    synchronized void increment() {
        count++; // Thread-safe
    }
}
