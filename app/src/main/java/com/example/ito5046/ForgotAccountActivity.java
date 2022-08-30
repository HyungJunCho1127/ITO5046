package com.example.ito5046;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotAccountActivity extends AppCompatActivity {
    private EditText resetEmailText, forgotUsername;
    private Button resetEmailButton, returnToLogin;
    private String email, username;
    private User checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_account);

        resetEmailText = findViewById(R.id.resetEmailText);
        resetEmailButton = findViewById(R.id.resetEmailButton);
        forgotUsername = findViewById(R.id.forgotUsername);
        returnToLogin = findViewById(R.id.returnToLogin);

        resetEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = resetEmailText.getText().toString();
                username = forgotUsername.getText().toString();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (email.isEmpty() || username.isEmpty()){
                            showToast("Please enter all fields");
                        } else {
                            checker = Database.getINSTANCE(getApplicationContext()).userDao().matchUsernameAndEmail(username, email);
                            if (checker == null){
                                showToast("Invalid Credentials");
                            } else {
                                setNewPassword(username);
                            }
                        }

                    }
                });
                thread.start();
            }
        });

        returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogin();
            }
        });
    }

    public void showToast(final String toast)
    {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show());
    }

    public void setNewPassword(String username){
        Intent intent = new Intent(getApplicationContext(), SetNewPasswordActivity.class);
        intent.putExtra("User", username);
        startActivity(intent);
    }

    public void backToLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }



}
