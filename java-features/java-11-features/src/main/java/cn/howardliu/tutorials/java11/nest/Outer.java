package cn.howardliu.tutorials.java11.nest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public void callInnerMethod() {
        final Inner inner = new Inner();
        inner.print4();
    }

    public void callInnerReflectionMethod()
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final Inner inner = new Inner();
        inner.callOuterPrivateMethod(this);
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

        public void callOuterPrivateMethod(Outer outer)
                throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            final Method method = outer.getClass().getDeclaredMethod("print12");
            method.invoke(outer);
        }
    }
}
