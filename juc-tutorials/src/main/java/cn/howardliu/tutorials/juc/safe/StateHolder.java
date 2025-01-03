package cn.howardliu.tutorials.juc.safe;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-01
 */
public class StateHolder {
    private String state;

    public StateHolder(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}