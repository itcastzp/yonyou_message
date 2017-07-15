package com.yonyou.message.service;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.message.po.ErMesgboard;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
public interface IErMessageService {
    public List<ErMesgboard> getHistoryMessages(String pk_djh,String user) throws Exception;
    public int sendMessage(ErMesgboard erMesgboard) throws Exception;

    public void callBackMessage(String messageId) throws Exception;

    ErMesgboard selectByPrimaryKey(String pk_messageid) throws Exception;
    List<ErMesgboard> selectByUserPkdjh(String userid,String pk_djh) throws Exception;
    int updateUnReadMessage(String userid,List<ErMesgboard> message) throws Exception;
    List<ErMesgboard> selectByUserBillIds(String userid, JSONArray billids) throws Exception;
}
