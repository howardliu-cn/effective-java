package cn.howardliu.tutorials.java9;

import java.math.BigDecimal;

/**
 * Created on 2021/12/18 10:21
 *
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 */
public class DiamondOperatorMain {
    public static void main(String[] args) {
        beforeJava9();
        java9();
    }

    private static void java9() {
        final Consumer<Integer> intConsumer = new Consumer<>(1) {
            @Override
            void accept() {
                System.out.println(getContent());
            }
        };
        intConsumer.accept();

        final Consumer<? extends Number> numConsumer = new Consumer<>(BigDecimal.TEN) {
            @Override
            void accept() {
                System.out.println(getContent());
            }
        };
        numConsumer.accept();

        final Consumer<?> objConsumer = new Consumer<>("看山") {
            @Override
            void accept() {
                System.out.println(getContent());
            }
        };
        objConsumer.accept();
    }

    private static void beforeJava9() {
        final Consumer<Integer> intConsumer = new Consumer<Integer>(1) {
            @Override
            void accept() {
                System.out.println(getContent());
            }
        };
        intConsumer.accept();

        final Consumer<? extends Number> numConsumer = new Consumer<Number>(BigDecimal.TEN) {
            @Override
            void accept() {
                System.out.println(getContent());
            }
        };
        numConsumer.accept();

        final Consumer<?> objConsumer = new Consumer<Object>("看山") {
            @Override
            void accept() {
                System.out.println(getContent());
            }
        };
        objConsumer.accept();
    }

    abstract static class Consumer<T> {
        private T content;

        public Consumer(T content) {
            this.content = content;
        }

        abstract void accept();

        public T getContent() {
            return content;
        }
    }
}
