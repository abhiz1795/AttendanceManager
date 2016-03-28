package hfad.com.attendancemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class TakeAttendanceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
    }
    public void onPresent(View view)
    {
        CharSequence text ="Present";
        int duration= Toast.LENGTH_SHORT;
        Toast toast=Toast.makeText(this,text,duration);
        toast.show();
    }
    public void onAbsent(View view)
    {
        CharSequence text ="Absent";
        int duration= Toast.LENGTH_SHORT;
        Toast toast=Toast.makeText(this,text,duration);
        toast.show();
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



}
