package com.twitter.githubissuetracker.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.Bind;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.adapters.SearchListAdapter;
import com.twitter.githubissuetracker.events.SearchQueryRequestedEvent;
import com.twitter.githubissuetracker.interfaces.GetIssueDetailsInterface;
import com.twitter.githubissuetracker.interfaces.SearchQueryInterface;
import com.twitter.githubissuetracker.models.Issue;
import com.twitter.githubissuetracker.providers.GithubServiceProvider;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchListFragment extends BaseFragment implements Callback<List<Issue>>,SearchQueryInterface {
  SearchQueryRequestedEvent event;
  GetIssueDetailsInterface listener;
  GithubServiceProvider githubServiceProvider = new GithubServiceProvider();
  ProgressDialog progressDialog;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  List<Issue> issues;

  public final static String TAG = SearchListFragment.class.getName();
  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.list,container,false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    progressDialog = new ProgressDialog(getContext());
    progressDialog.setMessage("Loading...");

    onResponse(issues);
  }

  public void onSearchQueryRequested(SearchQueryRequestedEvent event){
    this.event = event;
    Call<List<Issue>> searchCall = githubServiceProvider.get().getIssues(event.getOwner(),event.getRepo());
    searchCall.enqueue(this);
    progressDialog.show();
  }

  @Override public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
    onResponse(response.body());
  }

  @Override public void onFailure(Call<List<Issue>> call, Throwable t) {
    if(getContext() == null)
      return;
    Toast.makeText(getContext(),"Could not fetch data from github",Toast.LENGTH_LONG).show();
    progressDialog.hide();
  }

  private void onResponse(List<Issue> issueList){
    if(getContext() == null || issueList == null || issueList.size() == 0)
      return;
    this.issues = issueList;
    SearchListAdapter searchListAdapter = new SearchListAdapter(listener,event,issueList, getContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(searchListAdapter);
    searchListAdapter.notifyDataSetChanged();
    progressDialog.hide();
  }

  public void setGetIssueDetailsInterface(GetIssueDetailsInterface listener) {
    this.listener = listener;
  }
}
