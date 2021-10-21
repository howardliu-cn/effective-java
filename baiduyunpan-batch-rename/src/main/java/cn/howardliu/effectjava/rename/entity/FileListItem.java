package cn.howardliu.effectjava.rename.entity;

import lombok.Data;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@Data
public class FileListItem {
    private Integer tkbind_id;
    private Integer server_mtime;
    private Integer category;
    private String real_category;
    private Integer isdir;
    private Integer dir_empty;
    private Long oper_id;
    private Integer server_ctime;
    private Integer extent_tinyint7;
    private Integer wpfile;
    private Integer local_mtime;
    private Integer size;
    private Integer pl;
    private Integer share;
    private Long fs_id;
    private String path;
    private Integer local_ctime;
    private Integer owner_type;
    private Integer empty;
    private Integer server_atime;
    private String server_filename;
    private Integer unlist;
    private Integer owner_id;
    private String md5;
}
