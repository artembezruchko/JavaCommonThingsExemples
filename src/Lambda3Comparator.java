import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lambda3Comparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("First word");
        list.add("Second word");
        list.add("Third word12");
        list.add("Forth word1234");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(list + "  sorted by Comparator");

        list.sort((s1, s2) -> {
            if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() < s2.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        System.out.println(list + "  sorted by Lambda");

        Comparator<String> comparator = (s1, s2) -> {
            if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() < s2.length()) {
                return -1;
            } else {
                return 0;
            }
        };

        list.sort(comparator);
        System.out.println(list + "  sorted by Lambda with Comparator");
    }
}
