import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExeReentrantLock {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new  Mythread1());
        Thread thread2 = new Thread(new Mythread2());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Task.showCounter();

    }
    static class Mythread1 implements Runnable {

        @Override
        public void run() {
           Task.firstThread();
        }
    }
    static class Mythread2 implements Runnable {

        @Override
        public void run() {
            Task.secondThread();
        }
    }
    static class Task {
        private static int conter;
        private static Lock lock = new ReentrantLock();

        static public void incremet() {
            for (int i = 0; i < 1000; i++) {
                conter++;
            }
        }

       static public void firstThread() {
            lock.lock(); // логика работы локов похожа на блок synchronize на после .lock() все остальны потоки ждут .unlock();
           try {
               incremet();
           } finally { // .unlock() надо вызывать в блоке finally, чтоб он отдал монитор, если  до этого произойдет ошибка
               lock.unlock(); // .unlock(); надо вызвать на обьекте столько раз сколько был вызван .lock(); на этом же обьекте
           }
        }

        static public void secondThread() {
            lock.lock();
            try {

                incremet();
            } finally {
                lock.unlock();
            }
        }

       static public void showCounter() {
            System.out.println(conter);
        }


    }
}
