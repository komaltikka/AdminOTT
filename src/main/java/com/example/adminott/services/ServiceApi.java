package com.example.adminott.services;

import com.example.adminott.model.User;
import com.example.adminott.model.Useradmin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("adminregister.php")

    Call<User> doRegisteration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone,
            @Query("password") String password,
            @Query("confirmpassword") String confirmpassword,
            @Query("organizationId") String organizationId
    );

    @GET("adminlogin.php")
    Call<User> doLogin(
            @Query("email") String email,
            @Query("password") String password
    );

    @GET("adminotpverify.php")
    Call<User> doVerify(
            @Query("otp") String otp

    );
}
