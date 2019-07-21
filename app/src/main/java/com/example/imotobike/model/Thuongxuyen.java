package com.example.imotobike.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thuongxuyen {

    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("result")
    @Expose
    public List<ThuongXuyenResult> thuongXuyenResults = null;
    @SerializedName("message")
    @Expose
    public String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ThuongXuyenResult> getThuongXuyenResults() {
        return thuongXuyenResults;
    }

    public void setThuongXuyenResults(List<ThuongXuyenResult> thuongXuyenResults) {
        this.thuongXuyenResults = thuongXuyenResults;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}