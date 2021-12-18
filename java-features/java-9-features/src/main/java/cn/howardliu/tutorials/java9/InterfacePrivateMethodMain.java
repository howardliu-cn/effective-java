package cn.howardliu.tutorials.java9;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/18 10:51
 */
public class InterfacePrivateMethodMain {
    public static void main(String[] args) {
        final Metric metric = new Metric() {
            @Override
            public void info() {
                System.out.println("INFO");
            }
        };

        metric.info();
        metric.appendGlobal("看山");
        metric.appendDetail("看山的小屋");
    }
}
