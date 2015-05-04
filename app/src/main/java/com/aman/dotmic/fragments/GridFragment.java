package com.aman.dotmic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.aman.dotmic.Adapter.GridAdapter;
import com.aman.dotmic.Events.ItemNetworkEvent;
import com.aman.dotmic.R;

import de.greenrobot.event.EventBus;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Fragment showing the grid of products.
 */
public class GridFragment extends Fragment {

    GridView grid;
    TextView errortext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        grid = (GridView) view.findViewById(R.id.gridView);
        errortext = (TextView) view.findViewById(R.id.textView_error);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        try {
            EventBus.getDefault().unregister(this);
        } catch (Throwable t) {
            //this may crash if registration did not go through. just be safe
            t.printStackTrace();
        }
        super.onDestroyView();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(ItemNetworkEvent event) {
        if (event.isError()) {
            grid.setVisibility(View.GONE);
            errortext.setVisibility(View.VISIBLE);
            errortext.setText(event.getError_String());
        } else {
            grid.setVisibility(View.VISIBLE);
            errortext.setVisibility(View.GONE);
            GridAdapter adapter = new GridAdapter(getActivity(), event.getStoreItems());
            grid.setAdapter(adapter);
        }
    }
}
