package com.aman.dotmic.Events;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Event when a network call needs to be started.
 */
public class NetworkStartEvent {

    private String search;

    public NetworkStartEvent(String search) {
        this.search = search;
    }

    public String getsearch() {
        return search;
    }
}
