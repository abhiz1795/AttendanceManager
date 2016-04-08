package hfad.com.attendancemanager;

import android.app.Activity;
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
    int tLecture;

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
       // setPercentageAttendance();
        if(cursor.moveToNext())
        {
            updateClassInfo();
        }
        else
        {
            Intent intent=new Intent(this,FeatureListActivity.class);
            startActivity(intent);
            finish();
        }

    }
    public void onAbsent(View view)
    {
          incrementTotalLecture();
         // setPercentageAttendance();
        if(cursor.moveToNext())
        {
            updateClassInfo();

        }
        else
        {
            Intent intent=new Intent(this,FeatureListActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void onPrevious(View view)
    {
        CharSequence text ="Previous";
        int duration= Toast.LENGTH_SHORT;
        Toast toast=Toast.makeText(this,text,duration);
        toast.show();
    }
    public void onSubmit(View view)
    {
        CharSequence text ="Submit";
        int duration= Toast.LENGTH_SHORT;
        Toast toast=Toast.makeText(this,text,duration);
        toast.show();
    }
   private void incrementAttendance() {

      int  present = cursor.getInt(2);
       present++;
       db.execSQL("UPDATE " + tableName + " set PRESENT=" + present + " WHERE ID =" + cursor.getInt(0));

   }
   private void incrementTotalLecture()
   {
       tLecture = cursor.getInt(3);
       tLecture++;
       db.execSQL("UPDATE "+tableName+" SET T_LECTURE="+tLecture+" WHERE ID = "+cursor.getInt(0));

   }
   private void setPercentageAttendance()
   {
       float percentageAttendance;
       float presentLectures=cursor.getInt(2);
       float totalLecture=cursor.getInt(3);
       float hund=100;
       percentageAttendance =(presentLectures/totalLecture)*hund;
       db.execSQL("UPDATE "+tableName+" set PERCENTAGE="+percentageAttendance+" WHERE ID ="+cursor.getInt(0));

   }
    private void updateClassInfo()
    {
        String rollNo=Integer.toString(cursor.getInt(0));
        String name=cursor.getString(1);
        rollNoTV.setText(rollNo);
        studentNameTV.setText(name);

    }


}
