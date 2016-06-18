package com.twitter.githubissuetracker.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.events.SearchQueryRequestedEvent;
import com.twitter.githubissuetracker.interfaces.SearchQueryInterface;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchDialogFragment extends DialogFragment {
  ViewGroup input;
  EditText owner;
  EditText repo;
  SearchQueryInterface listener;

  public void setSearchQueryInterface(SearchQueryInterface listener){
    this.listener = listener;
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    LayoutInflater layoutInflater = getActivity().getLayoutInflater();
    input = (ViewGroup) layoutInflater.inflate(R.layout.input_dialog,null);
    owner = (EditText) input.findViewById(R.id.owner);
    repo = (EditText) input.findViewById(R.id.repo);
    input.findViewById(R.id.repo);
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle("Enter repo details");
    builder.setView(input);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        if(owner.getText().toString().length() == 0){
          Toast.makeText(getContext(),"Please enter owner of repo",Toast.LENGTH_SHORT).show();
        }else if(repo.getText().toString().length() == 0){
          Toast.makeText(getContext(),"Please enter repo name",Toast.LENGTH_SHORT).show();
        }
        else {
          if(listener != null)
            listener.onSearchQueryRequested(new SearchQueryRequestedEvent(owner.getText().toString(),repo.getText().toString()));
        }
        SearchDialogFragment.this.dismiss();
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        SearchDialogFragment.this.dismiss();
      }
    });
    return builder.create();
  }
}
