package cn.howardliu.tutorials.mapstruct.ignore;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carToCarDTO(Car car);
}
