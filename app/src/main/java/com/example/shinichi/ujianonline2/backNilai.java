package com.example.shinichi.ujianonline2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by ShiNiChI on 6/6/2016.
 */
public class backNilai extends AsyncTask<Void, Nilai, Void> {
    Context ctx;
    Activity activity;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ArrayList<Nilai> arrayList = new ArrayList<>();



    public backNilai (Context ctx)
    {
        this.ctx = ctx;
        activity = (Activity)ctx;
    }





    @Override
    protected void onPreExecute() {

        recyclerView = (RecyclerView)activity.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Tunggu Sebentar");
        progressDialog.setMessage("List is loading");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected Void doInBackground(Void... params) {
        String json_string = "http://ujian96jakarta.esy.es/ujianonline2/hasil_nilai.php";
        try {
            URL url = new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line="";

            while ((line=bufferedReader.readLine())!=null)
            {

                stringBuilder.append(line+"\n");
            }

            httpURLConnection.disconnect();
            String hasil_ujian = stringBuilder.toString().trim();
            Log.d("HASIL_UJIAN>>>>", hasil_ujian);

            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            while (count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;
                Nilai nilai = new Nilai(JO.getString("id_group_soal"),JO.getString("nis"),JO.getString("nilai"));
                publishProgress(nilai);
                Thread.sleep(1000);
            }





             } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
}



    @Override
    protected void onProgressUpdate(Nilai... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onPostExecute(Void aVoid) {
       progressDialog.dismiss();
    }
}
