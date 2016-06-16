package com.twitter.githubissuetracker.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
@Data
@AllArgsConstructor
public class SearchQueryRequestedEvent {
  String owner;
  String repo;
}
