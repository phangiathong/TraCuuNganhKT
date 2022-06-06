package com.academy.tracuunganhkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailsEconomicActivity extends AppCompatActivity {

    TextView txtId, txtLevel, txtName, txtExplain, txtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_economic);

        txtId = findViewById(R.id.txtIdDetail);
        txtLevel = findViewById(R.id.txtLevelDetail);
        txtName = findViewById(R.id.txtNameDetail);
        txtExplain = findViewById(R.id.txtExplainDetail);
        txtBack = findViewById(R.id.txtBack);

        Toolbar toolbar = findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        int level = intent.getIntExtra("level",0);
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");

        txtId.setText(id);
        txtLevel.setText(""+level);
        txtName.setText(name);
        txtExplain.setText(content);

        txtExplain.setMovementMethod(new ScrollingMovementMethod());

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}