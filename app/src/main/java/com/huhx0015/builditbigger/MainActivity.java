package com.huhx0015.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.huhx0015.jokesandroidlib.JokeActivity;
import com.huhx0015.jokeslib.JokeProducer;

public class MainActivity extends AppCompatActivity {

    /** ACTIVITY LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** JOKE METHODS ___________________________________________________________________________ **/

    // launchJokeIntent(): Launches an intent to the JokeActivity to display a joke.
    public void launchJokeIntent(View view){
        Intent intent = new Intent(this, JokeActivity.class);
        JokeProducer jokeSource = new JokeProducer();
        intent.putExtra(getResources().getString(R.string.joke_key), jokeSource.tellMeAJoke());
        startActivity(intent);
        finish();
    }
}