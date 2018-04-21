package com.example.rezafd.smartvillagert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rezafd.smartvillagert.API.SettingSession;

public class SettingActivity extends AppCompatActivity {

    ListView listsetting;
    SettingSession session = new SettingSession();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        session = new SettingSession(this);

        android.support.v7.widget.Toolbar toolbar_back = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listsetting=(ListView)findViewById(R.id.listsetting);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(SettingActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Setting));
        listsetting.setAdapter(mAdapter);
        listsetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(SettingActivity.this,ChangeProfile.class);
                    startActivity(intent);
                } else if (position==1){
                    session.saveSPBoolean(SettingSession.SP_SUDAH_LOGIN,false);
                    startActivity(new Intent(SettingActivity.this,LoginActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        });


    }
}
