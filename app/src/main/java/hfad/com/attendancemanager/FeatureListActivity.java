package hfad.com.attendancemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FeatureListActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_list);
    }
    public void onTakeOrViewAttendance(View view)
    {
        Intent intent =new Intent(this,ProvideClassActivity.class);
        startActivity(intent);
    }
    public void onAddClass(View view)
    {
        Intent intent =new Intent(this,ClassDetail.class);
        startActivity(intent);
        finish();
    }
}
