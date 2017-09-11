package com.yonyou.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.config.Config;
import com.yonyou.message.mapper.ErMesgboardMapper;
import com.yonyou.message.mapper.MessageGroupMapper;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.po.MessageGroup;
import com.yonyou.message.service.IErMessageService;
import com.yonyou.message.utils.HttpClientUtils;
import com.yonyou.message.utils.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/19.
 */
@Service
public class ErMessageServiceImpl implements IErMessageService {
    @Autowired
    private ErMesgboardMapper erMesgboardMapper;
    @Autowired
    private MessageGroupMapper messageGroupMapper;
    @Autowired
    private MybatisDao mybatisDao;
    @Autowired
    private Config config;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)//若有加入，无新建事务，读取已提交数据
    public List<ErMesgboard> getHistoryMessages(String pk_djh,String userid) throws Exception {
        return erMesgboardMapper.getHistoryMessages(pk_djh,userid);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int sendMessage(ErMesgboard erMesgboard) throws Exception {
        erMesgboardMapper.insert(erMesgboard);
        return Integer.parseInt(erMesgboard.getMessageId());
    }

    public void callBackMessage(String messageId) throws Exception {
        erMesgboardMapper.updateByPrimaryKey(messageId);
    }

    public ErMesgboard selectByPrimaryKey(String pk_messageid) throws Exception {
        ErMesgboard erMesgboard = erMesgboardMapper.selectByPrimaryKey(pk_messageid);
        return erMesgboard;
    }

    public List<ErMesgboard> selectByUserPkdjh(String userid, String pk_djh) throws Exception {
        return erMesgboardMapper.selectByUserPkdjh(userid,pk_djh);
    }

    public void updateUnReadMessage(String userid,List<ErMesgboard> message) throws Exception {
        for (ErMesgboard ems : message) {
            ems.setIsall(userid);
        }
        String sql = "com.yonyou.message.mapper.ErMesgboardMapper.updateUnReadMessage";
        mybatisDao.batchUpdate(sql,message);
        //return erMesgboardMapper.updateUnReadMessage(message);
    }

    public List<ErMesgboard> selectByUserBillIds(String userid, JSONArray billids) throws Exception {
        return erMesgboardMapper.selectByUserBillIds(userid,billids);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)//若有加入，无新建事务，读取已提交数据
    public MessageGroup insertGroup(MessageGroup group) throws Exception {
        messageGroupMapper.insertGroup(group);
        return group;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)//若有加入，无新建事务，读取已提交数据
    public MessageGroup getGroupByBillPk(String billpk,String tenant_id) throws Exception {
        MessageGroup messageGroup = messageGroupMapper.getGroupByBillPk(billpk,tenant_id);
        return messageGroup;
    }

    public String getDescUrlByTenantId(String tenant_id) throws Exception {
        String url = messageGroupMapper.getDescUrlByTenantId(tenant_id);
        return url;
    }

    public void insertDescUrl( String tenant_id,String url) throws Exception {
        messageGroupMapper.insertDescUrl(tenant_id,url);
    }

    public List<MessageGroup> getBillPk(String tenant_id, JSONArray groupPk) throws Exception {
        return messageGroupMapper.getBillPk(tenant_id, groupPk);
    }

    public String getServerToken() throws Exception {
        final String url = config.baseUrl+ config.eptId+"/"+config.appId+"/token";
        return redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] imServertoken = redisTemplate.getStringSerializer().serialize("imServertoken");
                if (redisConnection.exists(imServertoken)) {
                    return redisTemplate.getStringSerializer().deserialize(redisConnection.get(imServertoken));
                } else {//发送请求获取token
                    JSONObject json = new JSONObject();
                    json.put("clientId", config.clientId);
                    json.put("clientSecret", config.clientSecret);
                    String getServertoken  = null;
                    try {
                        getServertoken = HttpClientUtils.sendHttpPostJson(url,json.toJSONString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String token = JSON.parseObject(getServertoken).getString("token");
                    String expiration = JSON.parseObject(getServertoken).getString("expiration");
                    redisConnection.setNX(imServertoken, redisTemplate.getStringSerializer().serialize(token));
                    redisConnection.expireAt(imServertoken, Long.parseLong(expiration) /1000);
                    return token;
                }
            }
        }).toString();
    }
    public String getUserToken(final String userid) throws Exception {
        final String url = config.baseUrl+ config.eptId+"/"+config.appId+"/token";
        return redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] imUsertoken = redisTemplate.getStringSerializer().serialize("imUser"+userid);
                if (redisConnection.exists(imUsertoken)) {
                    return redisTemplate.getStringSerializer().deserialize(redisConnection.get(imUsertoken));
                } else {//发送请求获取token
                    JSONObject json = new JSONObject();
                    json.put("clientId", config.clientId);
                    json.put("clientSecret", config.clientSecret);
                    json.put("username", userid);
                    String getServertoken = null;
                    try {
                        getServertoken = HttpClientUtils.sendHttpPostJson(url,json.toJSONString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String token = JSON.parseObject(getServertoken).getString("token");
                    String expiration = JSON.parseObject(getServertoken).getString("expiration");
                    redisConnection.setNX(imUsertoken, redisTemplate.getStringSerializer().serialize(token));
                    redisConnection.expireAt(imUsertoken, Long.parseLong(expiration) / 1000);
                    return token;
                }
            }
        }).toString();
    }

    public String getGroupPkByBillId(String billid,String tenant_id) throws Exception {
        return messageGroupMapper.getGroupPkByBillId(billid,tenant_id);
    }
}
