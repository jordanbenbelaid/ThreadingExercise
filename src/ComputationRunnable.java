public class ComputationRunnable implements Runnable{

    private String name;
    private int n;

    ComputationRunnable(String name, int n) {
        this.name = name;
        this.n = n;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println(name + " computed sum 1.." + n + " = " + sum);
    }
}
