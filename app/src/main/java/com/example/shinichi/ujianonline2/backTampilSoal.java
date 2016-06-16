package com.example.shinichi.ujianonline2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

import com.example.shinichi.ujianonline2.model.modelSoal;
import com.example.shinichi.ujianonline2.utils.AsyncResult;
import com.google.gson.Gson;

/**
 * Created by ShiNiChI on 6/5/2016.
 */
public class backTampilSoal extends AsyncTask<String, Void, String> {
    Context ctx;
    //AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    public backTampilSoal(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    public AsyncResult delegate = null;

    @Override
    protected String doInBackground(String... params) {
        String idgs = params[0];


        // String login_guru_url = "http://192.168.43.161/ujianonline2/login_guru.php";
        //String login_guru_url = "http://192.168.43.161/Miku2/ujianonline2/login_guru.php";
        String login_guru_url = "http://ujian96jakarta.esy.es/ujianonline2/tampil_soal.php";
        // String login_guru_url = "http://192.168.100.6/ujianonline2/login_guru.php";

            try {
                URL url = new URL(login_guru_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("idgs", "UTF-8") + "=" + URLEncoder.encode((String) idgs, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
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
            JSONArray jsonArray = jsonObject.getJSONArray("row");
            List<modelSoal> listSoal= new ArrayList<modelSoal>();
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject JO = jsonArray.getJSONObject(i);
                modelSoal ms = new modelSoal();
                Gson gson = new Gson();
                ms = gson.fromJson(JO.toString(), modelSoal.class);
                listSoal.add(ms);
            }
            if(listSoal!=null&&listSoal.size()>0) {
              delegate.processFinish(listSoal);
            } else {
                Toast.makeText(this.ctx,"No Data", Toast.LENGTH_SHORT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDialog(String title, String message, String code) {
        builder.setTitle(title);
        if (code.equals("Login_false")) {
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