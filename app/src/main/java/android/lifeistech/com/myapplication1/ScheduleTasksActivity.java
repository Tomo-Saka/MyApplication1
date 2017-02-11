package android.lifeistech.com.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.lifeistech.com.myapplication1.model.Schedule;
import android.lifeistech.com.myapplication1.model.Task;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

/**
 * スケジュールの中のタスク一覧画面
 */
public class ScheduleTasksActivity extends AppCompatActivity {

    public static String KEY_SCHEDULE_ID = "schedule_id";

    public static Intent createIntent(Context context, Schedule schedule) {
        Intent intent = new Intent(context, ScheduleTasksActivity.class);
        intent.putExtra(KEY_SCHEDULE_ID, schedule.getId());
        return intent;
    }

    ListView listView;
    EditText detailEditText;

    Schedule schedule;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tasks);

        listView = (ListView) findViewById(R.id.listView);
        detailEditText = (EditText) findViewById(R.id.editText);
        // 前の画面からスケジュールのIDを受け取る　何も入ってなかったら-1
        long scheduleId = getIntent().getLongExtra(KEY_SCHEDULE_ID, -1);
        if (scheduleId == -1) {
            finish();
            return;
        }
        schedule = Schedule.findById(Schedule.class, scheduleId);
        adapter = new TaskAdapter(this);

        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //タップ時の処理
                Task item = adapter.getItem(position);
                // ListViewから削除する
                adapter.remove(item);
                // データベースから削除する
                item.delete();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //タップ時の処理
                Intent intent = new Intent(view.getContext(), ScheduleTaskDetailsActivity.class);
                startActivity(intent);
            }
        });
        final List<Task> tasks = Task.find(Task.class, "schedule = ?", String.valueOf(scheduleId));
        adapter.addAll(tasks);
    }

    public void add(View v) {
        Task task = new Task();
        task.title = detailEditText.getText().toString();
        task.schedule = schedule;
        task.save();
        adapter.add(task);

        Toast.makeText(this, task.title, Toast.LENGTH_SHORT).show();
    }
}
