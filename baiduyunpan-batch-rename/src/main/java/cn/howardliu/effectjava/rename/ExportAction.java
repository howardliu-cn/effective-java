package cn.howardliu.effectjava.rename;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import cn.howardliu.effectjava.rename.entity.FileName;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public final class ExportAction {
    private ExportAction() {
    }

    public static void export(String targetPath) throws IOException {
        final List<String> cookieInfo = CookieFileReader.read();

        final String cookie = cookieInfo.get(0);
        final String path = StringUtils.defaultString(targetPath,  cookieInfo.size() > 2 ? cookieInfo.get(2) : "/");
        final FileListCaller fileListCaller = new FileListCaller(cookie);

        final List<FileName> fileNames = fileListCaller.allFile(path);

        EasyExcelFactory.write(new File("./baiduyunpan_all_files.xlsx"))
                .head(FileName.class)
                .sheet()
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(fileNames);

        System.out.println("总共导出文件共：" + fileNames.size() + "条，导出文件为：baiduyunpan_all_files.xlsx");
    }
}
