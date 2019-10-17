package com.example.msi_dpr;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface postdata {
    @Headers("Content-Type: application/json")
    @POST("forms('Pm55mUUEBU6Dnb2Fq-UUmmmdiAShbOVMnUnhrh-VQdVURjNUVjlPQVVCS0c2UzlCWEhVTVhOQ1BWSy4u')/responses")
    Call<Object> completeQuestionnaire(@Body String answers);
}
