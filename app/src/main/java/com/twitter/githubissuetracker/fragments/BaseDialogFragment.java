package com.twitter.githubissuetracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import butterknife.ButterKnife;
import com.twitter.githubissuetracker.providers.BusProvider;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public abstract class BaseDialogFragment extends DialogFragment {
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
