package com.example.shinichi.ujianonline2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shinichi.ujianonline2.app.AppConfig;
import com.example.shinichi.ujianonline2.app.AppController;
import com.example.shinichi.ujianonline2.helper.SQLiteHandler;
import com.example.shinichi.ujianonline2.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class login_guru extends AppCompatActivity {
    TextView Register_Guru;
    EditText NIP, Password;
    //Button LOGIN;
   // String nip,password;
AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);

  NIP = (EditText) findViewById(R.id.nip);
        Password = (EditText) findViewById(R.id.password);

        Register_Guru = (TextView) findViewById(R.id.register);
       Register_Guru.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(login_guru.this, Register.class));
           }
       });

    }

    public void login_guru (View view){
        if (NIP.getText().toString().equals("") || Password.getText().toString().equals("")) {
            //if (NIP.getText().toString().equals("") || Password.getText().toString().equals("") || con_pass.getText().toString().equals("") || ID.getText().toString().equals("") || Nama.getText().toString().equals("")||Kelas.getText().toString().equals("")|| Jenis_kelamin.getText().toString().equals("") || Agama.getText().toString().equals("")) {
            builder = new AlertDialog.Builder(login_guru.this);
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
        String nip = NIP.getText().toString();
        String password = Password.getText().toString();
        String type = "guru_login";
        backLoginGuru backLoginGuru = new backLoginGuru(this);
        backLoginGuru.execute(type, nip, password);

    }

}

