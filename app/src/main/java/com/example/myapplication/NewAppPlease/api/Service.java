package com.example.myapplication.NewAppPlease.api;

import com.example.myapplication.NewAppPlease.model.IssueResponse;
import com.example.myapplication.NewAppPlease.model.Item;
import com.example.myapplication.NewAppPlease.model.ItemResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("search/repositories")
    Call<ItemResponce> getItems(
            @Query(value = "q",encoded = true) String q
    );

    @Headers("Accept: application/vnd.github.html,application/vnd.github.VERSION.raw")
    @GET("repos/{full_name}/issues")
    Call<IssueResponse> getIssues(
            @Path("full_name") String full_name,
            @Query(value = "sort", encoded = true) String sort
            );

}
