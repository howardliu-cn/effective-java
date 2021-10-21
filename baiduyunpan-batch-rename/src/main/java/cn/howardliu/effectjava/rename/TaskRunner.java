package cn.howardliu.effectjava.rename;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public class TaskRunner {
    public static void main(String[] args) throws IOException {
        if (ArrayUtils.isEmpty(args)) {
            printScriptAlert();
            return;
        }

        final String actionName = args[0];
        final String secondParam = args.length > 1 ? args[1] : null;

        if (StringUtils.equalsIgnoreCase("export", actionName)) {
            ExportAction.export(secondParam);
        } else if (StringUtils.equalsIgnoreCase("rename", actionName)) {
            RenameAction.rename(secondParam);
        } else {
            printScriptAlert();
        }
    }

    private static void printScriptAlert() {
        System.out.println("请执行任务后重新执行：");
        System.out.println("导出指定目录下所有文件： java -jar baiduyunpan-batch-rename.jar export /path/info");
        System.out.println(" /path/info 默认使用百度云盘的 根目录");
        System.out.println("批量重命名： java -jar baiduyunpan-batch-rename.jar rename bdstoken filename.xlsx");
        System.out.println(" filename.xlsx 默认使用当前目录下的 baiduyunpan_all_files.xlsx");
    }
}
