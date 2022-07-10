
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExSemaphore {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Connect connect = Connect.getConnect();
        for(int i = 0; i < 10; i++ ) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connect.work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }

    //Singleton
    static class Connect {
        private static Connect connect = new Connect();
        private int connectCount;
        private  Semaphore semaphore = new Semaphore(10); //фиксированное кодичество потоков что могут обращаться к ресурсу

        private Connect () {

        }

        public static Connect getConnect() {
            return connect;
        }

        public  void work() throws InterruptedException {
            semaphore.acquire(); // назначить потоку ресурс
            try {
            mainAction();
            } finally { // блок finally  обязателен, так как  в mainAction может произойти ошибка и тогда не вернется ресурс
            semaphore.release(); // вернуть ресурс у потока
            }
        }

        private  void mainAction() {
            synchronized (this) {
                connectCount++;
                System.out.println("Added new connection " + connectCount);
            }
            try {
                Thread.sleep(5000); //имитация работы на сервере
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                connectCount--;
                System.out.println("Disabled one connection.  Left behind " + connectCount);
            }
        }
    }
}
