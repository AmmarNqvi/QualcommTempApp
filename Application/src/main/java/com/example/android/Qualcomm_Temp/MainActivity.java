
package com.example.android.Qualcomm_Temp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
public class MainActivity extends FragmentActivity implements SensorEventListener {

    public static final String TAG = "MainActivity";

    private boolean mTempUnit;
    private TextView temperaturelabel;
    private SensorManager mSensorManager;
    private Sensor mTemperature;
    private final static String NOT_SUPPORTED_MESSAGE = "Sorry, this device does not have a temperature sensor !";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperaturelabel = (TextView) findViewById(R.id.phoneTemp);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            UIBuild fragment = new UIBuild();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            mTemperature= mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);	// requires API level 14.
        }
        if (mTemperature == null) {
            temperaturelabel.setText(NOT_SUPPORTED_MESSAGE);
        }

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App?")
                .setMessage("Are you sure you want exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float ambient_temperature = event.values[0];
        temperaturelabel.setText("phone's Ambient Temperature:\n " + String.valueOf(ambient_temperature) + getResources().getString(R.string.celsius));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public native double[] sumAndAverage(double[] numbers,int flag);
    boolean mTempToggle = true;
    int flag =1;//To check if current display is farenheit or celsius
    public void ChangeUnits(MenuItem item) {

        TextView tv = (TextView)findViewById(R.id.temp);
        TextView tv1 = (TextView)findViewById(R.id.temp2);
        TextView tv2 = (TextView)findViewById(R.id.temp3);
        TextView tv3 = (TextView)findViewById(R.id.temp4);
        TextView tv4 = (TextView)findViewById(R.id.temp5);
        double[] numbers={1,2,3,4,5} ;
            numbers[0]=Double.parseDouble(String.valueOf(tv.getText()).substring(0,4));
            numbers[1]=Double.parseDouble(String.valueOf(tv1.getText()).substring(0,4));
            numbers[2]=Double.parseDouble(String.valueOf(tv2.getText()).substring(0,4));
            numbers[3]=Double.parseDouble(String.valueOf(tv3.getText()).substring(0,4));
            numbers[4]=Double.parseDouble(String.valueOf(tv4.getText()).substring(0,4));

        double[] results = new MainActivity().sumAndAverage(numbers,flag);
        if (mTempToggle==true){
            mTempToggle=false;
            flag=0;
            tv.setText (String.valueOf(results[0]).substring(0,4)+" °F");
            tv1.setText(String.valueOf(results[1]).substring(0,4)+" °F");
            tv2.setText(String.valueOf(results[2]).substring(0,4)+" °F");
            tv3.setText(String.valueOf(results[3]).substring(0,4)+" °F");
            tv4.setText(String.valueOf(results[4]).substring(0,4)+" °F");
        }
        else {
            mTempToggle=true;
            flag=1;
            tv.setText (String.valueOf(results[0]).substring(0,4)+" °C");
            tv1.setText(String.valueOf(results[1]).substring(0,4)+" °C");
            tv2.setText(String.valueOf(results[2]).substring(0,4)+" °C");
            tv3.setText(String.valueOf(results[3]).substring(0,4)+" °C");
            tv4.setText(String.valueOf(results[4]).substring(0,4)+" °C");
        }

    }
    static {
        System.loadLibrary("hello-jni");
    }
}
