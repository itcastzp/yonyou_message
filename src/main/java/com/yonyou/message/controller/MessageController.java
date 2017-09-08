package com.yonyou.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.config.Config;
import com.yonyou.message.po.ErMesgboard;
import com.yonyou.message.po.ErUser;
import com.yonyou.message.po.MessageGroup;
import com.yonyou.message.service.IErMessageService;
import com.yonyou.message.service.IErUserService;
import com.yonyou.message.utils.HttpClientUtils;
import com.yonyou.message.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Autowired
    private Config config;

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
            List<ErMesgboard> list = erMessageService.getHistoryMessages(pk_djh, currentuser);
            List<ErMesgboard> updateUnRead = new ArrayList<ErMesgboard>();
            for (ErMesgboard er : list) {
                obj = (JSONObject) JSONObject.toJSON(er);
                jsonarr.add(obj);
            }
            data.put("code", "0");
            data.put("history", jsonarr);
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
     * 发送消息
     *
     * @param json
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public void sendMessage(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        try {
            String pk_djh = json.getString("pk_djh");
            String senderId = json.getString("sender");
            String touser = json.getString("touser");
            String content = json.getString("content");
            //设置留言时间

            String sendTime = TimeUtils.getCurrentTime();

            ErMesgboard erMesgboard = JSONObject.parseObject(JSONObject.toJSONString(json), ErMesgboard.class);
            erMesgboard.setSendTime(sendTime);
            if (erMesgboard.getTouser() != null && !erMesgboard.getTouser().equals("")) {
                erMesgboard.setIsall("1");
                erMesgboard.setIsread(erMesgboard.getTouser());
            } else {
                erMesgboard.setIsall("0");
            }
            int id = erMessageService.sendMessage(erMesgboard);
            data.put("data", JSONObject.toJSON(erMesgboard));
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
     * 发送消息
     *
     * @param json
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sendMessageByPhone", method = RequestMethod.POST)
    @ResponseBody
    public void sendMessageByPhone(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        try {
            //设置留言时间
            String sendTime = TimeUtils.getCurrentTime();
            ErMesgboard erMesgboard = JSONObject.parseObject(JSONObject.toJSONString(json), ErMesgboard.class);
            erMesgboard.setSendTime(sendTime);
            if (erMesgboard.getTouser() != null && !erMesgboard.getTouser().equals("")) {
                erMesgboard.setIsall("1");
                erMesgboard.setIsread(erMesgboard.getTouser());
            } else {
                erMesgboard.setIsall("0");
            }
            int id = erMessageService.sendMessage(erMesgboard);
            Map<String, String> paraMap = new ConcurrentHashMap<String, String>();
            List<String> userIds = new ArrayList<String>();
            paraMap.put("url", "sss");
//            Map<String, Object> reMap = pushService.pushMessageToUserIds(erMesgboard.getContent(), paraMap, userIds);
//            data.put("data", JSONObject.toJSON(erMesgboard));
//            data.put("code", "0");
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
     *
     * @param jsonObject
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/callBackMessage", method = RequestMethod.POST)
    public
    @ResponseBody
    void callBackMessage(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        try {
            String message_id = jsonObject.getString("messageId");
            if (message_id == null || message_id.equals("")) {
                data.put("message", "消息id接受失败！");
                data.put("code", "0");
//               throw new Exception("消息id接受失败！");
            }
            ErMesgboard erMesgboard = erMessageService.selectByPrimaryKey(message_id);
            if (erMesgboard != null) {
                long min = TimeUtils.betweenTime(erMesgboard.getSendTime());
                if (min > 2) {
                    data.put("code", "1");
                    data.put("message", "留言超过2分钟，不允许删除!");
                } else {
                    erMessageService.callBackMessage(message_id);
                    data.put("code", "0");
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            data.put("code", "1");
            data.put("message", e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }

    /**
     * 更新消息为已读
     *
     * @param jsonObject
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/updateUnReadMessage", method = RequestMethod.POST)
    public
    @ResponseBody
    void updateUnReadMessage(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            String dj_id = jsonObject.getString("billPk");
            String currentuser = jsonObject.getString("currentuser");
            //得到未读消息
            List<ErMesgboard> list = erMessageService.selectByUserPkdjh(currentuser, dj_id);
            if (list != null && list.size() > 0) {
                ErUser user = erUserService.selectByPrimaryKey(currentuser);
                //将未读消息中的isread更新，下次不显示为未读
                for (ErMesgboard message : list) {
                    String isread = message.getIsread();
                    if (isread != null && !isread.equals("")) {
                        message.setIsread(isread.replace(",[" + user.getId() + "]", ""));
                    }
                }
                erMessageService.updateUnReadMessage(user.getId(), list);
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

    /**
     * 根据用户单据id得到是否有未读消息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getUnreadByUserBillPk", method = RequestMethod.POST)
    public
    @ResponseBody
    void selectByUserPkdjh(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            String dj_id = json.getString("billPk");
            String userid = json.getString("currentuser");
            //得到未读消息
            List<ErMesgboard> list = erMessageService.selectByUserPkdjh(userid, dj_id);
            if (list != null && list.size() > 0) {
                data.put("messageSize", list.size());
            } else {
                data.put("messageSize", "0");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.debug("debug:" + e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data.toString());
            response.flushBuffer();
        }

    }

    /**
     * 门户列表根据id得到是否有未读
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/unReadMessageByIds", method = RequestMethod.POST)
    public
    @ResponseBody
    void unReadMessageByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            String dj_id = request.getParameter("row");
            String userid = request.getParameter("userid");
            JSONArray billjson = JSON.parseArray(dj_id);
            if (billjson != null && billjson.size() > 0) {
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

    @RequestMapping(value = "/billMembers", method = RequestMethod.POST)
    public
    @ResponseBody
    void BillMembers(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String billpk = jsonObject.getString("process_id");
        List<ErUser> userlist = null;
        ConcurrentLinkedQueue<String> userids = new ConcurrentLinkedQueue<String>();
        JSONObject rejson = new JSONObject();
        JSONObject groupJson = new JSONObject();
        groupJson.put("etpId", config.eptId);
        groupJson.put("appId", config.appId);
        try {
            if (jsonObject.getJSONObject("process_info") !=null && jsonObject.getJSONObject("process_info").getJSONObject("users") !=null && jsonObject.getJSONObject("process_info").getJSONObject("users").size()>0) {
                JSONArray history = jsonObject.getJSONObject("process_info").getJSONObject("users").getJSONArray("history");
                JSONArray todo = jsonObject.getJSONObject("process_info").getJSONObject("users").getJSONArray("todo");
                userlist = JSON.parseArray(history.toJSONString(), ErUser.class);
                userlist.addAll(JSON.parseArray(todo.toJSONString(), ErUser.class));
                if (userlist != null && userlist.size() > 0) {
                    for (ErUser user : userlist) {
                        userids.add(user.getId());
                    }
                }
            }
            String token = "";
            HttpSession session = request.getSession();
            if (session != null && session.getAttribute("expiration") != null && !session.getAttribute("expiration").equals("")) {
                if (Long.parseLong(session.getAttribute("expiration").toString()) > System.currentTimeMillis()) {
                    token = session.getAttribute("token").toString();
                }
            } else {
                ConcurrentHashMap<String, String> newtoken = getToken(config,jsonObject.getJSONObject("current_user").getString("id"));
                token = newtoken.get("token");
                session.setAttribute("token",newtoken.get("token"));
                session.setAttribute("expiration",newtoken.get("expiration"));
            }

            MessageGroup group = erMessageService.getGroupByBillPk(billpk,"");
            if (group != null && !group.getGrouppk().equals("")) {//获取群成员
                jsonObject.put("groupPk", group.getGrouppk());
                String reMessage = HttpClientUtils.sendHttpGet(config.baseUrl+"/remote/room/allmember?token="+token+"&name="+group.getGrouppk()+"."+config.appId+"."+config.eptId);
                if (reMessage.contains("@im.yyuap.com")) {//返回成功
                    String[] members = reMessage.split(",");
                    if (userlist != null && userlist.size() > 0) {
                        for (ErUser user : userlist) {
                            for(int i=0;i<members.length;i++) {
                                if(userids.contains(members[i])){
                                    userids.remove(members[i]);//移除已经存在的人员
                                }
                            }
                        }
                        //将剩余人员插入群组
                        groupJson.put("name", group.getGrouppk());
                        groupJson.put("operand", userids.toArray());
                        HttpClientUtils.sendHttpPut(config.baseUrl+"remote/room/member/add?token"+token,groupJson.toJSONString());
                    }
                }
            } else {//创建群，并加入成员
                if (userids.size() > 0) {//有流程人员信息
                    groupJson.put("operator", userids.toArray(new String[userids.size()])[0]);
                    groupJson.put("operand", userids.toArray(new String[userids.size()]));
                } else {//无,将当前登录人作为群主
                    groupJson.put("operator", jsonObject.getJSONObject("current_user").getString("id"));
                    groupJson.put("operand", jsonObject.getJSONObject("current_user").getString("id"));
                }

                groupJson.put("naturalLanguageName", billpk);
                String reMessage = HttpClientUtils.sendHttpPostJson(config.baseUrl+"remote/room/create?token="+token,groupJson.toJSONString());
                if (reMessage.contains(config.appId+"."+config.eptId)) {//创建成功
                    MessageGroup ingroup = new MessageGroup();
                    ingroup.setBillpk(billpk);
                    ingroup.setGrouppk(reMessage.substring(0,reMessage.indexOf(".")));
                    ingroup.setCreatetime(TimeUtils.getCurrentTime());
                    ingroup = erMessageService.insertGroup(ingroup);
                    jsonObject.put("groupPk", ingroup.getGrouppk());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toString());
            response.flushBuffer();
        }

    }

    @RequestMapping(value = "/BillMessage", method = RequestMethod.POST)
    public
    @ResponseBody
    void BillMessage4YBZ(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String billpk = json.getString("billId");
        String billtype = json.getString("billType");
        String tenant_id = json.getString("tenant_id");
        String billurl = json.getString("billUrl");
        List<ErUser> userlist = null;
        ConcurrentLinkedQueue<String> userids = new ConcurrentLinkedQueue<String>();
        JSONObject rejson = new JSONObject();
        JSONObject groupJson = new JSONObject();
        JSONObject imjson = new JSONObject();
        imjson.put("etpId", config.eptId);
        imjson.put("appId", config.appId);
        groupJson.put("etpId", config.eptId);
        groupJson.put("appId", config.appId);
        JSONObject para4ybz = new JSONObject();
        JSONObject json4ybz = null;
        para4ybz.put("billId", json.getString("billId"));
        para4ybz.put("tenantId", tenant_id);
        para4ybz.put("usercode", json.getString("usercode"));
        String[] operand = null;
        String re4ybz = HttpClientUtils.sendHttpPostJson(billurl,para4ybz.toJSONString() );

        try {
            if (re4ybz.contains("操作成功")) {
                json4ybz = JSON.parseObject(re4ybz);
                if (json4ybz.getJSONObject("jsonDatas") != null && json4ybz.getJSONObject("jsonDatas").getJSONObject("process_info").getJSONObject("users") != null && json4ybz.getJSONObject("jsonDatas").getJSONObject("process_info").getJSONObject("users").size() > 0) {
                    JSONArray history = json4ybz.getJSONObject("jsonDatas").getJSONObject("process_info").getJSONObject("users").getJSONArray("history");
                    JSONArray todo = json4ybz.getJSONObject("jsonDatas").getJSONObject("process_info").getJSONObject("users").getJSONArray("todo");
                    userlist = JSON.parseArray(history.toJSONString(), ErUser.class);
                    userlist.addAll(JSON.parseArray(todo.toJSONString(), ErUser.class));
                    if (userlist != null && userlist.size() > 0) {
                        for (ErUser user : userlist) {
                            userids.add(user.getTenant_id()+"_"+user.getId());
                        }
                    }
                }
                String descUrl = erMessageService.getDescUrlByTenantId(tenant_id);
                if(descUrl != null && !descUrl.equals("")){
                    String desc = HttpClientUtils.sendHttpPost(descUrl+"?billid="+billpk+"&billtype="+billtype+"&usercode="+json.getString("usercode"));//请求nc获取单据描述
                    if(JSON.parseObject(desc).getString("code").equals("0")){
                        json4ybz.getJSONObject("jsonDatas").getJSONObject("process_info").put("desc",JSON.parseObject(JSON.parseObject(desc).getString("desc") ));
                    }
                }

                String token = erMessageService.getServerToken();
                String userToken = erMessageService.getUserToken(json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("tenant_id") + "_" + json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("id"));
                imjson.put("userId",json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("tenant_id")+"_"+json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("id"));
                imjson.put("userToken", userToken);
                MessageGroup group = erMessageService.getGroupByBillPk(billpk,tenant_id);
                if (group != null && !group.getGrouppk().equals("")) {//获取群成员
                    String reMessage = HttpClientUtils.sendHttpGet(config.baseUrl + "/remote/room/allmember?token=" + token + "&name=" + group.getGrouppk() + "." + config.appId + "." + config.eptId);
                    if (reMessage.contains("@im.yyuap.com")) {//返回成功
                        String[] members = reMessage.split(",");
                        if (userlist != null && userlist.size() > 0) {
                            for (ErUser user : userlist) {
                                for (int i = 0; i < members.length; i++) {
                                    if (user.getId().equals("") || members[i].contains(user.getId())) {
                                        userids.remove(user.getId());//移除已经存在的人员
                                    }
                                }
                            }
                            //将剩余人员插入群组
                            operand = userids.toArray(new String[userids.size()]);
                            groupJson.put("name", group.getGrouppk());
                            groupJson.put("operand", operand);
                            HttpClientUtils.sendHttpPut(config.baseUrl + "remote/room/member/add?token=" + token, groupJson.toJSONString());
                            //群組踢人
                        }
                    }
                    imjson.put("groupId", group.getGrouppk());
                } else {//创建群，并加入成员
                    if (userids.size() > 0) {//有流程人员信息
                        operand = userids.toArray(new String[userids.size()]);
                        groupJson.put("operator", json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("tenant_id")+"_"+json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("id"));
                        groupJson.put("operand", operand);
                    } else {//无,将当前登录人作为群主
                        groupJson.put("operator", json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("tenant_id")+"_"+json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("id"));
                        groupJson.put("operand", json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("tenant_id")+"_"+json4ybz.getJSONObject("jsonDatas").getJSONObject("current_user").getString("id"));
                    }

                    groupJson.put("naturalLanguageName", billpk);
                    String reMessage = HttpClientUtils.sendHttpPostJson(config.baseUrl + "remote/room/create?token=" + token, groupJson.toJSONString());
                    if (reMessage.contains(config.appId + "." + config.eptId)) {//创建成功
                        MessageGroup ingroup = new MessageGroup();
                        ingroup.setBillpk(billpk);
                        ingroup.setGrouppk(reMessage.substring(0, reMessage.indexOf(".")));
                        ingroup.setCreatetime(TimeUtils.getCurrentTime());
                        ingroup.setTenant_id(tenant_id);
                        ingroup = erMessageService.insertGroup(ingroup);
                        imjson.put("groupId", ingroup.getGrouppk());
                    }
                }
                rejson.putAll(json4ybz);
                rejson.put("imInfo",imjson);
                rejson.put("code", "0");
            } else {
                String mes = "时间:"+TimeUtils.getCurrentTime()+":url:"+billurl+"mess:"+re4ybz.toString();
                log.error(mes);
                rejson.put("code", "1");
                rejson.put("message", "请求友报账数据失败");
            }
        } catch (Exception e) {
            rejson.put("code", "1");
            rejson.put("message", e.getMessage()==null?e.getCause().getMessage():e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rejson.toString());
            response.flushBuffer();
        }

    }
    @RequestMapping(value = "/addDescUrl", method = RequestMethod.GET)
    public @ResponseBody void insertDescUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String tenant_id = request.getParameter("tenant_id");
        String url = request.getParameter("url");
        JSONObject rejson = new JSONObject();
        try {
            String descurl = erMessageService.getDescUrlByTenantId(tenant_id);
            if(null == descurl || descurl.equals("")){
                erMessageService.insertDescUrl(tenant_id,url);
                rejson.put("code", "0");
                rejson.put("message", "插入成功");
            }else {
                rejson.put("code", "1");
                rejson.put("message", "已经存在");
            }

        } catch (Exception e) {
            rejson.put("code", "1");
            rejson.put("message", e.getMessage()==null?e.getCause().getMessage():e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rejson.toString());
            response.flushBuffer();
        }

    }

    @RequestMapping(value = "/getBillPks", method = RequestMethod.POST)
    public @ResponseBody void getBillPk(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String tenant_id = jsonObject.getString("tenant_id");
        JSONObject rejson = new JSONObject();
        try {
            if (tenant_id.equals("")) {
                rejson.put("code", "1");
                rejson.put("message", "参数错误");
            } else {
                List<MessageGroup> group = erMessageService.getBillPk(tenant_id,jsonObject.getJSONArray("grouppks"));
                JSONArray arr = JSON.parseArray(JSON.toJSONString(group));
                rejson.put("group", arr);
            }

            rejson.put("code", "0");

        } catch (Exception e) {
            rejson.put("code", "1");
            rejson.put("message", e.getMessage()==null?e.getCause().getMessage():e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rejson.toString());
            response.flushBuffer();
        }

    }
    @RequestMapping(value = "/getUserUnReadMessageCount", method = RequestMethod.GET)
    public @ResponseBody void getUserUnReadMessageCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenant_id = request.getParameter("tenant_id");
        String userid = request.getParameter("userid");
        String resource = request.getParameter("resource");
        String billid = request.getParameter("billId");
        JSONObject rejson = new JSONObject();
        String url = "https://im.yyuap.com/sysadmin/rest/version/";
        ConcurrentSkipListSet set = new ConcurrentSkipListSet();
        set.add("android");
        set.add("ios");
        set.add("web");
        set.add("pc");
        try {
            if (tenant_id.equals("") || userid.equals("") || resource.equals("") || !set.contains(resource)) {
                rejson.put("code", "1");
                rejson.put("message", "参数错误!");
            } else {
                url += config.eptId + "/" + config.appId + "/" + tenant_id + "_" + userid + "?token=" + erMessageService.getUserToken(tenant_id+"_"+userid) + "&resource=" + resource+"-v1.1";
                String grouppk = erMessageService.getGroupPkByBillId(billid);
                if (grouppk == null || grouppk.equals("")) {
                    rejson.put("message", "未找到单据id");
                } else {
                    String count = HttpClientUtils.sendHttpGet(url);
                    JSONObject message = JSON.parseObject(count);
                    Map<String,Integer> mess_Size = new ConcurrentHashMap<String, Integer>();
                    AtomicInteger size = new AtomicInteger(0);
                    if(message.getJSONArray("packets") != null && message.getJSONArray("packets").size()>0){
                        JSONArray packets = message.getJSONArray("packets");
                        for (Iterator ite = packets.iterator() ; ite.hasNext();) {
                            JSONObject packet = (JSONObject) ite.next();
                            JSONObject from = JSON.parseObject(packet.getString("body"));
                            String fromstr = from.getString("from");
                            String grouppk_im = fromstr.substring(0, fromstr.indexOf("."));
                            if (grouppk_im.equals(grouppk)) {
                                size.addAndGet(1);
                            }
                        }
                    }
                    rejson.put("size", size);
                }
                rejson.put("code", "0");
            }
        }catch (Exception e){
            rejson.put("code", "1");
            rejson.put("message", e.getMessage());
        }finally{
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rejson.toString());
            response.flushBuffer();
        }
    }
    @RequestMapping(value = "/getUserToken", method = RequestMethod.GET)
    public void getUserToken(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenant_id = request.getParameter("tenant_id");
        String userid = request.getParameter("userid");
        JSONObject rejson = new JSONObject();
        try {
            rejson.put("appId",config.appId);
            rejson.put("etpId",config.eptId);
            rejson.put("userId",tenant_id+"_"+userid);
            rejson.put("userToken",erMessageService.getUserToken(tenant_id+"_"+userid));
        } catch (Exception e) {

        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rejson.toString());
            response.flushBuffer();
        }
    }
//    @RequestMapping(value = "/getUserToken", method = RequestMethod.GET)
//    public void getUserUnReadMessageDetail(){
//
//    }
    /**
     * 获取新token
     * @param config
     * @return
     */
    public static ConcurrentHashMap<String, String> getToken(Config config,String currentUser) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String, String>();
        String url = config.baseUrl+ config.eptId+"/"+config.appId+"/token";
        JSONObject json = new JSONObject();
        json.put("clientId", config.clientId);
        json.put("clientSecret", config.clientSecret);
        String getServertoken = HttpClientUtils.sendHttpPostJson(url,json.toJSONString());
        json.put("username", currentUser);
        String userToken = HttpClientUtils.sendHttpPostJson(url,json.toJSONString());
        String token = JSON.parseObject(getServertoken).getString("token");
        String expiration = JSON.parseObject(getServertoken).getString("expiration");
        map.put("token", token);
        map.put("expiration", expiration);
        map.put("usertoken",JSON.parseObject(userToken).getString("token"));
        return map;
    }
}
