package com.yonyou.message.po;

public class ErMesgboard {
    private String messageId;

    public String getBillPk() {
        return billPk;
    }

    public void setBillPk(String billPk) {
        this.billPk = billPk;
    }

    private String billPk;

    private String senderId;

    private String senderName;

    private String content;

    private String sendTime;

    private String touser;
    private String isall;
    private String isread;

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getIsall() {
        return isall;
    }

    public void setIsall(String isall) {
        this.isall = isall;
    }

    public ErMesgboard(String pkMesgid, String billPk, String usercode, String username, String content, String createtime, String touser) {
        this.messageId = pkMesgid;
        this.billPk = billPk;
        this.senderId = usercode;
        this.senderName = username;
        this.content = content;
        this.sendTime = createtime;
        this.touser = touser;
    }

    public ErMesgboard() {
        super();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser == null ? null : touser.trim();
    }
}