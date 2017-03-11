package com.spieler.redis;

import java.util.concurrent.TimeUnit;

/**
 * Created by spielerl on 05/03/2017.
 */
public interface CentralCacheClient {
    void set(String key, String value);
    String get(String key);
    void delete(String key);
    boolean expire(final String key, final long timeout, final TimeUnit unit);
}
