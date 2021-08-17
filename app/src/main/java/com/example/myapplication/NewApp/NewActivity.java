package com.example.myapplication.NewApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.GitHubRepo;
import com.example.myapplication.GitHubRepoAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity {
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GithubApi githubApi = GithubApi.retrofit.create(GithubApi.class);
        listView = (ListView) findViewById(R.id.list_item);
        Button requestButton = (Button) findViewById(R.id.search_button);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<RepoModel>> call = githubApi.loadRepo("q","rails");

                call.enqueue(new Callback<List<RepoModel>>() {
                    @Override
                    public void onResponse(Call<List<RepoModel>> call, Response<List<RepoModel>> response) {
                        List<RepoModel> repos = response.body();
                        listView.setAdapter(new RepoAdapter(NewActivity.this,-1,repos));
                    }

                    @Override
                    public void onFailure(Call<List<RepoModel>> call, Throwable t) {
                        Toast.makeText(NewActivity.this,"erro",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
