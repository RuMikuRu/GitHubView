package com.example.myapplication.NewAppPlease;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NewAppPlease.model.Issue;
import com.example.myapplication.R;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {
        private List<Issue> issues;
        private Context context;

        public IssueAdapter(Context applicationContext, List<Issue> issueArrayList){ ;
            this.context = applicationContext;
            this.issues = issueArrayList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_issue, viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i)
        {
            viewHolder.title.setText(issues.get(i).getTitle());

        }
        @Override
        public int getItemCount()
        {
            return issues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView title;

            public ViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.issue);
            }
        }
}
