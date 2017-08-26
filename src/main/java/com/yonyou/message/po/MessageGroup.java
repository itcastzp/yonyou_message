package com.yonyou.message.po;


/**
 * Created by Administrator on 2017/6/24.
 */
public class MessageGroup {
    private int id;
    private String billpk;
    private String grouppk;
    private String tenant_id;

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getBillpk() {
        return billpk;
    }

    public void setBillpk(String billpk) {
        this.billpk = billpk;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getGrouppk() {
        return grouppk;
    }

    public void setGrouppk(String grouppk) {
        this.grouppk = grouppk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String createtime;


}
