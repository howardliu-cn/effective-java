package cn.howardliu.tutorials.mapstruct.extend;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
@Data
public abstract class Vehicle {
    private String color;
    private String speed;

    public abstract VehicleDTO accept(Visitor visitor);
}
