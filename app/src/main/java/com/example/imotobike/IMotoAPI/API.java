package com.example.imotobike.IMotoAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("Service/Login")
    Call<ResponseBody> GetLogin(@Body Object object);

    @POST("Service/GetPriceListMaterial")
    Call<ResponseBody> getAll(@Body Object object);

    @POST("Service/GetPriceListMaterialFollow")
    Call<ResponseBody> getThuongXuyen(@Body Object object);

}
