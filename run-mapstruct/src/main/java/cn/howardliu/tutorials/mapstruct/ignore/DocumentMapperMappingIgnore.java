package cn.howardliu.tutorials.mapstruct.ignore;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
@Mapper
public interface DocumentMapperMappingIgnore {
    DocumentMapperMappingIgnore INSTANCE = Mappers.getMapper(DocumentMapperMappingIgnore.class);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    DocumentDTO documentToDocumentDTO(Document entity);

    @Mapping(target = "modificationTime", ignore = true)
    Document documentDTOToDocument(DocumentDTO dto);
}
