package cn.howardliu.tutorials.java9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/12/17 07:49
 */
public class ModificationsMain {
    public static void main(String[] args) {

    }

    public static void tryWithResources() throws IOException {
        try (FileInputStream in2 = new FileInputStream("./")) {
            // do something
        }

        final Reader inputString = new StringReader("www.howardliu.cn 看山");
        final BufferedReader br = new BufferedReader(inputString);
        // 其他一些逻辑
        try (br) {
            System.out.println(br.lines());
        }
    }

    public static void tryWithResources8() throws IOException {
        try (FileInputStream in2 = new FileInputStream("./")) {
            // do something
        }

        final Reader inputString = new StringReader("www.howardliu.cn 看山");
        final BufferedReader br = new BufferedReader(inputString);
        // 其他一些逻辑
        try (BufferedReader br1 = br) {
            System.out.println(br1.lines());
        }
    }
}
