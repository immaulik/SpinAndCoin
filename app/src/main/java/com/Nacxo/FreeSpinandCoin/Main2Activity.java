package com.Nacxo.FreeSpinandCoin;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.Nacxo.FreeSpinandCoin.POJO.LinkData;
import com.Nacxo.FreeSpinandCoin.POJO.LinkDetail;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> title,link,post_date;
    ListView recyclerView;
    Api api;
    List<LinkDetail> list;
    Toolbar toolbar;
        public AdView adView;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        adView = new AdView(this, "483136609087757_483140312420720", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
//                Toast.makeText(Main2Activity.this, "Error: " + adError.getErrorMessage(),
//                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        });
        adView.loadAd();
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        post_date=new ArrayList<>();
        title=new ArrayList<>();
        link=new ArrayList<>();
        dialog=new ProgressDialog(this);

        toolbar.setTitle("Get Daily free Coins & Spin");
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar));
        }

        api=APIClient.getClient().create(Api.class);
        list=new ArrayList<>();
        recyclerView=findViewById(R.id.list1);
        getData("t");
    }

    @Override
    protected void onDestroy() {
        if (adView != null ) {
            adView.destroy();
        }
        super.onDestroy();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!InternetConnection.checkConnection(this)) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("No Internet Connection Available!")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();

            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setTypeface(Typeface.createFromAsset(getAssets(), "San Fransisco.otf"));

            Button btn1 = (Button) dialog.findViewById(android.R.id.button1);
            btn1.setTextColor(getResources().getColor(R.color.black));
            btn1.setTypeface(Typeface.createFromAsset(getAssets(), "San Fransisco.otf"));
        }
    }


    public void getData(final String link2)
    {
        dialog.setTitle("Fetch link");
        dialog.setMessage("Please wait while we fetching...!");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Call<LinkData> call=api.getData(link2);
        call.enqueue(new Callback<LinkData>() {
            @Override
            public void onResponse(Call<LinkData> call, Response<LinkData> response) {
                dialog.dismiss();
                LinkData linkData=response.body();
                list=linkData.getLinkDetails();
                listadpter listadpter=new listadpter(Main2Activity.this,list);
                recyclerView.setAdapter(listadpter);
            }

            @Override
            public void onFailure(Call<LinkData> call, Throwable t) {
                Log.d("aaaaa", t.getMessage());
                dialog.hide();
                Toast.makeText(Main2Activity.this, "Can't Load Data , Please Try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
