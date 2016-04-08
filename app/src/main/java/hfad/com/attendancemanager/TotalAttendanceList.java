package hfad.com.attendancemanager;

import static hfad.com.attendancemanager.Constants.FIRST_COLUMN;
import static hfad.com.attendancemanager.Constants.SECOND_COLUMN;
import static hfad.com.attendancemanager.Constants.THIRD_COLUMN;
import static hfad.com.attendancemanager.Constants.FOURTH_COLUMN;
import static hfad.com.attendancemanager.Constants.FIFTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class TotalAttendanceList extends Activity {

	private ArrayList<HashMap<String, String>> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_attendance_list);
		
		ListView listView=(ListView)findViewById(R.id.listView1);
		populateList();
		ListViewAdapter adapter=new ListViewAdapter(this, list);
		listView.setAdapter(adapter);
	
	}

	private void populateList() {
		// TODO Auto-generated method stub
		
		list=new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> temp=new HashMap<String, String>();
			temp.put(FIRST_COLUMN, "1");
			temp.put(SECOND_COLUMN, "Aman");
			temp.put(THIRD_COLUMN, "2");
			temp.put(FOURTH_COLUMN, "5");
			temp.put(FIFTH_COLUMN,"40");
		list.add(temp);
		
		HashMap<String,String> temp2=new HashMap<String, String>();
		temp2.put(FIRST_COLUMN, "1");
		temp2.put(SECOND_COLUMN, "Harsh");
		temp2.put(THIRD_COLUMN, "2");
		temp2.put(FOURTH_COLUMN, "5");
		temp2.put(FIFTH_COLUMN,"40");
	list.add(temp2);
	
	HashMap<String,String> temp3=new HashMap<String, String>();
			temp3.put(FIRST_COLUMN, "1");
		temp3.put(SECOND_COLUMN, "Harsh");
		temp3.put(THIRD_COLUMN, "2");
		temp3.put(FOURTH_COLUMN, "5");
		temp3.put(FIFTH_COLUMN,"40");
		list.add(temp3);
		
			
	}
}
