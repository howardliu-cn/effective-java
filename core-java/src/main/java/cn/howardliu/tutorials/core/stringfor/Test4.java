package cn.howardliu.tutorials.core.stringfor;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-27
 */
public class Test4 {
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            s.append(System.currentTimeMillis());
        }
        return s.toString();
    }
}
