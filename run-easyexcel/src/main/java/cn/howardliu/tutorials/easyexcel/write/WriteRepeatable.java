package cn.howardliu.tutorials.easyexcel.write;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import cn.howardliu.tutorials.easyexcel.entity.write.ComplexHeadItem;
import cn.howardliu.tutorials.easyexcel.entity.write.Item;

/**
 * 多次写入
 * <ol>
 *     <li>写到同一个sheet</li>
 *     <li>写到不同sheet（相同表头）</li>
 *     <li>写到不同sheet（不同表头）</li>
 * </ol>
 *
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteRepeatable extends BaseWrite {
    public static void main(String[] args) {
        writeOneSheet();
        writeDiffSheetWithSameHead();
        writeDiffSheetWithDiffHead();
    }

    /**
     * 写到同一个sheet
     */
    private static void writeOneSheet() {
        String fileName = defaultFileName("writeOneSheet");
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcelFactory.write(fileName)
                    .head(Item.class)
                    .build();
            final WriteSheet writeSheet = EasyExcelFactory.writerSheet("模板").build();
            for (int i = 0; i < 5; i++) {
                excelWriter.write(sampleItems(), writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 写到不同sheet（相同表头）
     */
    private static void writeDiffSheetWithSameHead() {
        String fileName = defaultFileName("writeDiffSheetWithSameHead");
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcelFactory.write(fileName)
                    .head(Item.class)
                    .build();
            for (int i = 0; i < 5; i++) {
                final WriteSheet writeSheet = EasyExcelFactory.writerSheet(i, "模板" + i)
                        .build();
                excelWriter.write(sampleItems(), writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 写到不同sheet（不同表头）
     */
    private static void writeDiffSheetWithDiffHead() {
        String fileName = defaultFileName("writeDiffSheetWithDiffHead");
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcelFactory.write(fileName)
                    .build();

            final WriteSheet writeSheet0 = EasyExcelFactory.writerSheet(0, "模板1")
                    .head(Item.class)
                    .build();
            excelWriter.write(sampleItems(), writeSheet0);

            final WriteSheet writeSheet1 = EasyExcelFactory.writerSheet(1, "模板2")
                    .head(ComplexHeadItem.class)
                    .build();
            excelWriter.write(sampleItems(), writeSheet1);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
