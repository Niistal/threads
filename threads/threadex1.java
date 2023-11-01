class FibonacciThread extends Thread {
    private int N;

    public FibonacciThread(int n) {
        this.N = n;
    }

    @Override
    public void run() {
        int[] fNumbers = new int[N];
        fNumbers[0] = 1;
        fNumbers[1] = 1;

        for (int i = 2; i < N; i++) {
        fNumbers[i] = fNumbers[i - 1] + fNumbers[i - 2];
        }

        System.out.println("Fibonacci sequence for N = " + N + ":");
        for (int num : fNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

public class threadex1 {
    public static void main(String[] args) {
        FibonacciThread thread1 = new FibonacciThread(10);
        FibonacciThread thread2 = new FibonacciThread(15);

        thread1.start();
        thread2.start();
    }
}
