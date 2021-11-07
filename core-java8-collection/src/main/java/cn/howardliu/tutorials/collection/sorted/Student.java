package cn.howardliu.tutorials.collection.sorted;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/4 22:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private int age;

    public static int compareByNameThenAge(Student s1, Student s2) {
        if (s1.name.equals(s2.name)) {
            return Integer.compare(s1.age, s2.age);
        } else {
            return s1.name.compareTo(s2.name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
