package com.mhossam.rocknfit.API;

import com.mhossam.rocknfit.model.AccountInfo;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.mhossam.rocknfit.model.Post;
import com.mhossam.rocknfit.model.PostComment;

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
    Call<Map<String, AccountInfo>> getFollowSuggestions(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, LoggedInUser>> login(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> addAccount(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, Post>> getPosts(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, PostComment>> getPostComments(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> likePost(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> likeComment(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> unlikePost(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> sharePost(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> deletePost(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<Map<String, String>> updatePost(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("MobAPIs/MobAPIs.php")
    Call<String> addComment(@FieldMap Map<String,String> params);


//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);

//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}

