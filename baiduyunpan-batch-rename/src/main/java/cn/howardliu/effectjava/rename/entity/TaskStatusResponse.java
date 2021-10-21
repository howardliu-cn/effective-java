package cn.howardliu.effectjava.rename.entity;

import java.util.List;

import lombok.Data;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@Data
public class TaskStatusResponse {
    private Integer errno;
    private Long request_id;
    private Integer task_errno;
    private String status;
    private List<TaskDetail> list;
    private Integer total;

    @Data
    public static class TaskDetail {
        private String from;
        private String to;
    }
}
