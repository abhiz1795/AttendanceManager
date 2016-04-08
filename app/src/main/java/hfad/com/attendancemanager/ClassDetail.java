package hfad.com.attendancemanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClassDetail extends Activity {
    String classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);}
    public void onEnterStudentDetails(View view)
    {   EditText no_of_student_value=(EditText)findViewById(R.id.no_of_student_value);
        updateClass();
        Intent intent=new Intent(this,StudentDetails.class);
        intent.putExtra(StudentDetails.NumberOfStudent,no_of_student_value.getText().toString());
        intent.putExtra(StudentDetails.TableName,classId);
        startActivity(intent);
        finish();
    }
    private void updateClass()
    {
        EditText className=(EditText)findViewById(R.id.class_value);
        EditText subject=(EditText)findViewById(R.id.subject_value);
        EditText nos=(EditText)findViewById(R.id.no_of_student_value);
        classId=className.getText().toString()+subject.getText().toString();
        try
        {
            SQLiteOpenHelper databaseHelper=new AMDatabase(this);
            SQLiteDatabase db=databaseHelper.getWritableDatabase();
            ContentValues class_details =new ContentValues();
            class_details.put("C_NAME",className.getText().toString());
            class_details.put("SUBJECT",subject.getText().toString());
            class_details.put("NOS",Integer.parseInt(nos.getText().toString()));
            db.insert("CLASSES", null, class_details);

            //CREATE TABLE EXAMPLE BEITBSPM ON NEW CLASS REGISTRATION
            db.execSQL("CREATE TABLE "+classId+" (ID INTEGER PRIMARY KEY  AUTOINCREMENT,S_NAME TEXT,PRESENT INTEGER,T_LECTURE INTEGER,PERCENTAGE REAL);");
        }
        catch(SQLiteException e)
        {
            Toast toast = Toast.makeText(this, "cd Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }

    }
}
