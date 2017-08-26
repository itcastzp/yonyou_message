package com.yonyou.message.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Administrator on 2017/8/1.
 */
@Configuration
@PropertySource(value="classpath:im.properties")
public class Config {
    @Value("${eptId}")
    public String eptId;
    @Value("${appId}")
    public String appId;
    @Value("${clientId}")
    public String clientId;
    @Value("${clientSecret}")
    public String clientSecret;
    @Value("${baseUrl}")
    public String baseUrl;
    @Value("${ybzUrl}")
    public String ybzUrl;
    @Value("${tenant_id}")
    public String tenant_id;
    @Value("${descUrl}")
    public String descUrl;
    public String token;
    public String expiration;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
