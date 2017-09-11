package com.yonyou.message.service;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.po.MessageGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/19.
 */
public interface IErMessageService {
    public List<ErMesgboard> getHistoryMessages(String pk_djh,String user) throws Exception;
    public int sendMessage(ErMesgboard erMesgboard) throws Exception;

    public void callBackMessage(String messageId) throws Exception;

    ErMesgboard selectByPrimaryKey(String pk_messageid) throws Exception;
    List<ErMesgboard> selectByUserPkdjh(String userid,String pk_djh) throws Exception;
    void updateUnReadMessage(String userid,List<ErMesgboard> message) throws Exception;
    List<ErMesgboard> selectByUserBillIds(String userid, JSONArray billids) throws Exception;

    MessageGroup insertGroup(MessageGroup group) throws Exception;

    MessageGroup getGroupByBillPk(String billpk,String tenant_id) throws Exception;
    String getDescUrlByTenantId(String tenant_id) throws Exception;
    void insertDescUrl(String tenant_id,String url) throws Exception;

    List<MessageGroup> getBillPk(String tenant_id, JSONArray groupPk) throws Exception;
    String getServerToken() throws Exception;

    String getUserToken(String userid) throws Exception;

    String getGroupPkByBillId(String billid,String tenant_id) throws Exception;
}
