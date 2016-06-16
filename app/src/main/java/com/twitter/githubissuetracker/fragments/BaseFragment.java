package com.twitter.githubissuetracker.fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import com.twitter.githubissuetracker.providers.BusProvider;

/**
 * Created by rupam.ghosh on 08/01/16.
 */
public abstract class BaseFragment extends Fragment {

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    BusProvider.getInstance().register(this);
  }

  @Override
  public void onDestroyView() {
    ButterKnife.unbind(this);
    BusProvider.getInstance().unregister(this);
    super.onDestroyView();
  }
}
