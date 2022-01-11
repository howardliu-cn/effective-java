package cn.howardliu.tutorials.java11.nest;

import cn.howardliu.tutorials.java11.nest.Outer.Inner;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021-12-30
 */
public class NestMain {
    public static void main(String[] args) {
        final String outerNestHostName = Outer.class.getNestHost().getName();
        System.out.println(outerNestHostName);

        final String innerNestHostName = Inner.class.getNestHost().getName();
        System.out.println(innerNestHostName);
    }
}
