package com.detoritlabs.dpl.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.RssItem;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.text.DateFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by andrewgiang on 7/11/14.
 */
public class NewsAdapter extends ArrayAdapter<RssItem> {
    private final LayoutInflater mLayoutInflater;

    public NewsAdapter(Context context, List<RssItem> objects) {
        super(context, R.layout.row_news, objects);
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RssItem item = getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_news, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(item.getTitle());
        holder.desc.setText(NetworkUtil.stripHtml(item.getDescription()));
        String pubDate = item.getPubDate();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");
        DateTime parse = DateTime.parse(pubDate, formatter);
        CharSequence formatDateTime = DateUtils.getRelativeTimeSpanString(getContext(), parse, DateUtils.FORMAT_ABBREV_RELATIVE);
        holder.datePublished.setText(formatDateTime);
        return convertView;
    }

    public static class ViewHolder {
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.desc)
        TextView desc;
        @InjectView(R.id.date_published)
        TextView datePublished;

        ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }
}
