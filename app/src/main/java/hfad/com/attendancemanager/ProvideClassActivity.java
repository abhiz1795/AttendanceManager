package hfad.com.attendancemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ProvideClassActivity extends Activity
{
    SQLiteDatabase db;
    SQLiteOpenHelper classDetailHelper;
    Spinner className;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_class);
        updateClassDetails();
    }
    public void onSubmit(View view)
    {
        RadioGroup operation =(RadioGroup)findViewById(R.id.operation);
        int id = operation.getCheckedRadioButtonId();

        Cursor classId=(Cursor)className.getSelectedItem();
        String value=classId.getString(classId.getColumnIndex("C_ID"));



       switch(id)
        {
            case R.id.take_attendance:
                Intent intentTakeAttendance =new Intent(this,TakeAttendanceActivity.class);

                intentTakeAttendance.putExtra(TakeAttendanceActivity.ClassId,value);
                startActivity(intentTakeAttendance);
                break;
            case R.id.view_attendance:
                Intent intentViewAttendance = new Intent(this,ViewAttendanceActivity.class);
                 intentViewAttendance.putExtra(ViewAttendanceActivity.ClassId,value);
                startActivity(intentViewAttendance);
                break;

        }
    }

    private void updateClassDetails()
    {
        try
        {
            classDetailHelper=new AMDatabase(this);
            db=classDetailHelper.getReadableDatabase();

            Cursor cursor = db.query("CLASSES", new String[]{"_id","C_ID"}, null, null, null, null, null);
            CursorAdapter classAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"C_ID"},
                    new int[]{android.R.id.text1},
                    0);
            className=(Spinner)findViewById(R.id.classes);
            className.setAdapter(classAdapter);



        }
        catch(SQLiteException e)
        {
            Toast toast=Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
