package hfad.com.attendancemanager;
import android.app.AlertDialog;
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
    int count=1;
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
        if(count < no && !(s_name.getText().toString().matches("")))
        {
            String name=s_name.getText().toString();
            updateClass(name);

            s_name.getText().clear();



            roll_no.setText(Integer.toString(count+1));
            count++;

        }
        else if(count == no)
        {
            updateClass(s_name.getText().toString());
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
        ContentValues student = new ContentValues();
        student.put("S_NAME", name);
        student.put("PRESENT",0);
        student.put("T_LECTURE",0);
        student.put("PERCENTAGE",0);
        db.insert(classId, null, student);
    }
    catch (SQLiteException e)
    {
        Toast toast = Toast.makeText(this, "uc Database unavailable", Toast.LENGTH_SHORT);
        toast.show();
    }

    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Alert!!!").
                setMessage("More Students Detail to fill").
                setNegativeButton("OK",null).show();
    }

}
