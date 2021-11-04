package cn.howardliu.tutorials.collection.sorted;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/4 22:35
 */
class PowerfulComparisonTest {

    @Test
    void baseSortedOrigin() {
        final List<Student> students = students();
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student h1, Student h2) {
                return h1.getName().compareTo(h2.getName());
            }
        });
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void baseSortedLambda() {
        final List<Student> students = students();
        Collections.sort(students, (Student h1, Student h2) -> h1.getName().compareTo(h2.getName()));
        students.sort((Student h1, Student h2) -> h1.getName().compareTo(h2.getName()));
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void baseSortedLambdaWithInferring() {
        final List<Student> students = students();
        Collections.sort(students, (h1, h2) -> h1.getName().compareTo(h2.getName()));
        students.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void sortedUsingStaticMethod() {
        final List<Student> students = students();
        students.sort(Student::compareByNameThenAge);
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void sortedUsingComparator() {
        final List<Student> students = students();
        Collections.sort(students, Comparator.comparing(Student::getName));
        students.sort(Comparator.comparing(Student::getName));
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void sortedReverseUsingComparator() {
        final List<Student> students = students();
        final Comparator<Student> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
        students.sort(comparator.reversed());
        Assertions.assertEquals(students.get(0), new Student("Tom", 10));
    }

    @Test
    void sortedReverseUsingComparator2() {
        final List<Student> students = students();
        final Comparator<Student> comparator = (h1, h2) -> h2.getName().compareTo(h1.getName());
        students.sort(comparator);
        Assertions.assertEquals(students.get(0), new Student("Tom", 10));
    }

    @Test
    void sortedMultiCondition() {
        final List<Student> students = students();
        students.sort((s1, s2) -> {
            if (s1.getName().equals(s2.getName())) {
                return Integer.compare(s1.getAge(), s2.getAge());
            } else {
                return s1.getName().compareTo(s2.getName());
            }
        });
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void sortedMultiConditionUsingComparator() {
        final List<Student> students = students();
        students.sort(Comparator.comparing(Student::getName).thenComparing(Student::getAge));
        Assertions.assertEquals(students.get(0), new Student("Jerry", 12));
    }

    @Test
    void streamSorted() {
        final List<Student> students = students();
        final Comparator<Student> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
        final List<Student> sortedStudents = students.stream().sorted(comparator).collect(Collectors.toList());
        Assertions.assertEquals(sortedStudents.get(0), new Student("Jerry", 12));
    }

    @Test
    void streamSortedUsingComparator() {
        final List<Student> students = students();
        final Comparator<Student> comparator = Comparator.comparing(Student::getName);
        final List<Student> sortedStudents = students.stream().sorted(comparator).collect(Collectors.toList());
        Assertions.assertEquals(sortedStudents.get(0), new Student("Jerry", 12));
    }

    @Test
    void streamReverseSorted() {
        final List<Student> students = students();
        final Comparator<Student> comparator = (h1, h2) -> h2.getName().compareTo(h1.getName());
        final List<Student> sortedStudents = students.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        Assertions.assertEquals(sortedStudents.get(0), new Student("Tom", 10));
    }

    @Test
    void streamReverseSortedUsingComparator() {
        final List<Student> students = students();
        final List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparing(Student::getName, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        Assertions.assertEquals(sortedStudents.get(0), new Student("Tom", 10));
    }

    @Test
    void sortedNullGotNPE() {
        final List<Student> students = studentsWithNull();
        Assertions.assertThrows(NullPointerException.class,
                () -> students.sort(Comparator.comparing(Student::getName)));
    }

    @Test
    void sortedNullNoNPE() {
        final List<Student> students = studentsWithNull();
        students.sort((s1, s2) -> {
            if (s1 == null) {
                return s2 == null ? 0 : 1;
            }
            else if (s2 == null) {
                return -1;
            }
            return s1.getName().compareTo(s2.getName());
        });

        Assertions.assertNotNull(students.get(0));
        Assertions.assertNull(students.get(1));
        Assertions.assertNull(students.get(2));
    }

    @Test
    void sortedNullLast() {
        final List<Student> students = studentsWithNull();
        students.sort(Comparator.nullsLast(Comparator.comparing(Student::getName)));
        Assertions.assertNotNull(students.get(0));
        Assertions.assertNull(students.get(1));
        Assertions.assertNull(students.get(2));
    }

    @Test
    void sortedNullFirst() {
        final List<Student> students = studentsWithNull();
        students.sort(Comparator.nullsFirst(Comparator.comparing(Student::getName)));
        Assertions.assertNull(students.get(0));
        Assertions.assertNull(students.get(1));
        Assertions.assertNotNull(students.get(2));
    }

    private List<Student> students() {
        return Lists.newArrayList(
                new Student("Tom", 10),
                new Student("Jerry", 12),
                new Student("Jerry", 13)
        );
    }

    private List<Student> studentsWithNull() {
        return Lists.newArrayList(
                null,
                new Student("Snoopy", 12),
                null
        );
    }
}
