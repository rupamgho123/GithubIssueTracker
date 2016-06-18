package com.twitter.githubissuetracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.adapters.CommentAdapter;
import com.twitter.githubissuetracker.models.Comment;
import com.twitter.githubissuetracker.models.Issue;
import com.twitter.githubissuetracker.providers.GithubServiceProvider;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rupam.ghosh on 17/06/16.
 */
public class IssueDetailsFragment extends BaseFragment implements Callback<List<Comment>>{
  @Bind(R.id.issue_reporter) TextView issueReporter;
  @Bind(R.id.issue_body) TextView issueBody;
  @Bind(R.id.comments_list) RecyclerView commentsList;
  public final static String TAG = IssueDetailsFragment.class.getName();
  public final static String ISSUE = "issue";
  public final static String REPO = "repo";
  public final static String OWNER = "owner";
  GithubServiceProvider githubServiceProvider = new GithubServiceProvider();

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.issue_details,container,false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Bundle arguments = getArguments();
    if(arguments != null){
      Issue issue = arguments.getParcelable(ISSUE);
      String repo = arguments.getString(REPO);
      String owner = arguments.getString(OWNER);
      if(issue != null)
        setTitle(issue.getNumber().toString());
        issueReporter.setText(String.format("Reported by user: %s", issue.getUser().getLogin()));
        issueBody.setText(String.format("Description: %s",issue.getBody()));
        Call<List<Comment>> listCall = githubServiceProvider.get().getComments(owner,repo,issue.getNumber());
        listCall.enqueue(this);
    }
  }

  @Override public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
    if(getContext() == null)
      return;
    List<Comment> comments = response.body();
    if(comments.size() > 0 && commentsList != null){
      CommentAdapter commentAdapter = new CommentAdapter(comments,getContext());
      commentsList.setLayoutManager(new LinearLayoutManager(getContext()));
      commentsList.setAdapter(commentAdapter);
    }else{
      Toast.makeText(getContext(),"No comments on this issues yet",Toast.LENGTH_SHORT).show();
    }
  }

  @Override public void onFailure(Call<List<Comment>> call, Throwable t) {
    if(getContext() == null)
      return;
    Toast.makeText(getContext(),"Could not fetch comments",Toast.LENGTH_SHORT).show();
  }

  @Override public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater) {
    menu.clear();
    super.onCreateOptionsMenu(menu,menuInflater);
  }
}
