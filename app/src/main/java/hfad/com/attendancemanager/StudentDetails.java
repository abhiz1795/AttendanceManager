package hfad.com.attendancemanager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentDetails extends Activity {
    public static final String NumberOfStudent ="NumberOfStudent";
    public static final String TableName="TableName";
    int no;
    int count=0;
    TextView roll_no;
    EditText s_name;
    String classId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Intent intent=getIntent();
        no=Integer.parseInt(intent.getStringExtra(NumberOfStudent));
        classId=intent.getStringExtra(TableName);
        roll_no=(TextView)findViewById(R.id.roll_no);
        roll_no.setText("1");
        s_name=(EditText)findViewById(R.id.student_name_value);
    }
    public void onNext(View view)
    {
        if(count < no-1 && !(s_name.getText().toString().matches("")))
        {
            String name=s_name.getText().toString();
            updateClass(name);

            s_name.getText().clear();

            count++;

            roll_no.setText(Integer.toString(count+1));

        }
        else if(count == no-1)
        {
            Intent intent=new Intent(this,FeatureListActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast toast = Toast.makeText(this, "Enter Student Name", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    private void updateClass(String name)
    {   try
    {
        SQLiteOpenHelper databaseHelper = new AMDatabase(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues studentName = new ContentValues();
        studentName.put("S_NAME", name);
        db.insert(classId, null, studentName);
    }
    catch (SQLiteException e)
    {
        Toast toast = Toast.makeText(this, "uc Database unavailable", Toast.LENGTH_SHORT);
        toast.show();
    }

    }

}
