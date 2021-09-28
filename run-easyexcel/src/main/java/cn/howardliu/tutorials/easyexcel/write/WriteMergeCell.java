package cn.howardliu.tutorials.easyexcel.write;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.merge.LoopMergeStrategy;

import cn.howardliu.tutorials.easyexcel.entity.Item;
import cn.howardliu.tutorials.easyexcel.entity.MergeCellItem;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class WriteMergeCell extends BaseWrite {
    public static void main(String[] args) {
        writeMergeCellUseAnnotation();
        writeMergeCellCustom();
    }

    /**
     * 使用注解{@link com.alibaba.excel.annotation.write.style.OnceAbsoluteMerge} 或
     * {@link com.alibaba.excel.annotation.write.style.ContentLoopMerge}实现合并单元格
     */
    private static void writeMergeCellUseAnnotation() {
        String fileName = defaultFileName("writeMergeCellUseAnnotation");
        EasyExcelFactory.write(fileName)
                .head(MergeCellItem.class)
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 使用 {@link com.alibaba.excel.write.merge.LoopMergeStrategy} 实现格式合并
     */
    private static void writeMergeCellCustom() {
        String fileName = defaultFileName("writeMergeCellCustom");
        // 每隔2行会合并
        // 把 eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .registerWriteHandler(loopMergeStrategy)
                .sheet()
                .doWrite(sampleItems());
    }
}
