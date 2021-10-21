package cn.howardliu.effectjava.rename.entity;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskStatusRequest extends BaseRequest {
    private Long taskid;

    public Map<String, Object> paramMap() {
        final Map<String, Object> params = Maps.newHashMap();
        params.put("channel", channel);
        params.put("app_id", appId);
        params.put("bdstoken", bdstoken);
        params.put("logid", logid);
        params.put("clienttype", clienttype);
        params.put("taskid", taskid);
        return params;
    }
}
