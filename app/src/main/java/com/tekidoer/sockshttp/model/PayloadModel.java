package com.tekidoer.sockshttp.model;

public class PayloadModel {
    String sSname,sSinfo,flags;
    public String getServerName() {
        return sSname;
    }
    public void setServerName(String name) {
        this.sSname = name;
    }

    public String getServerInfo() {
        return sSinfo;
    }
    public void setServerInfo(String info) {
        this.sSinfo = info;
    }
    
    public void setServerflag(String flag) {
        this.flags = flag;
    }
    public String getServerflag() {
        return flags;
    }
}
