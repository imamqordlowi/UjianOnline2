package com.example.shinichi.ujianonline2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ShiNiChI on 3/22/2016.
 */
public class DibalikLayarSiswa extends AsyncTask<String,Void,String> {
    // String Register_url = "http://192.168.100.6/ujianonline/register.php";
    //String Register_url = "http://192.168.43.161/ujianonline/register_siswa.php";
    String Register_url = "http://ujian96jakarta.esy.es/ujianonline2/register_siswa.php";
    //String Register_url = "http://192.168.100.6/ujianonline/register_siswa.php";
    //String login_guru_url = "http://192.168.42.58/ujianonline/login_guru.php";
  //  String login_guru_url = "http://192.168.43.161/ujianonline/login_siswa.php";
    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    public DibalikLayarSiswa (Context ctx)
    {
        this.ctx = ctx;
        activity = (Activity)ctx;
    }
    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Harap Tunggu");
        progressDialog.setMessage("Sedang Connect ke server...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("Register_Siswa"))
        {
            try {
                URL url = new URL(Register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String nis = params[1];
                String password_siswa = params [2];
                String nama = params [3];
                String jenis_kelamin_siswa = params [4];
                String tmpt_lhr = params [5];
                String agama = params [6];
                String kelas = params [7];
                String ThnMasuk = params [8];




                String data = URLEncoder.encode("nis", "UTF-8")+"="+URLEncoder.encode(nis,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password_siswa,"UTF-8")+"&"+
                        URLEncoder.encode("nama_siswa","UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"+
                        URLEncoder.encode("jenis_kelamin","UTF-8")+"="+URLEncoder.encode(jenis_kelamin_siswa,"UTF-8")+"&"+
                        URLEncoder.encode("tempat_lahir","UTF-8")+"="+URLEncoder.encode(tmpt_lhr,"UTF-8")+"&"+
                        URLEncoder.encode("agama","UTF-8")+"="+URLEncoder.encode(agama,"UTF-8")+"&"+
                        URLEncoder.encode("kelas","UTF-8")+"="+URLEncoder.encode(kelas,"UTF-8")+"&"+
                        URLEncoder.encode("tahun_masuk","UTF-8")+"="+URLEncoder.encode(ThnMasuk,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line ="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line+"\n");
                }

                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");
                return stringBuilder.toString().trim();


            }   catch (Exception e){

            }
        }
        else if(method.equals("view_kelas")) {

        }

        return null;

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {
        try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            if (code.equals("regSiswa_true"))
            {
                showDialog("Register Success", message,code);
            }
            else if (code.equals("regSiswa_false"))
            {
                showDialog("Register Failed", message,code);
            }


            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } catch (Exception e) {

        }


    }
    public  void showDialog(String title,String message,String code)
    {
        builder.setTitle(title);
        if (code.equals("regSiswa_true")||code.equals("regSiswa_false"))
        {
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    activity.finish();

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }

    }



}
