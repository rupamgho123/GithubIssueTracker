package com.twitter.githubissuetracker.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by rupam.ghosh on 17/06/16.
 */
@Data public class User implements Parcelable{
  @SerializedName("login") public String login;
  @SerializedName("id") public Integer id;
  @SerializedName("avatar_url") public String avatarUrl;
  @SerializedName("gravatar_id") public String gravatarId;
  @SerializedName("url") public String url;
  @SerializedName("html_url") public String htmlUrl;
  @SerializedName("followers_url") public String followersUrl;
  @SerializedName("following_url") public String followingUrl;
  @SerializedName("gists_url") public String gistsUrl;
  @SerializedName("starred_url") public String starredUrl;
  @SerializedName("subscriptions_url") public String subscriptionsUrl;
  @SerializedName("organizations_url") public String organizationsUrl;
  @SerializedName("repos_url") public String reposUrl;
  @SerializedName("events_url") public String eventsUrl;
  @SerializedName("received_events_url") public String receivedEventsUrl;
  @SerializedName("type") public String type;
  @SerializedName("site_admin") public Boolean siteAdmin;

  protected User(Parcel in) {
    login = in.readString();
    id = in.readByte() == 0x00 ? null : in.readInt();
    avatarUrl = in.readString();
    gravatarId = in.readString();
    url = in.readString();
    htmlUrl = in.readString();
    followersUrl = in.readString();
    followingUrl = in.readString();
    gistsUrl = in.readString();
    starredUrl = in.readString();
    subscriptionsUrl = in.readString();
    organizationsUrl = in.readString();
    reposUrl = in.readString();
    eventsUrl = in.readString();
    receivedEventsUrl = in.readString();
    type = in.readString();
    siteAdmin = in.readByte() != 0;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(login);
    if (id == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(id);
    }
    dest.writeString(avatarUrl);
    dest.writeString(gravatarId);
    dest.writeString(url);
    dest.writeString(htmlUrl);
    dest.writeString(followersUrl);
    dest.writeString(followingUrl);
    dest.writeString(gistsUrl);
    dest.writeString(starredUrl);
    dest.writeString(subscriptionsUrl);
    dest.writeString(organizationsUrl);
    dest.writeString(reposUrl);
    dest.writeString(eventsUrl);
    dest.writeString(receivedEventsUrl);
    dest.writeString(type);
    dest.writeByte((byte) (siteAdmin ? 1 : 0));
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
    @Override
    public User createFromParcel(Parcel in) {
      return new User(in);
    }

    @Override
    public User[] newArray(int size) {
      return new User[size];
    }
  };
}
