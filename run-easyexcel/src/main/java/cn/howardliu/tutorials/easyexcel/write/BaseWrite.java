package cn.howardliu.tutorials.easyexcel.write;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.howardliu.tutorials.easyexcel.entity.Item;

/**
 * @author Howard Liu <howardliu1988@163.com>
 * Created on 2021-09-26
 */
public abstract class BaseWrite {
    public static final String NAME_TEMPLATE = getPath() + "write-%s-%s.xlsx";

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
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(i * 0.1);
            data.setIgnore("这是一个忽略导出的字段" + i);
            list.add(data);
        }
        return list;
    }

}
