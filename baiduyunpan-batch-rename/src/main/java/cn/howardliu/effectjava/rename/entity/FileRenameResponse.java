package cn.howardliu.effectjava.rename.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@NoArgsConstructor
@Data
public class FileRenameResponse {
    private Integer errno;
    private List<String> info;
    private Long request_id;
    private Long taskid;
}
