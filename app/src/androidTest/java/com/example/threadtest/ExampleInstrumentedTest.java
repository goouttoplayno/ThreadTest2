package com.example.threadtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.threadtest", appContext.getPackageName());
    }

    @Test
    public void fixedThreadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        //每次执行三个线程，

            final int index = 1;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        System.out.println(index);
                        Log.i(TAG, index + " ");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

    }
}
