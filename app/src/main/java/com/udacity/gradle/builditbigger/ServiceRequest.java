package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import br.cassioy.androidjokelib.AndroidJokeActivity;

public class ServiceRequest extends AsyncTask<Context, Void, String>{

    private static MyApi myApiService = null;
    private Context mContext;


    public ServiceRequest(Context context) {
        super();
        mContext = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(mContext.getResources().getString(R.string.service_url_emulator))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return mContext.getResources().getString(R.string.async_error);
        }
    }


        @Override
        protected void onPostExecute(String result) {

            //Toast that shows Data Retrieved
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

            Intent passingJokes = new Intent(mContext, AndroidJokeActivity.class);
            passingJokes.putExtra(Intent.EXTRA_TEXT, result);
            mContext.startActivity(passingJokes);
        }
}
