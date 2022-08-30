package com.example.ito5046;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button loginButton, forgotAccount;
    private String user;
    private String pass;
    private User account, checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        forgotAccount = findViewById(R.id.forgotPassword);

        loginButton = findViewById(R.id.buttonSignIn1);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (user.isEmpty()|| pass.isEmpty()){
                        showToast("Please enter all fields");
                    } else {
                        checker = Database.getINSTANCE(getApplicationContext()).userDao().matchUsernameAndPassword(user, pass);
                        if (checker == null){
                            showToast("Incorrect Credentials");
                        } else {
                            signIn(user);
                        }
                    }

                }
            });
                thread.start();
            }
        });

        forgotAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotAccount();
            }
        });
    }



    public void showToast(final String toast)
    {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show());
    }

    public void signIn(String user){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        if (Singleton.getValue() != null){
            Singleton.replaceValue(user);
        } else  {
            Singleton.addValue(user);
        }
        startActivity(intent);
    }

    public void forgotAccount(){
        Intent intent = new Intent(getApplicationContext(), ForgotAccountActivity.class);
        startActivity(intent);
    }
}