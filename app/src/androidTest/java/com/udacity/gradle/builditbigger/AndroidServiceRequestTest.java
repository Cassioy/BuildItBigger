package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class AndroidServiceRequestTest extends InstrumentationTestCase{

    private static final String TAG = AndroidServiceRequestTest.class.getSimpleName();
    private String joke = "";
    private ServiceRequest sr;
    private List<String> jokesList;
    private Boolean hasJoke = false;
    private Context mContext;

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);



    @Before
    public void createJokesArraylist(){
        jokesList = new ArrayList<>();
        jokesList.add("When Chuck Norris was denied a Bacon McMuffin at McDonalds because it was 10:35, he roundhouse kicked the store so hard it became a KFC.");
        jokesList.add("Some people wear Superman pajamas. Superman wears Chuck Norris pajamas.");
        jokesList.add("Chuck Norris doesn\'t shower, he only takes blood baths.");
        jokesList.add("Chuck Norris is not Politically Correct. He is just Correct. Always.");
        jokesList.add("What\'s Forrest Gump\'s password? \nAnswer: 1Forrest1");
        jokesList.add("Why can\'t bicycles stand on their own? \nAnswer: They are two tired");
        jokesList.add("What do you call a singing Laptop? \nAnswer: A Dell");
        jokesList.add("What\'s the best thing about a Boolean? \nAnswer: Even if you're wrong, you\'re only off by a bit.");
        jokesList.add("Where do programmers like to hangout? \nAnswer: The Foo Bar");
        jokesList.add("I just watched a documentary about beavers. It was the best dam show I ever saw");
    }

    @Test
    public void testServiceRequest_ReturnsNotNull() throws Throwable {

        mContext = activityActivityTestRule.getActivity().getBaseContext();
        sr = new ServiceRequest(mContext);
        MockRequestJokesListener mockRequestJokesListener = new MockRequestJokesListener();
        mockRequestJokesListener.allJokesRetrieved(sr, mContext);
        synchronized (mockRequestJokesListener){
            mockRequestJokesListener.wait(3000);
        }

        joke = mockRequestJokesListener.getMockJoke();

        if(joke != "" && joke != null) {
            hasJoke = contains(jokesList, joke);
        }

        Log.d(TAG, "hasJoke is " + hasJoke);

        assertTrue(hasJoke);

        Log.d(TAG, "HERE'S THE JOKE: " +  joke);


    }

    public static boolean contains(List<String> arr, String item) {
        return arr.contains(item);
    }
}
