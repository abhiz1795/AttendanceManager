package hfad.com.attendancemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class AMDatabase extends SQLiteOpenHelper
{
    private static final String DB_NAME="Attendance";
    private static final int DB_VERSION=1;

    AMDatabase(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE USER(NAME TEXT,PASSWORD TEXT);"); //NOS == Number of Student
        db.execSQL("CREATE TABLE CLASSES(_id INTEGER PRIMARY KEY AUTOINCREMENT,C_NAME TEXT,SUBJECT TEXT,NOS INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
