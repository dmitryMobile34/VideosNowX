package com.elysiumbetas.videosnowx;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.orhanobut.hawk.Hawk;

public class Global extends Application {

    public static final String NOWX_ID = "nowxId";
    private static final String ONESIGNAL_APP_ID = "171d8ffb-5106-4f96-8496-c020d24775f3";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        com.onesignal.OneSignal.setLogLevel(com.onesignal.OneSignal.LOG_LEVEL.VERBOSE, com.onesignal.OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        com.onesignal.OneSignal.initWithContext(this);
        com.onesignal.OneSignal.setAppId(ONESIGNAL_APP_ID);

        asyncTask.execute();


        Hawk.init(this).build();

    }


    @SuppressLint("StaticFieldLeak")
    AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
            AdvertisingIdClient.Info idInfo = null;
            try {
                idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String advertId = null;
            try {


                advertId = idInfo.getId();

                Hawk.put(NOWX_ID, advertId);



            } catch (Exception e) {
                e.printStackTrace();
            }
            return advertId;
        }

        @Override
        protected void onPostExecute(String advertId) {


        }
    };
}