package cn.howardliu.tutorials.easyexcel.fill;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillConfig.FillConfigBuilder;
import com.alibaba.excel.write.metadata.fill.FillWrapper;

/**
 * 填充是可以实现自定义样式的，只要在模板中设置好样式，填充的数据就能够带着样式。
 *
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class FillComplexData extends BaseFill {
    public static void main(String[] args) {
        fillObjectAndListInMemory();
        fillObjectAndListManual();
        fillObjectAndListHorizontal();
        fillMultiList();
    }

    /**
     * 填充对象+列表，因为列表之后还有一个字段，所以需要将{@link FillConfigBuilder#forceNewRow(Boolean)}设置为 TRUE 才行。
     * <p>
     * 这样会有一个副作用：所有数据会在内存中，即数据量大的时候特别耗内存。
     * <p>
     * 想要解决有两种方式：
     *
     * <ul>
     *     <li>list之后没有数据了，{@link FillConfigBuilder#forceNewRow(Boolean)}设置为 FALSE</li>
     *     <li>list写完之后，手动写后面的数据</li>
     * </ul>
     */
    private static void fillObjectAndListInMemory() {
        String fileName = defaultFileName("fillObjectAndListInMemory");
        String templateFile = getPath() + File.separator + "template_fill_object_and_list.xlsx";

        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName).withTemplate(templateFile).build();
        try {
            final WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();

            Map<String, Object> map = new HashMap<>();
            map.put("date", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            map.put("total", System.currentTimeMillis());
            excelWriter.fill(map, writeSheet);

            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();

            excelWriter.fill(BaseFill::sampleItems, fillConfig, writeSheet);
            excelWriter.fill(sampleItems(), fillConfig, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 大数量的时候手动填充对象+列表
     */
    private static void fillObjectAndListManual() {
        String fileName = defaultFileName("fillObjectAndListManual");
        String templateFile = getPath() + File.separator + "template_fill_object_and_list_manual.xlsx";

        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName).withTemplate(templateFile).build();
        try {
            final WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();

            Map<String, Object> map = new HashMap<>();
            map.put("date", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            excelWriter.fill(map, writeSheet);

            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();

            excelWriter.fill(BaseFill::sampleItems, fillConfig, writeSheet);
            excelWriter.fill(sampleItems(), fillConfig, writeSheet);

            // 下面是纯手工写数据
            List<List<String>> totalListList = new ArrayList<>();
            List<String> totalList = new ArrayList<>();
            totalListList.add(totalList);
            totalList.add(null);
            totalList.add(null);
            totalList.add(null);
            totalList.add("统计:1000");
            excelWriter.write(totalListList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 横向填充数据
     */
    private static void fillObjectAndListHorizontal() {
        String fileName = defaultFileName("fillObjectAndListHorizontal");
        String templateFile = getPath() + File.separator + "template_fill_list_horizontal.xlsx";

        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName).withTemplate(templateFile).build();
        try {
            final WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();

            Map<String, Object> map = new HashMap<>();
            map.put("date", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            excelWriter.fill(map, writeSheet);

            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();

            excelWriter.fill(BaseFill::sampleItems, fillConfig, writeSheet);
            excelWriter.fill(sampleItems(), fillConfig, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 填充多个表格
     */
    private static void fillMultiList() {
        String fileName = defaultFileName("fillMultiList");
        String templateFile = getPath() + File.separator + "template_fill_multi_list.xlsx";

        final ExcelWriter excelWriter = EasyExcelFactory.write(fileName).withTemplate(templateFile).build();
        try {
            final WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();

            Map<String, Object> map = new HashMap<>();
            map.put("date", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            excelWriter.fill(map, writeSheet);

            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();

            excelWriter.fill(new FillWrapper("data1", sampleItems()), fillConfig, writeSheet);
            // data2分批写入
            excelWriter.fill(new FillWrapper("data2", sampleItems()), writeSheet);
            excelWriter.fill(new FillWrapper("data2", sampleItems()), writeSheet);
            excelWriter.fill(new FillWrapper("data3", sampleItems()), writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
