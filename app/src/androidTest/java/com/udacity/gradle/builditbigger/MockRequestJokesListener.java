package com.udacity.gradle.builditbigger;

import android.content.Context;

public class MockRequestJokesListener {
    private String mockJoke;

    public void allJokesRetrieved(ServiceRequest service, Context context){
        mockJoke = service.doInBackground(context);
        synchronized (this){
            notifyAll();
        }
    }

    public String getMockJoke() {
        return mockJoke;
    }
}
