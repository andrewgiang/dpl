package com.detoritlabs.dpl.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.RssItem;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.GregorianCalendar;
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
        final RssItem item = getItem(position);

        ViewHolder holder;
        ViewHolder2 holder2;
            if( item.getIsOnlyDate() ){
                convertView = mLayoutInflater.inflate(R.layout.row_event_header, parent, false);
                holder2 = new ViewHolder2(convertView);
                convertView.setTag(holder2);
                String[] dates = parseDate(item.getDescription());
                holder2.title.setText(dates[0]);
                convertView.setEnabled(false);
                convertView.setOnClickListener(null);
            }else{
                convertView = mLayoutInflater.inflate(R.layout.row_event, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
                String[] dates = parseDate(item.getDescription());
                holder.title.setText(item.getTitle());

                holder.time.setText(dates[1]);
                holder.addToCalendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //create intent
                        Intent calIntent = new Intent(Intent.ACTION_INSERT);
                        calIntent.setData(CalendarContract.Events.CONTENT_URI);

                        //set calendar details
                        calIntent.setType("vnd.android.cursor.item/event");
                        calIntent.putExtra(CalendarContract.Events.TITLE, item.getTitle());
                        String location = parseLocation(item.getDescription());
                        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, NetworkUtil.stripHtml(location));
                        //calIntent.putExtra(CalendarContract.Events.DESCRIPTION, NetworkUtil.stripHtml(item.getDescription()));

                        //set date and time
                        GregorianCalendar calDate = new GregorianCalendar(2012, 7, 15);
                        //calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                                calDate.getTimeInMillis());
                        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                                calDate.getTimeInMillis());

                        getContext().startActivity(calIntent);
                    }
                });
            }

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

    private String parseLocation(String description) {
        Document parse = Jsoup.parse(description);
        Elements elementsByClass = parse.getElementsByClass("");
        Element locationElement = elementsByClass.get(0);
        String locationText = locationElement.text();

        return locationText;
    }

    public static class ViewHolder {

        @InjectView(R.id.title)
        TextView title;

        @InjectView(R.id.time)
        TextView time;

        @InjectView(R.id.add_to_calendar)
        ImageButton addToCalendar;

        ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }
    public static class ViewHolder2 {

        @InjectView(R.id.title)
        TextView title;

        ViewHolder2(View v) {
            ButterKnife.inject(this, v);
        }
    }
}
