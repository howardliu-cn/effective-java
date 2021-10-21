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
public class FileRenameRequest extends BaseRequest {
    private String opera = "rename";
    private String async = "2";
    private String onnest = "fail";

    public Map<String, Object> paramMap() {
        final Map<String, Object> params = Maps.newHashMap();
        params.put("opera", opera);
        params.put("async", async);
        params.put("onnest", onnest);
        params.put("channel", channel);
        params.put("app_id", appId);
        params.put("bdstoken", bdstoken);
        params.put("logid", logid);
        params.put("clienttype", clienttype);
        return params;
    }
}
