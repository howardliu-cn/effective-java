package cn.howardliu.tutorials.mapstruct.multi;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-22
 */
@Data
public class DeliveryAddress {
    private String forename;
    private String surname;
    private String street;
    private String postalCode;
    private String county;
}
