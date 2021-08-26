package com.example.myapplication.NewAppPlease.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.NewAppPlease.ItemAdapter;
import com.example.myapplication.NewAppPlease.api.Client;
import com.example.myapplication.NewAppPlease.api.Service;
import com.example.myapplication.NewAppPlease.model.Item;
import com.example.myapplication.NewAppPlease.model.ItemResponce;
import com.example.myapplication.R;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    //private String RepoName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //loadJSON();
                Toast.makeText(NewActivity.this,"Github Repositories Refresh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews()
    {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Repository...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        pd.hide();
        //loadJSON();
    }
    private void loadJSON(String RepoName){
        if(RepoName.equals(""))
        {
            Disconnected = (TextView) findViewById(R.id.disconnected);
        }
        else
        {
            try {
                Client client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<ItemResponce> call = apiService.getItems(RepoName);
                call.enqueue(new Callback<ItemResponce>() {
                    @Override
                    public void onResponse(@NonNull Call<ItemResponce> call, @NonNull Response<ItemResponce> response) {
                        assert response.body() != null;
                        List<Item> items = response.body().getItems();
                        recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                        recyclerView.smoothScrollToPosition(0);
                        swipeContainer.setRefreshing(false);
                        pd.hide();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ItemResponce> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(NewActivity.this,"Erro Fetshining Data", Toast.LENGTH_SHORT).show();
                        Disconnected.setVisibility(View.VISIBLE);
                        pd.hide();
                    }
                });
            }catch (Exception e){
                Log.d("Erro", e.getMessage());
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.app_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.toString().equals(""))
                {

                }
                else{
                  loadJSON(s.toString());
                }
                return false;
            }
        });

        return  true;
    }
    //TODO Добавить поиск
}