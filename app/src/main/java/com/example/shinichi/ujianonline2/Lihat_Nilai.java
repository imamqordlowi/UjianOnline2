package com.example.shinichi.ujianonline2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lihat_Nilai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat__nilai);

        backNilai backnilai = new backNilai(Lihat_Nilai.this);
        backnilai.execute();

    }
}
