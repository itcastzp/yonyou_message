package com.yonyou.message.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.service.IErMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
@Controller

public class MessageController {

    @Resource
    private IErMessageService erMessageService;
    @RequestMapping(value = "/getHistorymessage", method = RequestMethod.POST)
    @ResponseBody
    public void getHistoryMessages(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            String pk_djh = json.getString("pk_djh");
            if (pk_djh == null || pk_djh.equals("")) {
                data.put("message", "单据id接受失败！");
                data.put("code", "1");
                throw new Exception("单据id接受失败！");
            }
            List<ErMesgboard> list = erMessageService.getHistoryMessages(pk_djh);
            for (ErMesgboard er : list) {
                obj = (JSONObject) JSONObject.toJSON(er);
                jsonarr.add(obj);
            }
            data.put("code", "0");
            data.put("history", jsonarr);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public void sendMessage(@RequestBody JSONObject json,HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        try {
            String pk_djh = json.getString("pk_djh");
            String sender = json.getString("sender");
            String touser = json.getString("touser");
            String content = json.getString("content");
            //设置留言时间
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(d);

            ErMesgboard erMesgboard = JSONObject.parseObject(JSONObject.toJSONString(json),ErMesgboard.class);
            erMesgboard.setCreatetime(createTime);
            int id = erMessageService.sendMessage(erMesgboard);
            data.put("data",JSONObject.toJSONString(erMesgboard));
            data.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }
    }
    @RequestMapping(value = "/callBackMessage", method = RequestMethod.POST)

    public @ResponseBody void callBackMessage(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        try {
            String message_id = jsonObject.getString("messageId");
            if (message_id == null || message_id.equals("")) {
                data.put("message", "消息id接受失败！");
                data.put("code","0");
//               throw new Exception("消息id接受失败！");
            }
            ErMesgboard erMesgboard = erMessageService.selectByPrimaryKey(message_id);
            if (erMesgboard != null) {
                long min = betweenTime(erMesgboard.getCreatetime());
                if (min > 2) {
                    data.put("code","0");
                    data.put("message", "留言超过2分钟，不允许删除!");
                }else{
                    erMessageService.callBackMessage(message_id);
                    data.put("code","1");
                }
            }

        } catch (Exception e) {
            data.put("code","0");
            data.put("message", e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }

    private long betweenTime(String beginTime) throws Exception{
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = sdf.format(d);
        long nd = 1000*24*60*60;//毫秒数
        long nh = 1000*60*60;//毫秒数
        long nm = 1000*60;//钟毫秒数
        //获两间毫秒间差异
        long time1 = sdf.parse(endTime).getTime();
        long time2 = sdf.parse(beginTime).getTime();
        long diff;
        diff = time1 - time2;
        long min = diff%nd%nh/nm;//计算差少钟
        return min;
    }
}
