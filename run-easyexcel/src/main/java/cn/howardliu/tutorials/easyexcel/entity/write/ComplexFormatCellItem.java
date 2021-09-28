package cn.howardliu.tutorials.easyexcel.entity.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.data.WriteCellData;

import lombok.Data;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
@Data
public class ComplexFormatCellItem {
    @ExcelProperty("超链接")
    private WriteCellData<String> hyperlink;
    @ExcelProperty("备注")
    private WriteCellData<String> commentData;
    @ExcelProperty("公式")
    private WriteCellData<String> formulaData;
    /**
     * 指定单元格的样式。当然样式 也可以用注解等方式。
     */
    @ExcelProperty("指定单元格的样式")
    private WriteCellData<String> writeCellStyle;
    @ExcelProperty("指定一个单元格有多个样式")
    private WriteCellData<String> richText;
}
