package com.yonyou.message.mapper;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.message.po.ErMesgboard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErMesgboardMapper {

    int deleteByPrimaryKey(String pkMesgid);

    int insert(ErMesgboard record);

    int insertSelective(ErMesgboard record);

    ErMesgboard selectByPrimaryKey(String pkMesgid);

    int updateByPrimaryKey(String messageId);
    List<ErMesgboard> getHistoryMessages(String djh,String userid);

    List<ErMesgboard> selectByUserPkdjh(String userid, String pk_djh);

    int updateUnReadMessage(List<ErMesgboard> message);
    List<ErMesgboard> selectByUserBillIds(@Param("userid")String userid, @Param("billids")JSONArray billids);
}