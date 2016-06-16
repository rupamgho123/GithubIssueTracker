package com.twitter.githubissuetracker.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.fragments.SearchDialogFragment;
import com.twitter.githubissuetracker.fragments.SearchListFragment;

public class MainActivity extends BaseActivity {

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
    getSupportFragmentManager().beginTransaction().
        replace(R.id.base_frame, new SearchListFragment()).
        commit();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
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

  private void showSearchDialog() {
    SearchDialogFragment fragment = new SearchDialogFragment();
    fragment.show(getSupportFragmentManager(), "");
  }
}

