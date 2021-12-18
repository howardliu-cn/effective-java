package cn.howardliu.tutorials.java9;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/18 11:00
 */
public interface Metric {
    // 常量
    String NAME = "METRIC";

    // 抽象方法
    void info();

    // 私有方法
    private void append(String tag, String info) {
        buildMetricInfo();
        System.out.println(NAME + "[" + tag + "]:" + info);
        clearMetricInfo();
    }

    // 默认方法
    default void appendGlobal(String message) {
        append("GLOBAL", message);
    }

    // 默认方法
    default void appendDetail(String message) {
        append("DETAIL", message);
    }

    // 私有静态方法
    private static void buildMetricInfo() {
        System.out.println("build base metric");
    }

    // 私有静态方法
    private static void clearMetricInfo() {
        System.out.println("clear base metric");
    }
}
