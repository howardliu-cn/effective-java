package cn.howardliu.tutorials.easyexcel.entity.write;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
@Data
public class IndexItem {
    @ExcelProperty(value = "字符串标题", index = 1)
    private String string;
    @ExcelProperty(value = "日期标题", index = 3)
    private Date date;
    @ExcelProperty(value = "数字标题", index = 5)
    private Double doubleData;
}
