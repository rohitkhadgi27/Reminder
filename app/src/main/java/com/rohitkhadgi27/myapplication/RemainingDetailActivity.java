package com.rohitkhadgi27.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class RemainingDetailActivity extends ActionBarActivity {

    private Toolbar toolBar;
    ArrayList<GS> workList;
    int id;
    String title, detail;
    EditText et_title, et_detail;
    Button btn_remainder;
    Button btn_save;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining_detail);

        db = new DatabaseHandler(this);
        workList = new ArrayList<>();

        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        id = i.getIntExtra("id", 0);
        title = i.getStringExtra("title");
        detail = i.getStringExtra("detail");

        et_title = (EditText) findViewById(R.id.et_title);
        et_detail = (EditText) findViewById(R.id.et_detail);
        btn_save = (Button) findViewById(R.id.remaining__detail_save_button);
        btn_remainder = (Button) findViewById(R.id.remaining__detail_remainder_button);

        et_title.setText(title);
        et_detail.setText(detail);

        btn_remainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.shiftItem(id);
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String detail = et_detail.getText().toString();
                db.updateIteminfo(id, title, detail);
                Toast.makeText(RemainingDetailActivity.this, "saved", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remaining_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
