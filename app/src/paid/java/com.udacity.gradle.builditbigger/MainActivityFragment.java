package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.app_joke_button) Button jokeButton;
    @BindView(R.id.get_joke_progress_bar) ProgressBar getJokeProgressBar;
    @BindView(R.id.retriving_joke) TextView retrieveJokeMessage;
    @BindView(R.id.instructions_text_view) TextView instructionsTextView;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        setRetainInstance(true);

        ButterKnife.bind(this, root);
        setDefaultViewVisibility();

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnclickViewsVisibility();
                new ServiceRequest(getContext()).execute(getContext());
            }
        });

        return root;
    }

    private void setOnclickViewsVisibility(){

        getJokeProgressBar.setVisibility(View.VISIBLE);
        retrieveJokeMessage.setVisibility(View.VISIBLE);
        jokeButton.setVisibility(View.GONE);
        instructionsTextView.setVisibility(View.GONE);

    }

    private void setDefaultViewVisibility(){

        getJokeProgressBar.setVisibility(View.GONE);
        retrieveJokeMessage.setVisibility(View.GONE);
        jokeButton.setVisibility(View.VISIBLE);
        instructionsTextView.setVisibility(View.VISIBLE);


    }

    @Override
    public void onStart() {
        super.onStart();
        setDefaultViewVisibility();
    }

    @Override
    public void onResume() {
        super.onResume();
        setDefaultViewVisibility();
    }
}
