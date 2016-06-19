package com.example.shinichi.ujianonline2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinichi.ujianonline2.model.modelSoal;

public class mengerjakan_ujianPG extends AppCompatActivity {
    TextView tvDurasi, tvSoal, tvOpsiA, tvOpsiB, tvOpsiC, tvOpsiD, tvOpsiE;
    EditText etJawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mengerjakan_ujian_pg);
        tvDurasi = (TextView)findViewById(R.id.tvDurasi);
        tvSoal = (TextView)findViewById(R.id.tvSoal);
        tvOpsiA = (TextView)findViewById(R.id.tvOpsiA);
        tvOpsiB = (TextView)findViewById(R.id.tvOpsiB);
        tvOpsiC = (TextView)findViewById(R.id.tvOpsiC);
        tvOpsiD = (TextView)findViewById(R.id.tvOpsiD);
        tvOpsiE = (TextView)findViewById(R.id.tvOpsiE);
        etJawaban = (EditText)findViewById(R.id.etJawaban);

       modelSoal ms = (modelSoal) this.getIntent().getSerializableExtra("res");
//       Toast.makeText(mengerjakan_ujianPG.this,res.getsoal(), Toast.LENGTH_SHORT);

        try {
            isiParameter(ms);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void isiParameter (modelSoal ms) {
        tvSoal.setText(ms.getsoal());
        tvOpsiA.setText(ms.getopsi_a());
//        tvOpsiB.setText(ms.getopsi_b());
//        tvOpsiC.setText(ms.getopsi_c());
//        tvOpsiD.setText(ms.getopsi_d());
//        tvOpsiE.setText(ms.getopsi_e());
    }


}
