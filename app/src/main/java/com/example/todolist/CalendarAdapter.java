package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends ArrayAdapter<Date> {
    private final Context context;
    private final List<Date> dates;

    public CalendarAdapter(Context context, List<Date> dates) {
        super(context, 0, dates);
        this.context = context;
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_calendar, parent, false);
        }

        TextView dateTextView = convertView.findViewById(R.id.listView);
        Date date = dates.get(position);

        // Format ngày theo cách bạn muốn, ví dụ: ngày/tháng/năm
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateTextView.setText(sdf.format(date));

        return convertView;
    }
}
