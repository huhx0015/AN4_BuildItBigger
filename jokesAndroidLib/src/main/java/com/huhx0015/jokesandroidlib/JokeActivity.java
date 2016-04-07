package com.huhx0015.jokesandroidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/** -----------------------------------------------------------------------------------------------
 *  [JokeActivity] CLASS
 *  DESCRIPTION: JokeActivity is an activity class that is responsible for displaying the
 *  JokeFragment.
 *  -----------------------------------------------------------------------------------------------
 */
public class JokeActivity extends AppCompatActivity {

    /** ACTIVITY LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }
}
