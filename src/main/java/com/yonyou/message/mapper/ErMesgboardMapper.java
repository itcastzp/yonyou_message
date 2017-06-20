package com.yonyou.message.mapper;

import com.yonyou.message.po.ErMesgboard;

import java.util.List;

public interface ErMesgboardMapper {

    int deleteByPrimaryKey(String pkMesgid);

    int insert(ErMesgboard record);

    int insertSelective(ErMesgboard record);

    ErMesgboard selectByPrimaryKey(String pkMesgid);

    int updateByPrimaryKey(String messageId);
    List<ErMesgboard> getHistoryMessages(String djh);
}