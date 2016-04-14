package hfad.com.attendancemanager;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentDetails extends Activity {
    public static final String NumberOfStudent = "NumberOfStudent";
    public static final String ClassName="ClassName";
    public static final String SubjectName="SubjectName";
    int no;
    int count = 0;
    TextView roll_no;
    EditText s_name;
    String classId;
    String[] studentNameArray;



    String classNameET;
    String subjectNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Intent intent = getIntent();
        no = Integer.parseInt(intent.getStringExtra(NumberOfStudent));
        studentNameArray = new String[no];
        classNameET=intent.getStringExtra(ClassName);
        subjectNameET=intent.getStringExtra(SubjectName);

        classId=classNameET.toUpperCase().concat(subjectNameET.toUpperCase());

        roll_no = (TextView) findViewById(R.id.roll_no);
        roll_no.setText("1");
        s_name = (EditText) findViewById(R.id.student_name_value);

    }

    public void onNext(View view) {  //Updated Code
        String name;
        if (count < no - 1 && !(s_name.getText().toString().matches(""))) {
            name = s_name.getText().toString();
            studentNameArray[count] = name;
            count++;
            roll_no.setText(Integer.toString(count + 1));
            s_name.getText().clear();
        }
        else if (s_name.getText().toString().matches(""))
        {
            Toast.makeText(this,"Fill Student name ",Toast.LENGTH_LONG).show();
        }
        else {
            name = s_name.getText().toString();
            studentNameArray[count] = name;


            ViewGroup layout = (ViewGroup) s_name.getParent();
            layout.removeAllViews();

            Button submitButton = new Button(this);
            submitButton.setText("Submit");

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentDetails.this);
                    alertDialog.setTitle("Submit?");
                    alertDialog.setMessage("New Class " + classId + " will be added");
                    alertDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateClass();
                        }
                    }).setNegativeButton("No", null).show();


                }
            });
            layout.addView(submitButton);


        }
    }

       /* if(count < no && !(s_name.getText().toString().matches("")))
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
*/


    private void updateClass() {
        try {
            SQLiteOpenHelper databaseHelper = new AMDatabase(this);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            ContentValues class_details =new ContentValues();
            class_details.put("C_NAME",classNameET);
            class_details.put("SUBJECT",subjectNameET);
            class_details.put("NOS",Integer.toString(no));
            class_details.put("C_ID",classId);
            db.insert("CLASSES", null, class_details);


            //CREATE TABLE EXAMPLE BEITB-SPM ON NEW CLASS REGISTRATION
            db.execSQL("CREATE TABLE " + classId + " (ID INTEGER PRIMARY KEY  AUTOINCREMENT,S_NAME TEXT,PRESENT INTEGER,T_LECTURE INTEGER,PERCENTAGE REAL);");

            for (int i = 0; i < no; i++) {
                ContentValues student = new ContentValues();
                student.put("S_NAME", studentNameArray[i]);
                student.put("PRESENT", 0);
                student.put("T_LECTURE", 0);
                student.put("PERCENTAGE", 0);
                db.insert(classId, null, student);

            }
            Intent intent = new Intent(StudentDetails.this, FeatureListActivity.class);
            startActivity(intent);
            finish();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "uc Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure you want to EXIT ?")
                    .setMessage("No Class will be addded.")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(StudentDetails.this, FeatureListActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("No", null).show();



    }


}
