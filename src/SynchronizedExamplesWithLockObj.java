import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedExamplesWithLockObj {
    public static void main(String[] args) {
        new Test().main();
    }

    public static class Test {

        public Object lock1 = new Object();    // поскольку каждый обьект имеет 1 монитор, мы сосздаем отдельные обьекты
        public Object lock2 = new Object();    // на которые будет синхронизироваться поктоки, таким образом один поток
                                               // не будет ждать другой

        public List<Integer> lsit1 = new ArrayList<>();
        public List<Integer> lsit2 = new ArrayList<>();
        Random random = new Random();

        public void addToList1() {
            synchronized (lock1) {            // синхронизация на специально созданный lock1
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lsit1.add(random.nextInt(100));
            }
        }

        public void addToList2() {
            synchronized (lock2) {            // синхронизация на специально созданный lock2
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lsit2.add(random.nextInt(100));
            }
        }

        public void work() {                 
            for (int i = 0; i < 1000; i++) {
                addToList1();
                addToList2();
            }
        }

        public void main() {
            long timeStart = System.currentTimeMillis();
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    work();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    work();
                }
            });
            thread1.start();
            thread2.start();

            try {
                thread1.join(); // это значит main поток будет ждать выполнения поктока thread1 и мосле этого продолжит main
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(lsit1.size());
            System.out.println(lsit2.size());
            long timeE = System.currentTimeMillis();
            System.out.println("Time of actionn: " + (timeE - timeStart));
        }
    }
}
