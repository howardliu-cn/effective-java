package cn.howardliu.tutorials.easyexcel.entity.write;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
@Data
public class ComplexHeadItem {
    @ExcelProperty({"大标题", "字符串标题"})
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty({"大标题", "数字标题0"})
    private Double doubleData;
    @ExcelProperty({"数字标题"})
    private Double doubleData1;
    @ExcelProperty({"数字标题"})
    private Double doubleData2 = 0.0;
}
