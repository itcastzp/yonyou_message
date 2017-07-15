package com.yonyou.message.po;

import org.apache.http.ssl.PrivateKeyStrategy;

/**
 * Created by Administrator on 2017/6/24.
 */
public class ErMessageDetail {
    private int mes_d_id;
    private String messageid;
    private String touser;
    private String isread;

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public int getMes_d_id() {
        return mes_d_id;
    }

    public void setMes_d_id(int mes_d_id) {
        this.mes_d_id = mes_d_id;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
}
