package com.example.shinichi.ujianonline2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class login_siswa extends AppCompatActivity {
TextView RegisterSiswa;
    EditText NIS, Password;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);

        NIS = (EditText) findViewById(R.id.nis);
        Password = (EditText) findViewById(R.id.password);


        RegisterSiswa = (TextView) findViewById(R.id.register_siswa);
        RegisterSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_siswa.this,Register_Siswa.class));
            }
        });

    }
    public void loginSiswa (View view){
        if (NIS.getText().toString().equals("") || Password.getText().toString().equals("")) {
            //if (NIP.getText().toString().equals("") || Password.getText().toString().equals("") || con_pass.getText().toString().equals("") || ID.getText().toString().equals("") || Nama.getText().toString().equals("")||Kelas.getText().toString().equals("")|| Jenis_kelamin.getText().toString().equals("") || Agama.getText().toString().equals("")) {
            builder = new AlertDialog.Builder(login_siswa.this);
            builder.setTitle("Cek Kembali...");
            builder.setMessage("Tolong Di Isi Datanya...");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        String nis = NIS.getText().toString();
        String password = Password.getText().toString();
//        String nis = "1234566";
//        String password = "1234566";
        String type = "login_siswa";
        backLoginSiswa backLoginSiswa = new backLoginSiswa(this);
        backLoginSiswa.execute(type, nis, password);

    }

}
