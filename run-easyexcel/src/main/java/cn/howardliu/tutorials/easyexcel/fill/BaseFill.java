package cn.howardliu.tutorials.easyexcel.fill;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.howardliu.tutorials.easyexcel.entity.fill.Item;
import cn.howardliu.tutorials.easyexcel.write.BaseWrite;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-28
 */
public abstract class BaseFill {
    public static final String NAME_TEMPLATE = getPath() + "fill-%s-%s.xlsx";

    public static String getPath() {
        return Objects.requireNonNull(BaseWrite.class.getResource("/")).getPath();
    }

    public static String defaultFileName(String prefixName) {
        return String.format(NAME_TEMPLATE, StringUtils.defaultString(prefixName), System.currentTimeMillis());
    }

    public static List<Item> sampleItems() {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item data = new Item();
            data.setName("刘" + i);
            data.setNumber(i);
            list.add(data);
        }
        return list;
    }

}
