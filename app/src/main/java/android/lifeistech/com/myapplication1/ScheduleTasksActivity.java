package android.lifeistech.com.myapplication1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by tomoharu on 2017/01/15.
 */
public class ScheduleTasksActivity extends ActionBarActivity {
    ListView listView2;
    EditText detailEditText;
    ArrayAdapter adapter;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tasks);

        listView2 = (ListView) findViewById(R.id.listView2);
        detailEditText = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1);
        listView2.setAdapter(adapter);

        pref=getSharedPreferences("pref_memo",MODE_PRIVATE);


        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //タップ時の処理
                ArrayAdapter adapter = (ArrayAdapter) listView2.getAdapter();

                String item = (String) adapter.getItem(position);
                adapter.remove(item);

                return false;
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //タップ時の処理
                Intent intent=new Intent(view.getContext(),ScheduleTaskDetailsActivity.class);
                startActivity(intent);
            }
        });

    }


    public void add (View v){
        String text;
        text=detailEditText.getText().toString();
        Toast.makeText(this, text,Toast.LENGTH_SHORT).show();

        adapter.add(text);
    }

    public void save2(View v){
        String detailText=detailEditText.getText().toString();

        SharedPreferences.Editor editor=pref.edit();
        editor.putString("key_detail",detailText);
        editor.commit();

        finish();

    }
}
