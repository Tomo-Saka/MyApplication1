package android.lifeistech.com.myapplication1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.lifeistech.com.myapplication1.model.Schedule;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class SchedulesActivity extends AppCompatActivity {
    private static final int REQUESTCODE = 1;
    ListView listView;
    EditText editText2;
    ScheduleAdapter adapter;
    TextView textViewDate;

    DatePickerDialog datePickerDialog;

    // 日付設定時のリスナ作成
    DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(android.widget.DatePicker datePicker, int year,
                              int monthOfYear, int dayOfMonth) {

            // トーストとログ出力
            Log.d("DatePicker", "year:" + year + " monthOfYear:" + monthOfYear
                    + " dayOfMonth:" + dayOfMonth);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = calendar.getTime();
            textViewDate.setText(format.format(date));
            textViewDate.setTag(date);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);
        listView = (ListView) findViewById(R.id.listView);
        editText2 = (EditText) findViewById(R.id.editText2);
        adapter = new ScheduleAdapter(this);
        listView.setAdapter(adapter);
        textViewDate = (TextView) findViewById(R.id.textViewDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        textViewDate.setText(format.format(date));
        textViewDate.setTag(date);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Schedule schedule = adapter.getItem(position);
                //タップ時の処理
                Intent intent = new Intent(view.getContext(), ScheduleEditActivity.class);
                intent.putExtra("title", schedule.title);
                intent.putExtra("date", schedule.date.getTime());
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUESTCODE);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //タップ時の処理
                Intent intent = new Intent(view.getContext(), ScheduleTasksActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add2(View v) {
        Schedule schedule = new Schedule();
        schedule.title = editText2.getText().toString();
        schedule.date = (Date) textViewDate.getTag();
        adapter.add(schedule);

        Toast.makeText(this, schedule.title, Toast.LENGTH_SHORT).show();
    }

    public void clickDate(View v) {
        // 日付情報の初期設定
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR); // 年
        int monthOfYear = calendar.get(Calendar.MONTH); // 月
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 日

        // 日付設定ダイアログの作成・リスナの登録
        datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Black_NoTitleBar, DateSetListener, year,
                monthOfYear, dayOfMonth);

        // 日付設定ダイアログの表示
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE:
                if (resultCode == RESULT_OK) {
                    Schedule schedule = adapter.getItem(data.getIntExtra("position", 0));
                    schedule.title = data.getStringExtra("title");
                    schedule.date = new Date(data.getLongExtra("date", new Date().getTime()));
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}