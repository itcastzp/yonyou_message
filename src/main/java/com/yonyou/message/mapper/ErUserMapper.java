package com.yonyou.message.mapper;

import com.yonyou.message.po.ErUser;

public interface ErUserMapper {

    int deleteByPrimaryKey(String userid);

    int insert(ErUser record);

    int insertSelective(ErUser record);

    ErUser selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(ErUser record);

    int updateByPrimaryKey(ErUser record);
}