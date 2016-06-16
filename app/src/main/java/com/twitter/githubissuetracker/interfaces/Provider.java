package com.twitter.githubissuetracker.interfaces;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public interface Provider<T> {
  T get();
}
