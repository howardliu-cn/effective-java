package cn.howardliu.tutorials.java21;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-28
 */
public class UnnamedExample {
    public static void main(String[] args) {
        var _ = new Point(1, 2);
    }

    record Point(int x, int y) {
    }
}
