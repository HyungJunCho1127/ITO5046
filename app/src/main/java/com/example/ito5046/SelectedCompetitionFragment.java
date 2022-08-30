package com.example.ito5046;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectedCompetitionFragment extends Fragment {
    private Button startCompetition, deleteCompetition;
    private TextView selected_battle_name,selected_battle_judge,selected_battle_competitors;
    private Competition competition;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_compeition, container, false);
        startCompetition = view.findViewById(R.id.start_judge_button);
        deleteCompetition = view.findViewById(R.id.delete_battle_button);
        selected_battle_name = (TextView) view.findViewById(R.id.selected_battle_name);
        selected_battle_judge = (TextView) view.findViewById(R.id.selected_battle_judge);
        selected_battle_competitors = (TextView) view.findViewById(R.id.selected_battle_competitors);

        setDataToViews();
        deleteCompetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new DeleteFragment()).commit();
                    }
                });
                thread.start();
            }
        });

        startCompetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fragment for battle start and saving etc etc.
                checkIfBattleComplete();
            }
        });

        return view;
    }

    public void setDataToViews(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                competition = Database.getINSTANCE(getContext()).competitionDao().findCompetitionName(Singleton.getBattle());
                selected_battle_name.setText(String.valueOf(competition.getCompetitionName()));
                selected_battle_judge.setText(String.valueOf(competition.getJudgeNumber()));
                selected_battle_competitors.setText(String.valueOf(competition.getCompetitorNumber()));
            }
        });
        thread.start();

    }

    public void checkIfBattleComplete(){
        // insert into database
        if (competition.getJudgeNumber() == 1 && competition.getJudgeOne() != null) {
            showToast("Competition has already been finished");
        } else if (competition.getJudgeNumber() == 2 && competition.getJudgeTwo() != null) {
            showToast("Competition has already been finished");
        } else if (competition.getJudgeNumber() == 3 && competition.getJudgeThree() != null) {
            showToast("Competition has already been finished");
        } else {
            showToast("Competition Start");
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new StartCompetitionFragment()).commit();
        }
    }

    public void showToast(final String toast)
    {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show());
    }
}
