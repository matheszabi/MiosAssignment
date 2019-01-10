package com.mios.testapplication.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceRetrofit {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of DeviceList
    */
    @GET("retrofit/json_object.json")
    Call<DeviceList> getMyJSON();
}
