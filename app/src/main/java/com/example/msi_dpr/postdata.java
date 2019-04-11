package com.example.msi_dpr;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface postdata {
    @POST("1zSFbuKw4xIkRCUTdNfnm3njNVvyEekjgWDbU2vUt_Jo/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.1596055276") String project_name,
            @Field("entry.1157681280") String date,
            @Field("entry.481225792") String project_manager,
            @Field("entry.2110584012") String site_engineer,
            @Field("entry.752170706") String channel_partner,
            @Field("entry.1837329720") String line_name,
            @Field("entry.555822094") String line_length,
            @Field("entry.1376698483") String route_length,
            @Field("entry.2006952254") String drum_number,
            @Field("entry.3555883") String location_number,
            @Field("entry.336247608") String issue_time,
            @Field("entry.215039202") String return_time,
            @Field("entry.876830043") String today_work,
            @Field("entry.1125865297") String tomorrow_plan,
            @Field("entry.298094379") String ehs,
            @Field("entry.1696127700") String remarks

    );


}
