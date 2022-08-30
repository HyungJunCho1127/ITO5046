package com.example.ito5046;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DeleteFragment extends Fragment {
    private TextView delete_battle_name;
    private Button yes,no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        delete_battle_name = view.findViewById(R.id.delete_battle_name);
        yes = view.findViewById(R.id.yes_delete_button);
        no = view.findViewById(R.id.no_delete_button);
        delete_battle_name.setText(Singleton.getBattle());


        return view;
    }
}
