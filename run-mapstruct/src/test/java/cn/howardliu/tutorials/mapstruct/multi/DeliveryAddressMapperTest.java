package cn.howardliu.tutorials.mapstruct.multi;

import static cn.howardliu.tutorials.mapstruct.multi.DeliveryAddressMapper.INSTANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-22
 */
class DeliveryAddressMapperTest {

    @Test
    void from() {
        // 给定一个客户
        final Customer customer = new Customer();
        customer.setFirstName("Howard");
        customer.setLastName("Liu");

        // 和一个地址
        final Address homeAddress = new Address();
        homeAddress.setStreet("howardliu.cn");
        homeAddress.setCounty("China");
        homeAddress.setPostalCode("261426");

        // 当调用DeliveryAddressMapper::from时
        final DeliveryAddress deliveryAddress = INSTANCE.from(customer, homeAddress);

        // 那么将根据给定的客户及其家庭地址创建一个新的DeliveryAddress
        assertEquals(deliveryAddress.getForename(), customer.getFirstName());
        assertEquals(deliveryAddress.getSurname(), customer.getLastName());
        assertEquals(deliveryAddress.getStreet(), homeAddress.getStreet());
        assertEquals(deliveryAddress.getCounty(), homeAddress.getCounty());
        assertEquals(deliveryAddress.getPostalCode(), homeAddress.getPostalCode());
    }

    @Test
    void updateAddress() {
        // 给定一个送货地址
        final DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setForename("Howard");
        deliveryAddress.setSurname("Liu");
        deliveryAddress.setStreet("howardliu.cn");
        deliveryAddress.setCounty("China");
        deliveryAddress.setPostalCode("261426");

        // 和一个新地址
        final Address newAddress = new Address();
        newAddress.setStreet("https://www.howardliu.cn");
        newAddress.setCounty("China");
        newAddress.setPostalCode("261400");

        // 当调用DeliveryAddressMapper::updateAddress时
        final DeliveryAddress updatedDeliveryAddress = INSTANCE.updateAddress(deliveryAddress, newAddress);

        // 那么现有送货地址将被更新
        assertSame(deliveryAddress, updatedDeliveryAddress);
        assertEquals(deliveryAddress.getStreet(), newAddress.getStreet());
        assertEquals(deliveryAddress.getCounty(), newAddress.getCounty());
        assertEquals(deliveryAddress.getPostalCode(), newAddress.getPostalCode());
    }
}
