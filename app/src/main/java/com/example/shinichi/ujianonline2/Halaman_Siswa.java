package com.example.shinichi.ujianonline2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shinichi.ujianonline2.helper.SessionManager;

public class Halaman_Siswa extends AppCompatActivity {
    TextView textView;
    SessionManager session;
    Button UAS, Keluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman__siswa);
        textView = (TextView)findViewById(R.id.halaman_Siswa);
        String message = getIntent().getStringExtra("message");
        textView.setText(message);

        session = new SessionManager(getApplicationContext());

        Keluar = (Button) findViewById(R.id.btnLogoutS);
        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                session.logoutUser();
                finish();
            }
        });




        UAS = (Button) findViewById(R.id.btUAS);
        UAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mengerjakan_UAS(UAS);
            }
        });
    }

    //mengerjakan SOAL UAS
    private void mengerjakan_UAS(View view) {
        Intent kerja_pg = new Intent(this, pilih_soal.class);
        startActivity(kerja_pg);
    }


}
