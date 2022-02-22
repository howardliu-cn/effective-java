package cn.howardliu.tutorials.java14;

import java.util.Objects;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/2/18 08:01
 */
public final class PersonBefore14 {
    private final String name;
    private final String address;

    public PersonBefore14(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String name() {
        return name;
    }

    public String address() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonBefore14 that = (PersonBefore14) o;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "PersonBefore14{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
