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
public class FileListRequest extends BaseRequest {
    private String order = "name";
    private String desc = "0";
    private String showempty = "0";
    private int page = 1;
    private int num = 100;
    private String dir = "/";
    private String t = "";

    public Map<String, Object> paramMap() {
        final Map<String, Object> params = Maps.newHashMap();
        params.put("order", order);
        params.put("desc", desc);
        params.put("showempty", showempty);
        params.put("web", web);
        params.put("page", page);
        params.put("num", num);
        params.put("dir", dir);
        params.put("t", t);
        params.put("channel", channel);
        params.put("app_id", appId);
        params.put("bdstoken", bdstoken);
        params.put("logid", logid);
        params.put("clienttype", clienttype);
        return params;
    }
}
