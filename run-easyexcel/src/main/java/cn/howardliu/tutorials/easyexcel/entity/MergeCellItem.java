package cn.howardliu.tutorials.easyexcel.entity;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentLoopMerge;
import com.alibaba.excel.annotation.write.style.OnceAbsoluteMerge;

import lombok.Data;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
// 将第6-7行的2-3列合并成一个单元格
@OnceAbsoluteMerge(firstRowIndex = 5, lastRowIndex = 6, firstColumnIndex = 1, lastColumnIndex = 2)
@Data
public class MergeCellItem {
    @ContentLoopMerge(eachRow = 2)
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
}
