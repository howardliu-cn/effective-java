package cn.howardliu.tutorials.java17;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/4/11 22:30
 */
public class SwitchMain {
    public static void main(String[] args) {
        System.out.println(formatterPatternSwitch(null));
        System.out.println(formatterPatternSwitch("1"));
        System.out.println(formatterPatternSwitch(2));
        System.out.println(formatterPatternSwitch(3L));
        System.out.println(formatterPatternSwitch(4.0));
        System.out.println(formatterPatternSwitch(new AtomicLong(5)));
    }

    static String formatterPatternSwitch(Object o) {
        return switch (o) {
            case null -> "null";
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.getClass().getSimpleName() + " " + o;
        };
    }
}
