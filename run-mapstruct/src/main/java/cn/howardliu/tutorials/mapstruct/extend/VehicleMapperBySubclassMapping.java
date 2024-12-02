package cn.howardliu.tutorials.mapstruct.extend;

import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
@Mapper(uses = {BusMapper.class, CarMapper.class})
public interface VehicleMapperBySubclassMapping {
    @SubclassMapping(source = Car.class, target = CarDTO.class)
    @SubclassMapping(source = Bus.class, target = BusDTO.class)
    VehicleDTO mapToVehicleDTO(Vehicle vehicle);
}
