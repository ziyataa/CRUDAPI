package com.ziyata.crudapi.model;

// TODO 1
import com.google.gson.annotations.SerializedName;

public class LoginBody {

    //Mengkoneksikan variable JSON dengan java
    @SerializedName("email")
    // Membuat penampung untuk menerima/megirim data ke JSON
    private String email;

    @SerializedName("password")
    private String password;

    // LANGKAH SELANJUTNYA BIKIN SETTER
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
