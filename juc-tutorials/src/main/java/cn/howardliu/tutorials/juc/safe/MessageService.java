package cn.howardliu.tutorials.juc.safe;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2025-01-01
 */
public class MessageService {

    private final String message;

    public MessageService(String message) {
        this.message = message;
    }

    public String getAndPrint() {
        System.out.println(message);
        return message;
    }

    public String getMessage() {
        return message;
    }
}
