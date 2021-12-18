package cn.howardliu.tutorials.java9;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/18 22:39
 */
public class OptionalMain {
    public static void main(String[] args) {
        doStream();

        doIfPresentOrElse();

        doOr();
    }

    private static void doStream() {
        final List<Optional<String>> list = Arrays.asList(
                Optional.empty(),
                Optional.of("看山"),
                Optional.empty(),
                Optional.of("看山的小屋"));

        //if optional is non-empty, get the value in stream, otherwise return empty
        final List<String> filteredList = list.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.toList());

        //Optional::stream method will return a stream of either one
        //or zero element if data is present or not.
        final List<String> filteredListJava9 = list.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        System.out.println(filteredList);
        System.out.println(filteredListJava9);
    }

    private static void doIfPresentOrElse() {
        Optional<String> optional = Optional.of("看山");
        optional.ifPresentOrElse(x -> System.out.println("作者: " + x), () -> System.out.println("佚名"));

        optional = Optional.empty();
        optional.ifPresentOrElse(x -> System.out.println("作者: " + x), () -> System.out.println("佚名"));
    }

    private static void doOr() {
        Optional<String> optional1 = Optional.of("看山");
        Supplier<Optional<String>> supplierString = () -> Optional.of("佚名");
        optional1 = optional1.or(supplierString);
        optional1.ifPresent(x -> System.out.println("作者: " + x));

        optional1 = Optional.empty();
        optional1 = optional1.or(supplierString);
        optional1.ifPresent(x -> System.out.println("作者: " + x));
    }
}
