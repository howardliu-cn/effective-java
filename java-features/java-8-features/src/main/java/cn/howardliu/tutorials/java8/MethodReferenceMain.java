package cn.howardliu.tutorials.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/24 21:53
 */
public class MethodReferenceMain {
    public static void main(String[] args) {
        final List<String> list = Lists.newArrayList("1", "2", "3", null, "4");
        final boolean hasNullElement = list.stream().anyMatch(x -> Objects.isNull(x));
        System.out.println(hasNullElement);

        final boolean hasNullElementAlso = list.stream().anyMatch(Objects::isNull);
        System.out.println(hasNullElementAlso);

        final DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        final List<LocalDate> dates = Lists.newArrayList(
                LocalDate.MIN,
                LocalDate.now(),
                LocalDate.MAX
        );
        final List<String> dateStrs = dates.stream()
                .map(d -> fmt.format(d))
                .collect(Collectors.toList());
        System.out.println(dateStrs);
        final List<String> dateStrList = dates.stream()
                .map(fmt::format)
                .collect(Collectors.toList());
        System.out.println(dateStrs);

        final List<String> nonNullList = Lists.newArrayList("1", "2", "3", "", "4", "");
        final long emptyCount = nonNullList.stream().filter(x -> x.isEmpty()).count();
        System.out.println(emptyCount);

        final long emptyElementCount = nonNullList.stream().filter(String::isEmpty).count();
        System.out.println(emptyElementCount);

        nonNullList.stream().map(String::new).collect(Collectors.toList());

        final List<String> allIntList = Lists.newArrayList("1", "2", "3", "4");
        final List<Integer> ints = allIntList.stream()
                .map(x -> new Integer(x))
                .collect(Collectors.toList());
        System.out.println(ints);
        final List<Integer> intList = allIntList.stream()
                .map(Integer::new)
                .collect(Collectors.toList());
        System.out.println(intList);
    }
}
