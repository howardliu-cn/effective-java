package cn.howardliu.tutorials.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Data;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/11/24 23:20
 */
public class OptionalMain {
    public static void main(String[] args) {
        List<String> listOpt = Optional.ofNullable(getList())
                .orElse(new ArrayList<>());

        Optional<User> user = Optional.ofNullable(getUser());
        String result = user
                .map(User::getAddress)
                .map(Address::getStreet)
                .orElse("not specified");
    }

    private static List<String> getList() {
        return null;
    }

    private static User getUser() {
        return null;
    }

    @Data
    public static class User {
        private Address address;
    }

    @Data
    public static class Address {
        private String street;
    }
}
