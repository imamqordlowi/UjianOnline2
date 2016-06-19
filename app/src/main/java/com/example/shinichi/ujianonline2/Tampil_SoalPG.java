package com.example.shinichi.ujianonline2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shinichi.ujianonline2.model.modelSoal;
import com.example.shinichi.ujianonline2.utils.AsyncResult;
import com.example.shinichi.ujianonline2.utils.CustomListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Tampil_SoalPG extends AppCompatActivity implements AsyncResult {
ListView lvSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil__soal_pg);
        lvSoal = (ListView) findViewById(R.id.lvSoal);
        String res = this.getIntent().getStringExtra("res");
        backTampilSoal asyncSoal = new backTampilSoal(this);

        asyncSoal.delegate = this;
        asyncSoal.execute(res);
    }

    @Override
    public void processFinish(List<modelSoal> asyncResult) {
        renderLV(asyncResult);
    }

    public void renderLV(List<modelSoal> asyncResult) {
        CustomListAdapter z = new CustomListAdapter(this, asyncResult, null);
//        ArrayAdapter<modelSoal> z = new ArrayAdapter<modelSoal>(this,android.R.layout.simple_list_item_1,isi);
        lvSoal.setAdapter(z);
        lvSoal.setClickable(true);
        lvSoal.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                modelSoal throwMs = new modelSoal();
                throwMs = (modelSoal) parent.getAdapter().getItem(position);
                Intent b = new Intent(Tampil_SoalPG.this, mengerjakan_ujianPG.class);
                b.putExtra("res", throwMs);
                startActivity(b);
            }
        });
    }
}