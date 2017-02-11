package android.lifeistech.com.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.lifeistech.com.myapplication1.model.Schedule;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;

/**
 * スケジュールの編集画面
 */
public class ScheduleEditActivity extends AppCompatActivity {
    public static String KEY_SCHEDULE_ID = "schedule_id";

    public static Intent createIntent(Context context, Schedule schedule) {
        Intent intent = new Intent(context, ScheduleEditActivity.class);
        intent.putExtra(KEY_SCHEDULE_ID, schedule.getId());
        return intent;
    }

    EditText editTextTitle;
    EditText editTextDate;

    private Schedule schedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);

        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextDate = (EditText) findViewById(R.id.editTextDate);

        // 前の画面からスケジュールのIDを受け取る　何も入ってなかったら-1
        long scheduleId = getIntent().getLongExtra(KEY_SCHEDULE_ID, -1);
        if (scheduleId == -1) {
            finish();
            return;
        }
        schedule = Schedule.findById(Schedule.class, scheduleId);

        editTextTitle.setText(schedule.title);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        editTextDate.setText(format.format(schedule.date));
    }

    public void edit(View v) {
        schedule.title = editTextTitle.getText().toString();
        // TODO Date
        schedule.save();
        setResult(RESULT_OK);
        finish();
    }

    public void delete(View v) {
        schedule.delete();
        setResult(RESULT_OK);
        finish();
    }
}
