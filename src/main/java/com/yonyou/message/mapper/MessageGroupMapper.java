package com.yonyou.message.mapper;

import com.yonyou.message.po.MessageGroup;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface MessageGroupMapper {
    void insertGroup(MessageGroup group);

    MessageGroup getGroupByBillPk(String billpk);
}
