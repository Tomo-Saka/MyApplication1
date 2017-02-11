package android.lifeistech.com.myapplication1;

import android.content.Context;
import android.lifeistech.com.myapplication1.model.Schedule;
import android.lifeistech.com.myapplication1.model.Task;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, null);
        }
        TextView textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView textViewDone = (TextView) convertView.findViewById(R.id.textViewDone);
        Task task = getItem(position);
        textViewTitle.setText(task.title);
        if (task.isDone) {
            textViewDone.setText("完了");
        } else {
            textViewDone.setText("未完了");
        }
        return convertView;
    }
}

