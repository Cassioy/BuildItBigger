package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.app_joke_button) Button jokeButton;
    @BindView(R.id.get_joke_progress_bar) ProgressBar getJokeProgressBar;
    @BindView(R.id.retriving_joke) TextView retrieveJokeMessage;
    @BindView(R.id.instructions_text_view) TextView instructionsTextView;
    private InterstitialAd mInterstitialAd;

    //Index helper for setting up fragment views visibility when interstitialAd is shown
    //indexFragmentStatus = 0; default
    //indexFragmentStatus = 1; for keeping loading screen after closing InterstitialAd
    private int indexFragmentStatus = 0;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        setRetainInstance(true);

        ButterKnife.bind(this, root);

        mInterstitialAd = new InterstitialAd(getContext());
        //set for Emulator
        mInterstitialAd.setAdUnitId(getContext().getResources().getString(R.string.interstitial_ad_id));
        loadInterstitialAd();

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    indexFragmentStatus = 1;

                    //Handler to make sure that ads can be seen.
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new ServiceRequest(getContext()).execute(getContext());

                        }
                    }, 2000);

                } else {
                    Log.d(TAG, getContext().getResources().getString(R.string.ad_load_error));
                    Toast.makeText(getContext(),getContext().getResources().getString(R.string.ad_load_error), Toast.LENGTH_LONG).show();

                }
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                if(indexFragmentStatus == 0){
                    setDefaultViewVisibility();
                }else{
                    setOnclickViewsVisibility();
                }
            }
        });

        return root;
    }


    private void setOnclickViewsVisibility(){

        getJokeProgressBar.setVisibility(View.VISIBLE);
        retrieveJokeMessage.setVisibility(View.VISIBLE);
        jokeButton.setVisibility(View.INVISIBLE);
        instructionsTextView.setVisibility(View.INVISIBLE);

    }

    private void setDefaultViewVisibility(){

        jokeButton.setVisibility(View.VISIBLE);
        instructionsTextView.setVisibility(View.VISIBLE);
        getJokeProgressBar.setVisibility(View.INVISIBLE);
        retrieveJokeMessage.setVisibility(View.INVISIBLE);


    }

    private void loadInterstitialAd(){

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onStart() {
        super.onStart();
        switch (indexFragmentStatus){
            case 0: setDefaultViewVisibility();
                break;

            case 1: setOnclickViewsVisibility();
                indexFragmentStatus = 0;
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (indexFragmentStatus){
            case 0: setDefaultViewVisibility();
                    break;

            case 1: setOnclickViewsVisibility();
                    indexFragmentStatus = 0;
                    break;

        }
    }
}
