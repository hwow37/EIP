package com.example.hk.eip.database;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hk.eip.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<InfoClass> infoList;
    private ViewHolder viewHolder;

    public CustomAdapter(Context c , ArrayList<InfoClass> array){
        inflater = LayoutInflater.from(c);
        infoList = array;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public InfoClass getItem(int arg0) {
        return infoList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = convertview;

        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.list_row, null);
            viewHolder.name = (TextView)v.findViewById(R.id.word_name);
            viewHolder.contact = (TextView)v.findViewById(R.id.word_meaning);
            viewHolder.img = (ImageView)v.findViewById(R.id.word_check);
            v.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.name.setText(infoList.get(position).name);
        viewHolder.contact.setText(infoList.get(position).meaning);
        if(infoList.get(position).check_word == 0) {
            viewHolder.img.setImageResource(0);
        }else{
            viewHolder.img.setImageResource(R.drawable.img_check);
        }

        return v;
    }

    public String getText(int position){
        return infoList.get(position).name;
    }

    public void setArrayList(ArrayList<InfoClass> arrays){
        this.infoList = arrays;
    }

    public ArrayList<InfoClass> getArrayList(){
        return infoList;
    }


    /*
     * ViewHolder
     */
    class ViewHolder{
        TextView name;
        TextView contact;
        ImageView img;
    }
}