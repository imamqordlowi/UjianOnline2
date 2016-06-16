package com.example.shinichi.ujianonline2;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by ShiNiChI on 6/6/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.RecyclerViewHolder>
    {
        private static final int TYPE_HEAD = 0;
        private static final int TYPE_LIST = 1;
        ArrayList<Nilai> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Nilai> arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_HEAD)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,parent,false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType);
            return recyclerViewHolder;
        }else if (viewType==TYPE_LIST) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType);
            return recyclerViewHolder;
        }
return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {

        if (holder.viewType==TYPE_LIST){

            Nilai nilai = arrayList.get(position-1);
            holder.txidgs.setText(nilai.getIdgs());
            holder.txnis.setText(nilai.getNilai());
            holder.txnilai.setText(nilai.getNilai());


        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView txidgs,txnis,txnilai;
        int viewType;
        public RecyclerViewHolder(View view,int viewType)
        {
         super(view);
        if (viewType==TYPE_LIST)
        {
            txidgs = (TextView) view.findViewById(R.id.idgroup);
            txnilai = (TextView)view.findViewById(R.id.txtNilai);
            txnis = (TextView) view.findViewById(R.id.txtNIS);
            this.viewType = TYPE_LIST;

        }
        else if (viewType==TYPE_HEAD)
        {
            this.viewType = TYPE_HEAD;
        }

        }

    }

        @Override
        public int getItemViewType(int position) {
            if (position==0)
                return TYPE_HEAD;
            return TYPE_LIST;
        }
    }