package cn.howardliu.tutorials.mapstruct.collection;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

import org.mapstruct.Mapper;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-26
 */
@Mapper(uses = EmployeeMapper.class, collectionMappingStrategy = ADDER_PREFERRED)
public interface CompanyMapperAdderPreferred {
    CompanyDTO map(Company company);
}
