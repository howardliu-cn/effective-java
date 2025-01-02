package cn.howardliu.tutorials.juc.safe;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-01
 */
public class ThreadState {

    public static final ThreadLocal<StateHolder> statePerThread =
            ThreadLocal.withInitial(() -> new StateHolder("active"));

    public static StateHolder getState() {
        return statePerThread.get();
    }
}
