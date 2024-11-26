package cn.howardliu.tutorials.mapstruct.collection;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-26
 */
@Mapper
public interface EmployeeFullNameMapper {
    List<EmployeeFullNameDTO> map(List<Employee> employees);

    @Mapping(target = "fullName", expression = "java(employee.getFirstName() + \" \" + employee.getLastName())")
    EmployeeFullNameDTO map(Employee employee);
}
