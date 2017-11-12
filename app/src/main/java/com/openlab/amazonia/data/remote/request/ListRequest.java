package com.openlab.amazonia.data.remote.request;


import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.entities.PayChartEntity;
import com.openlab.amazonia.data.entities.ResponseVisited;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by katherine on 12/06/17.
 */

public interface ListRequest {
    @GET("chart/")
    Call<ChartEntity> getChart(@Header("Authorization") String token);

    @GET("chart/{id}")
    Call<ChartEntity> getChartByMonth(@Header("Authorization") String token,
                                    @Query("month") int idMonth);


    @GET("chart-payers/")
    Call<PayChartEntity> getPayChart(@Header("Authorization") String token);

    @GET("chart-payers/{id}")
    Call<PayChartEntity> getPayChartByMonth(@Header("Authorization") String token,
                                            @Query("month") int idMonth);


    @GET("visits-anual/")
    Call<ResponseVisited> getListVisited(@Header("Authorization") String token);

    /*@GET("listdestinybycities/{pk}/")
    Call<TrackHolderEntity<DestinyTravelEntity>> getDestiny(@Path("pk") int id,
                                                            @Query("page") int numberPage);

    @GET("list/{date}/schedulebydate/")
    Call<TrackHolderEntity<SchedulesEntity>> getListSchedules(@Header("Authorization") String token,
                                                              @Path("date") String date,
                                                              @Query("search") String destinyName,
                                                              @Query("page") int numberPage);


    @GET("listschedulebydestinies/{destiny}/{date}/{num}/")
    Call<TrackHolderEntity<SchedulesEntity>> getListSchedulesInOrder(@Header("Authorization") String token,
                                                                     @Path("destiny") String destiny,
                                                                     @Path("date") String date,
                                                                     @Path("num") int num,
                                                                     @Query("page") int numberPage);


    @GET("myreservation/")
    Call<TrackHolderEntity<ReservationEntity>> getReservation(@Header("Authorization") String token,
                                                              @Query("page") int numberPage);*/

}
