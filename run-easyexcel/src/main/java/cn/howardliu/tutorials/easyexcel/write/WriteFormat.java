package cn.howardliu.tutorials.easyexcel.write;

import com.alibaba.excel.EasyExcelFactory;

import cn.howardliu.tutorials.easyexcel.entity.write.FormatCellItem;
import cn.howardliu.tutorials.easyexcel.entity.write.FormatContentItem;
import cn.howardliu.tutorials.easyexcel.entity.write.FormatStyleCellItem;

/**
 * 数据格式化：
 * <ul>
 *     <li>日期格式化</li>
 *     <li>数字格式化</li>
 *     <li>自定义格式化</li>
 * </ul>
 *
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteFormat extends BaseWrite {
    public static void main(String[] args) {
        writeFormatContent();
        writeFormatCell();
        writeFormatStyleCell();
    }

    /**
     * 日期、数字或者自定义格式转换
     * <p>1. 创建excel对应的实体对象 参照{@link FormatContentItem}
     * <p>2. 使用{@link com.alibaba.excel.annotation.ExcelProperty}配合使用注解
     * {@link com.alibaba.excel.annotation.format.DateTimeFormat}、
     * {@link com.alibaba.excel.annotation.format.NumberFormat}或者自定义注解（实现
     * {@link com.alibaba.excel.converters.Converter}接口）
     * <p>3. 直接写即可
     */
    public static void writeFormatContent() {
        final String fileName = defaultFileName("writeFormatContent");
        EasyExcelFactory.write(fileName)
                .head(FormatContentItem.class)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

    /**
     * 列宽、行高
     * <p>1. 创建excel对应的实体对象 参照{@link FormatCellItem}
     * <p>2. 使用注解{@link com.alibaba.excel.annotation.write.style.ColumnWidth}、
     * {@link com.alibaba.excel.annotation.write.style.HeadRowHeight}、
     * {@link com.alibaba.excel.annotation.write.style.ContentRowHeight}指定宽度或高度
     * <p>3. 直接写即可
     */
    public static void writeFormatCell() {
        final String fileName = defaultFileName("writeFormatCell");
        EasyExcelFactory.write(fileName)
                .head(FormatCellItem.class)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

    /**
     * 注解形式自定义样式
     */
    public static void writeFormatStyleCell() {
        final String fileName = defaultFileName("writeFormatStyleCell");
        EasyExcelFactory.write(fileName)
                .head(FormatStyleCellItem.class)
                .sheet()
                .doWrite(WriteSample::sampleItems);
    }
}
