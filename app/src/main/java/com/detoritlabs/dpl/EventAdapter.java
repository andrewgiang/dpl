package com.detoritlabs.dpl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detoritlabs.dpl.model.Event;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by andrewgiang on 7/11/14.
 */
public class EventAdapter extends ArrayAdapter<Event> {
    private final LayoutInflater mLayoutInflater;

    public EventAdapter(Context context, List<Event> objects) {
        super(context, R.layout.row_event, objects);
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event item = getItem(position);

        ViewHolder holder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.row_event, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDescription());
        return convertView;
    }

    public static class ViewHolder{
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.desc)
        TextView desc;

        ViewHolder(View v){
            ButterKnife.inject(this, v);
        }
    }
}
