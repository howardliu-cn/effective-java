package cn.howardliu.tutorials.mapstruct.collection;

import java.util.List;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-26
 */
@Data
public class Company {
    private List<Employee> employees;
}
