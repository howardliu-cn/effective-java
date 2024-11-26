package cn.howardliu.tutorials.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-26
 */
@Mapper(uses = PersonMapper.class)
public interface ArticleUsingPersonMapper {

    ArticleUsingPersonMapper INSTANCE = Mappers.getMapper(ArticleUsingPersonMapper.class);

    ArticleDTO articleToArticleDto(Article article);
}
