package cn.howardliu.tutorials.mapstruct.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mapstruct.Mapper;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
@Mapper
public interface EmployeeMapper {
    EmployeeDTO map(Employee employee);

    List<EmployeeDTO> map(List<Employee> employees);

    Set<EmployeeDTO> map(Set<Employee> employees);

    Map<String, EmployeeDTO> map(Map<String, Employee> idEmployeeMap);

    List<EmployeeFullNameDTO> mapFullName(List<Employee> employees);
}
