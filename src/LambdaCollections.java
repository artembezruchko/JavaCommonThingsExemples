import java.util.*;
import java.util.stream.Collectors;

public class LambdaCollections {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        List<Integer> list1 = new ArrayList<>();

        fillArr(arr1);
        fillList(list1);

        System.out.println(list1);
        System.out.println(Arrays.toString(arr1));

//        for (int i = 0; i < 10; i++) {
//            arr[i] = arr[i] * 2;
//            list.set(i, list.get(i) * 2);
//        }

       arr1 = Arrays.stream(arr1).map( i -> i * 2).toArray(); // map безет элемнет из набора данных и сопоставляет  ему новый элемент, и это сопоставление описывается с помощью лямбда выражений
        // map вернет intStream, поэтомудля массива надо сделть  .toArray()
        list1 = list1.stream().map(i -> i * 2).collect(Collectors.toList()); // в данном случае возвращается Stream<Object>
        // .collect(Collectors.toList()); необхожим для преобразования в исходный вид

        // map method
        arr1 = Arrays.stream(arr1).map( i -> 3).toArray();


        System.out.println(list1);
        System.out.println(Arrays.toString(arr1));

        //filter method

        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();

        fillArr(arr2);
        fillList(list2);

        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray(); // выраэжение в лямбда может быть только булеан
        list2 = list2.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());
        System.out.println(Arrays.toString(arr2));
        System.out.println(list2);


        Arrays.stream(arr2).forEach(System.out::println); // ничего не возвращаем
        list2.forEach(a -> System.out.println(a));

        System.out.println(Arrays.toString(arr2));
        System.out.println(list2);

        // reduce
        int[] arr3 = new int[10];
        List<Integer> list3 = new ArrayList<>();

        fillArr(arr3);
        fillList(list3);

        // когда не указывается значение акамулятора то акумулятор берет себе 1 элемент а итерация начинается со 2-го
        // если указыватеся значение акумулятора то итераци начинается с 1 элемента
        int sum1 = Arrays.stream(arr3).reduce((accumulator , b)  -> accumulator + b ).getAsInt();
        int sum12 = Arrays.stream(arr3).reduce(0, (accumulator , b)  -> accumulator + b ); // тут можем задать акумулятор
        int sum2 = list3.stream().reduce((acc, b) -> acc * b).get();
        System.out.println(sum1);
        System.out.println(sum12);
        System.out.println(sum2);

        // chain
        int[] arr4 = new int[10];
        List<Integer> list4 = new ArrayList<>();

        fillArr(arr4);
        fillList(list4);

        int[] newArr4 = Arrays.stream(arr4).filter( a -> a % 2 != 0 ).map(a -> a * 2).toArray();
        System.out.println(Arrays.toString(newArr4));

        //Collections
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Set newSet = set.stream().map( s -> s * 3 ).collect(Collectors.toSet());
        System.out.println(set);
        System.out.println(newSet);
    }

    private static void fillList(List<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
    }

    private static void fillArr(int[] arr) {
        for (int i = 0; i < 10; i++) {
            arr[i] = i + 1;
        }
    }
}
