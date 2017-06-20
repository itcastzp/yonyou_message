package com.yonyou.message.po;

public class ErMesgboard {
    private String pkMesgid;

    private String pkDjbh;

    private String usercode;

    private String username;

    private String mesg;

    private String createtime;

    private String touser;

    public ErMesgboard(String pkMesgid, String pkDjbh, String usercode, String username, String mesg, String createtime, String touser) {
        this.pkMesgid = pkMesgid;
        this.pkDjbh = pkDjbh;
        this.usercode = usercode;
        this.username = username;
        this.mesg = mesg;
        this.createtime = createtime;
        this.touser = touser;
    }

    public ErMesgboard() {
        super();
    }

    public String getPkMesgid() {
        return pkMesgid;
    }

    public void setPkMesgid(String pkMesgid) {
        this.pkMesgid = pkMesgid == null ? null : pkMesgid.trim();
    }

    public String getPkDjbh() {
        return pkDjbh;
    }

    public void setPkDjbh(String pkDjbh) {
        this.pkDjbh = pkDjbh == null ? null : pkDjbh.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg == null ? null : mesg.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser == null ? null : touser.trim();
    }
}