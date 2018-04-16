package org.cedricpump.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    Model model;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        model = MainActivity.model;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lst = (ListView) findViewById(R.id.listview);
        MyListAdapter la = new MyListAdapter(ListActivity.this,model);
        lst.setAdapter(la);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, PhotoViewerActivity.class);
                intent.putExtra("photo_index",position);
                startActivity(intent);
            }
        });

    }




}
