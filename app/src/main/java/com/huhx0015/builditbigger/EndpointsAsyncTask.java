package com.huhx0015.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;
import java.io.IOException;

/**
 * Created by Michael Yoon Huh on 4/4/2016.
 */

// TODO: https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
public class EndpointsAsyncTask {
//public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
//    private static MyApi myApiService = null;
//    private Context context;
//
//    @Override
//    protected String doInBackground(Pair<Context, String>... params) {
//        if(myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
//            // end options for devappserver
//
//            myApiService = builder.build();
//        }
//
//        context = params[0].first;
//        String name = params[0].second;
//
//        try {
//            return myApiService.sayHi(name).execute().getData();
//        } catch (IOException e) {
//            return e.getMessage();
//        }
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//    }
}
