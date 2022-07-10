import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExps {
    public static void main(String[] args) {
        /**
        https://regexlib.com/ - more examples

            \\d - одна цифра
            \\w - одна буква
            \\ [a-zA-z] == [\\w]
            + - один и более
            * - ноль и более

            ? - 0 или 1 символ до (символ перред знаком может быть а может не быть)
            (x|y|z) - одна строка из множетства строк (логическое или)

            [a-zA-z] - все английские буквы (все символы что могут встречаться)
            [abc] == (a|b|c)
            [0-9] == \\d
            [ˆ] - отрицание
            [ˆa-z] - все символы кроме английских букв

            . - любой символ

            {2} - точное количество сиволов до {\\d{2}} - на этом месте хочу видеть только 2 цифры
            {2,} - два символа или более
            {2, 4} - от двух до четырех символов

         */
        String a = "+123465";
        System.out.println(a.matches("(-|\\+)?\\d*"));

        String b = "asdasdasd131221";
        System.out.println(b.matches("[a-zA-Z]+\\d+"));
        String c = "33a1sdasdasd131221";
        System.out.println(c.matches("[a-zA-Z31]+\\d+"));

        String d = "https://google.com";
        String e = "https://google.ua";
        System.out.println(d.matches("https://.+\\.(com|ua)"));

        String f = "a-b-c-d";
        String[] ms = f.split("-");
        System.out.println(Arrays.toString(ms));
        String fResult = f.replaceAll("-", "\\.");
        String fResultFirst = f.replaceFirst("-", "\\.");
        System.out.println(fResult);
        System.out.println(fResultFirst );

        String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has" +
                " been the industry's standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                "centuries, but also the leap into electronic typesetting," +
                " remaining essentially unchanged. art@art.art It was popularised in " +
                "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently " +
                "with desktop publishing software like Aldus PageMaker including  123@art.art versions of Lorem Ipsum.";

        Pattern email = Pattern.compile("((\\w|\\d)+)@(art)\\.(art)"); // в () - группах есть ид группы
        Matcher matcher = email.matcher(text);
        while (matcher.find()) { // поиск очередного совпадения
            System.out.println(matcher.group()); //получение совпадения
            System.out.println(matcher.group(1)); //получение совпадения с 1 группой
        }
    }

}
