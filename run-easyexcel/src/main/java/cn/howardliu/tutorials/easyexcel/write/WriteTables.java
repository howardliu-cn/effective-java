package cn.howardliu.tutorials.easyexcel.write;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;

import cn.howardliu.tutorials.easyexcel.entity.ComplexHeadItem;
import cn.howardliu.tutorials.easyexcel.entity.Item;

/**
 * 从这里可以看到，表头的作用域分为：工作簿（excel域）、表单（sheet域）、表格（table域），是继承关系。
 *
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class WriteTables extends BaseWrite {
    public static void main(String[] args) {
        writeTable();
        writeTables();
        writeTablesWithDiffHead();
    }

    /**
     * 同一表单中创建表格
     */
    private static void writeTable() {
        String fileName = defaultFileName("writeTable");
        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName)
                .head(Item.class)
                .build();
        try {
            // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
            WriteSheet writeSheet = EasyExcelFactory.writerSheet()
                    .needHead(Boolean.FALSE)
                    .build();

            // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
            WriteTable writeTable0 = EasyExcelFactory.writerTable(0)
                    .needHead(Boolean.TRUE)
                    .build();

            excelWriter.write(sampleItems(), writeSheet, writeTable0);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 同一表单中创建不同表格（相同表头）
     */
    private static void writeTables() {
        String fileName = defaultFileName("writeTables");
        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName)
                .build();
        try {
            // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
            WriteSheet writeSheet = EasyExcelFactory.writerSheet()
                    .head(Item.class)
                    .needHead(Boolean.FALSE)
                    .build();

            // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
            WriteTable writeTable0 = EasyExcelFactory.writerTable(0)
                    .needHead(Boolean.TRUE)
                    .build();

            WriteTable writeTable1 = EasyExcelFactory.writerTable(1)
                    .needHead(Boolean.TRUE)
                    .build();
            // 第一次写入会创建头
            excelWriter.write(sampleItems(), writeSheet, writeTable0);
            // 第二次写如也会创建头，然后在第一次的后面写入数据
            excelWriter.write(sampleItems(), writeSheet, writeTable1);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 同一表单中创建不同表格（不同表头）
     */
    private static void writeTablesWithDiffHead() {
        String fileName = defaultFileName("writeTablesWithDiffHead");
        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName)
                .build();
        try {
            WriteSheet writeSheet = EasyExcelFactory.writerSheet()
                    .build();

            WriteTable writeTable0 = EasyExcelFactory.writerTable(0)
                    .head(Item.class)
                    .build();
            excelWriter.write(sampleItems(), writeSheet, writeTable0);

            WriteTable writeTable1 = EasyExcelFactory.writerTable(1)
                    .head(ComplexHeadItem.class)
                    .build();
            excelWriter.write(sampleItems(), writeSheet, writeTable1);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
