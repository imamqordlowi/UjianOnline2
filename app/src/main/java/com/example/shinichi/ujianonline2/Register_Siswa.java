package com.example.shinichi.ujianonline2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Entity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shinichi.ujianonline2.model.modelKelas;

import org.apache.http.util.EntityUtils;
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
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Register_Siswa extends AppCompatActivity {
EditText NIS, Password_Siswa, ConPasswordSiswa, Nama, TMP_LHR, Jenis_Kelamin_Siswa, Agama, ThnMasuk;
    Button BTRegisterSiswa;

   // static final int DATE_DIALOG_ID = 1;

    //CalendarView ;
  //  private String[] arrMonth = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    ArrayList<String> aarray;
    ArrayList<String>ary_int;
    List<modelKelas> hb;
    JSONObject data;
   // int mYear,mMonth, mDay;

    modelKelas modelData;

    Spinner Kelas;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__siswa);
        NIS = (EditText)findViewById(R.id.nis);
        Password_Siswa = (EditText)findViewById(R.id.password_siswa);
        ConPasswordSiswa = (EditText)findViewById(R.id.con_password_siswa);
        ThnMasuk = (EditText)findViewById(R.id.ThnMsk);
        TMP_LHR = (EditText) findViewById(R.id.tmp_lhr);
        Nama = (EditText)findViewById(R.id.nama);
        Kelas = (Spinner)findViewById(R.id.kelas);
        Jenis_Kelamin_Siswa = (EditText)findViewById(R.id.jenis_kelamin_siswa);
        Agama = (EditText)findViewById(R.id.agama);
         hb = new ArrayList<>();




        try {




            aarray = new ArrayList<>();
            ary_int = new ArrayList<>();

           data = new data().execute().get();
            JSONArray aryjson = data.getJSONArray("row");

            for(int a=0; a<aryjson.length() ; a++){


               modelKelas c = new modelKelas();
                JSONObject Kelas = aryjson.getJSONObject(a);
                aarray.add(Kelas.getString("nama_kelas"));
                c.setId_kelas(Kelas.getString("id_kelas"));
                c.setNama_kelas(Kelas.getString("nama_kelas"));
                hb.add(c);

            }



        }catch (Exception e){

        }


        //JSONObject o = kelasData();




        ArrayAdapter adapt = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,aarray);

        Kelas.setAdapter(adapt);

        BTRegisterSiswa = (Button)findViewById(R.id.btRegister_siswa);
        BTRegisterSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NIS.getText().toString().equals("") || Password_Siswa.getText().toString().equals("") || ConPasswordSiswa.getText().toString().equals("") || ThnMasuk.getText().toString().equals("") || Nama.getText().toString().equals("") || Jenis_Kelamin_Siswa.getText().toString().equals("") || Agama.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(Register_Siswa.this);
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

                } else if (!(Password_Siswa.getText().toString().equals(ConPasswordSiswa.getText().toString()))) {
                    builder = new AlertDialog.Builder(Register_Siswa.this);
                    builder.setTitle("Ada yang Salah, Coba cek kembali...");
                    builder.setMessage("Password Anda Tidak Sama....");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Password_Siswa.setText("");
                            ConPasswordSiswa.setText("");

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                } else {


                    String id_kelas="";
                    for(modelKelas ac :hb){
                        ac.getId_kelas();
                        if(ac.getNama_kelas() == Kelas.getSelectedItem().toString()){

                            id_kelas= ac.getId_kelas();
                        }
                    }

                    int id = Integer.parseInt(id_kelas);

                    DibalikLayarSiswa dibalikLayarSiswa = new DibalikLayarSiswa(Register_Siswa.this);
                    //              dibalikLayar.execute("Register",NIS.getText().toString(),Password_Siswa.getText().toString(),ID.getText().toString(),Nama.getText().toString(),Jenis_kelamin.getText().toString(),Agama.getText().toString());
                    dibalikLayarSiswa.execute("Register_Siswa", NIS.getText().toString(), Password_Siswa.getText().toString(), Nama.getText().toString(),  Jenis_Kelamin_Siswa.getText().toString(), TMP_LHR.getText().toString(), Agama.getText().toString(), id_kelas,  ThnMasuk.getText().toString());


                }


            }
        });

    }



}




class data extends AsyncTask<Void,String,JSONObject>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject json;

try {
        URL url = new URL("http://ujian96jakarta.esy.es/ujianonline2/kelas.php");
//    URL url = new URL("http://192.168.43.161/ujianonline2/kelas.php");
   //URL url = new URL("http://192.168.100.6/ujianonline2/kelas.php");
    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
    //httpURLConnection.setRequestMethod("GET");
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setDoInput(true);
    int ddd = httpURLConnection.getResponseCode();
    InputStream inputStream = httpURLConnection.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder stringBuilder = new StringBuilder();
    String line ="";
    while ((line=bufferedReader.readLine())!=null)
    {
        stringBuilder.append(line+"\n");
    }

    httpURLConnection.disconnect();
    //Thread.sleep(5000);
    Log.d("Test", "Test 3 pass");
    String adsds = stringBuilder.toString().trim();

    json = new JSONObject(adsds);



}catch (Exception e){
    json = null;
}

        return json;



    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }

}
