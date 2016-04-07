package com.huhx0015.jokeslib;

import java.util.Random;

/** -----------------------------------------------------------------------------------------------
 *  [JokeProducer] CLASS
 *  PROGRAMMER: Michael Yoon Huh
 *  DESCRIPTION: JokeProducer class contains methods for retrieving a joke String.
 *  -----------------------------------------------------------------------------------------------
 */

public class JokeProducer {

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    // JokeProducer(): Default constructor method.
    public JokeProducer() {}

    /** JOKE METHODS ___________________________________________________________________________ **/

    // tellMeAJoke(): Returns a joke in the form of a String.
    public String tellMeAJoke() {
        JokeProducer jokeProducer = new JokeProducer();
        return jokeProducer.jokeRandomizer(); // Returns a randomly chosen joke.
    }

    // jokeRandomizer(): Returns a randomly picked joke.
    private String jokeRandomizer() {

        int NUM_JOKES = 11; // Number of jokes available to choose from.
        int randInt; // A random integer value within the range of NUM_JOKES.
        Random randValue = new Random(); // A random generated value.
        randInt = randValue.nextInt(NUM_JOKES); // A random integer value within the range of NUM_JOKES.
        String randomJoke; // Used to store the randomly picked joke.

        // Switch case in which a joke is chosen from based on the randInt value.
        switch (randInt) {

            case 0:
                randomJoke = "Why did the chicken cross the road?";
                break;
            case 1:
                randomJoke = "What do you call a man with no arms or legs who gets into a fight with his cat?";
                break;
            case 2:
                randomJoke = "How much does a pirate pay for corn?";
                break;
            case 3:
                randomJoke = "Why did the police officer smell?";
                break;
            case 4:
                randomJoke = "What kind of guns do bees use?";
                break;
            case 5:
                randomJoke = "What do you call a nosy pepper?";
                break;
            case 6:
                randomJoke = "How does the man in the moon cut his hair?";
                break;
            case 7:
                randomJoke = "Why did the rapper carry an umbrella?";
                break;
            case 8:
                randomJoke = "How does an octopus go to war?";
                break;
            case 9:
                randomJoke = "Why shouldn't you write with a broken pencil?";
                break;
            case 10:
                randomJoke = "What kind of horses go out after dusk?";
                break;
            default:
                randomJoke = "Why did the chicken cross the road?";
        }

        return randomJoke;
    }
}