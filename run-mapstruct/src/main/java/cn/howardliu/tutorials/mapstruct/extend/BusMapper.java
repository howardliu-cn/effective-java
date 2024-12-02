package cn.howardliu.tutorials.mapstruct.extend;

import org.mapstruct.Mapper;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
@Mapper
public interface BusMapper {
    BusDTO busToDTO(Bus bus);
}
