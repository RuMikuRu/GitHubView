package com.example.myapplication.NewApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GithubApi {
    @GET("search/repositories")
    Call<List<RepoModel>> loadRepo(
            @Query(value = "q", encoded = true) String query,
            @Query(value = "repos", encoded = true) String Repos
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
