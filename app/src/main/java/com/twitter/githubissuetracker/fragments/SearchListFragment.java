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
import com.squareup.otto.Subscribe;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.adapters.SearchListAdapter;
import com.twitter.githubissuetracker.events.SearchQueryRequestedEvent;
import com.twitter.githubissuetracker.models.Issue;
import com.twitter.githubissuetracker.providers.BusProvider;
import com.twitter.githubissuetracker.providers.GithubServiceProvider;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchListFragment extends BaseFragment implements Callback<List<Issue>>{
  GithubServiceProvider githubServiceProvider = new GithubServiceProvider();
  ProgressDialog progressDialog;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.list,container,false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    progressDialog = new ProgressDialog(getContext());
    progressDialog.setMessage("Loading...");

    BusProvider.getInstance().post(new SearchQueryRequestedEvent("rails","rails"));
  }

  @Subscribe
  public void onSearchQueryRequested(SearchQueryRequestedEvent event){
    Call<List<Issue>> searchCall = githubServiceProvider.get().getIssues(event.getOwner(),event.getRepo());
    searchCall.enqueue(this);
    progressDialog.show();
  }

  @Override public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
    SearchListAdapter searchListAdapter = new SearchListAdapter(response.body(), getContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(searchListAdapter);
    searchListAdapter.notifyDataSetChanged();
    progressDialog.hide();
  }

  @Override public void onFailure(Call<List<Issue>> call, Throwable t) {
    Toast.makeText(getContext(),"Could not fetch data from github",Toast.LENGTH_LONG).show();
    progressDialog.hide();
  }
}
