package com.example.shinichi.ujianonline2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shinichi.ujianonline2.model.modelKelas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class pilih_soal extends AppCompatActivity {
    Button btMsk;
    EditText etSoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_soal);
        // Declaration
        etSoal = (EditText) findViewById(R.id.GroupSoal);
        btMsk = (Button) findViewById(R.id.btMsK);

        btMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etSoal.getText().equals("")) {
                    Intent a = new Intent(pilih_soal.this, Tampil_SoalPG.class);
                    a.putExtra("res", etSoal.getText().toString());
                    startActivity(a);
                }
            }
        });
    }




}
