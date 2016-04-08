package com.huhx0015.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.huhx0015.jokesandroidlib.JokeActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/** -----------------------------------------------------------------------------------------------
 *  [MainActivityFragment] CLASS
 *  DESCRIPTION: MainActivityFragment is a Fragment class in which a user can press a button to
 *  fetch a joke from the local backend.
 *  -----------------------------------------------------------------------------------------------
 */
public class MainActivityFragment extends Fragment {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // ADMOB VARIABLES
    private InterstitialAd interstitialAd;

    // LAYOUT VARIABLES
    private View fragmentView;

    // LOG VARIABLES
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    // VIEW INJECTION VARIABLES
    @Bind(R.id.fragment_main_joke_button) Button jokesButton;
    @Bind(R.id.fragment_main_center_layout) LinearLayout jokesButtonContainer;
    @Bind(R.id.fragment_main_progress_bar) ProgressBar jokesProgressBar;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public MainActivityFragment() {}

    /** FRAGMENT LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, fragmentView);
        initLayout(); // Initializes the layout for this fragment.

        return fragmentView;
    }

    /** LAYOUT METHODS _________________________________________________________________________ **/

    // initLayout(): Initializes the layout for this fragment.
    private void initLayout() {
        initAds(); // Initializes the ads for this fragment.
        initButton(); // Initializes the button listeners for this fragment.
    }

    // initAds(): Sets up the AdView for this fragment.
    private void initAds() {

        Log.d(LOG_TAG, "initAds(): Free build detected, initializing advertising...");

        // INTERSTITIAL AD INITIALIZATION: Sets up the interstitial ad request.
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id)); // Sets test unit ID.

        // Sets up an ad listener for the interstitial ad.
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(LOG_TAG, "onAdLoaded(): Interstitial ad has been loaded.");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

                Log.d(LOG_TAG, "onClick(): Executing EndpointsAsyncTask...");

                jokesButtonContainer.setVisibility(View.GONE); // Hides the button container.
                jokesProgressBar.setVisibility(View.VISIBLE); // Displays the progress bar.

                EndpointsAsyncTask task = new EndpointsAsyncTask(new EndpointsAsyncTask.Callback() {
                    @Override
                    public void onTaskFinished(String result) {
                        launchJokeIntent(result); // Launches an intent to JokeActivity with the result.
                        jokesButtonContainer.setVisibility(View.VISIBLE); // Shows the button container.
                        jokesProgressBar.setVisibility(View.GONE); // Hides the progress bar.
                    }
                });
                task.execute();

                // Requests the next interstitial ad when focus returns to this activity,
                requestInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

                Log.e(LOG_TAG, "onAdFailedToLoad(): ERROR: Interstitial ad failed to load: " + errorCode);

                // Requests the next interstitial ad when focus returns to this activity.
                requestInterstitialAd();
            }
        });

        requestInterstitialAd(); // Requests the next interstitial ad.

        // STANDARD AD INITIALIZATION: Creates an ad request. Check logcat output for the hashed
        // device ID to get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdView mAdView = (AdView) fragmentView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    // initButton(): Initializes the button listeners for this fragment.
    private void initButton() {

        // JOKES BUTTON: Executes the EndpointsAsyncTask to fetch a joke from the backend.
        jokesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Displays the interstitial ad if ready.
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {

                    jokesButtonContainer.setVisibility(View.GONE); // Hides the button container.
                    jokesProgressBar.setVisibility(View.VISIBLE); // Displays the progress bar.

                    EndpointsAsyncTask task = new EndpointsAsyncTask(new EndpointsAsyncTask.Callback() {
                        @Override
                        public void onTaskFinished(String result) {
                            launchJokeIntent(result); // Launches an intent to JokeActivity with the result.
                            jokesButtonContainer.setVisibility(View.VISIBLE); // Shows the button container.
                            jokesProgressBar.setVisibility(View.GONE); // Hides the progress bar.
                        }
                    });
                    task.execute();
                }
            }
        });
    }

    /** AD METHODS _____________________________________________________________________________ **/

    // requestInterstitialAd(): Requests for a new interstitial ad to display.
    private void requestInterstitialAd() {

        Log.d(LOG_TAG, "requestAd(): Requesting new interstital ad...");

        // Generates a new ad request.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

    /** INTENT METHODS _________________________________________________________________________ **/

    // launchJokeIntent(): Launches an intent to the JokeActivity class in jokesAndroidLib library.
    private void launchJokeIntent(String joke) {

        // Creates an Intent to launch the JokeActivity activity class.
        Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
        jokeIntent.putExtra(getActivity().getString(R.string.joke_key), joke);
        getActivity().startActivity(jokeIntent);
    }
}