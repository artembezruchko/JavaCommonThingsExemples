import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(i, countDownLatch));
        }
        executorService.shutdown(); // закончили прием работы

        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    static class Processor implements Runnable {
        private int id;
        private CountDownLatch countDownLatch;

        public Processor(int id, CountDownLatch countDownLatch){
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                countDownLatch.await(); // await() для ожидания самоблокировки/ длится пока отсчет обьекта не достигнет 0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread with id " + id + " proceeded" );
        }
    }
}
