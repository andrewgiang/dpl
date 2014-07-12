package com.detoritlabs.dpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.Hour;
import com.detoritlabs.dpl.model.Location;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class LocationAdapter extends ArrayAdapter<Location> {


    private final LayoutInflater mLayoutInflater;

    public LocationAdapter(Context context, List<Location> objects) {
        super(context, R.layout.row_location, objects);
        mLayoutInflater = LayoutInflater.from(getContext());

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Location location = getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_location, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(location.getName());
        holder.phone.setText(location.getNumber());
        holder.address.setText(location.getAddress());

        holder.hours.removeAllViews();
        for(Hour hour: location.getHours()){
            TextView textView = new TextView(getContext());
            textView.setText(hour.formattedText());
            holder.hours.addView(textView);

        }
        return convertView;
    }

    public static class ViewHolder {
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.phone)
        TextView phone;
        @InjectView(R.id.address)
        TextView address;
        @InjectView(R.id.hours)
        LinearLayout hours;

        ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }
}
