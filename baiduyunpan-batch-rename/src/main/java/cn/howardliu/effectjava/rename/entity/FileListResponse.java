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
public class FileListResponse {
    private Integer errno;
    private String guid_info;
    private List<FileListItem> list;
    private Long request_id;
    private Integer guid;
}
