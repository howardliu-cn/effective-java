package cn.howardliu.tutorials.java12;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/12 23:46
 */
class CollectorsTest {
    @Test
    void testTeeing() {
        var result = Stream.of("Sunday", "Monday", "Tuesday", "Wednesday")
                .collect(Collectors.teeing(
                        Collectors.filtering(n -> n.contains("u"), Collectors.toList()),
                        Collectors.filtering(n -> n.contains("n"), Collectors.toList()),
                        (list1, list2) -> List.of(list1, list2)
                ));

        assertEquals(2, result.size());
        assertTrue(isEqualCollection(List.of("Sunday", "Tuesday"), result.get(0)));
        assertTrue(isEqualCollection(List.of("Sunday", "Monday", "Wednesday"), result.get(1)));
    }
}
