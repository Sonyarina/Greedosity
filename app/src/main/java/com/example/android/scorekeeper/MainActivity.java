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
     * Suzy's "Surprise" button was clicked. This looks at the number of times
     * Suzy picked $50. If she picked $50 five or more times, she'll lose all her money.
     * If she picked $50 zero times, her score triples. If she only picked $50 once,
     * her score doubles. If the starting score is zero, the Surprise button adds a random
     * amount between $10 and $50.
     */
    public void surpriseSuzy(View view) {
        //Creates temporary string to store Suzy Game Alert Message
        String suzyStatusMsg = "";
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        numberOfTimesSuzySurprised = numberOfTimesSuzySurprised + 1;
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
                } else if (numberOfTimesSuzyPicked50 == 2) {
                    scoreSuzy = scoreSuzy + 5;
                    suzyStatusMsg = getString(R.string.fiveDollarEarningsSuzy);
                    //Display game status message
                    gameStatus.setText(suzyStatusMsg);
                    //Make Game Status Visible
                    gameStatus.setVisibility(View.VISIBLE);
                    //Display Suzy's updated earnings
                    displayForSuzy(scoreSuzy);
                } else {
                    scoreSuzy = 0;
                    numberOfTimesSuzyPicked50 = 0;
                    suzyStatusMsg = getString(R.string.backToZeroSuzy);
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
     * Mike's "Surprise" button was clicked. This looks at the number of times
     * Mike picked $50. If he picked $50 three or more times, he'll lose all his money.
     * If he picked $50 zero times, his score triples. If he only picked $50 once,
     * his score doubles. If the starting score is zero, the Surprise button adds a random
     * amount between $10 and $50.
     */
    public void surpriseMike(View view) {
        //Creates temporary string to store Mike Game Alert Message
        String mikeStatusMsg = "";
        TextView gameStatus = (TextView) findViewById(R.id.game_status_text_view);
        numberOfTimesMikeSurprised = numberOfTimesMikeSurprised + 1;
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
                } else if (numberOfTimesMikePicked50 == 2) {
                    scoreMike = scoreMike + 5;
                    mikeStatusMsg = getString(R.string.fiveDollarEarningsMike);
                    //Display game status message
                    gameStatus.setText(mikeStatusMsg);
                    //Make Game Status Visible
                    gameStatus.setVisibility(View.VISIBLE);
                    //Display Suzy's updated earnings
                    displayForSuzy(scoreSuzy);
                } else {
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
