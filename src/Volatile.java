import java.util.Scanner;

public class Volatile {
    public static void main(String[] args) {
        MyTread myTread = new  MyTread();
        myTread.start();

        Scanner scanner =new Scanner(System.in);
        scanner.nextLine();
        myTread.shutdown();
    }

     static class MyTread extends Thread {

        private volatile boolean running = true; //гарантия когерентности ядер - переменная будет храниться не в кешах ядер, а в памяти

        public void run() {
            int i = 0;
            while(running) {
                System.out.println(" run: " + i++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            this.running = false;
        }
    }
}
