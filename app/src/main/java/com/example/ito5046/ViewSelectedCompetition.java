package com.example.ito5046;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewSelectedCompetition extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_competition);



        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new SelectedCompetitionFragment()).commit();
        }
    }
}