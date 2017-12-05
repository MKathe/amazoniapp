package com.openlab.amazonia.data.remote.request;


import com.openlab.amazonia.data.entities.ChartEntity;
import com.openlab.amazonia.data.entities.ListEntity;
import com.openlab.amazonia.data.entities.PayChartEntity;
import com.openlab.amazonia.data.entities.ResponseVisited;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by katherine on 12/06/17.
 */

public interface ListRequest {
    @GET("reports/chart/visits/")
    Call<ChartEntity> getChart(@Header("Authorization") String token);

    @GET("reports/chart/visits/{month}")
    Call<ChartEntity> getChartByMonth(@Header("Authorization") String token,
                                      @Query("month") int idMonth);



    @GET("reports/visits-anual/{month}")
    Call<ResponseVisited> getListVisited(@Header("Authorization") String token,
                                         @Query("month") int idMonth);

    @GET("reports/visits-anual-period/{month}")
    Call<ResponseVisited> getListAcumulado(@Header("Authorization") String token,
                                           @Query("month") int idMonth);
    @GET("visits/")
    Call<ArrayList<ListEntity>> getListData(@Query("approved") boolean approved);

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
