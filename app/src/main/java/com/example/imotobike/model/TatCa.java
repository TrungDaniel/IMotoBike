package com.example.imotobike.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TatCa {

    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("result")
    @Expose
    public List<TatCaResult> tatCaResults = null;
    @SerializedName("message")
    @Expose
    public String message;

}