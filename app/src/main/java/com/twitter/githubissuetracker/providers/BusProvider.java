package com.twitter.githubissuetracker.providers;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
/**
 * Created by rupam.ghosh on 08/01/16.
 */
/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Maintains a singleton instance for obtaining the bus. Ideally this would be replaced with a more efficient means
 * such as through injection directly into interested classes.
 */
public final class BusProvider {
  private static final Bus BUS = new Bus(ThreadEnforcer.ANY);
  private static final Handler mainThread = new Handler(Looper.getMainLooper());

  private BusProvider() {
    // No instances.
  }

  public static Bus getInstance() {
    return BUS;
  }

  public static void postOnMain(final Object event) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      getInstance().post(event);
    } else {
      mainThread.post(new Runnable() {
        @Override
        public void run() {
          getInstance().post(event);

        }
      });
    }
  }
}
