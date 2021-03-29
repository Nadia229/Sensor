package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "Sensor";
    private static final String TABLE_NAME = "Sensor_value";
    private static final String ID = "_id";
    private static final String LIGHT = "sensor_name";
    static final String PRO = "sensor_name";
    static final String ACC = "sensor_name";
    static final String GY = "sensor_name";
   // private static final String VALUE = "value";
    private static final int version_NAME = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LIGHT+" INTEGER,"+PRO+" INTEGER,"+ACC+" INTEGER,"+GY+" INTEGER);";


    private Context context;
    public MyDatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, version_NAME);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(context,"On Create called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertdata (String light,String pro,String acc,String gy)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIGHT,light);
        contentValues.put(PRO,pro);
        contentValues.put(ACC,acc);
        contentValues.put(GY,gy);
        long rowid = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowid;
    }
}
