package com.huhx0015.builditbigger;

import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.huhx0015.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

/** ------------------------------------------------------------------------------------------------
 *  [EndpointsAsyncTask] CLASS
 *  DESCRIPTION: EndpointsAsyncTask is an AsyncTask class that queries the local backend to
 *  retrieve a joke in the background.
 *  ------------------------------------------------------------------------------------------------
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // API VARIABLES
    private MyApi myApiService = null;

    // CALLBACK VARIABLES
    private final Callback taskCallback;

    // LOG VARIABLES
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    // NETWORK VARIABLES
    private static final String EMULATOR_ADDRESS = "http://10.0.2.2:8080/_ah/api/";
    private static final String GENYMOTION_ADDRESS = "http://10.0.3.2:8080/_ah/api/";

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    // EndpointsAsyncTask(): Constructor method.
    public EndpointsAsyncTask(Callback callback) {
        this.taskCallback = callback;
    }

    /** ASYNCTASK METHODS ______________________________________________________________________ **/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(LOG_TAG, "onPreExecute(): Executing EndpointsAsyncTask...");
    }

    @Override
    protected String doInBackground(Void... params) {

        // Checks to see if myApiService is null first.
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(EMULATOR_ADDRESS)
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
        catch (IOException e) {
            Log.e(LOG_TAG, "doInBackground(): ERROR: An exception has occurred: " + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(LOG_TAG, "onPostExecute(): Joke result has been retrieved: " + result);
        if (taskCallback != null) { taskCallback.onTaskFinished(result); } // Signals the callback.
    }

    /** INTERFACE METHODS ______________________________________________________________________ **/

    // Callback: Contains an interface method that is run after the EndpointsAsyncTask has finished.
    public interface Callback {
        void onTaskFinished(String result);
    }
}