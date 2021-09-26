package cn.howardliu.tutorials.easyexcel.write;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.excel.EasyExcelFactory;

import cn.howardliu.tutorials.easyexcel.entity.Item;

/**
 * 指定包含或忽略的字段
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteExcludeOrInclude extends BaseWrite {

    public static void main(String[] args) {
        writeExcludeColumn();
        writeIncludeColumn();
    }

    /**
     * 指定列表数据中排除的字段。
     * <p>
     * 在使用ExcelProperty注解时，index表示字段放置第几列，order表示顺序。
     * 所以，如果index相同，直接会抛出异常，因为程序无法判断这个列放那个字段。如果index值中间有空的数字，就会出现空列。
     * <p>
     * 如果order和index同时使用，index优先占据位置，order做排序。index=-1的话，使用java默认排序，order值越小，列越靠前。
     */
    private static void writeExcludeColumn() {
        String fileName = defaultFileName("writeExcludeColumn");
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("date");

        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

    /**
     * 指定列表数据中导出的字段。
     * <p>
     * 在使用ExcelProperty注解时，index表示字段放置第几列，order表示顺序。
     * 所以，如果index相同，直接会抛出异常，因为程序无法判断这个列放那个字段。如果index值中间有空的数字，就会出现空列。
     * <p>
     * 如果order和index同时使用，index优先占据位置，order做排序。index=-1的话，使用java默认排序，order值越小，列越靠前。
     */
    private static void writeIncludeColumn() {
        String fileName = defaultFileName("writeIncludeColumn");
        Set<String> includeColumnFiledNames = new HashSet<>();
        includeColumnFiledNames.add("date");
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .includeColumnFiledNames(includeColumnFiledNames)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

}
