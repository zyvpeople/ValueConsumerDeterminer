package com.develop.zuzik.value_consumer_determiner.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.develop.zuzik.value_consumer_determiner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.recyclerViewSample).setOnClickListener(runActivity(RecyclerViewSampleActivity.class));
        findViewById(R.id.viewGroupSample).setOnClickListener(runActivity(ViewGroupSampleActivity.class));
    }

    private View.OnClickListener runActivity(final Class<? extends Activity> activityClass) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, activityClass));
            }
        };
    }
}
