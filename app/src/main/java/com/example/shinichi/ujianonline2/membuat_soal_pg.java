package com.example.shinichi.ujianonline2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shinichi.ujianonline2.model.modelKelas;
import com.example.shinichi.ujianonline2.model.modelPG;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class membuat_soal_pg extends AppCompatActivity {
EditText nomSoal, xSoal, xopsiA, xopsiB, xopsiC, xopsiD, xopsiE, xJawaban;
    Button xbtnSimpan;

    ArrayList<String> aarray;
    ArrayList<String>ary_int;
    List<modelPG> hb;
    JSONObject data;
    modelPG modelData;

    Spinner idgs;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membuat_soal_pg);

        nomSoal = (EditText)findViewById(R.id.nosoal);
        xSoal = (EditText)findViewById(R.id.soal);
        xopsiA = (EditText)findViewById(R.id.opsiA);
        xopsiB = (EditText)findViewById(R.id.opsiB);
        xopsiC = (EditText) findViewById(R.id.opsiC);
        xopsiD = (EditText)findViewById(R.id.opsiD);
        xopsiE = (EditText) findViewById(R.id.opsiE);
        xJawaban = (EditText)findViewById(R.id.jawaban);
        idgs = (Spinner)findViewById(R.id.idgroup);


        hb = new ArrayList<>();

        try {




            aarray = new ArrayList<>();
            ary_int = new ArrayList<>();

            data = new data().execute().get();
            JSONArray aryjson = data.getJSONArray("row");

            for(int a=0; a<aryjson.length() ; a++){


                modelPG c = new modelPG();
                JSONObject idgs = aryjson.getJSONObject(a);
                aarray.add(idgs.getString("id_group_soal"));
                c.setId_group_soal(idgs.getString("id_group_soal"));

                hb.add(c);

            }



        }catch (Exception e){

        }

        ArrayAdapter adapt = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,aarray);
        idgs.setAdapter(adapt);
        xbtnSimpan = (Button)findViewById(R.id.btSimpan);
        xbtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomSoal.getText().toString().equals("") || xSoal.getText().toString().equals("") || xopsiA.getText().toString().equals("") || xopsiB.getText().toString().equals("") || xopsiC.getText().toString().equals("") || xopsiD.getText().toString().equals("") || xopsiE.getText().toString().equals("") || xJawaban.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(membuat_soal_pg.this);
                    builder.setTitle("Ada yang Salah, Coba cek kembali...");
                    builder.setMessage("Tolong Isi Data Anda Dengan Benar....");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else  {


                    String id_group_soal="";
                    for(modelPG ac :hb){
                        ac.getId_group_soal();
                        if(ac.getId_group_soal() == idgs.getSelectedItem().toString()){

                            id_group_soal= ac.getId_group_soal();
                        }
                    }

                    int id = Integer.parseInt(id_group_soal);

                    dibalikSoalPG dibalikSoalPG = new dibalikSoalPG(membuat_soal_pg.this);
                    //              dibalikLayar.execute("Register",NIS.getText().toString(),Password_Siswa.getText().toString(),ID.getText().toString(),Nama.getText().toString(),Jenis_kelamin.getText().toString(),Agama.getText().toString());
                    dibalikSoalPG.execute("pilihan_ganda", id_group_soal, nomSoal.getText().toString(), xSoal.getText().toString(), xopsiA.getText().toString(), xopsiB.getText().toString(), xopsiC.getText().toString(), xopsiD.getText().toString(), xopsiE.getText().toString(), xJawaban.getText().toString());


                }


            }
        });

    }


    class data extends AsyncTask<Void,String,JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject json;

            try {

                URL url = new URL("http://ujian96jakarta.esy.es/ujianonline2/idgroupsoal.php");
                //URL url = new URL("http://192.168.100.6/ujianonline2/kelas.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                int ddd = httpURLConnection.getResponseCode();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                httpURLConnection.disconnect();
                //Thread.sleep(5000);
                String adsds = stringBuilder.toString().trim();

                json = new JSONObject(adsds);


            } catch (Exception e) {
                json = null;
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
        }


    }
    }
