package com.mornning.lunar;

import android.util.Log;

import java.util.Calendar;


public class KLunar {

    static final String lunarMonthName[] = {
            "腊", "正", "二", "三",
            "四", "五", "六", "七",
            "八", "九", "十", "冬"
    };
    static final String lunarDayName[] = {
            "三十", "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九",
            "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九",
            "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九"
    };
    //天干
    static final String nature[] = {
            "癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬"
    };

    //地支
    static final String earth[] = {
            "亥", "子", "丑", "寅", "卯", "辰", "巳", "午", "末", "申", "酉", "戌"
    };

    //属相名
    static final String animal[] = {
            "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗"
    };

    //节气
    static final String solarTerm[][] = {
            {"大雪", "冬至"}, {"小寒", "大寒"}, {"立春", "雨水"},
            {"惊蛰", "春分"}, {"清明", "谷雨"}, {"立夏", "小满"},
            {"芒种", "夏至"}, {"小暑", "大暑"}, {"立秋", "处暑"},
            {"白露", "秋分"}, {"寒露", "霜降"}, {"立冬", "小雪"}
    };

    public static LunarDate sunToLunar(Calendar calendar) {
        Log.d("KLunar", String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)));
        int[] result = sunToLunar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        LunarDate date = new LunarDate();
        date.nature = nature[result[0]];
        date.earth = earth[result[1]];
        date.animal = animal[result[2]];
        date.lunarMonth = lunarMonthName[result[4] % 12];
        date.lunarDay = lunarDayName[result[5] % 30];
        date.isRn = result[3] == 1;
        date.month = result[4] % 12;
        date.day = result[5] % 30;
        date.dayCount = result[6];
        date.solar = result[8] == -1 ? "" : solarTerm[(calendar.get(Calendar.MONTH) + 1) % 12][result[8]];
        return date;
    }

    public static native int[] sunToLunar(int year, int month, int day);

    static {
        System.loadLibrary("klunar");
    }
}
