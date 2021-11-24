package cn.howardliu.tutorials.java8;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/24 07:54
 */
public class InterfaceMain {
    public static void main(String[] args) {
        final String target = Producer.producer();
        System.out.println(target);

        final Producer producer = new Hamburger();
        System.out.println(producer.produce());
    }
}
