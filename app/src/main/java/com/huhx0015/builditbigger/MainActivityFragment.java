package com.huhx0015.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.huhx0015.builditbigger.backend.myApi.MyApi;
import com.huhx0015.jokesandroidlib.JokeActivity;
import java.io.IOException;
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

    // BUILD VARIABLES
    private static final String BUILD_FREE = "1.0-free";

    // LAYOUT VARIABLES
    private View fragmentView;

    // NETWORK VARIABLES
    private static final String EMULATOR_ADDRESS = "http://10.0.2.2:8080/_ah/api/";
    private static final String GENYMOTION_ADDRESS = "http://10.0.3.2:8080/_ah/api/";

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

        // Create an ad request. Check logcat output for the hashed device ID to get test ads on a
        // physical device. e.g. "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test
        // ads on this device."
        if (BuildConfig.VERSION_NAME.equals(BUILD_FREE)) {
            AdView mAdView = (AdView) fragmentView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }
    }

    // initButton(): Initializes the button listeners for this fragment.
    private void initButton() {

        // JOKES BUTTON: Executes the EndpointsAsyncTask to fetch a joke from the backend.
        jokesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EndpointsAsyncTask task = new EndpointsAsyncTask();
                task.execute();
            }
        });
    }

    /** INTENT METHODS _________________________________________________________________________ **/

    // launchJokeIntent(): Launches an intent to the JokeActivity class in jokesAndroidLib library.
    private void launchJokeIntent(String joke) {

        // Creates an Intent to launch the JokeActivity activity class.
        Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
        jokeIntent.putExtra(getActivity().getString(R.string.joke_key), joke);
        getActivity().startActivity(jokeIntent);
        getActivity().finish();
    }

    /** SUBCLASSES _____________________________________________________________________________ **/

    /** --------------------------------------------------------------------------------------------
     *  [EndpointsAsyncTask] CLASS
     *  DESCRIPTION: EndpointsAsyncTask is an AsyncTask class that queries the local backend to
     *  retrieve a joke in the background.
     *  --------------------------------------------------------------------------------------------
     */
    public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

        private MyApi myApiService = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jokesButtonContainer.setVisibility(View.GONE); // Hides the button container.
            jokesProgressBar.setVisibility(View.VISIBLE); // Displays the progress bar.
        }

        @Override
        protected String doInBackground(Void... params) {

            // Checks to see if myApiService is null first.
            if (myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl(GENYMOTION_ADDRESS)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }

            // Attempts to fetch the joke from the backend.
            try { return myApiService.fetchJoke().execute().getData(); }
            catch (IOException e) { return e.getMessage(); }
        }

        @Override
        protected void onPostExecute(String result) {
            jokesProgressBar.setVisibility(View.GONE); // Hides the progress bar.
            if (result != null) {
                launchJokeIntent(result); // Launches an intent to JokeActivity.
            } else {
                jokesButtonContainer.setVisibility(View.VISIBLE); // Displays the joke button container.
            }
        }
    }
}