package com.example.myapplication;

import android.app.appsearch.SearchResult;

import com.example.myapplication.NewApp.RepoModel;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Client {
    @GET("search/repositories")
    Call<List<GitHubRepo>> loadRepo(
            @Query(value = "q", encoded = true) String query,
            @Query("repos") String Repos
    );
}
