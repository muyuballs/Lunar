#include "test_help.h"
#include "com_mornning_lunar_KLunar.h"
#include <memory.h>
#include <jni.h>

int sun_to_lunar(SunDate sunDate,int lunar[11]){
	memset(lunar,0,11);
	LunarDate* pLD=(LunarDate*)malloc(sizeof(LunarDate));
	lunar[0]=yearToNatureIndex(sunDate);
	lunar[1]=EA(sunDate);
	lunar[2]=EA(sunDate);
	int tmp=lunarLeapMonth(sunDate.year);
	BL* bl=getLunarBLArray(sunDate.year);
	int ret=sunToLunar(pLD,&sunDate,bl,tmp);
	if(ret>=0){
		if(tmp){
			if(pLD->month<tmp){
				lunar[3]=0;
				lunar[4]=pLD->month+1;
			}else if(pLD->month>tmp){
				lunar[3]=0;
				lunar[4]=pLD->month;
			}else{
				lunar[3]=1;
				lunar[4]=tmp;
			}
		}
		else{
			lunar[3]=0;
			lunar[4]=pLD->month;
		}
		lunar[5]=pLD->day;
		if(tmp){
		    lunar[6]=bl->array[tmp];
        }
        else{
            lunar[6]=bl->array[lunar[4]-1];
        }
		int solarDay=solarTermDayInMonth(sunDate.year,sunDate.month,0);
		if(solarDay==sunDate.day){
			lunar[8]=0;
		}else{
			solarDay=solarTermDayInMonth(sunDate.year,sunDate.month,1);
			if(solarDay==sunDate.day){
				lunar[8]=1;
			}else{
				lunar[8]=-1;
			}
		}
	}
	free(pLD);
	pLD=NULL;
	freeBLArray(bl);
	return 0;
}

JNIEXPORT jintArray JNICALL Java_com_mornning_lunar_KLunar_sunToLunar
  (JNIEnv *env, jclass class, jint year, jint month, jint day)
  {
    SunDate sunDate;
    int cResult[11];
    sunDate.year=year;
    sunDate.month=month;
    sunDate.day=day;
    sun_to_lunar(sunDate,cResult);
    jintArray iarr = (*env)->NewIntArray(env, 11);
   if (iarr == NULL) {
            return NULL;
   }
   jint tmp[11];
   int j;
   for (j = 0; j < 11; j++) {
            tmp[j] = cResult[j];
   }
   (*env)->SetIntArrayRegion(env, iarr, 0, 11, tmp);
   return iarr;
  }