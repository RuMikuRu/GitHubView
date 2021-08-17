package com.example.myapplication.NewApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.GitHubRepo;
import com.example.myapplication.R;

import java.util.List;

public class RepoAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<RepoModel> list;
    public RepoAdapter(Context context, int resource, List<RepoModel> objects)
    {
        super(context,resource,objects);
        this.context = context;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            view = inflater.inflate(R.layout.list_item,null);
        TextView name = (TextView) view.findViewById(R.id.textview);

        RepoModel repoModel = list.get(position);
        if(name!=null)
            name.setText(repoModel.getName());
        return view;
    }

}
