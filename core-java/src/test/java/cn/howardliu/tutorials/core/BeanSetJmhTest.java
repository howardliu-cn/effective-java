package cn.howardliu.tutorials.core;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-17
 */
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 1)
@Measurement(iterations = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 2)
@Threads(4)
@State(Scope.Benchmark)
@OperationsPerInvocation
public class BeanSetJmhTest {
    @Param({"10", "100", "200", "500"})
    private int n;

    private final Class<User> clazz = User.class;
    private Constructor<User> constructor;
    private Field usernameField;
    private Field levelField;
    private Method setUsernameMethod;
    private Method setLevelMethod;
    private PropertyDescriptor[] userProps;

    private PropertyDescriptor usernameProp;
    private Method usernameWriteMethod;
    private PropertyDescriptor levelProp;
    private Method levelWriteMethod;

    @Setup
    public void setup() throws NoSuchMethodException, NoSuchFieldException, IntrospectionException {
        constructor = clazz.getConstructor();
        usernameField = clazz.getDeclaredField("username");
        usernameField.setAccessible(true);
        levelField = clazz.getDeclaredField("level");
        levelField.setAccessible(true);

        setUsernameMethod = clazz.getMethod("setUsername", String.class);
        setLevelMethod = clazz.getMethod("setLevel", int.class);

        BeanInfo userBeanInfo = Introspector.getBeanInfo(User.class);
        userProps = userBeanInfo.getPropertyDescriptors();

        usernameProp = new PropertyDescriptor("username", User.class);
        usernameWriteMethod = usernameProp.getWriteMethod();

        levelProp = new PropertyDescriptor("level", User.class);
        levelWriteMethod = levelProp.getWriteMethod();
    }

    @Test
    public void runBenchmarks() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .result("BeanSetJmhTest.json")
                .resultFormat(ResultFormatType.JSON)
                .build();

        new Runner(options).run();
    }

    /**
     * 基准线
     */
    @Benchmark
    public void testBase(Blackhole blackhole) {
        for (int i = 0; i < n; i++) {
            final User user = new User();
            user.setUsername("看山");
            user.setLevel(100);

            blackhole.consume(user);
        }
    }

    /**
     * 使用反射为指定 Field 赋值
     */
    @Benchmark
    public void testAccessFieldByReflect(Blackhole blackhole)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {
        for (int i = 0; i < n; i++) {
            final Class<User> clazz = User.class;
            final Constructor<User> constructor = clazz.getConstructor();
            final User user = constructor.newInstance();

            final Field usernameField = clazz.getDeclaredField("username");
            usernameField.setAccessible(true);
            usernameField.set(user, "看山");
            final Field levelField = clazz.getDeclaredField("level");
            levelField.setAccessible(true);
            levelField.set(user, 100);

            blackhole.consume(user);
        }
    }

    /**
     * 使用内省为指定 PropertyDescriptor 赋值
     */
    @Benchmark
    public void testAccessFieldByIntrospectorDirectProp(Blackhole blackhole)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            final User user = new User();

            final PropertyDescriptor usernameProp = new PropertyDescriptor("username", User.class);
            final Method usernameWriteMethod = usernameProp.getWriteMethod();
            usernameWriteMethod.invoke(user, "看山");

            final PropertyDescriptor levelProp = new PropertyDescriptor("level", User.class);
            final Method levelWriteMethod = levelProp.getWriteMethod();
            levelWriteMethod.invoke(user, 100);

            blackhole.consume(user);
        }
    }

    /**
     * 使用内省循环 PropertyDescriptor 赋值
     */
    @Benchmark
    public void testAccessFieldByIntrospectorProps(Blackhole blackhole)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            final BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
            final User user = new User();

            final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : props) {
                if ("username".equals(prop.getName())) {
                    final Method method = prop.getWriteMethod();
                    method.invoke(user, "看山");
                } else if ("level".equals(prop.getName())) {
                    final Method method = prop.getWriteMethod();
                    method.invoke(user, 100);
                }
            }

            blackhole.consume(user);
        }
    }

    /**
     * 使用反射为指定 Field 赋值（Field已缓存）
     */
    @Benchmark
    public void testAccessFieldCacheByReflectField(Blackhole blackhole)
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            final User user = constructor.newInstance();

            usernameField.set(user, "看山");
            levelField.set(user, 100);

            blackhole.consume(user);
        }
    }

    /**
     * 使用内省循环 PropertyDescriptor 赋值(prop已缓存)
     */
    @Benchmark
    public void testAccessFieldCacheByIntrospectorDirectProp(Blackhole blackhole)
            throws InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            final User user = new User();

            final Method usernameWriteMethod = usernameProp.getWriteMethod();
            usernameWriteMethod.invoke(user, "看山");

            final Method levelWriteMethod = levelProp.getWriteMethod();
            levelWriteMethod.invoke(user, 100);

            blackhole.consume(user);
        }
    }

    /**
     * 使用内省循环 PropertyDescriptor 赋值（prop已缓存）
     */
    @Benchmark
    public void testAccessFieldCacheByIntrospectorProps(Blackhole blackhole)
            throws InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            final User user = new User();

            for (PropertyDescriptor prop : userProps) {
                if ("username".equals(prop.getName())) {
                    final Method method = prop.getWriteMethod();
                    method.invoke(user, "看山");
                } else if ("level".equals(prop.getName())) {
                    final Method method = prop.getWriteMethod();
                    method.invoke(user, 100);
                }
            }

            blackhole.consume(user);
        }
    }

    @Benchmark
    public void testMethodByReflect(Blackhole blackhole)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            final Class<User> clazz = User.class;
            final Constructor<User> constructor = clazz.getConstructor();
            final User user = constructor.newInstance();

            final Method setUsernameMethod = clazz.getMethod("setUsername", String.class);
            setUsernameMethod.invoke(user, "看山");
            final Method setLevelMethod = clazz.getMethod("setLevel", int.class);
            setLevelMethod.invoke(user, 100);

            blackhole.consume(user);
        }
    }

    @Benchmark
    public void testMethodCacheByReflect(Blackhole blackhole)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (int i = 0; i < n; i++) {
            final Class<User> clazz = User.class;
            final Constructor<User> constructor = clazz.getConstructor();
            final User user = constructor.newInstance();

            setUsernameMethod.invoke(user, "看山");
            setLevelMethod.invoke(user, 100);

            blackhole.consume(user);
        }
    }


    @Benchmark
    public void testMethodCacheByIntrospector(Blackhole blackhole)
            throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        for (int i = 0; i < n; i++) {
            final Class<User> clazz = User.class;
            final Constructor<User> constructor = clazz.getConstructor();
            final User user = constructor.newInstance();

            usernameWriteMethod.invoke(user, "看山");
            levelWriteMethod.invoke(user, 100);

            blackhole.consume(user);
        }
    }

    @Data
    public static class User {
        private String username;
        private int level;
    }
}
