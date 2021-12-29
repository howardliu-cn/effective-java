package cn.howardliu.tutorials.java11.nest;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021-12-29
 */
public class Outer {
    private int i;

    public void print1() {
        print11();
        print12();
    }

    private void print11() {
        System.out.println(i);
    }

    private void print12() {
        System.out.println(i);
    }

    public class Inner {
        public void print3() {
            print1();
        }

        public void print4() {
            print11();
            print12();
        }

        private void print5() {
            print11();
            print12();
        }
    }
}
