package com.example.reworksample.model.model;

import com.example.reworksample.model.model.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlexibleDeskRespone {
    @SerializedName("status")
    @Expose
    private Boolean staus;
    @SerializedName("message_code")
    @Expose
    private Object message_code;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStaus() {
        return staus;
    }

    public void setStaus(Boolean staus) {
        this.staus = staus;
    }

    public Object getMessage_code() {
        return message_code;
    }

    public void setMessage_code(Object message_code) {
        this.message_code = message_code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
