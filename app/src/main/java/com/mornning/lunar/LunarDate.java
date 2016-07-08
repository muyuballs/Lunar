package com.mornning.lunar;

public class LunarDate {
    public int day;
    public int month;
    public boolean isRn;
    public String nature;
    public String earth;
    public String animal;
    public String solar;
    public String lunarDay;
    public String lunarMonth;
    public int dayCount;

    @Override
    public String toString() {
        return nature+earth+"年"+(isRn?"潤":"")+lunarMonth+"月"+lunarDay+" "+animal+" ("+dayCount+") "+" "+solar;
    }
}
