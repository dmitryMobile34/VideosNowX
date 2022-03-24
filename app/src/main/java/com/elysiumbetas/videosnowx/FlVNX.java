package com.elysiumbetas.videosnowx;

import static com.elysiumbetas.videosnowx.MainActivity.CPN_ONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FlVNX extends AppCompatActivity {

    TextView prikoldes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fl_vnx);

        prikoldes = findViewById(R.id.inviteTxt);

        new asyncF().execute();
    }

    public class asyncF extends AsyncTask<Void, Void, Void> {


        String jsoup;

        String hawk = Hawk.get(CPN_ONE);

        String flink = "https://videosnowx.space/7pSn2?";


        String odone = "sub_id_1=";



        String videox = flink + odone + hawk;


        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(videox).get();


                jsoup = doc.text();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            prikoldes.setText(jsoup);

            Intent hyinyaitself = new Intent(getApplicationContext(), VNXS.class);

            Intent naebimenyaskorei = new Intent(getApplicationContext(), WVNX.class);
            if (jsoup.equals("j8V2")) {
                startActivity(hyinyaitself);
            } else {
                startActivity(naebimenyaskorei);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }
}