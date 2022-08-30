package com.example.ito5046;

import android.content.Intent;
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

public class DeleteFragment extends Fragment {
    private TextView delete_battle_name;
    private Button yes,no;
    private Competition competition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        delete_battle_name = view.findViewById(R.id.delete_battle_name);
        yes = view.findViewById(R.id.yes_delete_button);
        no = view.findViewById(R.id.no_delete_button);
        delete_battle_name.setText(Singleton.getBattle());

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        competition = Database.getINSTANCE(getContext()).competitionDao().findCompetitionName(Singleton.getBattle());
                        System.out.println(competition.toString());
                        Database.getINSTANCE(getContext()).competitionDao().deleteCompetition(competition);
                    }
                });
                thread.start();
                showToast("Competition Deleted");
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new SelectCompetitionFragment()).commit();
            }
        });


        return view;
    }

    public void showToast(final String toast)
    {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show());
    }
}
