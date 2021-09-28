package cn.howardliu.tutorials.easyexcel.fill;

import java.io.File;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class FillList extends BaseFill {
    public static void main(String[] args) {
        fillListInMemory();
        fillListSegment();
    }

    private static void fillListInMemory() {
        String fileName = defaultFileName("fillListInMemory");
        String templateFile = getPath() + File.separator + "template_fill_list.xlsx";

        EasyExcelFactory.write(fileName)
                .withTemplate(templateFile)
                .sheet()
                .doFill(sampleItems());
    }

    private static void fillListSegment() {
        String fileName = defaultFileName("fillListSegment");
        String templateFile = getPath() + File.separator + "template_fill_list.xlsx";

        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName).withTemplate(templateFile).build();
        try {
            final WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();
            excelWriter.fill(BaseFill::sampleItems, writeSheet);
            excelWriter.fill(sampleItems(), writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
