package cn.howardliu.tutorials.java8;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/24 07:53
 */
public interface Producer {
    static String producer() {
        return "target: " + System.currentTimeMillis();
    }

    default String produce() {
        return "NULL";
    }
}
