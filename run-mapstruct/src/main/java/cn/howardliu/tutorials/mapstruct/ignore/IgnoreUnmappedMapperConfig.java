package cn.howardliu.tutorials.mapstruct.ignore;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-25
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfig {
}
