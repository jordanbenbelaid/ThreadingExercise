public class ComputationTask implements Runnable {
    private String taskName;
    private int n;

    ComputationTask(String taskName, int n) {
        this.taskName = taskName;
        this.n = n;
    }

    @Override
    public void run() {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            // prevent overflow for large n
            if (factorial < 0) break;
        }
        System.out.println(taskName + " computed factorial of " + n + " = " + factorial);
    }
}
