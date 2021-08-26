package com.example.myapplication.NewAppPlease;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NewAppPlease.controller.DetailActivity;
import com.example.myapplication.NewAppPlease.controller.IssueActivity;
import com.example.myapplication.NewAppPlease.model.Item;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList){
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_repository, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.title.setText(items.get(i).getName());
        viewHolder.githublinkl.setText(items.get(i).getHtml_url());
        viewHolder.description.setText(items.get(i).getDescription());
        Picasso.with(context)
                .load(items.get(i).getAvatarUrl().getAvatar_url())
                .placeholder(R.drawable.ic_launcher_background)
                .into(viewHolder.imageView);
    }
    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView githublinkl;
        private final TextView description;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            githublinkl = (TextView) view.findViewById(R.id.githublinkl);
            description = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("full_name", items.get(pos).getName());
                        intent.putExtra("html_url",items.get(pos).getHtml_url());
                        intent.putExtra("avatar_url",items.get(pos).getAvatarUrl().getAvatar_url());
                        intent.putExtra("description", items.get(pos).getDescription());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked"+clickedDataItem.getName(),Toast.LENGTH_SHORT ).show();
                    }
                }
            });
        }
    }
}
