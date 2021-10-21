package cn.howardliu.effectjava.rename;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import cn.howardliu.effectjava.rename.entity.TaskStatusRequest;
import cn.howardliu.effectjava.rename.entity.TaskStatusResponse;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public class TaskStatusCaller extends BaseCaller {

    public TaskStatusCaller(String cookie) {
        super(cookie, Maps.newHashMap());
    }

    public TaskStatusCaller(String cookie, Map<String, List<String>> customHeaders) {
        super(cookie, customHeaders);
    }

    public TaskStatusResponse queryTaskStatus(Long taskId, String params) {
        final TaskStatusRequest taskStatusRequest = new TaskStatusRequest();
        taskStatusRequest.setTaskid(taskId);
        return queryTaskStatus(taskStatusRequest, params);
    }

    private TaskStatusResponse queryTaskStatus(TaskStatusRequest taskStatusRequest, String params) {
        TaskStatusResponse response;
        final String queryParam = HttpUtil.toParams(taskStatusRequest.paramMap());
        do {
            final String body = HttpRequest.post("https://pan.baidu.com/share/taskquery?" + queryParam)
                    .header(this.headers)
                    .cookie(this.cookie)
                    .body(params)
                    .execute()
                    .body();

            response = JSONUtil.toBean(body, TaskStatusResponse.class);
        } while (response.getErrno() != 0 || StringUtils.equalsAny(response.getStatus(), "running", "pending"));
        return response;
    }
}
