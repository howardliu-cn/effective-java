package cn.howardliu.tutorials.java20;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-09
 */
public class ScopedValueMain {
    // 声明一个作用域值用于存储用户名
    public final static ScopedValue<String> USERNAME = ScopedValue.newInstance();

    private static final Runnable printUsername = () ->
            System.out.println(Thread.currentThread().threadId() + " 用户名是 " + USERNAME.get());

    public static void main(String[] args) throws Exception {
        // 将用户名 "Bob" 绑定到作用域并执行 Runnable
        ScopedValue.where(USERNAME, "Bob").run(() -> {
            printUsername.run();
            new Thread(printUsername).start();
        });

        // 将用户名 "Chris" 绑定到另一个作用域并执行 Runnable
        ScopedValue.where(USERNAME, "Chris").run(() -> {
            printUsername.run();
            new Thread(() -> {
                new Thread(printUsername).start();
                printUsername.run();
            }).start();
        });

        // 检查在任何作用域外 USERNAME 是否被绑定
        System.out.println("用户名是否被绑定: " + USERNAME.isBound());
    }

}
