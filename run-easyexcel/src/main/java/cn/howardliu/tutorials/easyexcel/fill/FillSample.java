package cn.howardliu.tutorials.easyexcel.fill;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.excel.EasyExcelFactory;

import cn.howardliu.tutorials.easyexcel.entity.fill.Item;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public class FillSample extends BaseFill {
    public static void main(String[] args) {
        fillUseObject();
        fillUseMap();
    }

    private static void fillUseObject() {
        String fileName = defaultFileName("fillUseObject");
        String templateFile = getPath() + File.separator + "template_fill_sample.xlsx";

        Item item = new Item();
        item.setName("法外狂徒张三");
        item.setNumber(89757);
        EasyExcelFactory.write(fileName)
                .withTemplate(templateFile)
                .sheet()
                .doFill(item);
    }

    private static void fillUseMap() {
        String fileName = defaultFileName("fillUseMap");
        String templateFile = getPath() + File.separator + "template_fill_sample.xlsx";

        Map<String, Object> data = new HashMap<>();
        data.put("name", "法外狂徒张三");
        data.put("number", 89757);
        EasyExcelFactory.write(fileName)
                .withTemplate(templateFile)
                .sheet()
                .doFill(data);
    }
}
