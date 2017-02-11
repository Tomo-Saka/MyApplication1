package android.lifeistech.com.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tomoharu on 2017/01/14.
 */
public class ScheduleEditActivity extends AppCompatActivity {
    public String title;
    public Date date;
    private int position;
    EditText editText3;
    EditText editText4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);

        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        date = new Date(intent.getLongExtra("date", new Date().getTime()));
        position = intent.getIntExtra("position", 0);
        editText3.setText(title);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        editText4.setText(format.format(date));
    }

    public void edit(View v) {
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("date", date.getTime());
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
