package hfad.com.attendancemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class TakeAttendanceActivity extends Activity {

    public static final String ClassId="ClassId";
    TextView classNameTV;
    TextView studentNameTV;
    TextView rollNoTV;
    SQLiteOpenHelper databaseHelper;
    SQLiteDatabase db;
    String tableName;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        classNameTV=(TextView)findViewById(R.id.class_name);
        studentNameTV=(TextView)findViewById(R.id.student_name);
        rollNoTV=(TextView)findViewById(R.id.roll_no);

        Intent intent=getIntent();
        tableName=intent.getStringExtra(ClassId);
        classNameTV.setText(tableName);

        try
        {
            databaseHelper=new AMDatabase(this);
            db=databaseHelper.getWritableDatabase();
            cursor = db.query(tableName, new String[]{"ID", "S_NAME", "PRESENT", "T_LECTURE", "PERCENTAGE"}, null, null, null, null, null);

            if(cursor.moveToFirst())
            {

                updateClassInfo();


            }

        }
        catch(SQLiteException e)
        {
            Toast toast = Toast.makeText(this, "cd Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void onPresent(View view)
    {

        incrementAttendance();
        incrementTotalLecture();

        if(cursor.moveToNext())
        {
            updateClassInfo();
        }
        else
        {   setPercentageAttendance();
            Intent intent=new Intent(this,FeatureListActivity.class);
            startActivity(intent);
            finish();
        }

    }
    public void onAbsent(View view)
    {
          incrementTotalLecture();

        if(cursor.moveToNext())
        {
            updateClassInfo();

        }
        else
        {
            setPercentageAttendance();
            Intent intent=new Intent(this,FeatureListActivity.class);
            startActivity(intent);
            finish();
        }
    }

   private void incrementAttendance()
   {

       int present = cursor.getInt(2);
       present++;
       db.execSQL("UPDATE " + tableName + " set PRESENT=" + present + " WHERE ID =" + cursor.getInt(0));

   }
   private void incrementTotalLecture()
   {
       int tLecture = cursor.getInt(3);
       tLecture++;
       db.execSQL("UPDATE "+tableName+" SET T_LECTURE="+tLecture+" WHERE ID = "+cursor.getInt(0));

   }
   private void setPercentageAttendance()
   {
       float totalAttendance;
       int present;
       int tLecture;
       cursor = db.query(tableName, new String[]{"ID", "S_NAME", "PRESENT", "T_LECTURE", "PERCENTAGE"}, null, null, null, null, null);
       if(cursor.moveToFirst())
       {
           present=cursor.getInt(2);
           tLecture=cursor.getInt(3);
           totalAttendance=((float)present/tLecture)*100;
           db.execSQL("UPDATE "+tableName+" set PERCENTAGE="+totalAttendance+" WHERE ID ="+cursor.getInt(0));

       }
       while(cursor.moveToNext())
       {
           present=cursor.getInt(2);
           tLecture=cursor.getInt(3);
           totalAttendance=((float)present/tLecture)*100;
           db.execSQL("UPDATE " + tableName + " set PERCENTAGE=" + totalAttendance + " WHERE ID =" + cursor.getInt(0));
       }
   }
    private void updateClassInfo()
    {
        String rollNo=Integer.toString(cursor.getInt(0));
        String name=cursor.getString(1);
        rollNoTV.setText(rollNo);
        studentNameTV.setText(name);

    }
     @Override
    public void onBackPressed()
     {
       new AlertDialog.Builder(this)
               .setIcon(android.R.drawable.ic_dialog_alert)
               .setTitle("Alert!!!").
               setMessage("Attendance still Incomplete").
               setNegativeButton("OK",null).show();
    }

}
