public class Lambda {
    public static void main(String[] args) {
        Runner runner = new Runner();
        /***
        Три абсолютно одинаковых по смыслу реализации
         ***/
        runner.run(new ExecuteImplementation());

        runner.run(new Execute() {
            @Override
            public void execute() {
                System.out.println("Hi! I am anonymous implementation ");
            }
        });

        runner.run(() -> System.out.println("Hi! I am lambda implementation") );
        /*
        лямбда, естественно, самая предпочтительная
         */
    }
}


interface Execute { // я интерфейс
        void execute();
    }

    class ExecuteImplementation implements  Execute { // я реаплизую интрефейс

        @Override
        public void execute() {
            System.out.println("Hi! I am ExecuteImplementation");
        }
    }

    class Runner {
        public void run (Execute e) {  // я принимаю на вхож интерфейс
            e.execute();
        }
    }
