package cn.howardliu.tutorials.java12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/12 23:23
 */
class FilesTest {
    @Test
    void testMismatch() throws IOException {
        final Path pathA = Files.createFile(Paths.get("a.txt"));
        final Path pathB = Files.createFile(Paths.get("b.txt"));

        // 写入相同内容
        Files.write(pathA, "看山".getBytes(), StandardOpenOption.WRITE);
        Files.write(pathB, "看山".getBytes(), StandardOpenOption.WRITE);

        final long mismatch1 = Files.mismatch(pathA, pathB);
        Assertions.assertEquals(-1L, mismatch1);

        // 追加不同内容
        Files.write(pathA, "是山".getBytes(), StandardOpenOption.APPEND);
        Files.write(pathB, "不是山".getBytes(), StandardOpenOption.APPEND);

        final long mismatch2 = Files.mismatch(pathA, pathB);
        Assertions.assertEquals(6L, mismatch2);

        Files.deleteIfExists(pathA);
        Files.deleteIfExists(pathB);
    }
}
