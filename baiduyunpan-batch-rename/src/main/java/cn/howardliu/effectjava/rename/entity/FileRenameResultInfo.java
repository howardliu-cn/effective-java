package cn.howardliu.effectjava.rename.entity;

import lombok.Data;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@Data
public class FileRenameResultInfo {
    private FileName fileName;
    private String failReason;
}
