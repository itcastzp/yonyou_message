package com.yonyou.message.service;

import com.yonyou.message.po.ErUser;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */
public interface IErUserService {
    int inserUsers(List<ErUser> list);
    List<ErUser> selectUserByIds(List<ErUser> list);

    ErUser selectByPrimaryKey(String userid);
}
