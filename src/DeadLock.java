
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    public static void main(String[] args) {
        Runner runner = new Runner();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
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

        runner.finished();
    }

    static class Runner {
        public Account account1 = new Account();
        public Account account2 = new Account();

        public Lock lock1 = new ReentrantLock();
        public Lock lock2 = new ReentrantLock();

        public void tryToLock(Lock l1 , Lock l2){
            boolean isLockedFirst = false;
            boolean isLockedSecond = false;

            while (true) {
                try {

                    isLockedFirst = l1.tryLock(); // tryLock делает lock() и возвращает true если обьет залочился
                    isLockedSecond = l2.tryLock();

                } finally { // unlock(); всегда делать в блоке finally

                    if (isLockedFirst && isLockedSecond) {
                        return;
                    }
                    if (isLockedFirst) {
                        l1.unlock();
                    }
                    if (isLockedSecond) {
                        l2.unlock();
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void firstThread() {
            Random rand = new Random();
            for(int i = 0; i < 10000; i++) {
                tryToLock(lock1, lock2);
                try {
                    Account.transfer(account1, account2, rand.nextInt(100));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        }

        public void secondThread() {
            Random rand = new Random();
            for(int i = 0; i < 10000; i++) {
                tryToLock(lock2, lock1);
                try {
                    Account.transfer(account2, account1, rand.nextInt(100));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        }

        public void finished() {
            System.out.println(account1.getBalance());
            System.out.println(account2.getBalance());
            System.out.println("Total balance " + (account1.getBalance() + account2.getBalance()));
        }
    }

    static class Account {
        private int balance = 10000;

        public void deposit(int amount){
            balance += amount;
        }

        public void withdraw(int amount){
            balance -= amount;
        }

        public int getBalance(){
            return  balance;
        }

        public static void transfer(Account acc1, Account acc2, int amount) {
            acc1.withdraw(amount);
            acc2.deposit(amount);
        }
    }

}
