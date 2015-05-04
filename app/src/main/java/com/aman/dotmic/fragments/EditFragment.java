package com.aman.dotmic.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.aman.dotmic.Events.NetworkStartEvent;
import com.aman.dotmic.R;

import de.greenrobot.event.EventBus;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Fragment where user will enter the text to search.
 */
public class EditFragment extends Fragment {

    EditText search;
    Button startsearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_filter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = (EditText) view.findViewById(R.id.editText_search);
        startsearch = (Button) view.findViewById(R.id.button_search);
        startsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getText().toString().trim().length() > 0) {
                    EventBus.getDefault().post(new NetworkStartEvent(search.getText().toString().trim()));
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                } else {
                    search.setHint("I am Dumb.Enter Something.");
                }
            }
        });
    }
}
