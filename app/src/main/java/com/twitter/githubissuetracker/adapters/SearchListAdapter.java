package com.twitter.githubissuetracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.events.SearchQueryRequestedEvent;
import com.twitter.githubissuetracker.events.ShowIssueEvent;
import com.twitter.githubissuetracker.interfaces.OnItemClickListener;
import com.twitter.githubissuetracker.models.Issue;
import com.twitter.githubissuetracker.providers.BusProvider;
import java.util.List;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.IssueHolder> implements
    OnItemClickListener {
  List<Issue> issues;
  Context context;
  SearchQueryRequestedEvent event;
  public SearchListAdapter(SearchQueryRequestedEvent event,List<Issue> issues,Context context){
    this.event = event;
    this.issues = issues;
    this.context = context;
    if(issues.size() == 0){
      Toast.makeText(context,"No issues for repo",Toast.LENGTH_LONG).show();
    }
  }

  @Override public IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new IssueHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false),this);
  }

  @Override public void onBindViewHolder(IssueHolder holder, int position) {
    Issue issue = issues.get(position);
    holder.titleView.setText(issue.getTitle());
  }

  @Override public int getItemCount() {
    return issues.size();
  }

  public static class IssueHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView titleView;
    OnItemClickListener listener;
    public IssueHolder(View itemView,OnItemClickListener listener) {
      super(itemView);
      this.listener = listener;
      titleView = (TextView) itemView.findViewById(R.id.title);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      listener.onClick(v,getPosition());
    }
  }

  @Override public void onClick(View v,int position) {
    BusProvider.getInstance().post(new ShowIssueEvent(event.getOwner(),event.getRepo(),issues.get(position)));
  }
}
