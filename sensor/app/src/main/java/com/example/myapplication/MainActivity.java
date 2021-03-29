package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    MyDatabaseHelper myDatabaseHelper;


    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;

    SensorManager sensorManager;
    Sensor light,proxi,accelerometer,gyro;

    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();




        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView4 = findViewById(R.id.text4);
        textView5 = findViewById(R.id.text5);
        textView6 = findViewById(R.id.text6);
        textView7 = findViewById(R.id.text7);
        textView8 = findViewById(R.id.text8);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);


        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light!=null)
        {
            sensorManager.registerListener(this,light,sensorManager.SENSOR_DELAY_NORMAL);

        }
        else
        {
            textView.setText("light not supported");
        }



        proxi = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(light!=null)
        {
            sensorManager.registerListener(this,proxi,sensorManager.SENSOR_DELAY_NORMAL);

        }
        else
        {
            textView2.setText("proxi not supported");
        }

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer!=null)
        {
            sensorManager.registerListener(this,accelerometer,sensorManager.SENSOR_DELAY_NORMAL);

        }
        else
        {
            textView3.setText("light not supported");
        }
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyro!=null)
        {
            sensorManager.registerListener(this,gyro,sensorManager.SENSOR_DELAY_NORMAL);

        }
        else
        {
            textView6.setText("light not supported");
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                String light = textView.toString();
                String proximeter = textView2.toString();
                String accele = textView3.toString();
                String gyr = textView6.toString();


                long rowid = myDatabaseHelper.insertdata(light,proximeter,accele,gyr);

                if(rowid==-1)
                {
                    Toast.makeText(getApplicationContext(),"unsuccessfull",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Row "+rowid+" is inserted successfully",Toast.LENGTH_LONG).show();
                }



            }
        },300000);








    }





    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;

        if(sensor.getType()==sensor.TYPE_LIGHT)
        {
            textView.setText(""+event.values[0]);
        }
        else if(sensor.getType()==sensor.TYPE_PROXIMITY)
        {
            textView2.setText(""+event.values[0]);
        }
        else if(sensor.getType()==sensor.TYPE_ACCELEROMETER)
        {
            textView3.setText(""+event.values[0]);
            textView4.setText(""+event.values[1]);
            textView5.setText(""+event.values[2]);
        }
        else if(sensor.getType()==sensor.TYPE_GYROSCOPE)
        {
            textView6.setText(""+event.values[0]);
            textView7.setText(""+event.values[1]);
            textView8.setText(""+event.values[2]);
        }




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}