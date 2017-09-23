package com.develop.zuzik.value_consumer_determiner.sample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.zuzik.value_consumer_determiner.R;
import com.develop.zuzik.value_consumer_determiner.sample.valueview.QAView;

public class QAFragment extends Fragment {

    public static QAFragment newInstance() {
        return new QAFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return new QAView<>(getContext());
    }

}
