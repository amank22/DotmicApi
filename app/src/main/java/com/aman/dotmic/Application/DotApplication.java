package com.aman.dotmic.Application;

import android.app.Application;

import com.aman.dotmic.HelperClasses.Constants;
import com.aman.dotmic.Interface.ApiService;

import retrofit.RestAdapter;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Base Application Class for this app to initialise stuffs.
 */
public class DotApplication extends Application{

    private static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        getApiService();
    }

    /**
     *If object is null,create a new instance of ApiService.
     * @return single instance of ApiService.
     */
    public static ApiService getApiService() {
        if(apiService==null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.BaseUrl).setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();
            apiService=restAdapter.create(ApiService.class);
        }
        return apiService;
    }
}
