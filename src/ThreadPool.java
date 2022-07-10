import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) {
        new Example().main();
    }

    static class Example {
        public void main() {
            ExecutorService executorService = Executors.newFixedThreadPool(5); // Пулл потоков

            for (int i = 0; i < 5; i++) {
                executorService.submit(new Work(i)); // пулу передается работа и он сам определяет какой поток ее возьмет
            }
            executorService.shutdown(); // пул перестает принимать задание, а начинает выполнять переданные, после этого начнется выполнять задания
            System.out.println("All tasks submited");

            try {
                executorService.awaitTermination(1, TimeUnit.DAYS); // даю времени на выполнение потоками заданий
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        class Work implements Runnable {
            private int id;

            public Work(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Work id = " + id + " done");
            }
        }
    }
}
