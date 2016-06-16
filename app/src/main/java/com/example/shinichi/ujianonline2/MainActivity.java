package com.example.shinichi.ujianonline2;


import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

import android.content.Intent;

import android.view.View;





public class MainActivity extends Activity {
    Button LoginSiswa;
    Button LoginGuru;
    Button InformasiNilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginSiswa = (Button) findViewById(R.id.btLoginSiswa);
        LoginSiswa.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                menuloginsiswa(LoginSiswa);
            }
        });

//       Untuk masuk ke login guru

        LoginGuru = (Button) findViewById(R.id.btLoginGuru);
        LoginGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menuloginguru(LoginGuru);
            }
        });


        InformasiNilai = (Button) findViewById(R.id.btInformasi);
        InformasiNilai.setOnClickListener(new View.OnClickListener () {

            public void onClick(View arg0) {
                menuinformasinilai(InformasiNilai);
            }
        });
    }

    private void menuinformasinilai(View view) {
        Intent menuinformasinilai = new Intent(this, Lihat_Nilai.class);
        startActivity(menuinformasinilai);
    }


    private void menuloginsiswa(View view) {
         Intent menuloginsiswa = new Intent(this, login_siswa.class);
         startActivity(menuloginsiswa);
    }

/*
AaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
*/

    private void menuloginguru(View view) {
        Intent menuloginguru = new Intent(this, login_guru.class);
        startActivity(menuloginguru);
    }
}
