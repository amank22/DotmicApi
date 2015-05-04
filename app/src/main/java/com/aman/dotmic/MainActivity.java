package com.aman.dotmic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.aman.dotmic.Application.DotApplication;
import com.aman.dotmic.Events.ItemNetworkEvent;
import com.aman.dotmic.Events.NetworkStartEvent;
import com.aman.dotmic.HelperClasses.Constants;
import com.aman.dotmic.HelperClasses.ZoomOutPageTransformer;
import com.aman.dotmic.POJO.StoreItem;
import com.aman.dotmic.fragments.EditFragment;
import com.aman.dotmic.fragments.GridFragment;

import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Main Activity of the app.
 */
public class MainActivity extends ActionBarActivity {

    private ViewPager mPager;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        /*
        The pager adapter, which provides the pages to the view pager widget.
       */
        PagerAdapter mPagerAdapter = new ItemPagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ItemPagerAdapter extends FragmentStatePagerAdapter {
        public ItemPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return new GridFragment();
            }
            else
                return new EditFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void callNetwork(Map<String, String> queries) {
        DotApplication.getApiService().storeList(queries, new Callback<List<StoreItem>>() {
            @Override
            public void success(List<StoreItem> storeItems, Response response) {
                progress.setVisibility(View.INVISIBLE);
                mPager.setCurrentItem(1, true);
                if (storeItems != null && !storeItems.isEmpty()) {
                    EventBus.getDefault().post(new ItemNetworkEvent(false, null, storeItems));
                } else {
                    EventBus.getDefault().post(new ItemNetworkEvent(true, "No Search Results", null));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progress.setVisibility(View.INVISIBLE);
                mPager.setCurrentItem(1, true);
                error.printStackTrace();
                if (error.getResponse() != null)
                    EventBus.getDefault().post(new ItemNetworkEvent(true, error.getResponse().getReason(), null));
                else
                    EventBus.getDefault().post(new ItemNetworkEvent(true, "Failed to Connect to Service.\n" +
                            "Might be some Network Problem", null));
            }
        });
    }

    @Override
    public void onDestroy() {
        try {
            EventBus.getDefault().unregister(this);
        } catch (Throwable t) {
            //this may crash if registration did not go through. just be safe
            t.printStackTrace();
        }
        super.onDestroy();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(NetworkStartEvent event) {
        Map<String, String> queries = Constants.getQUERY(event.getsearch(), 0, null, null);
        callNetwork(queries);
        progress.setVisibility(View.VISIBLE);
    }

}
