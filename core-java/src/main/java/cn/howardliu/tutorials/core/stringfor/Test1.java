package cn.howardliu.tutorials.core.stringfor;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-27
 */
public class Test1 {
    public String toString() {
        String s = "";
        for (int i = 0; i < 100; i++) {
            s += System.currentTimeMillis();
        }
        return s;
    }
}
