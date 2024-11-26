package cn.howardliu.tutorials.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-26
 */
@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleDTO articleToArticleDto(Article article);

    default PersonDTO personToPersonDto(Person person) {
        return Mappers.getMapper(PersonMapper.class).personToPersonDTO(person);
    }
}
