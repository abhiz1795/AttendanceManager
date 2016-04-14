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

    EditText className;
    EditText subject;
    EditText no_of_student_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        className=(EditText)findViewById(R.id.class_value);
        subject=(EditText)findViewById(R.id.subject_value);

        no_of_student_value=(EditText)findViewById(R.id.no_of_student_value);

    }
    public void onEnterStudentDetails(View view)
    {
       String c_name;
        String s_name;
        String nos;

        c_name=className.getText().toString();
        s_name=subject.getText().toString();
        nos=no_of_student_value.getText().toString();
        if(!(c_name.matches("")) && !(s_name.matches("")) && !(nos.matches("")))
        {
            Intent intent = new Intent(this, StudentDetails.class);
            intent.putExtra(StudentDetails.NumberOfStudent, no_of_student_value.getText().toString());
            intent.putExtra(StudentDetails.ClassName, className.getText().toString());
            intent.putExtra(StudentDetails.SubjectName, subject.getText().toString());
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this,"Details incomplete",Toast.LENGTH_LONG).show();
        }
    }



}
