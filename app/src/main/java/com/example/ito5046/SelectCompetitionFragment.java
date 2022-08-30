package com.example.ito5046;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectCompetitionFragment extends Fragment {
    private RecyclerView recyclerView;

    private ArrayList<String> competitionName;
    private CompetitionListAdapter competitionListAdapter;
    private List<Competition> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_competition, container, false);
        competitionName = new ArrayList<>();
        storeDataInArrays();
        recyclerView = view.findViewById(R.id.view);


        competitionListAdapter = new CompetitionListAdapter(getContext(), competitionName);
        recyclerView.setAdapter(competitionListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void storeDataInArrays(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list = Database.getINSTANCE(getContext()).competitionDao().getAllUserCompetitions(Singleton.getValue());
                if (list != null){
                    for (int i = 0; i < list.size();i++){
                        competitionName.add(list.get(i).getCompetitionName());
                    }
                } else {
                    showToast("No Data");
                }
            }
        });
        thread.start();


    }

    public void showToast(final String toast)
    {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show());
    }
}
