package com.detoritlabs.dpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.RssItem;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        String[] dates = parseDate(item.getDescription());
        holder.date.setText(dates[0]);
        holder.time.setText(dates[1]);

        return convertView;
    }

    private String[] parseDate(String description) {
        Document parse = Jsoup.parse(description);
        Elements elementsByClass = parse.getElementsByClass("date-display-single");
        Element dateElement = elementsByClass.get(0);
        String dateText = dateElement.text();
        String[] split = dateText.split("-", 2);

        return split;
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
