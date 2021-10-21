package cn.howardliu.effectjava.rename.entity;

import java.util.List;

import lombok.Data;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@Data
public class FileInfo {
    private FileName fileName;
    private boolean isDir;
    private List<FileInfo> children;
}
