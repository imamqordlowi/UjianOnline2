package com.example.shinichi.ujianonline2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ShiNiChI on 5/18/2016.
 */
public class dibalikSoalPG extends AsyncTask<String,Void,String> {
    //String soal_url = "http://192.168.43.161/ujianonline/register_siswa.php";
    String soal_url = "http://ujian96jakarta.esy.es/ujianonline2/pilihan_ganda.php";
    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    public dibalikSoalPG (Context ctx)
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
        if (method.equals("pilihan_ganda"))
        {
            try {
                URL url = new URL(soal_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String idgs = params[1];
                String noSoal = params [2];
                String xSoal = params [3];
                String xopsiA = params [4];
                String xopsiB = params [5];
                String xopsiC = params [6];
                String xopsiD = params [7];
                String xopsiE = params [8];
                String xjawaban = params [9];

                String data = URLEncoder.encode("id_group_soal", "UTF-8")+"="+URLEncoder.encode(idgs,"UTF-8")+"&"+
                        URLEncoder.encode("nomor_soal_pg","UTF-8")+"="+URLEncoder.encode(noSoal,"UTF-8")+"&"+
                        URLEncoder.encode("soal","UTF-8")+"="+URLEncoder.encode(xSoal,"UTF-8")+"&"+
                        URLEncoder.encode("option_a","UTF-8")+"="+URLEncoder.encode(xopsiA,"UTF-8")+"&"+
                        URLEncoder.encode("option_b","UTF-8")+"="+URLEncoder.encode(xopsiB,"UTF-8")+"&"+
                        URLEncoder.encode("option_c","UTF-8")+"="+URLEncoder.encode(xopsiC,"UTF-8")+"&"+

                        URLEncoder.encode("option_d","UTF-8")+"="+URLEncoder.encode(xopsiD,"UTF-8")+"&"+
                        URLEncoder.encode("option_e","UTF-8")+"="+URLEncoder.encode(xopsiE,"UTF-8")+"&"+
                        URLEncoder.encode("jawaban","UTF-8")+"="+URLEncoder.encode(xjawaban,"UTF-8");

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
                Log.d("Success", "Success");
                //Log.d("Test", "Test 3 pass");
                return stringBuilder.toString().trim();

            }   catch (Exception e){
                e.printStackTrace();
                Log.d("Error","Failed to save");
            }
        }
        else if(method.equals("view_idgs")) {

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
            if (code.equals("save_true"))
            {
                showDialog("Simpan Success", message,code);
            }
            else if (code.equals("save_false"))
            {
                showDialog("Simpan Failed", message,code);
            }


            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } catch (Exception e) {

        }


    }
    public  void showDialog(String title,String message,String code)
    {
        builder.setTitle(title);
        if (code.equals("save_true")||code.equals("save_false"))
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
