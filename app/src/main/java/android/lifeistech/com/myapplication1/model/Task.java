package android.lifeistech.com.myapplication1.model;

import com.orm.SugarRecord;

/**
 * Created by tomoharu on 2017/02/11.
 */
public class Task extends SugarRecord {
    public String title;
    public String contents;
    public boolean isDone = false;

    public Schedule schedule;

    public Task() {

    }

    public Task(String title, String contents, Schedule schedule, boolean isDone) {
        this.title = title;
        this.contents = contents;
        this.schedule = schedule;
        this.isDone = isDone;
    }

}
