package com.twitter.githubissuetracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.models.Issue;
import java.util.List;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.IssueHolder> {

  List<Issue> issues;
  Context context;
  public SearchListAdapter(List<Issue> issues,Context context){
    this.issues = issues;
    this.context = context;
    if(issues.size() == 0){
      Toast.makeText(context,"No issues for repo",Toast.LENGTH_LONG).show();
    }
  }

  @Override public IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new IssueHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false));
  }

  @Override public void onBindViewHolder(IssueHolder holder, int position) {
    Issue issue = issues.get(position);
    holder.titleView.setText(issue.getTitle());
  }

  @Override public int getItemCount() {
    return issues.size();
  }

  public static class IssueHolder extends RecyclerView.ViewHolder{
    TextView titleView;

    public IssueHolder(View itemView) {
      super(itemView);
      titleView = (TextView) itemView.findViewById(R.id.title);
    }
  }
}
