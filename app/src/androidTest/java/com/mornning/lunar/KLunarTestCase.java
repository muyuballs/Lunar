package com.mornning.lunar;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;


public class KLunarTestCase extends AndroidTestCase {


    @SmallTest
    public void testA() throws Exception {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        for (int i = 0; i < 365; i++) {
            LunarDate date = KLunar.sunToLunar(cal);
            Log.d("MA", date.toString());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}