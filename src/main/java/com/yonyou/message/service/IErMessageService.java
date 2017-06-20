package com.yonyou.message.service;

import com.yonyou.message.po.ErMesgboard;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
public interface IErMessageService {
    public List<ErMesgboard> getHistoryMessages(String pk_djh) throws Exception;
    public int sendMessage(ErMesgboard erMesgboard) throws Exception;

    public void callBackMessage(String messageId) throws Exception;

    ErMesgboard selectByPrimaryKey(String pk_messageid) throws Exception;
}
