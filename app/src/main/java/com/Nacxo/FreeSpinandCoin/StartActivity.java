package com.Nacxo.FreeSpinandCoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.ads.*;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.AudienceNetworkAds;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(R.color.toolbar1));
            }
        }

    }

    public void StartApp(View view) {

        Intent intent=new Intent(StartActivity.this,Main2Activity.class);
        startActivity(intent);

    }

    public void ShareApp(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hello Friend I am using This Free spin and coin CoinMaster.I am Really Enjoyed This a Lot." +
                        "" +
                        "" +
                        "You Can also Download it from Here: https://play.google.com/store/apps/details?id="+this.getPackageName());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void RateApp(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +this.getPackageName())));
    }

    public void FeedbackApp(View view) {
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Coin Master Feedback" + "&body=" + "" + "&to=" + "maulik231b@gmail.com");
        testIntent.setData(data);
        startActivity(testIntent);
    }

    public void PrivacyApp(View view) {
        Intent intent=new Intent(StartActivity.this,Privacy_Policy.class);
        startActivity(intent);
    }
}
