package com.yonyou.message.service.impl;

import com.yonyou.message.mapper.ErUserMapper;
import com.yonyou.message.po.ErUser;
import com.yonyou.message.service.IErUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */
@Service
public class erUserServiceImpl implements IErUserService{
    @Autowired
    private ErUserMapper erUserMapper;

    public int inserUsers(List<ErUser> list) {
        int users= erUserMapper.insertUsers(list);
        return users;
    }

    public List<ErUser> selectUserByIds(List<ErUser> list) {
        List<ErUser> users= erUserMapper.selectUserByIds(list);
        return users;
    }

    public ErUser selectByPrimaryKey(String userid) {
        return erUserMapper.selectByPrimaryKey(userid);
    }
}
