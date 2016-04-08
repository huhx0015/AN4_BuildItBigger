package com.huhx0015.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.huhx0015.jokesandroidlib.JokeActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/** -----------------------------------------------------------------------------------------------
 *  [MainActivityFragment] CLASS
 *  DESCRIPTION: MainActivityFragment is a Fragment class in which a user can press a button to
 *  fetch a joke from the local backend.
 *  -----------------------------------------------------------------------------------------------
 */
public class MainActivityFragment extends Fragment {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // LAYOUT VARIABLES
    private View fragmentView;

    // VIEW INJECTION VARIABLES
    @Bind(R.id.fragment_main_joke_button) Button jokesButton;
    @Bind(R.id.fragment_main_center_layout) LinearLayout jokesButtonContainer;
    @Bind(R.id.fragment_main_progress_bar) ProgressBar jokesProgressBar;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public MainActivityFragment() {}

    /** FRAGMENT LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, fragmentView);
        initLayout(); // Initializes the layout for this fragment.

        return fragmentView;
    }

    /** LAYOUT METHODS _________________________________________________________________________ **/

    // initLayout(): Initializes the layout for this fragment.
    private void initLayout() {
        initButton(); // Initializes the button listeners for this fragment.
    }

    // initButton(): Initializes the button listeners for this fragment.
    private void initButton() {

        // JOKES BUTTON: Executes the EndpointsAsyncTask to fetch a joke from the backend.
        jokesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jokesButtonContainer.setVisibility(View.GONE); // Hides the button container.
                jokesProgressBar.setVisibility(View.VISIBLE); // Displays the progress bar.

                EndpointsAsyncTask task = new EndpointsAsyncTask(new EndpointsAsyncTask.Callback() {
                    @Override
                    public void onTaskFinished(String result) {
                        launchJokeIntent(result); // Launches an intent to JokeActivity with the result.
                        jokesButtonContainer.setVisibility(View.VISIBLE); // Shows the button container.
                        jokesProgressBar.setVisibility(View.GONE); // Hides the progress bar.
                    }
                });
                task.execute();
            }
        });
    }

    /** INTENT METHODS _________________________________________________________________________ **/

    // launchJokeIntent(): Launches an intent to the JokeActivity class in jokesAndroidLib library.
    private void launchJokeIntent(String joke) {

        // Creates an Intent to launch the JokeActivity activity class.
        Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
        jokeIntent.putExtra(getActivity().getString(R.string.joke_key), joke);
        getActivity().startActivity(jokeIntent);
    }
}