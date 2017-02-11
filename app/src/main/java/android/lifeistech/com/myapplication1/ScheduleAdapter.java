package android.lifeistech.com.myapplication1;

import android.content.Context;
import android.lifeistech.com.myapplication1.model.Schedule;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by tomoharu on 2017/01/28.
 */
public class ScheduleAdapter extends ArrayAdapter<Schedule> {

    public ScheduleAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_schedule, null);
        }
        TextView textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
        Schedule schedule = getItem(position);
        textViewTitle.setText(schedule.title);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        textViewDate.setText(format.format(schedule.date));
        return convertView;
    }

    public void updateView() {

    }
}

