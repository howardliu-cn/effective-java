package cn.howardliu.tutorials.easyexcel.write;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import cn.howardliu.tutorials.easyexcel.entity.write.Item;

/**
 * 简单的写文件
 *
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteSample extends BaseWrite {
    public static void main(String[] args) {
        writeAutoWriter();
        writeManualWither();
    }

    /**
     * 借助{@link com.alibaba.excel.write.builder.ExcelWriterSheetBuilder}自动创建{@link com.alibaba.excel.ExcelWriter}写入数据。
     * <p>
     * 提供列表和函数作为数据源
     */
    public static void writeAutoWriter() {
        final String fileName = defaultFileName("writeAutoWriter");
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .sheet("模板")
                .doWrite(WriteSample::sampleItems);
    }

    /**
     * 手动创建{@link com.alibaba.excel.ExcelWriter}，指定sheet写入数据。
     * <p>
     * 提供列表和函数作为数据源
     */
    public static void writeManualWither() {
        String fileName = defaultFileName("writeManualWriter");
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcelFactory.write(fileName)
                    .head(Item.class)
                    .build();
            final WriteSheet writeSheet1 = EasyExcelFactory.writerSheet("模板1").build();
            excelWriter.write(WriteSample::sampleItems, writeSheet1);

            final WriteSheet writeSheet2 = EasyExcelFactory.writerSheet("模板2").build();
            excelWriter.write(sampleItems(), writeSheet2);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


}
