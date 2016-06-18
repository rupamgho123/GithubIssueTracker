package com.twitter.githubissuetracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.twitter.githubissuetracker.R;
import com.twitter.githubissuetracker.models.Comment;
import java.util.List;

/**
 * Created by rupam.ghosh on 18/06/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>{
  List<Comment> commentList;
  Context context;
  public CommentAdapter(List<Comment> commentList,Context context){
    this.commentList = commentList;
    this.context = context;
  }

  @Override public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CommentHolder(LayoutInflater.from(context).inflate(R.layout.comment_list_item,parent,false));
  }

  @Override public void onBindViewHolder(CommentHolder holder, int position) {
    Comment comment = commentList.get(position);
    holder.username.setText(String.format("User: %s", comment.getUser().getLogin()));
    holder.commentBody.setText(comment.getBody());
  }

  @Override public int getItemCount() {
    return commentList.size();
  }

  public static class CommentHolder extends RecyclerView.ViewHolder{
    TextView username;
    TextView commentBody;
    public CommentHolder(View itemView) {
      super(itemView);
      username = (TextView) itemView.findViewById(R.id.username);
      commentBody = (TextView) itemView.findViewById(R.id.comment_body);
    }
  }
}
