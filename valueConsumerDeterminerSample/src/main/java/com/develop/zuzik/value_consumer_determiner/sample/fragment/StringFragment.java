package com.develop.zuzik.value_consumer_determiner.sample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.zuzik.value_consumer_determiner.R;

public class StringFragment extends Fragment {
    private static final String ARGUMENT_STRING = "ARGUMENT_STRING";

    public static StringFragment newInstance(String value) {
        StringFragment fragment = new StringFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STRING, value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String valueString = "";
        if (getArguments() != null) {
            valueString = getArguments().getString(ARGUMENT_STRING);
        }
        View inflate = inflater.inflate(R.layout.fragment_string, container, false);
        TextView value = (TextView) inflate.findViewById(R.id.value);
        value.setText(valueString);
        return inflate;
    }

}
