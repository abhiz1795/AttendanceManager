package hfad.com.attendancemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abhishek on 3/10/2016.
 */
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
        db.execSQL("CREATE TABLE USER(NAME TEXT,PASSWORD TEXT);");
        db.execSQL("CREATE TABLE CLASSES(ID INTEGER PRIMARY KEY AUTOINCREMENT,C_NAME TEXT,SUBJECT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
