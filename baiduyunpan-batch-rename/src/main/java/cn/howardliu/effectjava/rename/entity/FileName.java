package cn.howardliu.effectjava.rename.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
@Data
public class FileName {
    @ExcelProperty("路径")
    private String path;
    @ExcelProperty("MD5")
    private String md5;
    @ExcelProperty("原名称")
    private String originName;
    @ExcelProperty("新名称")
    private String newName;
}
