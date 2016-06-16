package com.twitter.githubissuetracker.services;

import com.twitter.githubissuetracker.models.Issue;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rupam.ghosh on 16/06/16.
 */
public interface GithubService {
  @GET("repos/{owner}/{repo}/issues") Call<List<Issue>> getIssues(@Path("owner") String owner,@Path("repo") String repo);
}
