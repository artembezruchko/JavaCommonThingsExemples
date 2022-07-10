public class Lambda2Return {
    public static void main(String[] args) {
        RunnerTest runner = new RunnerTest();
        /***
         Три абсолютно одинаковых по смыслу реализации
         ***/
        runner.run(new ExeImplementation());

        runner.run(new Exe() {
            @Override
            public int execute(int x, int y) {
                System.out.println("Hi! I am anonymous implementation ");
                int result = x + y;
                System.out.println("Result = " +  result);
                return result;
            }
        });
        runner.run((x, y) -> {
            System.out.println("Hi! I am lambda implementation");
            int result = x + y;
            System.out.println("Result = " +  result);
            return result;
        });

        /*
        лямбда, естественно, самая предпочтительная
         */
    }
}

interface Exe { // я интерфейс
    int execute(int x, int y);
}

class ExeImplementation implements  Exe { // я реаплизую интрефейс

    @Override
    public int execute(int x, int y){
        System.out.println("Hi! I am ExecuteImplementation");
        int result = x + y;
        System.out.println("Result = " +  result);
        return x + y;
    }
}

class RunnerTest {
    public void run (Exe e) {  // я принимаю на вхож интерфейс
        e.execute( 10, 9);
    }
}
