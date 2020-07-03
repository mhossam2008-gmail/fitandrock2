package com.mhossam.rocknfit.model;

import com.google.gson.annotations.SerializedName;

public class BasicRequest {
    @SerializedName("Action")
    private String action;
    @SerializedName("ApiUser")
    private String apiUser;
    @SerializedName("ApiPass")
    private String apiPassword;
    @SerializedName("AccountID")
    private Integer accountID;

    public BasicRequest(String action, String apiUser, String apiPassword, Integer accountID) {
        this.action = action;
        this.apiUser = apiUser;
        this.apiPassword = apiPassword;
        this.accountID = accountID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApiUser() {
        return apiUser;
    }

    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }
}
