
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue; //dct все методы потокобезопасны и синхронизированны
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);
    private static Random rand = new Random();

    public static void main(String[] arg) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                producer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                consumer();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void producer() {

        while(true) {
            try {
                queue.put(rand.nextInt(100)); // в очередь кладем число, put потокобезопасный и ждет если очередь заполнена
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consumer() {
        while(true) {
            if (rand.nextInt(10) == 5) {
                try {
                    Thread.sleep(600);
                    System.out.println(queue.take());// потокобезопасный и желдет если очередь пустая
                    System.out.println("Queue size is " + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
