package com.twitter.githubissuetracker.interfaces;

import com.twitter.githubissuetracker.events.ShowIssueEvent;

/**
 * Created by rupam.ghosh on 18/06/16.
 */
public interface GetIssueDetailsInterface {
  void onShowIssueEvent(ShowIssueEvent event);
}
