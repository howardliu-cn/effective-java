package cn.howardliu.effectjava.rename;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.excel.EasyExcelFactory;

import cn.howardliu.effectjava.rename.entity.FileName;
import cn.howardliu.effectjava.rename.entity.FileRenameResultInfo;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public class RenameAction {
    public static void main(String[] args) throws IOException {
        final List<String> cookieInfo = CookieFileReader.read();
        final String cookie = cookieInfo.get(0);

        final InputStream inputStream = FileUtils.openInputStream(new File("baiduyunpan_all_files.xlsx"));

        List<FileName> fileNames = EasyExcelFactory.read(inputStream)
                .head(FileName.class)
                .sheet()
                .doReadSync();

        final FileRenameCaller fileRenameCaller = new FileRenameCaller(cookie);
        final List<FileRenameResultInfo> fileRenameResultInfos = fileRenameCaller.rename(fileNames);
        for (FileRenameResultInfo fileRenameResultInfo : fileRenameResultInfos) {
            final FileName fileName = fileRenameResultInfo.getFileName();
            final String path = fileName.getPath();
            final String newName = fileName.getNewName();
            final String failReason = fileRenameResultInfo.getFailReason();
            System.out.println("路径：" + path + "，新名：" + newName + ": " + failReason);
        }
    }
}
