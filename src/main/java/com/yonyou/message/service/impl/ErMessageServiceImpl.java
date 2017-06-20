package com.yonyou.message.service.impl;

import com.yonyou.message.mapper.ErMesgboardMapper;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.service.IErMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
@Service
public class ErMessageServiceImpl implements IErMessageService {
    @Autowired
    private ErMesgboardMapper erMesgboardMapper;
    public List<ErMesgboard> getHistoryMessages(String pk_djh) throws Exception {
        return erMesgboardMapper.getHistoryMessages(pk_djh);
    }

    public int sendMessage(ErMesgboard erMesgboard) throws Exception {
        erMesgboardMapper.insert(erMesgboard);
        return Integer.parseInt(erMesgboard.getPkMesgid());
    }

    public void callBackMessage(String messageId) throws Exception {
        erMesgboardMapper.updateByPrimaryKey(messageId);
    }

    public ErMesgboard selectByPrimaryKey(String pk_messageid) throws Exception {
        ErMesgboard erMesgboard = erMesgboardMapper.selectByPrimaryKey(pk_messageid);
        return erMesgboard;
    }
}
