package cn.howardliu.tutorials.mapstruct.extend;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-12-02
 */
public interface Visitor {
    VehicleDTO visit(Car car);

    VehicleDTO visit(Bus bus);
}
