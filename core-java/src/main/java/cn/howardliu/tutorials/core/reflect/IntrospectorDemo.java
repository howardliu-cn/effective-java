package cn.howardliu.tutorials.core.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-16
 */
public class IntrospectorDemo {
    public static void main(String[] args)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        final BeanInfo userBeanInfo = Introspector.getBeanInfo(User.class);
        final User user = new User();

        for (MethodDescriptor methodDescriptor : userBeanInfo.getMethodDescriptors()) {
            System.out.println("Method Name: " + methodDescriptor.getName());
            final ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
            if (parameterDescriptors == null) {
                continue;
            }
            for (ParameterDescriptor parameterDescriptor : parameterDescriptors) {
                System.out.println("Parameter Name: " + parameterDescriptor.getName());
                System.out.println("Parameter DisplayName: " + parameterDescriptor.getDisplayName());
            }
        }

        // 遍历所有属性描述符
        for (PropertyDescriptor prop : userBeanInfo.getPropertyDescriptors()) {
            System.out.println("Property Name: " + prop.getName());

            // 获取getter方法
            final Method readMethod = prop.getReadMethod();
            if (readMethod != null) {
                Object value = readMethod.invoke(user);
                System.out.println("Property Value: " + value);
            }

            if ("username".equals(prop.getName())) {
                // 获取setter方法
                final Method writeMethod = prop.getWriteMethod();
                if (writeMethod != null) {
                    Object value = writeMethod.invoke(user, "看山");
                    System.out.println("Property Value: " + value);
                }
            }
        }
        System.out.println(user);

        System.out.println("========");

        final BeanInfo userBeanInfo2 = Introspector.getBeanInfo(UserNoSetter.class);
        final UserNoSetter user2 = new UserNoSetter();

        for (MethodDescriptor methodDescriptor : userBeanInfo2.getMethodDescriptors()) {
            System.out.println("Method Name: " + methodDescriptor.getName());
            final ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
            if (parameterDescriptors == null) {
                continue;
            }
            for (ParameterDescriptor parameterDescriptor : parameterDescriptors) {
                System.out.println("Parameter Name: " + parameterDescriptor.getName());
                System.out.println("Parameter DisplayName: " + parameterDescriptor.getDisplayName());
            }
        }

        // 遍历所有属性描述符
        for (PropertyDescriptor prop : userBeanInfo2.getPropertyDescriptors()) {
            System.out.println("Property Name: " + prop.getName());

            // 获取getter方法
            final Method readMethod = prop.getReadMethod();
            if (readMethod != null) {
                Object value = readMethod.invoke(user2);
                System.out.println("Property Value: " + value);
            }

            if ("username".equals(prop.getName())) {
                // 获取setter方法
                final Method writeMethod = prop.getWriteMethod();
                if (writeMethod != null) {
                    Object value = writeMethod.invoke(user2, "看山");
                    System.out.println("Property Value: " + value);
                }
            }
        }
        System.out.println(user2);
    }

    @Data
    public static class User {
        private String username;
    }

    public static class UserNoSetter {
        private String username;

        public void username(String username) {
            this.username = username;
        }

        public UserNoSetter setUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public String toString() {
            return "UserNoSetter{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
