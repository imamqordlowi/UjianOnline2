package com.example.shinichi.ujianonline2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ShiNiChI on 4/18/2016.
 */
public class backLoginGuru extends AsyncTask <String, Void, String> {
    Context ctx;
    //AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;
   public backLoginGuru (Context ctx){
       this.ctx = ctx;
       activity = (Activity)ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String type = params [0];
        String nip = params [1];
        String password = params [2];
       // String login_guru_url = "http://192.168.43.161/ujianonline2/login_guru.php";
        //String login_guru_url = "http://192.168.43.161/Miku2/ujianonline2/login_guru.php";
        String login_guru_url = "http://ujian96jakarta.esy.es/ujianonline2/login_guru.php";
       // String login_guru_url = "http://192.168.100.6/ujianonline2/login_guru.php";
        if (type.equals("guru_login")){
            try {
                URL url = new URL(login_guru_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("nip", "UTF-8")+"="+URLEncoder.encode(nip,"UTF-8")+"&"
                        + URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line = br.readLine())!=null){
                    result += line;
                }
                br.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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
    protected void onPostExecute(String json) {
        try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            if (code.equals("login_true"))
            {

                Intent intent = new Intent(activity,Halaman_Guru.class);
                intent.putExtra("message", message);
                activity.startActivity(intent);
            }
            else if (code.equals("Login_false"))
            {
                showDialog("Login Failed", message,code);
            }
        }catch (Exception e){

        }
    }

    private void showDialog(String title,String message,String code) {
        builder.setTitle(title);
        if (code.equals("Login_false"))
        {
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText NIP, Password;
                    NIP = (EditText) activity.findViewById(R.id.nip);
                    Password = (EditText) activity.findViewById(R.id.password);
                    NIP.setText("");
                    Password.setText("");

                    dialog.dismiss();
                }
            });

        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
