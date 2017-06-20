package com.yonyou.message.po;

public class ErUser {
    private String userid;

    private String busnessId;

    private String usercode;

    private String userName;

    private String userPhone;

    private String userEmail;

    public ErUser(String userid, String busnessId, String usercode, String userName, String userPhone, String userEmail) {
        this.userid = userid;
        this.busnessId = busnessId;
        this.usercode = usercode;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public ErUser() {
        super();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getBusnessId() {
        return busnessId;
    }

    public void setBusnessId(String busnessId) {
        this.busnessId = busnessId == null ? null : busnessId.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }
}