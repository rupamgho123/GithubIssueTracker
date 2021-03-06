package com.twitter.githubissuetracker.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.events.SearchQueryRequestedEvent;
import com.twitter.githubissuetracker.events.ShowIssueEvent;
import com.twitter.githubissuetracker.fragments.IssueDetailsFragment;
import com.twitter.githubissuetracker.fragments.SearchDialogFragment;
import com.twitter.githubissuetracker.fragments.SearchListFragment;
import com.twitter.githubissuetracker.interfaces.GetIssueDetailsInterface;
import com.twitter.githubissuetracker.interfaces.SearchQueryInterface;

public class MainActivity extends BaseActivity implements SearchQueryInterface,GetIssueDetailsInterface {
  SearchListFragment searchListFragment;
  IssueDetailsFragment issueDetailsFragment;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    openSearchListFragment();
    initActionBar();
  }

  private void initActionBar() {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
  }

  private void openSearchListFragment() {
    searchListFragment = new SearchListFragment();
    searchListFragment.setGetIssueDetailsInterface(this);
    getSupportFragmentManager().beginTransaction().
        replace(R.id.base_frame, searchListFragment,SearchListFragment.TAG).
        addToBackStack(SearchListFragment.TAG).
        commit();
  }

  public void onShowIssueEvent(ShowIssueEvent event){
    issueDetailsFragment = new IssueDetailsFragment();
    Bundle args = new Bundle();
    args.putParcelable(IssueDetailsFragment.ISSUE,event.getIssue());
    args.putString(IssueDetailsFragment.REPO,event.getRepo());
    args.putString(IssueDetailsFragment.OWNER,event.getOwner());
    issueDetailsFragment.setArguments(args);
    getSupportFragmentManager().beginTransaction().
        replace(R.id.base_frame, issueDetailsFragment, IssueDetailsFragment.TAG).
        addToBackStack(IssueDetailsFragment.TAG).
        commit();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      case R.id.menu_search:
        showSearchDialog();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onBackPressed() {
    int count = getSupportFragmentManager().getBackStackEntryCount();
    if(count <= 1){
      finish();
    }else {
      super.onBackPressed();
    }
  }

  private void showSearchDialog() {
    SearchDialogFragment fragment = new SearchDialogFragment();
    fragment.setSearchQueryInterface(this);
    fragment.show(getSupportFragmentManager(), "");
  }

  @Override public void onSearchQueryRequested(SearchQueryRequestedEvent event) {
    searchListFragment.onSearchQueryRequested(event);
  }
}

