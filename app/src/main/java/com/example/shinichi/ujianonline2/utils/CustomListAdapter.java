package com.example.shinichi.ujianonline2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shinichi.ujianonline2.R;
import com.example.shinichi.ujianonline2.model.modelSoal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShiNiChI on 6/16/2016.
 */
public class CustomListAdapter {
//public class CustomListAdapter extends BaseAdapter implements View.OnClickListener {
//   private Context aContext;
//    private List<modelSoal> amodelSoal;
//
//    public CustomListAdapter(Activity a, ArrayList d, Resources resLocal){
////        activity = a;
//        data=d;
//        res=resLocal;
//
//        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//
//        if (data.size()<=0)
//            return 1;
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//
//        return position;
//    }
//
//    public static class ViewHolder{
//        public TextView text;
//        public TextView text1;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View vi = convertView;
//        ViewHolder holder;
//
//        if (convertView==null);{
//            vi = inflater.inflate(R.layout.activity_tampil__soal_pg,null);
//            holder = new ViewHolder();
//            holder.text = (TextView) vi.findViewById(R.id.text);
//            holder.text1 = (TextView) vi.findViewById(R.id.text1);
//
//            vi.setTag(holder);
//        }
//
//   return vi;
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
}
