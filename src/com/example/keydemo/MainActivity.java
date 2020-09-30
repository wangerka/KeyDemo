package com.example.keydemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.hardware.input.InputManager;
import android.telephony.TelephonyManager;
import android.app.Instrumentation;
import android.util.Log;
//import android.os.Process;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import android.view.View;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	//long now = SystemClock.uptimeMillis();
	//KeyEvent down = new KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_POWER, 0);
	//android.util.Log.i("gege","ok:"+InputManager.getInstance().injectInputEvent(down, InputManager.INJECT_INPUT_EVENT_MODE_ASYNC));
	//KeyEvent up = new KeyEvent(now, now, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_POWER, 0);
	//InputManager.getInstance().injectInputEvent(up, InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);




    }

public void power(View view){
    new Thread(new Runnable() {
            @Override
            public void run() {
                try {
        Instrumentation inst = new Instrumentation();

        Log.i("gege", "Exception ppppp ");
//Thread.sleep(2000); 
        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_POWER);
    execByRuntime("input keyevent 26");
//execByRuntime("ls");
    } catch (Exception e) {
        Log.i("gege", "Exception " + e.toString());
    }
            }
        }).start();
}

public static String execByRuntime(String cmd) {
        Process process = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            inputStreamReader = new InputStreamReader(process.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
 
            int read;
            char[] buffer = new char[4096];
            StringBuilder output = new StringBuilder();
            while ((read = bufferedReader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
Log.i("gege", "Exception111 " + output.toString());
            return output.toString();
        } catch (Exception e) {
Log.i("gege", "Exception112221 " + e.toString());
            e.printStackTrace();
            return null;
        } finally {
Log.i("gege", "Exception22222222 ");
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (Throwable t) {
                    //
                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (Throwable t) {
                    //
                }
            }
            if (null != process) {
                try {
                    process.destroy();
                } catch (Throwable t) {
                    //
                }
            }
        }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
