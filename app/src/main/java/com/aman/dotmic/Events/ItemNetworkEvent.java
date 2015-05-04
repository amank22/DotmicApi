package com.aman.dotmic.Events;

import com.aman.dotmic.POJO.StoreItem;

import java.util.List;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * EventBus's Event when real network call needs to be done.
 * Broadcasts all the result back to Main Activity
 */
public class ItemNetworkEvent {

    private boolean error;
    private String error_String;
    private List<StoreItem> storeItems;

    public ItemNetworkEvent(boolean error, String error_String, List<StoreItem> storeItems) {
        this.error = error;
        this.error_String = error_String;
        this.storeItems = storeItems;
    }

    public boolean isError() {
        return error;
    }

    public String getError_String() {
        return error_String;
    }

    public List<StoreItem> getStoreItems() {
        return storeItems;
    }
}
