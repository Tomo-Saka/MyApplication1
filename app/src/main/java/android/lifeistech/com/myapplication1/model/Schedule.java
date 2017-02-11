package android.lifeistech.com.myapplication1.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tomoharu on 2017/01/28.
 */
public class Schedule extends SugarRecord {
    public String title;
    public Date date;

    public Schedule() {

    }

    public Schedule(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
