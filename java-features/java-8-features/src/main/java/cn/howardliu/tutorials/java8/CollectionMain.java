package cn.howardliu.tutorials.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/8 10:24
 */
public class CollectionMain {
    public static void main(String[] args) {
        final List<String> vars = Arrays.asList("1", "2", "3");
        final Object[] objArray = vars.toArray();
        final String[] strArray = vars.toArray(new String[0]);
        System.out.println(objArray);
        System.out.println(strArray);
    }
}
