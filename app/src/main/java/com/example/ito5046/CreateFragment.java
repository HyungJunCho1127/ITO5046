package com.example.ito5046;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateFragment extends Fragment {
    private Button createBattleButton;
    private Spinner spinner;
    private EditText battleName, competitorNo;
    private String name, number, user;
    private Competition checker, competition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        Bundle data = getArguments();
        if (data != null){
            user = data.get("data").toString();
        }

        createBattleButton = (Button) view.findViewById(R.id.createBattleButton);

        battleName = (EditText) view.findViewById(R.id.battleName);
        competitorNo = (EditText) view.findViewById(R.id.competitorNo);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.judgeNo));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        createBattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (battleName.getText().toString().length() == 0 ||
                                competitorNo.getText().toString().length() == 0 ||
                                spinner.getSelectedItem().toString().length() == 0){
                            showToast("Please fill in all fields");
                        } else {
                            // put method here to check in Database if name exists
                            checker = Database.getINSTANCE(getContext()).competitionDao().findCompetitionName(battleName.getText().toString());
                            if (checker == null){
                                competition = new Competition(Singleton.getValue(),battleName.getText().toString(),
                                        Integer.parseInt(spinner.getSelectedItem().toString()), Integer.parseInt(competitorNo.getText().toString()));
                                InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
                                insertAsyncTask.execute(competition);
                                showToast("Competition Created");
                            } else {
                                showToast("Competition already exists \n Please change Competition Name");
                            }

                        }

                        // send this to methods that make the battle and populate recycler view.
                    }
                });
                thread.start();
                    }
                });

        return view;
    }

    class InsertAsyncTask extends AsyncTask<Competition,Void,Void> {

        @Override
        protected Void doInBackground(Competition... Competitions) {
            Database.getINSTANCE(getContext()).competitionDao().insertCompetition(Competitions[0]);
            return null;
        }
    }

    public void showToast(final String toast)
    {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show());
    }


}
