package com.Nacxo.FreeSpinandCoin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class linkopen extends AppCompatActivity {
    String url2;
    WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkopen);
        url2=getIntent().getStringExtra("link");
        Log.d("linkks",url2);
        webView=findViewById(R.id.webView1);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null || url.startsWith("http://") || url.startsWith("https://")) return false;
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    view.getContext().startActivity(intent);
                    return true;
                } catch (Exception e) {
                    Toast.makeText(linkopen.this, "Not install coin master", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url2));
                    startActivity(intent);
                    return true;
                }

            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.toolbar));
        }

        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Use remote resource
        webView.loadUrl(url2);



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
}
