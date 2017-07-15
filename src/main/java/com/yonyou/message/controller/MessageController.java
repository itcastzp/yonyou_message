package com.yonyou.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.po.ErUser;
import com.yonyou.message.service.IErMessageService;
import com.yonyou.message.service.IErUserService;
import com.yonyou.message.utils.HttpClientUtils;
import com.yonyou.message.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
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
@RequestMapping(value = "/message")
public class MessageController {

    private static Logger log = LoggerFactory.getLogger(MessageController.class);
    /**
     * 得到历史流程消息
     */
    @Resource
    private IErMessageService erMessageService;
    @Resource
    private IErUserService erUserService;
    @RequestMapping(value = "/getHistoryMessages", method = RequestMethod.POST)
    @ResponseBody
    public void getHistoryMessages(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            String pk_djh = json.getString("billPk");
            String currentuser = json.getString("currentuser");
            if (pk_djh == null || pk_djh.equals("")) {
                data.put("message", "单据id接受失败！");
                data.put("code", "1");
            }
            List<ErMesgboard> list = erMessageService.getHistoryMessages(pk_djh,currentuser);
            for (ErMesgboard er : list) {
                obj = (JSONObject) JSONObject.toJSON(er);
                jsonarr.add(obj);
            }
            if (list != null && list.size() > 0) {
                ErUser user = erUserService.selectByPrimaryKey(currentuser);
                //将未读消息中的isread更新，下次不显示为未读
                for (ErMesgboard message : list) {
                    String isread = message.getIsread();
                    if(isread !=null && !isread.equals("")){
                        message.setIsread(isread.replace(",["+user.getId()+"]",""));
                    }

                }
                int a = erMessageService.updateUnReadMessage(user.getId(),list);
            }

            data.put("code", "0");
            data.put("history", jsonarr);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }

    /**
     * 发送消息
     * @param json
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public void sendMessage(@RequestBody JSONObject json,HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        try {
            String pk_djh = json.getString("pk_djh");
            String senderId = json.getString("sender");
            String touser = json.getString("touser");
            String content = json.getString("content");
            //设置留言时间

            String sendTime = TimeUtils.getCurrentTime();

            ErMesgboard erMesgboard = JSONObject.parseObject(JSONObject.toJSONString(json),ErMesgboard.class);
            erMesgboard.setSendTime(sendTime);
            if (erMesgboard.getTouser() != null && !erMesgboard.getTouser().equals("")) {
                erMesgboard.setIsall("1");
                erMesgboard.setIsread(erMesgboard.getTouser());
            } else {
                erMesgboard.setIsall("0");
            }
            int id = erMessageService.sendMessage(erMesgboard);
            data.put("data",JSONObject.toJSON(erMesgboard));
            data.put("code", "0");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }
    }

    /**
     * 撤回消息
     * @param jsonObject
     * @param request
     * @param response
     * @throws IOException
     */
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
                long min = TimeUtils.betweenTime(erMesgboard.getSendTime());
                if (min > 2) {
                    data.put("code","1");
                    data.put("message", "留言超过2分钟，不允许删除!");
                }else{
                    erMessageService.callBackMessage(message_id);
                    data.put("code","0");
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            data.put("code","1");
            data.put("message", e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }


    @RequestMapping(value = "/getMessageMembers", method = RequestMethod.POST)
    public @ResponseBody void getMessageMembers(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            String dj_id = jsonObject.getString("objectId");
            param.put("dj_id", dj_id);
//            String message = HttpClientUtils.sendHttpPostJson("http://127.0.0.1:1228/iwebap/message/getMessageMembers",jsonObject.toJSONString());
            String a = HttpClientUtils.sendHttpGet("http://127.0.0.1:1228/iwebap/message/getMessageMembers?dj_id='sadfdsf'");
            data.put("users","aa" );
            data.put("groups","");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }

    @RequestMapping(value = "/selectByUserPkdjh", method = RequestMethod.POST)
    public @ResponseBody void selectByUserPkdjh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            String dj_id = request.getParameter("pkdjbh");
            String userid = request.getParameter("userid");
            //得到未读消息
            List<ErMesgboard> list = erMessageService.selectByUserPkdjh(userid, dj_id);
            if (list != null && list.size() > 0) {
                data.put("messages", list.size());

            } else {
                data.put("messages", "0");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            log.debug("debug:"+e.getMessage());

            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }

    @RequestMapping(value = "/unReadMessageByIds", method = RequestMethod.POST)
    public @ResponseBody void unReadMessageByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            String dj_id = request.getParameter("row");
            String userid = request.getParameter("userid");
            JSONArray billjson = JSON.parseArray(dj_id);
            if(billjson != null && billjson.size() >0){
                List<ErMesgboard> list = erMessageService.selectByUserBillIds(userid, billjson);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < billjson.size(); i++) {
                       JSONObject obj = (JSONObject) billjson.get(i);
                        for (ErMesgboard mesgboard : list) {
                            if (mesgboard.getBillPk().equals(obj.getString("billid"))) {
                                obj.put("unread", "1");
                            }
                        }
                    }
                }
                data.put("rows", billjson);
            }


        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }
}
