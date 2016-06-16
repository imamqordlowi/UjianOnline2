package com.example.shinichi.ujianonline2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
//EditText NIP,Password, con_pass, ID,Nama,Kelas,Jenis_kelamin,Agama;
    EditText NIP,Password, con_pass,Nama,Jenis_kelamin,Agama;
    Button REGISTER;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        NIP = (EditText)findViewById(R.id.nip);
        Password = (EditText)findViewById(R.id.password);
        con_pass = (EditText)findViewById(R.id.con_password);
       // ID = (EditText)findViewById(R.id.id);
        //Kelas = (EditText)findViewById(R.id.kelas);
        Nama = (EditText)findViewById(R.id.nama);
        Jenis_kelamin = (EditText)findViewById(R.id.jenis_kelamin);
        Agama = (EditText)findViewById(R.id.agama);

        REGISTER = (Button)findViewById(R.id.btRegister);
        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NIP.getText().toString().equals("") || Password.getText().toString().equals("") || con_pass.getText().toString().equals("") || Nama.getText().toString().equals("")|| Jenis_kelamin.getText().toString().equals("") || Agama.getText().toString().equals("")) {
             //if (NIP.getText().toString().equals("") || Password.getText().toString().equals("") || con_pass.getText().toString().equals("") || ID.getText().toString().equals("") || Nama.getText().toString().equals("")||Kelas.getText().toString().equals("")|| Jenis_kelamin.getText().toString().equals("") || Agama.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(Register.this);
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
                else if (!(Password.getText().toString().equals(con_pass.getText().toString()))) {
                    builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("Cek Kembali...");
                    builder.setMessage("Password Anda tidak Sama...");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Password.setText("");
                            con_pass.setText("");

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }


                else

                {
                    DibalikLayar dibalikLayar = new DibalikLayar(Register.this);
                    dibalikLayar.execute("Register",NIP.getText().toString(),Password.getText().toString(),Nama.getText().toString(),Jenis_kelamin.getText().toString(),Agama.getText().toString());
       //           dibalikLayar.execute("Register",NIP.getText().toString(),Password.getText().toString(),ID.getText().toString(),Nama.getText().toString(),Kelas.getText().toString(),Jenis_kelamin.getText().toString(),Agama.getText().toString());

                }
            }
        });

    }
}
