package com.yonyou.message.mapper;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.message.po.MessageGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface MessageGroupMapper {
    void insertGroup(MessageGroup group);

    MessageGroup getGroupByBillPk(String billpk,String tenant_id);

    String getDescUrlByTenantId(String tenant_id);

    void insertDescUrl( String tenant_id,String url);

    List<MessageGroup> getBillPk(@Param("tenant_id") String tenant_id, @Param("grouppk") JSONArray groupPk);

    String getGroupPkByBillId(String billid);
}
