package com.example.imotobike.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TatCaResult {

    @SerializedName("materialID")
    @Expose
    public String materialID;
    @SerializedName("materialName")
    @Expose
    public String materialName;
    @SerializedName("unit")
    @Expose
    public String unit;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("maxKm")
    @Expose
    public Integer maxKm;

}