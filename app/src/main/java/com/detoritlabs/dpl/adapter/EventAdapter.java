package com.detoritlabs.dpl.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.RssItem;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class EventAdapter extends ArrayAdapter<RssItem> {
    private final LayoutInflater mLayoutInflater;

    public EventAdapter(Context context, List<RssItem> objects) {
        super(context, R.layout.row_event, objects);
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RssItem item = getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_event, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(item.getTitle());
        Date date = parseDate(item.getDescription());

        return convertView;
    }

    private Date parseDate(String description) {
        Spanned spanned = Html.fromHtml(description);
        Log.i(EventAdapter.class.getName(), spanned.toString());

        return new Date();
    }

    public static class ViewHolder {

        @InjectView(R.id.title)
        TextView title;

        @InjectView(R.id.date)
        TextView date;

        @InjectView(R.id.time)
        TextView time;

        ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }
}
