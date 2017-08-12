package com.yonyou.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.config.Config;
import com.yonyou.message.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/3.
 */
public class TokenServlet extends HttpServlet{
    @Autowired
    private Config config;
    @Autowired
    HttpServletRequest request;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        String url = config.baseUrl+ config.eptId+"/"+config.appId+"/token";
        JSONObject json = new JSONObject();
        json.put("clientId", config.clientId);
        json.put("clientSecret", config.clientSecret);
        String gettoken = HttpClientUtils.sendHttpPostJson(url,json.toJSONString());
        String token = JSON.parseObject(gettoken).getString("token");
        String expiration = JSON.parseObject(gettoken).getString("expiration");

        HttpSession session = request.getSession();
        session.setAttribute("token",token);
        session.setAttribute("expiration",expiration);

    }
}
