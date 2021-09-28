package cn.howardliu.tutorials.easyexcel.write;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import cn.howardliu.tutorials.easyexcel.entity.Item;
import cn.howardliu.tutorials.easyexcel.write.handler.CellStyleWriteHandler;
import cn.howardliu.tutorials.easyexcel.write.handler.ColumnValidationWriteHandler;
import cn.howardliu.tutorials.easyexcel.write.handler.CommentWriteHandler;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class WriteUseWriteHandler extends BaseWrite {
    public static void main(String[] args) {
        writeByCellStyleStrategy();
        writeByCustomCellStyleStrategyOfEasyexcel();
        writeByCustomCellStyleStrategyOfPoi();
        writeUseLongestMatchColumnWidthStyleStrategy();
        writeUseCustomWriteHandler();
        writeUseCustomWriteHandler2();
    }

    /**
     * 使用已有策略实现自定义样式
     *
     * <ul>
     *     <li>HorizontalCellStyleStrategy 每一行的样式都一样 或者隔行一样</li>
     *     <li>AbstractVerticalCellStyleStrategy 每一列的样式都一样 需要自己回调每一页</li>
     * </ul>
     */
    private static void writeByCellStyleStrategy() {
        String fileName = defaultFileName("writeByCellStyleStrategy");

        // 表头策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 40);
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 表体策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为 FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色。表头默认了 FillPatternType 所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 使用easyexcel的方式自定义格式策略（这种方式官方不推荐，有现成的干嘛要自己实现呢）
     */
    private static void writeByCustomCellStyleStrategyOfEasyexcel() {
        String fileName = defaultFileName("writeByCustomCellStyleStrategyOfEasyexcel");
        final CellWriteHandler writeHandler = new CellWriteHandler() {
            @Override
            public void afterCellDispose(CellWriteHandlerContext context) {
                // 当前事件会在 数据设置到poi的cell里面才会回调
                // 判断不是头的情况 如果是fill 的情况 这里会==null 所以用not true
                if (BooleanUtils.isNotTrue(context.getHead())) {
                    // 第一个单元格
                    // 只要不是头 一定会有数据 当然fill的情况 可能要context.getCellDataList() ,这个需要看模板，因为一个单元格会有多个 WriteCellData
                    WriteCellData<?> cellData = context.getFirstCellData();
                    // 这里需要去cellData 获取样式
                    // 很重要的一个原因是 WriteCellStyle 和 dataFormatData绑定的 简单的说 比如你加了 DateTimeFormat
                    // ，已经将writeCellStyle里面的dataFormatData 改了 如果你自己new了一个WriteCellStyle，可能注解的样式就失效了
                    // 然后 getOrCreateStyle 用于返回一个样式，如果为空，则创建一个后返回
                    WriteCellStyle writeCellStyle = cellData.getOrCreateStyle();
                    writeCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                    // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND
                    writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
                    // 这样样式就设置好了 后面有个 FillStyleCellWriteHandler 默认会将 WriteCellStyle 设置到 cell里面去 所以可以不用管了
                }
            }
        };
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .registerWriteHandler(writeHandler)
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 使用poi的方式自定义格式策略（这种方式官方不推荐，有现成的干嘛要自己实现呢）
     * <p>
     * 此处有两个坑：
     * <p>
     * 坑1：style里面有dataformat 用来格式化数据的 所以自己设置可能导致格式化注解不生效
     * <p>
     * 坑2：不要一直去创建style 记得缓存起来 最多创建6W个就挂了
     */
    private static void writeByCustomCellStyleStrategyOfPoi() {
        String fileName = defaultFileName("writeByCustomCellStyleStrategyOfPoi");
        final CellWriteHandler writeHandler = new CellWriteHandler() {
            @Override
            public void afterCellDispose(CellWriteHandlerContext context) {
                // 当前事件会在 数据设置到poi的cell里面才会回调
                // 判断不是头的情况 如果是fill 的情况 这里会==null 所以用not true
                if (BooleanUtils.isNotTrue(context.getHead())) {
                    Cell cell = context.getCell();
                    // 拿到poi的workbook
                    Workbook workbook = context.getWriteWorkbookHolder().getWorkbook();
                    // 这里千万记住 想办法能复用的地方把他缓存起来 一个表格最多创建6W个样式
                    // 不同单元格尽量传同一个 cellStyle
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                    // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(cellStyle);

                    // 由于这里没有指定 dataformat 最后展示的数据 格式可能会不太正确

                    // 这里要把 WriteCellData 的样式清空， 不然后面还有一个拦截器 FillStyleCellWriteHandler 默认会将 WriteCellStyle 设置到
                    // cell里面去 会导致自己设置的不一样
                    context.getFirstCellData().setWriteCellStyle(null);
                }
            }
        };
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .registerWriteHandler(writeHandler)
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 自动列宽策略。不是很精确
     */
    private static void writeUseLongestMatchColumnWidthStyleStrategy() {
        String fileName = defaultFileName("writeUseLongestMatchColumnWidthStyleStrategy");
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 使用自定义拦截器实现表单的个性化配置
     */
    private static void writeUseCustomWriteHandler() {
        String fileName = defaultFileName("writeUseCustomWriteHandler");
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .registerWriteHandler(new ColumnValidationWriteHandler())
                .registerWriteHandler(new CellStyleWriteHandler())
                .sheet()
                .doWrite(sampleItems());
    }

    /**
     * 实现插入批注，是通过实现{@link com.alibaba.excel.write.handler.RowWriteHandler}。
     *
     * 注意细节：想要支持批注，inMemory需要设置为 true。
     */
    private static void writeUseCustomWriteHandler2() {
        String fileName = defaultFileName("writeUseCustomWriteHandler2");
        EasyExcelFactory.write(fileName)
                .head(Item.class)
                .inMemory(Boolean.TRUE)
                .registerWriteHandler(new CommentWriteHandler())
                .sheet()
                .doWrite(sampleItems());
    }
}
