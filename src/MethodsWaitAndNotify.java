
import java.util.Scanner;

public class MethodsWaitAndNotify {
   static WaitAndNotify wn = new WaitAndNotify();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread1());
        Thread thread2 = new Thread(new MyThread2());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static class WaitAndNotify {
        public void produce() {
            synchronized (this) {
                System.out.println("Producer thread started...");
                try {
                    wait(); // вызывается на на обьекте this, локи надо указывать явно lock.wait()| 1- отдаем intrinsic lock(монитор) 2- ждем вызова notify() на этом обьекте
                    //wait(1000); ждет таймаут до вызова notify() если через этот таймаут notify() не вызовется -продолжит выполнени блока
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer thread resumed...");
            }
        }

        public void consumer(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Scanner sc = new Scanner(System.in);
            synchronized (this) {
                System.out.println("Waiting fot return key pressed");
                sc.nextLine();
                notify(); //пробуждает только один поток| важно чтоб wait notify синхронизировлаись на одном обьекте / также notify не отдает монитор
                // notifyAll(); пробуждает все потоки которые ждут

            }// монитор отдастся после выболнения блока synchronized

        }
    }

    static class MyThread1 implements Runnable {

        @Override
        public void run() {
            wn.produce();
        }
    }

    static class MyThread2 implements Runnable {

        @Override
        public void run() {
            wn.consumer();
        }
    }
}
