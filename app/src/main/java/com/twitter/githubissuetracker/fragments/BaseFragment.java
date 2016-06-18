package com.twitter.githubissuetracker.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.ButterKnife;
import com.twitter.githubissuetracker.activities.BaseActivity;

/**
 * Created by rupam.ghosh on 08/01/16.
 */
public abstract class BaseFragment extends Fragment {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }

  @Override
  public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }

  protected void setTitle(String title){
    BaseActivity activity = (BaseActivity)getActivity();
    activity.getSupportActionBar().setTitle(title);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    activity.invalidateOptionsMenu();
  }
}
