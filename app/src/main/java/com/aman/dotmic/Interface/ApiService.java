package com.aman.dotmic.Interface;

import com.aman.dotmic.POJO.StoreItem;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Interface of RETROFIT for Api call.
 */
public interface ApiService {
    /**
     * GET METHOD
     * @param queries A map of queries to be submitted to Dotmic Api
     * @return LIST of storeItem objects
     */
    @GET("/")
    void storeList(@QueryMap Map<String, String> queries, Callback<List<StoreItem>> callback);

}
