package cn.howardliu.tutorials.easyexcel.write;

import java.io.File;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import cn.howardliu.tutorials.easyexcel.entity.Item;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteByTemplate extends BaseWrite {
    public static void main(String[] args) {
        writeByTemplate();
    }

    /**
     * 使用withTemplate方法输入模板文件，这个方法有三个实现方式：
     * <ul>
     *     <li>指定模板文件路径：{@link ExcelWriterBuilder#withTemplate(java.lang.String)}</li>
     *     <li>指定模板文件对象：{@link ExcelWriterBuilder#withTemplate(java.io.File)}</li>
     *     <li>指定模板文件输入流：{@link ExcelWriterBuilder#withTemplate(java.io.InputStream)}</li>
     * </ul>
     * 指定模板文件和模板文件对象都是操作文件的，需要有文件信息。
     * <p>
     * 指定模板文件输入流是只要文件流，这个可操作性空间就比较大了。
     * <p>
     * 比如，模板文件是可变的，我们可以基于一个带变量的模板文件，使用填充写入的方式初始化模板文件，然后再用模板写入的方式，写入列表。
     */
    private static void writeByTemplate() {
        String fileName = defaultFileName("writeByTemplate");
        String templateFile = getPath() + File.separator + "write_by_template.xlsx";
        EasyExcelFactory.write(fileName)
                .withTemplate(templateFile)
                .head(Item.class)
                .sheet()
                .doWrite(sampleItems());
    }
}
