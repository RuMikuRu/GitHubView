package com.example.myapplication.NewAppPlease.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private Context context;
    TextView Link, RepoName, Description;
    Toolbar mActionBarToolbar;
    ImageView imageView;
    private RecyclerView recyclerView;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeConteiner;
    TextView Disconnected;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        RepoName =(TextView) findViewById(R.id.username);
        Description = (TextView) findViewById(R.id.description);
        Link = (TextView) findViewById(R.id.link);


        String repoName = getIntent().getExtras().getString("full_name");
        String avatar_url = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");
        String description = getIntent().getExtras().getString("description");

        Description.setText(description);
        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        RepoName.setText(repoName);

        Glide.with(this)
                .load(avatar_url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        getSupportActionBar().setTitle("Detail Activity");

        //button = (Button) findViewById(R.id.button);

    }

    public void OnClick(View view)
    {
        Intent intent = new Intent(this, IssueActivity.class);
        intent.putExtra("full_name",RepoName.toString());
        this.startActivity(intent);
    }

    private Intent createShareForcastIntent()
    {
        String repoName = getIntent().getExtras().getString("full_name");
        String link = getIntent().getExtras().getString("link");
        return ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer @"+repoName + ", " + link)
                .getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForcastIntent());
        return  true;
    }
    //TODO Добавить issue
}
