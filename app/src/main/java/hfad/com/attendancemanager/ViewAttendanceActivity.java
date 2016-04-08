package hfad.com.attendancemanager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ViewAttendanceActivity extends Activity {

    public static final String ClassId="ClassId";
    String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        Intent intent=getIntent();
        tableName=intent.getStringExtra(ClassId);
    }
    public void onTotalAttendance(View view)
    {
        Intent intent = new Intent(this,TotalAttendanceList.class);
        intent.putExtra(TotalAttendanceList.TABLENAME,tableName);
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
