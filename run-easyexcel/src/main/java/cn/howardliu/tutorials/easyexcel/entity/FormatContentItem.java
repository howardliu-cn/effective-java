package cn.howardliu.tutorials.easyexcel.entity;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;

import cn.howardliu.tutorials.easyexcel.write.converter.TitleFormatConverter;
import lombok.Data;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
@Data
public class FormatContentItem {
    @ExcelProperty(value = "字符串标题", converter = TitleFormatConverter.class)
    private String string;
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "日期标题")
    private Date date;
    @NumberFormat("0.000%")
    @ExcelProperty("数字标题")
    private Double doubleData;
}
