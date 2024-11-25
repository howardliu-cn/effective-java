package cn.howardliu.tutorials.mapstruct.ignore;

import java.util.List;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
@Data
public class DocumentDTO {
    private int id;
    private String title;
    private String text;
    private List<String> comments;
    private String author;
}
