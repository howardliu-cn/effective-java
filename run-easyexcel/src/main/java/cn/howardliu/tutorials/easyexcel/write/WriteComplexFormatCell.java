package cn.howardliu.tutorials.easyexcel.write;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.CommentData;
import com.alibaba.excel.metadata.data.FormulaData;
import com.alibaba.excel.metadata.data.HyperlinkData;
import com.alibaba.excel.metadata.data.HyperlinkData.HyperlinkType;
import com.alibaba.excel.metadata.data.RichTextStringData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;

import cn.howardliu.tutorials.easyexcel.entity.write.ComplexFormatCellItem;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public class WriteComplexFormatCell extends BaseWrite {
    public static void main(String[] args) {
        writeComplexFormatCell();
    }

    /**
     * 这个例子比较定制化，可以看<a href="https://www.yuque.com/easyexcel/doc/write#orUi8">官方文档</a>
     */
    private static void writeComplexFormatCell() {
        String fileName = defaultFileName("writeComplexFormatCell");

        ComplexFormatCellItem writeCellDemoData = new ComplexFormatCellItem();

        // 设置超链接
        HyperlinkData hyperlinkData = new HyperlinkData();
        hyperlinkData.setAddress("https://www.howardliu.cn");
        hyperlinkData.setHyperlinkType(HyperlinkType.URL);
        WriteCellData<String> hyperlink = new WriteCellData<>("网站");
        hyperlink.setHyperlinkData(hyperlinkData);
        writeCellDemoData.setHyperlink(hyperlink);

        // 设置备注
        CommentData commentData = new CommentData();
        commentData.setAuthor("Howard Liu");
        commentData.setRichTextStringData(new RichTextStringData("这是一个备注"));
        // 备注的默认大小是按照单元格的大小 这里想调整到4个单元格那么大 所以向后 向下 各额外占用了一个单元格
        commentData.setRelativeLastColumnIndex(1);
        commentData.setRelativeLastRowIndex(1);
        WriteCellData<String> comment = new WriteCellData<>("备注的单元格信息");
        comment.setCommentData(commentData);
        writeCellDemoData.setCommentData(comment);

        // 设置公式
        FormulaData formulaData = new FormulaData();
        // 将 123456789 中的第一个数字替换成 2
        // 这里只是例子 如果真的涉及到公式 能内存算好尽量内存算好 公式能不用尽量不用
        formulaData.setFormulaValue("REPLACE(123456789,1,1,2)");
        WriteCellData<String> formula = new WriteCellData<>();
        formula.setFormulaData(formulaData);
        writeCellDemoData.setFormulaData(formula);

        // 设置单个单元格的样式 当然样式 很多的话 也可以用注解等方式。
        WriteCellStyle writeCellStyleData = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为 FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.
        writeCellStyleData.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        writeCellStyleData.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteCellData<String> writeCellStyle = new WriteCellData<>("单元格样式");
        writeCellStyle.setWriteCellStyle(writeCellStyleData);
        writeCellStyle.setType(CellDataTypeEnum.STRING);
        writeCellDemoData.setWriteCellStyle(writeCellStyle);

        // 设置单个单元格多种样式
        RichTextStringData richTextStringData = new RichTextStringData();
        richTextStringData.setTextString("红色绿色默认");
        // 前2个字红色
        WriteFont writeFont = new WriteFont();
        writeFont.setColor(IndexedColors.RED.getIndex());
        richTextStringData.applyFont(0, 2, writeFont);
        // 接下来2个字绿色
        writeFont = new WriteFont();
        writeFont.setColor(IndexedColors.GREEN.getIndex());
        richTextStringData.applyFont(2, 4, writeFont);
        WriteCellData<String> richTest = new WriteCellData<>();
        richTest.setType(CellDataTypeEnum.RICH_TEXT_STRING);
        richTest.setRichTextStringDataValue(richTextStringData);
        writeCellDemoData.setRichText(richTest);

        List<ComplexFormatCellItem> data = new ArrayList<>();
        data.add(writeCellDemoData);
        EasyExcelFactory.write(fileName)
                .head(ComplexFormatCellItem.class)
                .inMemory(true)
                .sheet()
                .doWrite(data);
    }
}
