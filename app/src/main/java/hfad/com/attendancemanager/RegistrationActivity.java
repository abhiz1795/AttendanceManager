package hfad.com.attendancemanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends Activity {

    private EditText username;
    private EditText password;
    private EditText confirm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username=(EditText)findViewById(R.id.user_name_value);
        password=(EditText)findViewById(R.id.password_value);
        confirm_password=(EditText)findViewById(R.id.confirm_password_value);
    }
    public void onRegister(View view)
    {
        if(password.getText().toString().equals(confirm_password.getText().toString()) && !(username.getText().toString().matches("")) && !(password.getText().toString().matches(""))) {
            try {
                SQLiteOpenHelper databaseHelper = new AMDatabase(this);
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                ContentValues user = new ContentValues();
                user.put("NAME",username.getText().toString());
                user.put("PASSWORD", password.getText().toString());
                db.insert("USER", null, user);

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
