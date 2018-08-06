package br.cassioy.androidjokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidJokeActivity extends AppCompatActivity {

    @BindView(R2.id.android_joke_text) TextView androidJokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_joke);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        handleSendText(intent, androidJokeText);
    }

    void handleSendText(Intent intent, TextView textView) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            textView.setText(sharedText);
        }else{
            textView.setText(getResources().getString(R.string.no_text));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
