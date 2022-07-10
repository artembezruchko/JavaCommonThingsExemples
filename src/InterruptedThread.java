import java.util.Random;

public class InterruptedThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random rand  = new Random();
                for(int i = 0; i < 100_000_000; i++){

//                    try { // поток прирвется по такой же логике так как будет поймана InterruptedException
//                        Thread.sleep(1);
//                    } catch (InterruptedException e){
//                        System.out.println("Thread Interrupted try-catch");
//                        break;
//                    }

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread Interrupted");
                        break;
                    }
                    Math.sin(rand.nextInt(100));
                }
            }
        });

        System.out.println("Thread started");
        thread.start();
        thread.interrupt(); // прерывание должно просихожить до join(); к main thread
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finished");
    }
}
