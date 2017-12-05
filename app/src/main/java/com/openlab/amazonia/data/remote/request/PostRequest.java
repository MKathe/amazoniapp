package com.openlab.amazonia.data.remote.request;

import com.openlab.amazonia.data.entities.ApprovedResponse;
import com.openlab.amazonia.data.entities.ListEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by katherine on 10/05/17.
 */

public interface PostRequest {


    /*@FormUrlEncoded
    @POST("register/")
    Call<AccessTokenEntity> registerUser(@Field("email") String email,
                                         @Field("password") String password,
                                         @Field("first_name") String first_name,
                                         @Field("last_name") String last_name,
                                         @Field("cellphone") String cellphone,
                                         @Field("gender") String gender);*/

    @FormUrlEncoded
    @PUT("visits/{id}/")
    Call<ApprovedResponse> updateApproved(@Path("id") int id,
                                          @Field("approved") boolean approved);

}
