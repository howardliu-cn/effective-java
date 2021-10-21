package cn.howardliu.effectjava.rename.entity;

import lombok.Data;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@Data
public abstract class BaseRequest {
    protected String channel = "chunlei";
    protected String web = "1";
    protected String appId = "250528";
    protected String bdstoken = "";
    protected String logid = "";
    protected String clienttype = "0";
}
