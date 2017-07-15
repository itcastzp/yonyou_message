package com.yonyou.message.mapper;

import com.yonyou.message.po.ErUser;

import java.util.List;

public interface ErUserMapper {

    int deleteByPrimaryKey(String userid);

    int insert(ErUser record);

    ErUser selectByPrimaryKey(String userid);

    int updateByPrimaryKey(ErUser record);

    int insertUsers(List<ErUser> users);
    List<ErUser> selectUserByIds(List<ErUser> users);
}