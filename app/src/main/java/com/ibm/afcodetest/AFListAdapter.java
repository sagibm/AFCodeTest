package com.ibm.afcodetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ibm.afcodetest.model.AFData;

import java.util.ArrayList;

/**
 * Created by sheldongabriel on 6/16/16.
 */
public class AFListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AFData> list;

    private class Holder {
        TextView topDescView;
    }


    public AFListAdapter(Context context,ArrayList<AFData> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AFData entry = getItem(position);
        Holder holder = new Holder();

        // Find views
        if (convertView == null) {

            convertView = layout.inflate(R.layout.af_listview,null);
            holder.topDescView = (TextView) convertView.findViewById(R.id.topdescription);
            convertView.setTag(holder);

        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.topDescView.setText(entry.getTitle());

        return convertView;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public AFData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }


}
