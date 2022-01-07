package cn.howardliu.tutorials.java11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/7 08:21
 */
class FilesTest {
    @Test
    void testReadWriteString() throws IOException {
        final Path tmpPath = Path.of("./");
        final Path tempFile = Files.createTempFile(tmpPath, "demo", ".txt");
        final Path filePath = Files.writeString(tempFile, "看山 howardliu.cn\n公众号：看山的小屋", StandardCharsets.UTF_16);
        assertEquals(tempFile, filePath);

        final String fileContent = Files.readString(filePath);
        assertEquals("看山 howardliu.cn\n公众号：看山的小屋", fileContent);

        Files.deleteIfExists(filePath);
    }
}
