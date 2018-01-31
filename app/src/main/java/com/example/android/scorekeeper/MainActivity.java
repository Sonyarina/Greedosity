package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int scoreSuzy = 0;
    int scoreMike = 0;
    int numberOfTimesSuzyPicked50 = 0;
    int numberOfTimesMikePicked50 = 0;
    int numberOfTimesSuzySurprised = 0;
    int numberOfTimesMikeSurprised = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // displayForSuzy(0);
    }

    /**
     * Displays the given score for Suzy.
     */
    public void displayForSuzy(int score) {
        TextView scoreView = (TextView) findViewById(R.id.suzy_earnings);
        scoreView.setText(NumberFormat.getCurrencyInstance().format(score));
    }

    /**
     * Suzy's $10 button was clicked.
     */
    public void addTenDollarsSuzy(View view) {
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.tenDollarsSuzy));
        //Make Game Status Visible
        gameStatus.setVisibility(View.VISIBLE);
        numberOfTimesSuzySurprised = 0;
        scoreSuzy = scoreSuzy + 10;
        displayForSuzy(scoreSuzy);
    }

    /**
     * Suzy's $50 button was clicked.
     */
    public void addFiftyDollarsSuzy(View view) {
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.fiftyDollarsSuzy));
        //Make Game Status Visible
        gameStatus.setVisibility(View.VISIBLE);
        numberOfTimesSuzySurprised = 0;
        scoreSuzy = scoreSuzy + 50;
        numberOfTimesSuzyPicked50 = numberOfTimesSuzyPicked50 + 1;
        displayForSuzy(scoreSuzy);
    }

    /**
     * If the player has zero dollars, the Surprise button adds a random
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
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        numberOfTimesSuzySurprised = numberOfTimesSuzySurprised + 1;
        //This if statement prevents the player from choosing Surprise twice in a row
        if (numberOfTimesSuzySurprised >= 2) {
            suzyStatusMsg = "Sorry, Suzy. " + getString(R.string.noDoubleSurprise);
            gameStatus.setText(suzyStatusMsg);
            //Make Game Status Visible
            gameStatus.setVisibility(View.VISIBLE);
        } else {
            if (scoreSuzy == 0) {
                //Creates random integer between 10 and 50
                scoreSuzy = 10 + (int) (Math.random() * (41));
                suzyStatusMsg = getString(R.string.surpriseAmountEarnedSuzy) + scoreSuzy;
                //Display game status message
                gameStatus.setText(suzyStatusMsg);
                //Make Game Status Visible
                gameStatus.setVisibility(View.VISIBLE);
                //Display Suzy's updated earnings
                displayForSuzy(scoreSuzy);

            } else {
                if (numberOfTimesSuzyPicked50 == 1) {
                    scoreSuzy = scoreSuzy * 2;
                    suzyStatusMsg = getString(R.string.doubleEarningsSuzy);
                    //Display game status message
                    gameStatus.setText(suzyStatusMsg);
                    //Make Game Status Visible
                    gameStatus.setVisibility(View.VISIBLE);
                    //Display Suzy's updated earnings
                    displayForSuzy(scoreSuzy);
                } else if (numberOfTimesSuzyPicked50 == 0) {
                    scoreSuzy = scoreSuzy * 3;
                    suzyStatusMsg = getString(R.string.tripleEarningsSuzy);
                    //Display game status message
                    gameStatus.setText(suzyStatusMsg);
                    //Make Game Status Visible
                    gameStatus.setVisibility(View.VISIBLE);
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
                        //Make Game Status Visible
                        gameStatus.setVisibility(View.VISIBLE);
                        //Display Suzy's updated earnings
                        displayForSuzy(scoreSuzy);
                    } else {
                        //If the random integer created in the if statement was 6 or more
                        scoreSuzy = scoreSuzy * 3;
                        suzyStatusMsg = getString(R.string.tripleEarningsSuzy);
                        //Display game status message
                        gameStatus.setText(suzyStatusMsg);
                        //Make Game Status Visible
                        gameStatus.setVisibility(View.VISIBLE);
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
        TextView scoreView = (TextView) findViewById(R.id.mike_earnings);
        scoreView.setText(NumberFormat.getCurrencyInstance().format(score));
    }

    /**
     * Mike's $10 button was clicked.
     */
    public void addTenDollarsMike(View view) {
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.tenDollarsMike));
        //Make Game Status Visible
        gameStatus.setVisibility(View.VISIBLE);
        numberOfTimesMikeSurprised = 0;
        scoreMike = scoreMike + 10;
        displayForMike(scoreMike);
    }

    /**
     * Mike's $50 button was clicked.
     */
    public void addFiftyDollarsMike(View view) {
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.fiftyDollarsMike));
        //Make Game Status Visible
        gameStatus.setVisibility(View.VISIBLE);
        numberOfTimesMikeSurprised = 0;
        scoreMike = scoreMike + 50;
        numberOfTimesMikePicked50 = numberOfTimesMikePicked50 + 1;
        displayForMike(scoreMike);
    }

    /**
     * If the player has zero dollars, the Surprise button adds a random
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
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        numberOfTimesMikeSurprised = numberOfTimesMikeSurprised + 1;
        //This if statement prevents the player from choosing Surprise twice in a row
        if (numberOfTimesMikeSurprised >= 2) {
            mikeStatusMsg = "Sorry, Mike. " + getString(R.string.noDoubleSurprise);
            gameStatus.setText(mikeStatusMsg);
            //Make Game Status Visible
            gameStatus.setVisibility(View.VISIBLE);
        } else {
            if (scoreMike == 0) {
                //Creates random integer between 10 and 50
                scoreMike = 10 + (int) (Math.random() * (41));
                mikeStatusMsg = getString(R.string.surpriseAmountEarnedMike) + scoreMike;
                //Display game status message
                gameStatus.setText(mikeStatusMsg);
                //Make Game Status Visible
                gameStatus.setVisibility(View.VISIBLE);
                //Display Mike's updated earnings
                displayForMike(scoreMike);

            } else {
                if (numberOfTimesMikePicked50 == 1) {
                    scoreMike = scoreMike * 2;
                    mikeStatusMsg = getString(R.string.doubleEarningsMike);
                    //Display game status message
                    gameStatus.setText(mikeStatusMsg);
                    //Make Game Status Visible
                    gameStatus.setVisibility(View.VISIBLE);
                    //Display Mike's updated earnings
                    displayForMike(scoreMike);
                } else if (numberOfTimesMikePicked50 == 0) {
                    scoreMike = scoreMike * 3;
                    mikeStatusMsg = getString(R.string.tripleEarningsMike);
                    //Display game status message
                    gameStatus.setText(mikeStatusMsg);
                    //Make Game Status Visible
                    gameStatus.setVisibility(View.VISIBLE);
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
                        //Make Game Status Visible
                        gameStatus.setVisibility(View.VISIBLE);
                        //Display Mike's updated earnings
                        displayForMike(scoreMike);
                    } else {
                        //If the random integer created in the if statement was 6 or more
                        scoreMike = scoreMike * 3;
                        mikeStatusMsg = getString(R.string.tripleEarningsMike);
                        //Display game status message
                        gameStatus.setText(mikeStatusMsg);
                        //Make Game Status Visible
                        gameStatus.setVisibility(View.VISIBLE);
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
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        gameStatus.setText(getString(R.string.startNewGame));
        //Make Game Status Visible
        gameStatus.setVisibility(View.VISIBLE);
        scoreSuzy = 0;
        scoreMike = 0;
        displayForSuzy(scoreSuzy);
        displayForMike(scoreMike);
        numberOfTimesSuzyPicked50 = 0;
        numberOfTimesMikePicked50 = 0;
        numberOfTimesSuzySurprised = 0;
        numberOfTimesMikeSurprised = 0;
    }


}
