package com.ziyata.crudapi.model;
// TODO 10
import com.google.gson.annotations.SerializedName;

public class SingelUserResponse {
    @SerializedName("data")
    private UserData data;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
