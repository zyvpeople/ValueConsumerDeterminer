package com.develop.zuzik.viewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.develop.zuzik.viewadapter.recyclerviewadapter.RecyclerViewAdapter;
import com.develop.zuzik.viewadapter.recyclerviewadapter.RecyclerViewAdapterBuilder;
import com.develop.zuzik.viewadapter.recyclerviewadapter.decorator.MatchParentWidthRecyclerViewAdapterDecorator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List values = Arrays.asList(
                new TextValue("Alpha"),
                new TextValue("Beta"),
                new TextValue("Gamma"),
                new IntValue(1),
                new IntValue(2),
                new IntValue(3));

        RecyclerViewAdapter<Object> adapter = RecyclerViewAdapterBuilder
                .create()
                .displayCustomViewIfValueCantBeDisplayed(new QAViewFactory())
                .factory(TextValue.class, new TextValueViewFactory())
//                .factory(IntValue.class, new IntValueViewFactory())
                .build();
        adapter.setValues(values);
        recyclerView.setAdapter(new MatchParentWidthRecyclerViewAdapterDecorator<>(adapter));
    }
}
