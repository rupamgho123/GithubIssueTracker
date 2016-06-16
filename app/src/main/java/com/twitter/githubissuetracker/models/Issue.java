package com.twitter.githubissuetracker.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by rupam.ghosh on 16/06/16.
 */
@Data public class Issue implements Parcelable{
  @SerializedName("url") public String url;
  @SerializedName("repository_url") public String repositoryUrl;
  @SerializedName("labels_url") public String labelsUrl;
  @SerializedName("comments_url") public String commentsUrl;
  @SerializedName("events_url") public String eventsUrl;
  @SerializedName("html_url") public String htmlUrl;
  @SerializedName("id") public Integer id;
  @SerializedName("number") public Integer number;
  @SerializedName("title") public String title;
  @SerializedName("user") public User user;
  @SerializedName("state") public String state;
  @SerializedName("locked") public Boolean locked;
  @SerializedName("comments") public Integer comments;
  @SerializedName("created_at") public String createdAt;
  @SerializedName("updated_at") public String updatedAt;
  @SerializedName("body") public String body;

  protected Issue(Parcel in) {
    url = in.readString();
    repositoryUrl = in.readString();
    labelsUrl = in.readString();
    commentsUrl = in.readString();
    eventsUrl = in.readString();
    htmlUrl = in.readString();
    id = in.readByte() == 0x00 ? null : in.readInt();
    number = in.readByte() == 0x00 ? null : in.readInt();
    title = in.readString();
    user = (User) in.readValue(User.class.getClassLoader());
    state = in.readString();
    byte lockedVal = in.readByte();
    locked = lockedVal == 0x02 ? null : lockedVal != 0x00;
    comments = in.readByte() == 0x00 ? null : in.readInt();
    createdAt = in.readString();
    updatedAt = in.readString();
    body = in.readString();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(url);
    dest.writeString(repositoryUrl);
    dest.writeString(labelsUrl);
    dest.writeString(commentsUrl);
    dest.writeString(eventsUrl);
    dest.writeString(htmlUrl);
    if (id == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(id);
    }
    if (number == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(number);
    }
    dest.writeString(title);
    dest.writeValue(user);
    dest.writeString(state);
    if (locked == null) {
      dest.writeByte((byte) (0x02));
    } else {
      dest.writeByte((byte) (locked ? 0x01 : 0x00));
    }
    if (comments == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(comments);
    }
    dest.writeString(createdAt);
    dest.writeString(updatedAt);
    dest.writeString(body);
  }

  @SuppressWarnings("unused") public static final Parcelable.Creator<Issue> CREATOR =
      new Parcelable.Creator<Issue>() {
        @Override public Issue createFromParcel(Parcel in) {
          return new Issue(in);
        }

        @Override public Issue[] newArray(int size) {
          return new Issue[size];
        }
      };
}
