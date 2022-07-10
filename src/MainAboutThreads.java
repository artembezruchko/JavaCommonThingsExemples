import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainAboutThreads {
    public static void main(String[] args) {
        java.lang.Thread My1thread = new java.lang.Thread(new MyThread());
        My1thread.start(); // все потоки стартуются этим методом
        // При чем поток main не останавливается
        List<String> listString = new ArrayList<>();
        listString.add("att");
        listString.add("attt");
        listString.add("atttt");
        listString.add("attttt");
        listString.add("atttttt");

        Collections.sort(listString);
        System.out.println(listString);
    }

    class Person {
        String id;
        String name;

        @Override
        public boolean equals(Object o) { // cвой метод который сравнивает хеши обьэктов втоего кода
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(id, person.id) && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 100; i++ ) {
                System.out.println("inner code run = " + i);
            }
        }
    }
// Можно так реализовывать многопоточность, но не советуют
//    static class MyThreadThread extends Thread {
//        @Override
//        public void run() {
//            for(int i = 0; i < 100; i++ ) {
//                System.out.println("Thread inner code run = " + i);
//            }
//        }
//    }
}