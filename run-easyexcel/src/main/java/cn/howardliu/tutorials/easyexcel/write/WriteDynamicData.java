package cn.howardliu.tutorials.easyexcel.write;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.AbstractParameterBuilder;

import cn.howardliu.tutorials.easyexcel.entity.write.FormatContentItem;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class WriteDynamicData extends BaseWrite {
    public static void main(String[] args) {
        writeDynamicHead();
        writeDynamicMultiHead();
        writeDynamicData();
    }

    /**
     * 动态表头，传入的是{@code List<List<String>>}格式数据。
     * <p>
     * 可以实现多层表头。
     */
    private static void writeDynamicHead() {
        String fileName = defaultFileName("writeDynamicHead");
        EasyExcelFactory.write(fileName)
                .head(dynamicHead())
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 可以同时设置head参数：
     * {@link AbstractParameterBuilder#head(java.util.List)}
     * {@link AbstractParameterBuilder#head(java.lang.Class)}
     * <p>
     * 对于表头设置，最终起作用的是{@link AbstractParameterBuilder#head(java.util.List)}，这样的话，我们可以实现国际化的配置。
     */
    private static void writeDynamicMultiHead() {
        String fileName = defaultFileName("writeDynamicMultiHead");
        EasyExcelFactory.write(fileName)
                .head(dynamicHead())
                .head(FormatContentItem.class)
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 动态列表
     */
    private static void writeDynamicData() {
        String fileName = defaultFileName("writeDynamicData");
        EasyExcelFactory.write(fileName)
                .head(dynamicHead())
                .sheet()
                .doWrite(dynamicData());
    }

    private static List<List<String>> dynamicHead() {
        List<List<String>> heads = new ArrayList<>();
        final List<String> head0 = new ArrayList<>(Arrays.asList("头0", "字符串标题【动态】"));
        heads.add(head0);
        final List<String> head1 = new ArrayList<>(Arrays.asList("头0", "日期标题【动态】"));
        heads.add(head1);
        final List<String> head2 = new ArrayList<>(Collections.singletonList("数字标题【动态】"));
        heads.add(head2);
        return heads;
    }

    private static List<List<Object>> dynamicData() {
        List<List<Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add((i + 1) * 0.1);
            list.add(data);
        }
        return list;
    }
}
