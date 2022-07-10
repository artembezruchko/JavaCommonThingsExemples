import java.util.Random;
import java.util.concurrent.*;

public class СallableFeature {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = executorService.submit(() -> { // interface new Callable replaced with lambda
            System.out.println("Start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int randValue =  new Random().nextInt(10);
            if (randValue < 5) {
                throw new Exception("Exception in thread for catch case");
            }

            return randValue;
        });
        executorService.shutdown();
        try {
            int result = future.get(); // get дожидается оконания потока
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
