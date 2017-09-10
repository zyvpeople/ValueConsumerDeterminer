package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class QAView extends LinearLayout {

    public QAView(Context context) {
        super(context);
        inflate(context, R.layout.view_qa, this);
    }
}
