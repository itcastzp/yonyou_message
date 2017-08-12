package com.yonyou.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.po.ErUser;
import com.yonyou.message.service.IErUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/6/24.
 */
@Controller
@RequestMapping(value = "/message")
public class UsersController {
    private static Logger log = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private IErUserService iErUserService;
    @RequestMapping(value = "members",method = RequestMethod.POST)
    public @ResponseBody void InsertMembers(@RequestBody JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        List<ErUser> relist = new ArrayList<ErUser>();
        try {
            if (jsonObj != null) {
                List<ErUser> insertIist = new ArrayList<ErUser>();
                List<ErUser> user_extis = null;
                if (jsonObj.getJSONObject("users") !=null) {
                    JSONArray history = jsonObj.getJSONObject("users").getJSONArray("history");
                    JSONArray todo = jsonObj.getJSONObject("users").getJSONArray("todo");
                    List<ErUser> list =  JSON.parseArray(history.toJSONString(), ErUser.class);
                    list.addAll(JSON.parseArray(todo.toJSONString(), ErUser.class));
                    if (list != null && list.size() > 0) {
                        user_extis = iErUserService.selectUserByIds(list);
                        if (user_extis != null && user_extis.size() > 0) {
                            Map<String, String> user_have = new ConcurrentHashMap<String, String>();
                            for (ErUser user : user_extis) {
                                user_have.put(user.getId(),user.getUser_name());
                            }
                            for (ErUser user : list) {
                                if (user_have.get(user.getId()) == null) {
                                    insertIist.add(user);
                                }
                            }
                            relist.addAll(user_extis);
                        } else {
                            insertIist = list;
                        }

                        if (insertIist != null && insertIist.size() > 0) {
                            int returnUserList = iErUserService.inserUsers(insertIist);
                        }
                    }

                    relist.addAll(insertIist);
                }
                json.put("users", JSONObject.toJSON(relist));
                json.put("code", "0");
            }

        } catch (Exception e) {
            json.put("code","1");
            json.put("message",e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toJSONString());
            response.flushBuffer();
        }
    }
}
