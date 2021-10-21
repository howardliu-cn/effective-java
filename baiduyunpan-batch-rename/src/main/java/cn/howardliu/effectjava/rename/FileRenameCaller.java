package cn.howardliu.effectjava.rename;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import cn.howardliu.effectjava.rename.entity.FileName;
import cn.howardliu.effectjava.rename.entity.FileRenameRequest;
import cn.howardliu.effectjava.rename.entity.FileRenameResponse;
import cn.howardliu.effectjava.rename.entity.FileRenameResultInfo;
import cn.howardliu.effectjava.rename.entity.TaskStatusResponse;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public class FileRenameCaller extends BaseCaller {
    private static final String RENAME_BODY_PARAM = "filelist=[{\"path\":\"%s\",\"newname\":\"%s\"}]";
    private final TaskStatusCaller taskStatusCaller;
    private final String bdstoken;

    public FileRenameCaller(String cookie, String bdstoken) {
        this(cookie, bdstoken, Maps.newHashMap());
    }

    public FileRenameCaller(String cookie, String bdstoken, Map<String, List<String>> customHeaders) {
        super(cookie, customHeaders);
        this.bdstoken = bdstoken;
        taskStatusCaller = new TaskStatusCaller(cookie, customHeaders);
    }

    public List<FileRenameResultInfo> rename(List<FileName> fileNames) {
        if (CollectionUtils.isEmpty(fileNames)) {
            return Collections.emptyList();
        }

        fileNames = fileNames.stream()
                .filter(file -> !StringUtils.isAnyEmpty(file.getNewName(), file.getPath()))
                .collect(Collectors.toList());

        final List<FileRenameResultInfo> failInfos = Lists.newArrayList();
        final FileRenameRequest fileRenameRequest = new FileRenameRequest();
        fileRenameRequest.setBdstoken(this.bdstoken);
        for (FileName fileName : fileNames) {
            final String params = String.format(RENAME_BODY_PARAM, fileName.getPath(), fileName.getNewName());
            final Long taskId = rename(fileRenameRequest, params);
            final FileRenameResultInfo fileRenameResultInfo = new FileRenameResultInfo();
            fileRenameResultInfo.setFileName(fileName);
            failInfos.add(fileRenameResultInfo);
            if (taskId > 0) {
                final TaskStatusResponse taskStatusResponse = taskStatusCaller.queryTaskStatus(taskId, params);
                if (taskStatusResponse == null) {
                    fileRenameResultInfo.setFailReason("任务提交成功，查询任务执行情况失败");
                } else {
                    fileRenameResultInfo.setFailReason("执行结束，状态为：" + taskStatusResponse.getStatus());
                }
            } else {
                fileRenameResultInfo.setFailReason("任务提交失败");
            }
        }

        return failInfos;
    }

    private Long rename(FileRenameRequest fileRenameRequest, String params) {
        final String queryParam = HttpUtil.toParams(fileRenameRequest.paramMap());
        final HttpRequest httpRequest = HttpRequest.post("https://pan.baidu.com/api/filemanager?" + queryParam)
                .header(this.headers)
                .cookie(this.cookie)
                .body(params);
        final String body = httpRequest.execute().body();
        final FileRenameResponse response = JSONUtil.toBean(body, FileRenameResponse.class);
        if (response.getErrno() == 0) {
            return response.getTaskid();
        }
        return -1L;
    }
}
