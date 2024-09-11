package cn.howardliu.tutorials.java22;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-02
 */
public class SuperExample {
    public class Example {
        public Example() {
            System.out.println("执行前置语句");
            super();
        }
    }
}
