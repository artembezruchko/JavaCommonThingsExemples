import java.util.LinkedList;
import java.util.Queue;

public class NotSafeProducerConsumer {

        static ProducerConsumer pc = new ProducerConsumer();

        public static void main(String[] args) {

            Thread thread1 = new Thread(new MyThread1());
            Thread thread2 = new Thread(new MyThread2());

            thread1.start();
            thread2.start();
            System.out.println("test");

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static class ProducerConsumer {

            private Queue<Integer> queue = new LinkedList<>();
            private final int LIMIT = 10;
            private final Object lock = new Object();

            public void produce() {

                int value = 0;
                while (true) {
                    synchronized (lock) {
                        while (queue.size() == LIMIT) {
                            try {
                                lock.wait(); // отдаем монитор если очередь заполнена к потребителю
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                            queue.offer(value++); // в имном случае добавляем значение в очередь метод .offer() не потокобезопасный
                            lock.notify(); // подтверждение потребителю, что очередь не пустая
                    }
                }
            }

            public void consumer(){
                while (true) {
                    synchronized (lock) {
                        while (queue.size() == 0) {
                            try {
                                lock.wait(); // отдается монитор производителю если очередь пустая
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        int value = queue.poll();
                        System.out.println("Value = " + value);
                        System.out.println("Queue size = " + queue.size());
                        lock.notify(); // сообщаем производителю, что обьект взят из очереди
                    }// монитор отдастся после выболнения блока synchronized
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static class MyThread1 implements Runnable {

            @Override
            public void run() {
                pc.produce();
            }
        }

        static class MyThread2 implements Runnable {

            @Override
            public void run() {
                pc.consumer();
            }
        }
}
