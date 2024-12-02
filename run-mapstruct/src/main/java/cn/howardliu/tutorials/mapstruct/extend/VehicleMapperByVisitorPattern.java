package cn.howardliu.tutorials.mapstruct.extend;

import org.mapstruct.Mapper;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
@Mapper
public abstract class VehicleMapperByVisitorPattern implements Visitor {
    public VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        return vehicle.accept(this);
    }

    @Override
    public VehicleDTO visit(Car car) {
        return map(car);
    }

    @Override
    public VehicleDTO visit(Bus bus) {
        return map(bus);
    }

    abstract CarDTO map(Car car);

    abstract BusDTO map(Bus bus);
}
