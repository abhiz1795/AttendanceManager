package hfad.com.attendancemanager;

import static hfad.com.attendancemanager.Constants.FIRST_COLUMN;
import static hfad.com.attendancemanager.Constants.SECOND_COLUMN;
import static hfad.com.attendancemanager.Constants.THIRD_COLUMN;
import static hfad.com.attendancemanager.Constants.FOURTH_COLUMN;
import static hfad.com.attendancemanager.Constants.FIFTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

public class DefaulterListActivity extends Activity {

    public static final String TABLENAME="TableName";
    private ArrayList<HashMap<String, String>> list;
    String tableName;
    Cursor cursor;
    SQLiteOpenHelper databaseHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance_list);

        Intent intent=getIntent();
        tableName=intent.getStringExtra(TABLENAME);

        try
        {
            databaseHelper=new AMDatabase(this);
            db=databaseHelper.getReadableDatabase();
            cursor = db.query(tableName, new String[]{"ID", "S_NAME", "PRESENT", "T_LECTURE", "PERCENTAGE"},"PERCENTAGE < ?",new String[]{Integer.toString(75)}, null, null, null);
        }
        catch (SQLiteException e)
        {
            Toast.makeText(this,"Database Unavailable",Toast.LENGTH_SHORT).show();
        }

        ListView listView=(ListView)findViewById(R.id.listView1);
        populateList();
        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

    }

    private void populateList() {
        // TODO Auto-generated method stub
        String id;
        String name;
        String present;
        String tLecture;
        String perAttendance;
        list=new ArrayList<HashMap<String,String>>();


        HashMap<String,String> temp;


        if (cursor.moveToFirst())
        {
            temp=new HashMap<String, String>();
            id=Integer.toString(cursor.getInt(0));
            name=cursor.getString(1);
            present=Integer.toString(cursor.getInt(2));
            tLecture=Integer.toString(cursor.getInt(3));
            perAttendance=Float.toString(cursor.getFloat(4));
            temp.put(FIRST_COLUMN, id);
            temp.put(SECOND_COLUMN, name);
            temp.put(THIRD_COLUMN, present);
            temp.put(FOURTH_COLUMN, tLecture);
            temp.put(FIFTH_COLUMN, perAttendance);
            list.add(temp);
        }
        while(cursor.moveToNext())
        {
            temp=new HashMap<String, String>();
            id=Integer.toString(cursor.getInt(0));
            name=cursor.getString(1);
            present=Integer.toString(cursor.getInt(2));
            tLecture=Integer.toString(cursor.getInt(3));
            perAttendance=Float.toString(cursor.getFloat(4));
            temp.put(FIRST_COLUMN, id);
            temp.put(SECOND_COLUMN, name);
            temp.put(THIRD_COLUMN, present);
            temp.put(FOURTH_COLUMN, tLecture);
            temp.put(FIFTH_COLUMN, perAttendance);
            list.add(temp);
        }


    }
}
