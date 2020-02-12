package com.laimilife.frame.account.vo;

public class TbBaseUser {

    private String id; // 用户ID
    
    private String account; // 登录账号
    
    private String userName; // 用户名
    
     private String roleId; // 角色ID
     
     private String shopId; // 门店ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
     
     
}
