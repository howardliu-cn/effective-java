package cn.howardliu.tutorials.mapstruct.extend;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Bus extends Vehicle {
    private Integer capacity;

    @Override
    public VehicleDTO accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
