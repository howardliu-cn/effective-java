package cn.howardliu.effectjava.rename;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.Maps;

import cn.howardliu.effectjava.rename.entity.FileInfo;
import cn.howardliu.effectjava.rename.entity.FileListItem;
import cn.howardliu.effectjava.rename.entity.FileListRequest;
import cn.howardliu.effectjava.rename.entity.FileListResponse;
import cn.howardliu.effectjava.rename.entity.FileName;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public class FileListCaller extends BaseCaller {

    public FileListCaller(String cookie) {
        super(cookie, Maps.newHashMap());
    }

    public FileListCaller(String cookie, Map<String, List<String>> customHeaders) {
        super(cookie, customHeaders);
    }

    public List<FileName> allFile(String path) {
        return allFile(listFileWithDir(path));
    }

    private List<FileName> allFile(List<FileInfo> files) {
        final List<FileName> fileNames = Lists.newArrayList();

        for (FileInfo file : files) {
            if (file.isDir()) {
                fileNames.addAll(allFile(file.getChildren()));
            } else {
                fileNames.add(file.getFileName());
            }
        }

        return fileNames;
    }

    public List<FileInfo> listFileWithDir(String path) {
        final List<FileListItem> listItems = listAllFileCurrentPath(path);
        if (CollectionUtils.isEmpty(listItems)) {
            return Collections.emptyList();
        }

        final List<FileInfo> fileInfos = Lists.newArrayList();
        for (FileListItem listItem : listItems) {
            final FileName fileName = new FileName();
            fileName.setPath(listItem.getPath());
            fileName.setOriginName(listItem.getServer_filename());
            fileName.setMd5(listItem.getMd5());
            final Integer isdir = listItem.getIsdir();

            final FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setDir(isdir == 1);
            if (isdir == 1) {
                fileInfo.setChildren(listFileWithDir(listItem.getPath()));
            }

            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }

    public List<FileListItem> listAllFileCurrentPath(String path) {
        final List<FileListItem> list = Lists.newArrayList();
        int page = 1;
        final FileListRequest fileListRequest = new FileListRequest();
        fileListRequest.setDir(path);
        while (true) {
            fileListRequest.setPage(page);
            final List<FileListItem> segList = listFileCurrentPath(fileListRequest);
            if (CollectionUtils.isEmpty(segList)) {
                break;
            }
            list.addAll(segList);
            page++;
        }
        return list;
    }

    private List<FileListItem> listFileCurrentPath(FileListRequest fileListRequest) {
        final String body = HttpRequest.get("https://pan.baidu.com/api/list")
                .form(fileListRequest.paramMap())
                .header(this.headers)
                .cookie(this.cookie)
                .execute()
                .body();
        final FileListResponse response = JSONUtil.toBean(body, FileListResponse.class);
        if (response.getErrno() == 0) {
            return response.getList();
        }
        return Collections.emptyList();
    }
}
