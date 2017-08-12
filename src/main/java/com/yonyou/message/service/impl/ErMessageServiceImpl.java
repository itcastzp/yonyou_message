package com.yonyou.message.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.message.mapper.ErMesgboardMapper;
import com.yonyou.message.mapper.MessageGroupMapper;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.po.MessageGroup;
import com.yonyou.message.service.IErMessageService;
import com.yonyou.message.utils.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
@Service
public class ErMessageServiceImpl implements IErMessageService {
    @Autowired
    private ErMesgboardMapper erMesgboardMapper;
    @Autowired
    private MessageGroupMapper messageGroupMapper;
    @Autowired
    private MybatisDao mybatisDao;
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)//若有加入，无新建事务，读取已提交数据
    public List<ErMesgboard> getHistoryMessages(String pk_djh,String userid) throws Exception {
        return erMesgboardMapper.getHistoryMessages(pk_djh,userid);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int sendMessage(ErMesgboard erMesgboard) throws Exception {
        erMesgboardMapper.insert(erMesgboard);
        return Integer.parseInt(erMesgboard.getMessageId());
    }

    public void callBackMessage(String messageId) throws Exception {
        erMesgboardMapper.updateByPrimaryKey(messageId);
    }

    public ErMesgboard selectByPrimaryKey(String pk_messageid) throws Exception {
        ErMesgboard erMesgboard = erMesgboardMapper.selectByPrimaryKey(pk_messageid);
        return erMesgboard;
    }

    public List<ErMesgboard> selectByUserPkdjh(String userid, String pk_djh) throws Exception {
        return erMesgboardMapper.selectByUserPkdjh(userid,pk_djh);
    }

    public void updateUnReadMessage(String userid,List<ErMesgboard> message) throws Exception {
        for (ErMesgboard ems : message) {
            ems.setIsall(userid);
        }
        String sql = "com.yonyou.message.mapper.ErMesgboardMapper.updateUnReadMessage";
        mybatisDao.batchUpdate(sql,message);
        //return erMesgboardMapper.updateUnReadMessage(message);
    }

    public List<ErMesgboard> selectByUserBillIds(String userid, JSONArray billids) throws Exception {
        return erMesgboardMapper.selectByUserBillIds(userid,billids);
    }

    public MessageGroup insertGroup(MessageGroup group) throws Exception {
        messageGroupMapper.insertGroup(group);
        return group;
    }

    public MessageGroup getGroupByBillPk(String billpk) throws Exception {
        MessageGroup messageGroup = messageGroupMapper.getGroupByBillPk(billpk);
        return messageGroup;
    }

}
