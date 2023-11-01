class SumThread extends Thread {
    private int start;
    private int end;
    private long partialSum;

    public SumThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public long getPartialSum() {
        return partialSum;
    }

    @Override
    public void run() {
        partialSum = 0;
        for (int i = start; i <= end; i++) {
            partialSum += i;
        }
    }
}

public class threadex2 {
    public static void main(String[] args) throws InterruptedException {
        int totalNumbers = 1000000;
        int threadCount = 4;

        SumThread[] threads = new SumThread[threadCount];
        int numbersPerThread = totalNumbers / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * numbersPerThread + 1;
            int end = (i + 1) * numbersPerThread;

            threads[i] = new SumThread(start, end);
            threads[i].start();
        }

        long totalSum = 0;
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
            totalSum += threads[i].getPartialSum();
        }

        System.out.println("Total sum: " + totalSum);
    }
}
