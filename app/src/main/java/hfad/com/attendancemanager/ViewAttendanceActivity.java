package hfad.com.attendancemanager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ViewAttendanceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
    }
    public void onTotalAttendance(View view)
    {
        Intent intent = new Intent(this,TotalAttendanceList.class);
        startActivity(intent);
    }
    public void onMonthlyAttendance(View view)
    {
        Intent intent =new Intent(this,SelectMonthActivity.class);
        startActivity(intent);
    }
    public void onDefaulters(View view)
    {
        CharSequence text = "Working!!!";
        int duration= Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this,text,duration);
        toast.show();
    }
}
