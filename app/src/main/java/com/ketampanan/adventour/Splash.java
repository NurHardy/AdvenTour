package com.ketampanan.adventour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yasir on 3/5/2016.
 */
public class Splash extends AppCompatActivity {
    public static long ms = 0;
    public static long waktu = 3000;
    public boolean aktif = true;
    public boolean paused = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    while (aktif && ms < waktu){
                        if (!paused)
                            ms = ms+100;
                        sleep(100);
                    }
                } catch (Exception e) {

                }
                finally {
                    Intent a = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(a);
                    finish();
                }
            }
        };
        thread.start();
    }
}
