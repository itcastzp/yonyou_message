package com.yonyou.message.service.impl;

import com.yonyou.message.mapper.MessageFileMapper;
import com.yonyou.message.po.MessageFile;
import com.yonyou.message.service.IErMessageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service
public class erMessageFileServiceImpl implements IErMessageFileService{
    @Autowired
    private MessageFileMapper fileMapper;

    public int insert(MessageFile file) throws Exception {
        int id = fileMapper.inserFile(file);
        return id;
    }

    public List<MessageFile> getMessageFile(String messageid) throws Exception {
        List<MessageFile> files = (List<MessageFile>) fileMapper.getMessageFile(messageid);
        return files;
    }

    public MessageFile getMessageFileByFileName(String filename, String messageid) throws Exception {
        MessageFile file = fileMapper.getMessageFileByFileName(filename, messageid);
        return file;
    }



    public void deleteFile(String file_id) throws Exception {
        fileMapper.deleteFile(file_id);
    }

    public MessageFile getMessageFileByFileId(String fileid) throws Exception {
        MessageFile file = fileMapper.getMessageFileByFileId(fileid);
        return file;
    }
}
