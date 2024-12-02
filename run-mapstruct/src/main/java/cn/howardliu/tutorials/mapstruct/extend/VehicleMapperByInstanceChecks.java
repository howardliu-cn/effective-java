package cn.howardliu.tutorials.mapstruct.extend;

import org.mapstruct.Mapper;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
@Mapper
public interface VehicleMapperByInstanceChecks {
    CarDTO map(Car car);

    BusDTO map(Bus bus);

    default VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        if (vehicle instanceof Bus) {
            return map((Bus) vehicle);
        } else if (vehicle instanceof Car) {
            return map((Car) vehicle);
        } else {
            return null;
        }
    }
}
