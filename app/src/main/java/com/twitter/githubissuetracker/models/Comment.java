package com.twitter.githubissuetracker.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by rupam.ghosh on 18/06/16.
 */
@Data
public class Comment implements Parcelable{
  @SerializedName("url")
  public String url;
  @SerializedName("html_url")
  public String htmlUrl;
  @SerializedName("issue_url")
  public String issueUrl;
  @SerializedName("id")
  public Integer id;
  @SerializedName("user")
  public User user;
  @SerializedName("created_at")
  public String createdAt;
  @SerializedName("updated_at")
  public String updatedAt;
  @SerializedName("body")
  public String body;

  protected Comment(Parcel in) {
    url = in.readString();
    htmlUrl = in.readString();
    issueUrl = in.readString();
    id = in.readByte() == 0x00 ? null : in.readInt();
    user = (User) in.readValue(User.class.getClassLoader());
    createdAt = in.readString();
    updatedAt = in.readString();
    body = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(url);
    dest.writeString(htmlUrl);
    dest.writeString(issueUrl);
    if (id == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(id);
    }
    dest.writeValue(user);
    dest.writeString(createdAt);
    dest.writeString(updatedAt);
    dest.writeString(body);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
    @Override
    public Comment createFromParcel(Parcel in) {
      return new Comment(in);
    }

    @Override
    public Comment[] newArray(int size) {
      return new Comment[size];
    }
  };
}