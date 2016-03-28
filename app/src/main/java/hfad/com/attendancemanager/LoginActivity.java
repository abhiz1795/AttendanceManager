package hfad.com.attendancemanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity
{
    private EditText username;
    private EditText password;
    private Button register;
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.user_name_value);
        password = (EditText) findViewById(R.id.password_value);
        register = (Button) findViewById(R.id.register);

        try {
            databaseHelper = new AMDatabase(this);
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("USER", new String[]{"NAME"}, null, null, null, null, null);
            int count = cursor.getCount();
            if (count > 0) {
                ViewGroup layout=(ViewGroup)username.getParent();
                layout.removeView(findViewById(R.id.register));
            }
            else {
                Button login=(Button)findViewById(R.id.login_button);
                login.setClickable(false);
            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, " LoginActivity Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void onLogin(View view)
    {
        String username=this.username.getText().toString();
        String password=this.password.getText().toString();

        try
        {
            cursor = db.query("USER", new String[]{"NAME", "PASSWORD"}, null, null, null, null, null);
            cursor.moveToFirst();
            if (username.matches(cursor.getString(0)) && password.matches(cursor.getString(1)))
            {
                Intent intent = new Intent(this, FeatureListActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast check_msg = Toast.makeText(this,"Username or password invalid!!", Toast.LENGTH_SHORT);
                check_msg.show();
            }
        }catch(SQLiteException e)
        {
            Toast toast =Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void onNewRegister(View view)
    {

        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
    public void onDestroy()
    {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}


