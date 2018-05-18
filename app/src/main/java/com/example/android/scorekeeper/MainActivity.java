package com.example.android.scorekeeper;

import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //Added strings that will save the activity data
    private final static String GAME_STATUS_KEY = "game_status_key";
    private final static String SUZY_SCORE_VIEW_KEY = "suzy_score_view_key";
    private final static String MIKE_SCORE_VIEW_KEY = "mike_score_view_key";
    private final static String SUZY_SCORE_KEY = "suzy_score_key";
    private final static String MIKE_SCORE_KEY = "mike_score_key";
    private final static String SUZY_PICKED_50_KEY = "suzy_picked_50_key";
    private final static String MIKE_PICKED_50_KEY = "mike_picked_50_key";
    private final static String SUZY_SURPRISED_KEY = "suzy_surprised_key";
    private final static String MIKE_SURPRISED_KEY = "mike_surprised_key";

    int scoreSuzy = 0;
    int scoreMike = 0;
    int numberOfTimesSuzyPicked50 = 0;
    int numberOfTimesMikePicked50 = 0;
    int numberOfTimesSuzySurprised = 0;
    int numberOfTimesMikeSurprised = 0;
    TextView gameStatus;
    TextView suzyScoreDisplay;
    TextView mikeScoreDisplay;
    ImageView bigLogo;
    ImageView smallLogo;

    //This variable will keep track of activity layout orientation
    boolean isLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameStatus = findViewById(R.id.game_status_text_view);
        suzyScoreDisplay = findViewById(R.id.suzy_earnings);
        mikeScoreDisplay = findViewById(R.id.mike_earnings);
        bigLogo = findViewById(R.id.greedosity_logo);

        //Get screen orientation state from resource file
        Resources res = getResources();
        isLandscapeMode = res.getBoolean(R.bool.is_landscape);

        //Only run the next few lines of code if the screen is in landscape mode
        if (isLandscapeMode) {
            Toast.makeText(MainActivity.this, "Landscape Mode", Toast.LENGTH_SHORT).show();
            smallLogo = findViewById(R.id.greedosity_in_game_logo);
        }

        //Steps for if there was saved data
        if (savedInstanceState != null) {

            //Restore TextView with Game Status Message
            String savedGameStatus = savedInstanceState.getString(GAME_STATUS_KEY);
            gameStatus.setText(savedGameStatus);

            //Restore TextViews that show Mike and Suzy's Current Earnings
            String savedSuzyScoreView = savedInstanceState.getString(SUZY_SCORE_VIEW_KEY);
            suzyScoreDisplay.setText(savedSuzyScoreView);

            String savedMikeScoreView = savedInstanceState.getString(MIKE_SCORE_VIEW_KEY);
            mikeScoreDisplay.setText(savedMikeScoreView);

            //Restore data from the integer variables that kept track of score and other data
            scoreSuzy = savedInstanceState.getInt(SUZY_SCORE_KEY);
            scoreMike = savedInstanceState.getInt(MIKE_SCORE_KEY);
            numberOfTimesSuzyPicked50 = savedInstanceState.getInt(SUZY_PICKED_50_KEY);
            numberOfTimesMikePicked50 = savedInstanceState.getInt(MIKE_PICKED_50_KEY);
            numberOfTimesSuzySurprised = savedInstanceState.getInt(SUZY_SURPRISED_KEY);
            numberOfTimesMikeSurprised = savedInstanceState.getInt(MIKE_SURPRISED_KEY);

        } else {
            // Shows a "New Game" message on the screen if no information was saved
            Toast.makeText(MainActivity.this, "New Game", Toast.LENGTH_SHORT).show();
        }

        //Adjust Visibility of views
        adjustViewsVisibility();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        //Saves the textview data for Game Status and Suzy and Mike's Scores
        savedInstanceState.putString(GAME_STATUS_KEY, gameStatus.getText().toString());
        savedInstanceState.putString(SUZY_SCORE_VIEW_KEY, suzyScoreDisplay.getText().toString());
        savedInstanceState.putString(MIKE_SCORE_VIEW_KEY, mikeScoreDisplay.getText().toString());

        //Saves the integer variables that store Suzy and Mike's score
        savedInstanceState.putInt(SUZY_SCORE_KEY, scoreSuzy);
        savedInstanceState.putInt(MIKE_SCORE_KEY, scoreMike);

        //Saves the current number of times that Suzy and Mike clicked the $50 button since reset
        savedInstanceState.putInt(SUZY_PICKED_50_KEY, numberOfTimesSuzyPicked50);
        savedInstanceState.putInt(MIKE_PICKED_50_KEY, numberOfTimesMikePicked50);

        //Saves variable that ensures that Suzy and Mike don't press SURPRISE consecutively
        savedInstanceState.putInt(SUZY_SURPRISED_KEY, numberOfTimesSuzySurprised);
        savedInstanceState.putInt(MIKE_SURPRISED_KEY, numberOfTimesMikeSurprised);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Swaps out the visibility of views based on the state of the game
     */
    public void adjustViewsVisibility() {

        if (scoreSuzy == 0 && scoreMike == 0 && numberOfTimesMikeSurprised == 0 && numberOfTimesSuzySurprised == 0) {

            //Adjust Visibility of views
            gameStatus.setVisibility(View.GONE);
            bigLogo.setVisibility(View.VISIBLE);

            //Only run the next few lines of code if the screen is in landscape mode
            if (isLandscapeMode) {
                smallLogo.setVisibility(View.GONE);
            }

        } else {
            gameStatus.setVisibility(View.VISIBLE);

            //Check for screen orientation, only run code if in Landscape mode
            if (isLandscapeMode) {
                bigLogo.setVisibility(View.GONE);
                smallLogo.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Displays the given score for Suzy.
     */
    public void displayForSuzy(int score) {
        //TextView scoreView = (TextView) findViewById(R.id.suzy_earnings);
        suzyScoreDisplay.setText(NumberFormat.getCurrencyInstance().format(score));
    }

    /**
     * Suzy's $10 button was clicked.
     */
    public void addTenDollarsSuzy(View view) {
        //TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.tenDollarsSuzy));

        numberOfTimesSuzySurprised = 0;
        scoreSuzy = scoreSuzy + 10;
        displayForSuzy(scoreSuzy);

        //Call Method to adjust views
        adjustViewsVisibility();
    }

    /**
     * Suzy's $50 button was clicked.
     */
    public void addFiftyDollarsSuzy(View view) {
        //TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.fiftyDollarsSuzy));

        numberOfTimesSuzySurprised = 0;
        scoreSuzy = scoreSuzy + 50;
        numberOfTimesSuzyPicked50 = numberOfTimesSuzyPicked50 + 1;
        displayForSuzy(scoreSuzy);

        //Shows number of times Suzy picked $50 button
        Toast.makeText(MainActivity.this,
                "Suzy $50 clicks: " +
                        numberOfTimesSuzyPicked50,
                Toast.LENGTH_LONG).show();

        //Call Method to adjust views
        adjustViewsVisibility();
    }

    /**
     * If the player has zero dollars, the GREED! (Surprise) button adds a random
     * amount of money between $10 and $50. Otherwise, this method looks at the number
     * of times the player has selected the $50 button since that player's score was reset to 0
     * (either through a Surprise or Game Reset). The number of times the player has selected $50
     * is kept track of through the int variable "numberOfTimesSuzyPicked50,"
     * which is reset to 0 whenever the player's earnings are reset to 0.
     * If the player has not clicked the $50 button since the last reset,
     * the player's earnings are tripled. If the player picked $50 once since reset,
     * the player's earnings are doubled. If the player picked $50 two or more times
     * since the last reset to 0, a random number between 1 and 10 is generated.
     * If the random number is less than or equal to 5, the player's earnings are
     * reset to 0. If the random number is greater than or equal to 6,
     * the player's earnings are tripled. Players may not choose the Surprise option
     * twice in a row.
     */

    public void surpriseSuzy(View view) {
        //Creates temporary string to store Suzy Game Alert Message
        String suzyStatusMsg;

        numberOfTimesSuzySurprised = numberOfTimesSuzySurprised + 1;

        //Call Method to adjust views
        adjustViewsVisibility();

        //This if statement prevents the player from choosing Surprise twice in a row
        if (numberOfTimesSuzySurprised >= 2) {
            suzyStatusMsg = "Sorry, Suzy. " + getString(R.string.noDoubleSurprise);
            gameStatus.setText(suzyStatusMsg);

        } else {
            if (scoreSuzy == 0) {
                //Creates random integer between 10 and 50
                scoreSuzy = 10 + (int) (Math.random() * (41));
                suzyStatusMsg = getString(R.string.surpriseAmountEarnedSuzy) + scoreSuzy;

                //Display game status message
                gameStatus.setText(suzyStatusMsg);

                //Display Suzy's updated earnings
                displayForSuzy(scoreSuzy);

            } else {
                if (numberOfTimesSuzyPicked50 == 1) {
                    scoreSuzy = scoreSuzy * 2;
                    suzyStatusMsg = getString(R.string.doubleEarningsSuzy);

                    //Display game status message
                    gameStatus.setText(suzyStatusMsg);

                    //Display Suzy's updated earnings
                    displayForSuzy(scoreSuzy);

                } else if (numberOfTimesSuzyPicked50 == 0) {
                    scoreSuzy = scoreSuzy * 3;
                    suzyStatusMsg = getString(R.string.tripleEarningsSuzy);

                    //Display game status message
                    gameStatus.setText(suzyStatusMsg);

                    //Display Suzy's updated earnings
                    displayForSuzy(scoreSuzy);

                } else {

                    //Creates random integer between 1 and 10 and checks if less than 5
                    int random = 1 + (int) (Math.random() * (10));
                    if (random <= 5) {

                        //Sets Suzy's earnings to zero and resets $50 count to 0
                        scoreSuzy = 0;
                        numberOfTimesSuzyPicked50 = 0;
                        suzyStatusMsg = getString(R.string.backToZeroSuzy);

                        //Display game status message
                        gameStatus.setText(suzyStatusMsg);

                        //Display Suzy's updated earnings
                        displayForSuzy(scoreSuzy);
                    } else {
                        //If the random integer created in the if statement was 6 or more
                        scoreSuzy = scoreSuzy * 3;
                        suzyStatusMsg = getString(R.string.tripleEarningsSuzy);

                        //Display game status message
                        gameStatus.setText(suzyStatusMsg);

                        //Display Suzy's updated earnings
                        displayForSuzy(scoreSuzy);
                    }
                }
            }
        }
    }

    /**
     * Displays the given score for Mike.
     */
    public void displayForMike(int score) {
        mikeScoreDisplay.setText(NumberFormat.getCurrencyInstance().format(score));
    }

    /**
     * Mike's $10 button was clicked.
     */
    public void addTenDollarsMike(View view) {
        gameStatus.setText(getString(R.string.tenDollarsMike));

        numberOfTimesMikeSurprised = 0;
        scoreMike = scoreMike + 10;
        displayForMike(scoreMike);

        //Call Method to adjust views
        adjustViewsVisibility();
    }

    /**
     * Mike's $50 button was clicked.
     */
    public void addFiftyDollarsMike(View view) {
        gameStatus.setText(getString(R.string.fiftyDollarsMike));

        numberOfTimesMikeSurprised = 0;
        scoreMike = scoreMike + 50;
        numberOfTimesMikePicked50 = numberOfTimesMikePicked50 + 1;
        displayForMike(scoreMike);

        Toast.makeText(MainActivity.this,
                "Mike $50 clicks: " +
                        numberOfTimesMikePicked50,
                Toast.LENGTH_LONG).show();

        //Call Method to adjust views
        adjustViewsVisibility();
    }

    /**
     * If the player has zero dollars, the GREED! (Surprise) button adds a random
     * amount of money between $10 and $50. Otherwise, this method looks at the number
     * of times the player has selected the $50 button since that player's score was reset to 0
     * (either through a Surprise or Game Reset). The number of times the player has selected $50
     * is kept track of through the int variable "numberOfTimesMikePicked50,"
     * which is reset to 0 whenever the player's earnings are reset to 0.
     * If the player has not clicked the $50 button since the last reset,
     * the player's earnings are tripled. If the player picked $50 once since reset,
     * the player's earnings are doubled. If the player picked $50 two or more times
     * since the last reset to 0, a random number between 1 and 10 is generated.
     * If the random number is less than or equal to 5, the player's earnings are
     * reset to 0. If the random number is greater than or equal to 6,
     * the player's earnings are tripled. Players may not choose the Surprise option
     * twice in a row.
     */
    public void surpriseMike(View view) {
        //Creates temporary string to store Mike Game Alert Message
        String mikeStatusMsg;
        numberOfTimesMikeSurprised = numberOfTimesMikeSurprised + 1;

        //Call Method to adjust views
        adjustViewsVisibility();

        //This if statement prevents the player from choosing Surprise twice in a row
        if (numberOfTimesMikeSurprised >= 2) {
            mikeStatusMsg = "Sorry, Mike. " + getString(R.string.noDoubleSurprise);
            gameStatus.setText(mikeStatusMsg);

        } else {
            if (scoreMike == 0) {
                //Creates random integer between 10 and 50
                scoreMike = 10 + (int) (Math.random() * (41));
                mikeStatusMsg = getString(R.string.surpriseAmountEarnedMike) + scoreMike;

                //Display game status message
                gameStatus.setText(mikeStatusMsg);

                //Display Mike's updated earnings
                displayForMike(scoreMike);

            } else {
                if (numberOfTimesMikePicked50 == 1) {
                    scoreMike = scoreMike * 2;
                    mikeStatusMsg = getString(R.string.doubleEarningsMike);

                    //Display game status message
                    gameStatus.setText(mikeStatusMsg);

                    //Display Mike's updated earnings
                    displayForMike(scoreMike);

                } else if (numberOfTimesMikePicked50 == 0) {
                    scoreMike = scoreMike * 3;
                    mikeStatusMsg = getString(R.string.tripleEarningsMike);

                    //Display game status message
                    gameStatus.setText(mikeStatusMsg);

                    //Display Mike's updated earnings
                    displayForMike(scoreMike);

                } else {
                    //Creates random integer between 1 and 10 and checks if less than 5
                    int random = 1 + (int) (Math.random() * (10));
                    if (random <= 5) {
                        //Sets Mike's earnings to zero and resets $50 count to 0
                        scoreMike = 0;
                        numberOfTimesMikePicked50 = 0;
                        mikeStatusMsg = getString(R.string.backToZeroMike);

                        //Display game status message
                        gameStatus.setText(mikeStatusMsg);

                        //Display Mike's updated earnings
                        displayForMike(scoreMike);
                    } else {
                        //If the random integer created in the if statement was 6 or more
                        scoreMike = scoreMike * 3;
                        mikeStatusMsg = getString(R.string.tripleEarningsMike);

                        //Display game status message
                        gameStatus.setText(mikeStatusMsg);

                        //Display Mike's updated earnings
                        displayForMike(scoreMike);
                    }
                }
            }
        }
    }

    /**
     * This method is called when the "New Game" button is clicked.
     */
    public void resetGameScore(View view) {
        gameStatus.setText(getString(R.string.startNewGame));

        scoreSuzy = 0;
        scoreMike = 0;
        displayForSuzy(scoreSuzy);
        displayForMike(scoreMike);
        numberOfTimesSuzyPicked50 = 0;
        numberOfTimesMikePicked50 = 0;
        numberOfTimesSuzySurprised = 0;
        numberOfTimesMikeSurprised = 0;

        //Call Method to adjust views
        adjustViewsVisibility();
    }
}