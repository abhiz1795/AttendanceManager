package hfad.com.attendancemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProvideClassActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_class);
    }
    public void onSubmit(View view)
    {
        RadioGroup operation =(RadioGroup)findViewById(R.id.operation);
        int id = operation.getCheckedRadioButtonId();
        switch(id)
        {
            case R.id.take_attendance:Intent intentTakeAttendance =new Intent(this,TakeAttendanceActivity.class);
                startActivity(intentTakeAttendance);
                break;
            case R.id.view_attendance:Intent intent = new Intent(this,ViewAttendanceActivity.class);
                startActivity(intent);
                break;

        }
    }

}
