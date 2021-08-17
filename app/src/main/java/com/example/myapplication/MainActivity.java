package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        Client client = retrofit.create(Client.class);
        Call<List<GitHubRepo>> call =client.loadRepo("q","rails");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(@NonNull Call<List<GitHubRepo>> call, @NonNull Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();
                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this,-1,repos));
            }

            @Override
            public void onFailure(@NonNull Call<List<GitHubRepo>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this,"erro",Toast.LENGTH_SHORT).show();
                }
        });
    }
}