package cn.howardliu.tutorials.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-21
 */
class UserBodyValuesMapperTest {

    @Test
    void testNamed() {
        final UserBodyImperialValuesDTO dto = new UserBodyImperialValuesDTO();
        dto.setMeter(1.1);

        final UserBodyValues obj = UserBodyValuesMapper.INSTANCE.userBodyValuesMapper(dto);

        assertNotNull(obj);
        assertEquals(110, obj.getCentimeter(), 0);
    }

    @Test
    void testPound() {
        final UserBodyImperialValuesDTO dto = new UserBodyImperialValuesDTO();
        dto.setGram(1000);

        final UserBodyValues obj = UserBodyValuesMapper.INSTANCE.userBodyValuesMapper(dto);

        assertNotNull(obj);
        assertEquals(1, obj.getKilogram(), 0);
    }
}
