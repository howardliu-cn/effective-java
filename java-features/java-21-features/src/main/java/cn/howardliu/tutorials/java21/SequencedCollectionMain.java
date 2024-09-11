package cn.howardliu.tutorials.java21;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.SequencedCollection;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-15
 */
public class SequencedCollectionMain {
    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(3, 2, 1));

        // 获取反向视图
        SequencedCollection<Integer> reversed = linkedHashSet.reversed();

        // 反向遍历
        System.out.println("原始数据：" + linkedHashSet);
        System.out.println("反转数据：" + reversed);
    }
}
