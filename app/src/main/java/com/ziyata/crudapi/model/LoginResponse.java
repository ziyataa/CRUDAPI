package com.ziyata.crudapi.model;

//TODO 2

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    //Membuat variable untuk menanpung nilai yang diberikan oleh JSON
    @SerializedName("token")
    private String token;

    // Membuat GETTER
    public String getToken() {
        return token;
    }
}
