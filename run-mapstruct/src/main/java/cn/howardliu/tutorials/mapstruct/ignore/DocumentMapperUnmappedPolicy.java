package cn.howardliu.tutorials.mapstruct.ignore;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapperUnmappedPolicy {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentDTO documentToDocumentDTO(Document entity);

    Document documentDTOToDocument(DocumentDTO dto);
}
