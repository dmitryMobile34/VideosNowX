package com.elysiumbetas.videosnowx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.orhanobut.hawk.Hawk;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private static final String AF_DEV_KEY = "UsGB5T4Ke9i5TASVPRqvoR";

    String stringNow;

    public static final String CPN_ONE = "cpn1";

    public static final String CPN_TWO = "cpn2";

    public static final String CPN_THREE = "cpn3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(prefs.getBoolean("activity_exec", false)){
            Intent intent = new Intent(this, FlVNX.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor exec = prefs.edit();
            exec.putBoolean("activity_exec", true);
            exec.commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        appsyFi();
    }


    public void appsyFi() {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {



            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {



                Log.d("TESTING_ZONE", "af stat is " + conversionData.get("af_status"));

                stringNow = (String) conversionData.get("campaign");


                Log.d("NAMING TEST", "campaign attributed: " + stringNow);


                StringTokenizer tokenizer = new StringTokenizer(stringNow, "_");


                String one = tokenizer.nextToken();
                String two = tokenizer.nextToken();
                String three = tokenizer.nextToken();



                Hawk.put(CPN_ONE, one);
                Hawk.put(CPN_TWO, two);
                Hawk.put(CPN_THREE, three);

                eventHor();

                finish();


            }




            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);

                eventHor();



            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }

        };


        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);


    }


    public void eventHor(){

        Intent EH = new Intent(MainActivity.this, FlVNX.class);
        startActivity(EH);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();

    }
}