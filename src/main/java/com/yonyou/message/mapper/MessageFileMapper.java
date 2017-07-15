package com.yonyou.message.mapper;

import com.yonyou.message.po.MessageFile;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface MessageFileMapper {
    int inserFile(MessageFile file) throws Exception;

    List<MessageFile> getMessageFile(String messageId) throws Exception;

    String deleteFile(String fileid) throws Exception;

    MessageFile getMessageFileByFileName(String filename,String messageid) throws Exception;
    MessageFile getMessageFileByFileId(String fileid) throws Exception;
}
