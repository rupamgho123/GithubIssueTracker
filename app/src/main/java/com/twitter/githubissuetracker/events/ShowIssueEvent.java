package com.twitter.githubissuetracker.events;

import com.twitter.githubissuetracker.models.Issue;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by rupam.ghosh on 18/06/16.
 */
@Data
@AllArgsConstructor
public class ShowIssueEvent {
  private String owner;
  private String repo;
  private Issue issue;
}
