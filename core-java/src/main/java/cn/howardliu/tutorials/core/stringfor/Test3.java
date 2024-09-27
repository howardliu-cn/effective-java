package cn.howardliu.tutorials.core.stringfor;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-27
 */
public class Test3 {
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            s.append(System.currentTimeMillis());
        }
        return s.toString();
    }
}
