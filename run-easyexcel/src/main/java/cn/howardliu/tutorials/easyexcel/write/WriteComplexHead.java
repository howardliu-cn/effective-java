package cn.howardliu.tutorials.easyexcel.write;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;

import cn.howardliu.tutorials.easyexcel.entity.ComplexHeadItem;
import cn.howardliu.tutorials.easyexcel.entity.EmptyItem;
import cn.howardliu.tutorials.easyexcel.entity.IndexItem;

/**
 * <ol>
 *     <li>不使用{@link ExcelProperty}注解表头，将会直接使用字段名作为表头</li>
 *     <li>定义index指定字段列位置</li>
 *     <li>定义多表头和合并表头</li>
 * </ol>
 * <p>
 * 从这里可以看到，表头传入的类与表体传入的类可以不一致。
 *
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteComplexHead extends BaseWrite {
    public static void main(String[] args) {
        writeNoAnnotation();
        writeWithIndex();
        writeWithMultiHead();
    }

    /**
     * 没有使用{@link ExcelProperty}注解的表头。
     */
    public static void writeNoAnnotation() {
        final String fileName = defaultFileName("writeNoAnnotation");
        EasyExcelFactory.write(fileName)
                .head(EmptyItem.class)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

    /**
     * 使用{@link ExcelProperty}注解表头，定义index字段值指定列位置
     */
    public static void writeWithIndex() {
        final String fileName = defaultFileName("writeWithIndex");
        EasyExcelFactory.write(fileName)
                .head(IndexItem.class)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

    /**
     * 使用{@link ExcelProperty}注解定义多表头和合并表头
     */
    public static void writeWithMultiHead() {
        final String fileName = defaultFileName("writeWithMultiHead");
        EasyExcelFactory.write(fileName)
                .head(ComplexHeadItem.class)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

}
