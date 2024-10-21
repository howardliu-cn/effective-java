package cn.howardliu.tutorials.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-16
 */
public class ReflectDemo {
    public void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchFieldException {
        final Class<?> clazz = MyClass.class;
        // Class<?> clazz = new MyClass().getClass();
        // Class<?> clazz = Class.forName("cn.howardliu.tutorials.core.reflect.ReflectDemo.MyClass");

        final Constructor<?> constructor = clazz.getConstructor();
        // Constructor<?> constructor = clazz.getConstructor(String.class);
        final Object o = constructor.newInstance();

        final Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(o, "看山");

        final Method method = clazz.getMethod("echoName");
        final Object result = method.invoke(o);
        System.out.println(result);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyClass {
        private String name;

        public String echoName() {
            return name;
        }
    }
}
