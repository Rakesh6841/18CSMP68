package com.computerlabspace.parsexmlandjson_program6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button parseXMLBtn, parseJSONBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseXMLBtn = (Button) findViewById(R.id.main_parse_xml_btn_id);
        parseJSONBtn = (Button) findViewById(R.id.main_parse_json_id);
        parseXMLBtn.setOnClickListener(this);
        parseJSONBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(parseXMLBtn)) {
            // Intent will act like a broadcaster
            Intent intent = new Intent(this,ViewDataActivity.class);
            intent.putExtra("dataType", "xml");
            startActivity(intent);
        } else if(view.equals(parseJSONBtn)) {
            Intent intent = new Intent(this,ViewDataActivity.class);
            intent.putExtra("dataType", "json");
            startActivity(intent);
        }
    }
}