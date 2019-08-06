package com.Nacxo.FreeSpinandCoin;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.Nacxo.FreeSpinandCoin.POJO.LinkDetail;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class listadpter extends BaseAdapter {
    private InterstitialAd interstitialAd;

    Context context;
    List<LinkDetail> link2;
    private String TAG="aaaaa";
    private ProgressDialog dialog;

    public listadpter(Context context, List<LinkDetail> link2) {
        this.context=context;
        this.link2=link2;

    }


    @Override
    public int getCount() {
        return link2.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.link_itemfile,viewGroup,false);
        dialog=new ProgressDialog(context);
        TextView textView;
        Button button=view.findViewById(R.id. button);
        TextView textView1=view.findViewById(R.id.date);
        final LinearLayout linearLayout=view.findViewById(R.id.l1);
        textView=view.findViewById(R.id.item);
        final LinkDetail linkDetail1=link2.get(i);
            interstitialAd = new InterstitialAd(context, "483136609087757_483140569087361");
            if(linkDetail1.getTitle().equals("Reward Link will be available soon. You can check out previous link if you missed it."))
            {
                textView.setText("Reward Link will be available soon");
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                button.setVisibility(View.GONE);
            }
            else {
                textView.setText(linkDetail1.getTitle());
                String mytime=linkDetail1.getPostDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "MMM dd, yyyy");

                Date myDate = null;
                try {
                    myDate = dateFormat.parse(mytime);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat timeFormat = new SimpleDateFormat("dd MMM, yyyy");
                String finalDate = timeFormat.format(myDate);
                textView1.setText(finalDate);
            }
            if(!linkDetail1.getTitle().equals("Reward Link will be available soon. You can check out previous link if you missed it."))
            {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.setMessage("Please wait..");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        interstitialAd.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
//                                dialog.dismiss();
                                Log.e(TAG, "Interstitial ad displayed.");
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                dialog.dismiss();
                                Log.e(TAG, "Interstitial ad dismissed.");
                                Intent intent = new Intent(context, linkopen.class);
                                intent.putExtra("link", linkDetail1.getLink());
                                context.startActivity(intent);
                            }
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                dialog.dismiss();
                                Intent intent = new Intent(context, linkopen.class);
                                intent.putExtra("link", linkDetail1.getLink());
                                context.startActivity(intent);
//                                Toast.makeText(context, "Please Try Again Later!", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                            }
                            @Override
                            public void onAdLoaded(Ad ad) {
                                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                                interstitialAd.show();
                                dialog.dismiss();
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
//                                dialog.dismiss();
                                Log.d(TAG, "Interstitial ad clicked!");
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
//                                dialog.dismiss();
                                Log.d(TAG, "Interstitial ad impression logged!");
                            }
                        });
                        interstitialAd.loadAd();
                                dialog.dismiss();
                                //Toast.makeText(context, "Please Try Again Later!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else {
                view.setClickable(false);
            }
        return view;
    }
}
