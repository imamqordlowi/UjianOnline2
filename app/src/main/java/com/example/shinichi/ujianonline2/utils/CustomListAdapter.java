package com.example.shinichi.ujianonline2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shinichi.ujianonline2.R;
import com.example.shinichi.ujianonline2.Tampil_SoalPG;
import com.example.shinichi.ujianonline2.mengerjakan_ujianPG;
import com.example.shinichi.ujianonline2.model.modelSoal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ShiNiChI on 6/16/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    Context context;
    List<String> lNoSoal = new ArrayList<String>();
    List<String>  lSoal = new ArrayList<String>();
    List<String> lJawaban = new ArrayList<String>();
    List<modelSoal> throwMs = new ArrayList<modelSoal>();
    String[] noSoal, soal, jawaban;
    private static LayoutInflater inflater=null;
    public  CustomListAdapter(Context mainActivity, List<modelSoal> ms, List<String> jwb){
        context = mainActivity;
        inflater=( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        System.out.println("RESULTSIZE : "+ms.size());
        for (modelSoal res:ms) {
//            System.out.println("INSIDE : "+res.getsoal());
            lNoSoal.add(res.getnomor_soal_pg().toString());
            lSoal.add(res.getsoal().toString());
            throwMs.add(res);
        }

        if(jwb!=null&&jwb.size()>0){
            lJawaban = jwb;
        } else {
            for(int i = 0; i < ms.size(); i++) {
                lJawaban.add(" ");
            }
        }

        try {
            convertToArray();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void convertToArray(){
        int size = lNoSoal.size();
        noSoal = lNoSoal.toArray(new String[size]);
        soal = lSoal.toArray(new String[size]);
        jawaban = lJawaban.toArray(new String[size]);
    }

    @Override
    public int getCount() {
        return lNoSoal.size();
    }

    @Override
    public Object getItem(int position) {
        modelSoal throwMSfinal = throwMs.get(position);
        return throwMSfinal;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tvNoSoal, tvSoal, tvJawaban;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder =new Holder();
        View rowView = inflater.inflate(R.layout.ujian_list, null);
        holder.tvNoSoal=(TextView) rowView.findViewById(R.id.tvNoSoal);
        holder.tvSoal=(TextView) rowView.findViewById(R.id.tvSoal);
        holder.tvJawaban=(TextView) rowView.findViewById(R.id.tvJawaban);
        holder.tvNoSoal.setText(noSoal[position]);
        holder.tvSoal.setText(soal[position]);
        holder.tvJawaban.setText(jawaban[position]);
        return rowView;
    }}
