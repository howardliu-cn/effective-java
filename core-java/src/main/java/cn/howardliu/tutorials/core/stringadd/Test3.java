package cn.howardliu.tutorials.core.stringadd;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-27
 */
public class Test3 {
    public String toString() {
        String a = "" + System.currentTimeMillis();
        String b = "" + System.currentTimeMillis();
        return new StringBuilder().append(a).append(b).toString();
    }
}