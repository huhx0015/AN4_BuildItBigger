package com.huhx0015.jokesandroidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/** -----------------------------------------------------------------------------------------------
 *  [JokeActivity] CLASS
 *  PROGRAMMER: Michael Yoon Huh
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

    /** ACTIVITY LIFECYCLE EXTENSION METHODS ___________________________________________________ **/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
