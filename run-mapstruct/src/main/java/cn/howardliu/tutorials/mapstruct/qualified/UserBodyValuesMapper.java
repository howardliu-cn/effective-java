package cn.howardliu.tutorials.mapstruct.qualified;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-21
 */
@Mapper
public interface UserBodyValuesMapper {

    UserBodyValuesMapper INSTANCE = Mappers.getMapper(UserBodyValuesMapper.class);

    @Mapping(source = "meter", target = "centimeter", qualifiedByName = "meterToCentimeterName")
    @Mapping(source = "gram", target = "kilogram", qualifiedBy = GramToKilogram.class)
    UserBodyValues userBodyValuesMapper(UserBodyImperialValuesDTO dto);

    @Named("meterToCentimeterName")
    static double meterToCentimeter(double meter) {
        return meter * 100;
    }

    @GramToKilogram
    static double gramToKilogram(int gram) {
        return gram * 0.001;
    }
}
