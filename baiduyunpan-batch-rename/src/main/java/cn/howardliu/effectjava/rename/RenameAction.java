package cn.howardliu.effectjava.rename;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.excel.EasyExcelFactory;

import cn.howardliu.effectjava.rename.entity.FileName;
import cn.howardliu.effectjava.rename.entity.FileRenameResultInfo;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public final class RenameAction {
    private RenameAction() {
    }

    public static void rename(String targetFile) throws IOException {
        final List<String> cookieInfo = CookieFileReader.read();
        final String cookie = cookieInfo.get(0);
        final String bdstoken = cookieInfo.size() > 1 ? cookieInfo.get(1) : null;
        if (StringUtils.isEmpty(bdstoken)) {
            throw new IllegalArgumentException("请在当前目录下 cookie.txt 文件第二行指定bdstoken参数，该参数从控制台获取");
        }

        final String renameFile = StringUtils.defaultString(targetFile, "baiduyunpan_all_files.xlsx");
        final InputStream inputStream = FileUtils.openInputStream(new File(renameFile));

        List<FileName> fileNames = EasyExcelFactory.read(inputStream)
                .head(FileName.class)
                .sheet()
                .doReadSync();

        final FileRenameCaller fileRenameCaller = new FileRenameCaller(cookie, bdstoken);
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
