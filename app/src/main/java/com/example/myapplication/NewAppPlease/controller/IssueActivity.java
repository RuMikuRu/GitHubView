package com.example.myapplication.NewAppPlease.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.NewAppPlease.IssueAdapter;
import com.example.myapplication.NewAppPlease.api.Client;
import com.example.myapplication.NewAppPlease.api.Service;
import com.example.myapplication.NewAppPlease.model.Issue;
import com.example.myapplication.NewAppPlease.model.IssueResponse;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
    private ProgressDialog pd;
    private RecyclerView recyclerView;
    private String full_name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        full_name =(String) getIntent().getExtras().getString("full_name");
        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerIssue);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(() -> {
            loadJSON();
            Toast.makeText(IssueActivity.this,"Github Repositories Refresh", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadJSON() {
        //Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<IssueResponse> call = apiService.getIssues(full_name,"created");
            call.enqueue(new Callback<IssueResponse>() {
                @Override
                public void onResponse(@NonNull Call<IssueResponse> call, @NonNull Response<IssueResponse> response) {
                    assert response.body() != null;
                    List<Issue> issues = response.body().getIssues();
                    recyclerView.setAdapter(new IssueAdapter(getApplicationContext(), issues));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(@NonNull Call<IssueResponse> call, @NonNull Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(IssueActivity.this,"Erro Fetshining Data", Toast.LENGTH_SHORT).show();
                    //Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });
        }catch (Exception e){
            Log.d("Erro", e.getMessage());
            Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Issue...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewIssue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();

    }
}
