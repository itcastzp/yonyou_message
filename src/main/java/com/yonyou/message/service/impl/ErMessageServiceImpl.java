package com.yonyou.message.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.message.mapper.ErMesgboardMapper;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.service.IErMessageService;
import com.yonyou.message.utils.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private MybatisDao mybatisDao;
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//不需要事务
    public List<ErMesgboard> getHistoryMessages(String pk_djh,String userid) throws Exception {
        return erMesgboardMapper.getHistoryMessages(pk_djh,userid);
    }

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

    public int updateUnReadMessage(String userid,List<ErMesgboard> message) throws Exception {
        for (ErMesgboard ems : message) {
            ems.setIsall(userid);
        }
        String sql = "com.yonyou.message.mapper.ErMesgboardMapper.updateByPrimaryKey";
        mybatisDao.batchUpdate(sql,message);
        return erMesgboardMapper.updateUnReadMessage(message);
    }

    public List<ErMesgboard> selectByUserBillIds(String userid, JSONArray billids) throws Exception {
        return erMesgboardMapper.selectByUserBillIds(userid,billids);
    }

}
