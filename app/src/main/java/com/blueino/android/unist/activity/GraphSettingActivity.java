package com.blueino.android.unist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import com.blueino.android.unist.R;
import com.blueino.android.unist.util.PreferenceUtil;

public class GraphSettingActivity extends AppCompatActivity {

    private EditText maxEditText;
    private EditText minEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        maxEditText = (EditText) findViewById(R.id.maxEditText);
        minEditText = (EditText) findViewById(R.id.minEditText);

        if( getSupportActionBar() != null )
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        PreferenceUtil.save(this, PreferenceUtil.PREFERENCE_MAX_Y_SCALE, maxEditText.getText().toString());
        PreferenceUtil.save(this, PreferenceUtil.PREFERENCE_MIN_Y_SCALE, minEditText.getText().toString());

        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}