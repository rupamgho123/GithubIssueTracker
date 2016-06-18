package com.twitter.githubissuetracker.interfaces;

import com.twitter.githubissuetracker.events.SearchQueryRequestedEvent;

/**
 * Created by rupam.ghosh on 18/06/16.
 */
public interface SearchQueryInterface{
  void onSearchQueryRequested(SearchQueryRequestedEvent event);
}
