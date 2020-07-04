package com.mhossam.rocknfit.API;

import com.mhossam.rocknfit.model.AccountInfo;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.Post;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, AccountInfo>> getUserInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, LoggedInUser>> login(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> addAccount(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, Post>> getPosts(@FieldMap Map<String,String> params);


//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);

//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}

