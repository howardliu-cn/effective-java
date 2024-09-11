package cn.howardliu.tutorials.java19;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-08
 */
public class SwitchPatternMatchingMain {
    static String formatValue(Object obj) {
        return switch (obj) {
            case null -> "null";
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            case Person(String name, String address) -> String.format("Person %s %s", name, address);
            default -> obj.toString();
        };
    }

    public record Person(String name, String address) {}

    public static void main(String[] args) {
        System.out.println(formatValue(10));
        System.out.println(formatValue(20L));
        System.out.println(formatValue(3.14));
        System.out.println(formatValue("Hello"));
        System.out.println(formatValue(null));
        System.out.println(formatValue(new Person("Howard", "Beijing")));
    }
}
