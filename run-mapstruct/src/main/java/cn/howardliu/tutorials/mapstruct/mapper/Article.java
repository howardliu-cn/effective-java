package cn.howardliu.tutorials.mapstruct.mapper;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-26
 */
@Getter
@Setter
public class Article {
    private int id;
    private String name;
    private Person author;
}
