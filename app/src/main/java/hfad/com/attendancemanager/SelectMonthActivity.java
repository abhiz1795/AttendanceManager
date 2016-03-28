package hfad.com.attendancemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class SelectMonthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_month);

    }
    public void onSubmit(View view)
    {
        CharSequence text = "Working!!!";
        int duration= Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this,text,duration);
        toast.show();
    }

}
