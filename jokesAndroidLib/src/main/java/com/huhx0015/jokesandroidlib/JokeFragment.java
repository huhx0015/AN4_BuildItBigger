package com.huhx0015.jokesandroidlib;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeFragment extends Fragment {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private TextView jokeTextView;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public JokeFragment() {}

    /** FRAGMENT LIFECYCLE METHODS _____________________________________________________________ **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        jokeTextView = (TextView) root.findViewById(R.id.joke_textview);
        initLayout(); // Initializes the layout for the fragment.

        return root;
    }

    /** LAYOUT METHODS _________________________________________________________________________ **/

    // initLayout(): Initializes the layout for this fragment.
    private void initLayout() {

        // Checks the Intent extras for any passed jokes. If any jokes were found, it is set to the
        // jokeTextView TextView object.
        Intent intent = getActivity().getIntent();
        String jokeExtra = intent.getStringExtra(getResources().getString(R.string.joke_key));
        if (jokeExtra != null) { jokeTextView.setText(jokeExtra); }
        else { jokeTextView.setText(getResources().getString(R.string.missing_joke)); }
    }
}
