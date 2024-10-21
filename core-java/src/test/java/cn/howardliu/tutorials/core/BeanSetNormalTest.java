package cn.howardliu.tutorials.core;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-18
 */
public class BeanSetNormalTest {
    public static void main(String[] args) throws Exception {
        JavaBean bean = new JavaBean();

        // 反射赋值
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            setPropertyUsingReflection(bean, "name", "Test");
        }
        long end = System.currentTimeMillis();
        System.out.println("Reflection: " + (end - start) + " ms");

        // 内省赋值
        start = System.currentTimeMillis();
        PropertyDescriptor pd = new PropertyDescriptor("name", JavaBean.class);
        Method writeMethod = pd.getWriteMethod();
        for (int i = 0; i < 1000000; i++) {
            setPropertyUsingIntrospection(writeMethod, bean, "Test");
        }
        end = System.currentTimeMillis();
        System.out.println("Introspection: " + (end - start) + " ms");
    }

    private static void setPropertyUsingReflection(JavaBean bean, String fieldName, Object value) throws Exception {
        Field field = JavaBean.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(bean, value);
    }

    private static void setPropertyUsingIntrospection(Method writeMethod, JavaBean bean, Object value) throws Exception {
        writeMethod.invoke(bean, value);
    }

    public static class JavaBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
