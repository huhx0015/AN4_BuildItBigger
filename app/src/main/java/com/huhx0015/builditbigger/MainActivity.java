package com.huhx0015.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.huhx0015.jokesandroidlib.JokeActivity;
import com.huhx0015.jokeslib.JokeProducer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tellJoke(View view){
        JokeProducer jokeProducer = new JokeProducer();
        Toast.makeText(this, jokeProducer.tellMeAJoke(), Toast.LENGTH_SHORT).show();
    }

    public void launchJokeActivity(View view){
        Intent intent = new Intent(this, JokeActivity.class);
        JokeProducer jokeSource = new JokeProducer();
        String joke = jokeSource.tellMeAJoke();
        intent.putExtra(getResources().getString(R.string.joke_key), joke);
        startActivity(intent);
    }
}
