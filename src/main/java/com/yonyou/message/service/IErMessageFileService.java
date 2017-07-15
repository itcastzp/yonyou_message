package com.yonyou.message.service;

import com.yonyou.message.po.MessageFile;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface IErMessageFileService {
    int insert(MessageFile file) throws Exception;
    List<MessageFile> getMessageFile(String messageid) throws Exception;

    MessageFile getMessageFileByFileName(String filename,String messageid) throws Exception;
    void deleteFile(String file_id) throws Exception;

    MessageFile getMessageFileByFileId(String messageid) throws Exception;
}
