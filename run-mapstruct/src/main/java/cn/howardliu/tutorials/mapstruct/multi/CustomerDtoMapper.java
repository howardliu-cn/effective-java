package cn.howardliu.tutorials.mapstruct.multi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-22
 */
@Mapper
public interface CustomerDtoMapper {
    @Mapping(source = "firstName", target = "forename")
    @Mapping(source = "lastName", target = "surname")
    CustomerDto from(Customer customer);
}
