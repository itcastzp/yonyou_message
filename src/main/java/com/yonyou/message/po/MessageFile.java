package com.yonyou.message.po;

/**
 * Created by Administrator on 2017/6/22.
 */
public class MessageFile {
    private String file_id;
    private String file_name;
    private String file_path;
    private String messageid;
    private String file_uploader;
    private String file_time;

    public String getFile_time() {
        return file_time;
    }

    public void setFile_time(String file_time) {
        this.file_time = file_time;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getFile_uploader() {
        return file_uploader;
    }

    public void setFile_uploader(String file_uploader) {
        this.file_uploader = file_uploader;
    }



}
