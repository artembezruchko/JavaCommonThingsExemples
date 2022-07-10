public class SynchronizedExamples {

    private int counter = 0;

    public static void main(String[] args) {
            SynchronizedExamples test = new SynchronizedExamples(); // при создании обьекта в джаве ему выдается монитор
                                                                    // и в 1 единицу времени с монитором может работать 1 поток
            test.doWork(); // нестатический медот doWork(), что пернадлежит обьекту  вызываем в потоке main
        }

    public synchronized void increment() { // только метод может быть синхронизированным, не переменная. смысл в том
        counter++;                         // что к телу метода в единицу времени имеет доступ только 1 поток
                                           // тут мы синхронизируемся не явно на обьекте this. обьекте класса SynchronizedExamples и его мониторе
    }

    public void doWork() {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                       increment();//  - так потокобезопасно переменная не может быть синхронизированной, только метод
                    }

                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increment(); // counter++;
                    }
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

            System.out.println(counter); // эта переменная редок будет 20000 если increment не будет synchronized,
                                         // потму что это вывод находится в main поктоке, а потоки 1 и 2 устраивают гонки
        }
}
