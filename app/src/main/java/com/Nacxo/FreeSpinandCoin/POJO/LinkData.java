
package com.Nacxo.FreeSpinandCoin.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("link_details")
    @Expose
    private List<LinkDetail> linkDetails = null;
    @SerializedName("token")
    @Expose
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LinkDetail> getLinkDetails() {
        return linkDetails;
    }

    public void setLinkDetails(List<LinkDetail> linkDetails) {
        this.linkDetails = linkDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
