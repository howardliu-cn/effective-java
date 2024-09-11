package cn.howardliu.tutorials.java21;

import static java.util.FormatProcessor.FMT;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-26
 */
public class StringTemplateExample {
    public static void main(String[] args) {
        // 拼装变量
        String name = "看山";
        String info = STR. "My name is \{ name }" ;
        assert info.equals("My name is 看山");

        // 拼装变量
        String firstName = "Howard";
        String lastName = "Liu";
        String fullName = STR. "\{ firstName } \{ lastName }" ;
        assert fullName.equals("Howard Liu");
        String sortName = STR. "\{ lastName }, \{ firstName }" ;
        assert sortName.equals("Liu, Howard");

        // 模板中调用方法
        String s2 = STR. "You have a \{ getOfferType() } waiting for you!" ;
        assert s2.equals("You have a gift waiting for you!");

        Request req = new Request("2017-07-19", "09:15", "https://www.howardliu.cn");
        // 模板中引用对象属性
        String s3 = STR. "Access at \{ req.date } \{ req.time } from \{ req.address }" ;
        assert s3.equals("Access at 2017-07-19 09:15 from https://www.howardliu.cn");

        LocalTime now = LocalTime.now();
        String markTime = DateTimeFormatter
                .ofPattern("HH:mm:ss")
                .format(now);
        // 模板中调用方法
        String time = STR. "The time is \{
                // The java.time.format package is very useful
                DateTimeFormatter
                        .ofPattern("HH:mm:ss")
                        .format(now)
                } right now" ;
        assert time.equals("The time is " + markTime + " right now");

        // 模板嵌套模板
        String[] fruit = {"apples", "oranges", "peaches"};
        String s4 = STR. "\{ fruit[0] }, \{
                STR. "\{ fruit[1] }, \{ fruit[2] }"
                }" ;
        assert s4.equals("apples, oranges, peaches");

        // 模板与文本块结合
        String title = "My Web Page";
        String text = "Hello, world";
        String html = STR. """
    <html>
      <head>
        <title>\{ title }</title>
      </head>
      <body>
        <p>\{ text }</p>
      </body>
    </html>
    """ ;
        assert html.equals("""
                <html>
                  <head>
                    <title>My Web Page</title>
                  </head>
                  <body>
                    <p>Hello, world</p>
                  </body>
                </html>
                """);

        // 带格式化的字符串模板
        record Rectangle(String name, double width, double height) {
            double area() {
                return width * height;
            }
        }
        Rectangle[] zone = new Rectangle[] {
                new Rectangle("Alfa", 17.8, 31.4),
                new Rectangle("Bravo", 9.6, 12.4),
                new Rectangle("Charlie", 7.1, 11.23),
        };
        String table = FMT. """
        Description     Width    Height     Area
        %-12s\{ zone[0].name }  %7.2f\{ zone[0].width }  %7.2f\{ zone[0].height }     %7.2f\{ zone[0].area() }
        %-12s\{ zone[1].name }  %7.2f\{ zone[1].width }  %7.2f\{ zone[1].height }     %7.2f\{ zone[1].area() }
        %-12s\{ zone[2].name }  %7.2f\{ zone[2].width }  %7.2f\{ zone[2].height }     %7.2f\{ zone[2].area() }
        \{ " ".repeat(28) } Total %7.2f\{ zone[0].area() + zone[1].area() + zone[2].area() }
        """ ;
        assert table.equals("""
                Description     Width    Height     Area
                Alfa            17.80    31.40      558.92
                Bravo            9.60    12.40      119.04
                Charlie          7.10    11.23       79.73
                                             Total  757.69
                """);
    }

    public static String getOfferType() {
        return "gift";
    }

    record Request(String date, String time, String address) {
    }
}
