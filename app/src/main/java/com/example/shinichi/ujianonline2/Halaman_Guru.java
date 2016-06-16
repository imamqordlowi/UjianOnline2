package com.example.shinichi.ujianonline2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shinichi.ujianonline2.helper.SessionManager;

public class Halaman_Guru extends AppCompatActivity {
TextView textView;
    Button soalPG, Keluar;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman__guru);
    textView = (TextView)findViewById(R.id.halaman_guru);
        String message = getIntent().getStringExtra("message");
        textView.setText(message);

        session = new SessionManager(getApplicationContext());

        Keluar = (Button) findViewById(R.id.btnLogoutG);
        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                session.logoutUser();
                finish();
            }
        });







        soalPG = (Button) findViewById(R.id.btninputsoalpg);
        soalPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membuat_soal_pg(soalPG);
            }
        });
    }

    //membuat soal PG
    private void membuat_soal_pg(View view) {
        Intent membuat_pg = new Intent(this, membuat_soal_pg.class);
        startActivity(membuat_pg);
    }


}
