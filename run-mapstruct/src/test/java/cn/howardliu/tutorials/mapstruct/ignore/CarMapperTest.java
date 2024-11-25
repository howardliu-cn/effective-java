package cn.howardliu.tutorials.mapstruct.ignore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
class CarMapperTest {

    @Test
    void carToCarDTO() {
        final Car entity = new Car();
        entity.setId(1);
        entity.setName("看山");

        final CarDTO carDto = CarMapper.INSTANCE.carToCarDTO(entity);
        assertEquals(carDto.getId(), entity.getId());
        assertEquals(carDto.getName(), entity.getName());
    }
}
