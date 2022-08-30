package com.example.ito5046;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class StartCompetitionFragment extends Fragment {
    private TextView battleName, competitorCount, competitor_text;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10;
    private Button nextButton;
    private String name;
    private int competitorTotal;
    private int position;
    private boolean answered;
    private Score score;
    private Competition competition;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_competition, container, false);
        battleName = view.findViewById(R.id.scoringName);
        competitor_text = view.findViewById(R.id.competitor_text);
        radioGroup = view.findViewById(R.id.radio_group);
        rb1 = view.findViewById(R.id.radio_button1);
        rb2 = view.findViewById(R.id.radio_button2);
        rb3 = view.findViewById(R.id.radio_button3);
        rb4 = view.findViewById(R.id.radio_button4);
        rb5 = view.findViewById(R.id.radio_button5);
        rb6 = view.findViewById(R.id.radio_button6);
        rb7 = view.findViewById(R.id.radio_button7);
        rb8 = view.findViewById(R.id.radio_button8);
        rb9 = view.findViewById(R.id.radio_button9);
        rb10 = view.findViewById(R.id.radio_button10);
        nextButton = view.findViewById(R.id.next_button);
        competitorCount = view.findViewById(R.id.competitor_count_text);
        score = startBattle();

        battleName.setText(Singleton.getBattle());
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!answered) {
                            // check if rb is checked
                            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()
                                    || rb5.isChecked() || rb6.isChecked() || rb7.isChecked() || rb8.isChecked()
                                    || rb9.isChecked() || rb10.isChecked()) {
                                // get next position
                                getNextBattler();
                            } else {
                                showToast("Please Select Score");
                            }

                        } else {
                            loadInformation();
                        }
                    }
                });
                thread.start();
            }
        });

        return view;
    }

    private void loadInformation(){
        radioGroup.clearCheck();

        if (position < competitorTotal) {
            position++;
            answered = false;
            nextButton.setText("Confirm");
        }
    }

    private void getNextBattler(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                RadioButton rbSelected = getActivity().findViewById(radioGroup.getCheckedRadioButtonId());
                System.out.println(rbSelected);
                // save input into int array
                int input = radioGroup.indexOfChild(rbSelected) +1;
                System.out.println(input);
                switch (input) {
                    case 1:
                        input = 10;
                        break;
                    case 2:
                        input = 9;
                        break;
                    case 3:
                        input = 8;
                        break;
                    case 4:
                        input = 7;
                        break;
                    case 5:
                        input = 6;
                        break;
                    case 6:
                        input = 5;
                        break;
                    case 7:
                        input = 4;
                        break;
                    case 8:
                        input = 3;
                        break;
                    case 9:
                        input = 2;
                        break;
                    case 10:
                        input = 1;
                        break;
                }
                score.getScoreArray()[score.getPosition()] = input;

                score.increasePosition(score.getPosition());

                if (position >= competitorTotal) {
                      finishBattle();
                }
                position++;
                if (score.getPosition() < competitorTotal){
                    nextButton.setText("Next");
                } else {
                    nextButton.setText("Finish");
                }
                if (position  > competitorTotal){
                    competitor_text.setText("Finished");
                } else {
                    competitorCount.setText(String.valueOf(position));
                }
                System.out.println("input is " + input);
                System.out.println("position is " + position);
                System.out.println("competitorTotal is " + competitorTotal);
                radioGroup.clearCheck();
            }
        });

    }

    public void showToast(final String toast)
    {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show());
    }

    private void finishBattle(){
        // insert into database
        if (competition.getJudgeNumber() > 0 && competition.getJudgeOne() == null) {
            competition.setJudgeOne(Arrays.toString(score.getScoreArray()));
            showToast("First Judge Score recorded");
        } else if (competition.getJudgeNumber() > 1 && competition.getJudgeTwo() == null) {
            competition.setJudgeTwo(Arrays.toString(score.getScoreArray()));
            showToast("Second Judge Score recorded");
        } else if (competition.getJudgeNumber() > 2 && competition.getJudgeThree() == null) {
            competition.setJudgeThree((Arrays.toString(score.getScoreArray())));
            showToast("Third Judge Score recorded");
        } else {
            showToast("Competition has already been recorded");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Database.getINSTANCE(getContext()).competitionDao().updateCompetition(competition);
            }
        });
        thread.start();

        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }



    private Score startBattle(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                score = new Score();
                competition = Database.getINSTANCE(getContext()).competitionDao().findCompetitionName(Singleton.getBattle());
                //set text for competitor
                score.setScoreArray(new int[competition.getCompetitorNumber()]);
                position = score.getPosition();
                competitorCount.setText(String.valueOf(position + 1));
                competitorTotal = score.getScoreArray().length;
                position++;
            }
        });
        thread.start();

        return score;
    }

}
