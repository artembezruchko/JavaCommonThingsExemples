import java.util.Arrays;

public class VarArgs {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.showVarArgs("a", "b", "c", "d");
    }
    static class MyClass {
        public void showVarArgs(String ...value ) {
            Arrays.stream(value).forEach(System.out::println);
        }
    }
}
