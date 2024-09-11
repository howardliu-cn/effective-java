package cn.howardliu.tutorials.java19;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-07
 */
public class RecordMain {
    public static void main(String[] args) {
        var p = new Point(1, 2);
        var cp1 = new ColoredPoint(p, Color.RED);
        var cp2 = new ColoredPoint(p, Color.GREEN);
        var square = new Square(cp1, cp2);

        instancePatternsAndPrint(square);
        instancePatternsAndPrint(cp1);
        instancePatternsAndPrint(p);

        switchPatternsAndPrint(square);
        switchPatternsAndPrint(cp1);
        switchPatternsAndPrint(p);
    }

    private static void instancePatternsAndPrint(Object o) {
        if (o instanceof Square(ColoredPoint upperLeft, ColoredPoint lowerRight)) {
            System.out.println("Square类型：" + upperLeft + " " + lowerRight);
        } else if (o instanceof ColoredPoint(Point(int x, int y), Color color)) {
            System.out.println("ColoredPoint类型：" + x + " " + y + " " + color);
        } else if (o instanceof Point p) {
            System.out.println("Point类型：" + p);
        }
    }

    private static void switchPatternsAndPrint(Object o) {
        switch (o) {
            case Square(ColoredPoint upperLeft, ColoredPoint lowerRight) -> {
                System.out.println("Square类型：" + upperLeft + " " + lowerRight);
            }
            case ColoredPoint(Point(int x, int y), Color color) -> {
                System.out.println("ColoredPoint类型：" + x + " " + y + " " + color);
            }
            case Point p -> {
                System.out.println("Point类型：" + p);
            }
            default -> throw new IllegalStateException("Unexpected value: " + o);
        }
    }

    enum Color {
        RED,
        GREEN,
        BLUE
    }

    record Point(int x, int y) {
    }

    record ColoredPoint(Point p, Color color) {
    }

    record Square(ColoredPoint upperLeft, ColoredPoint lowerRight) {
    }
}
