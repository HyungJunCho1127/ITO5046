package com.example.ito5046;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetNewPasswordActivity extends AppCompatActivity {
    private String user, newPassword, reNewPassword;
    private Button setNewPassword, backToLogin;
    private EditText password, confirmPassword;
    private User account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        user = getIntent().getStringExtra("User");
        setNewPassword = findViewById(R.id.setNewPassword);
        backToLogin = findViewById(R.id.returnToLogin2);
        password = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmNewPassword);
        setNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPassword = password.getText().toString();
                reNewPassword = confirmPassword.getText().toString();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (newPassword.isEmpty()|| reNewPassword.isEmpty()){
                            showToast("Please enter all fields");
                        } else {
                            if (newPassword.equals(reNewPassword)){
                                account = Database.getINSTANCE(getApplicationContext()).userDao().findUsername(user);
                                account.setPassword(newPassword);
                                Database.getINSTANCE(getApplicationContext()).userDao().updatePassword(account);
                                passwordChangeComplete(user);
                            } else {
                                showToast("Passwords do not match");
                            }
                        }

                    }
                });
                thread.start();
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogin();
            }
        });
    }

    public void backToLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void showToast(final String toast)
    {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show());
    }

    public void passwordChangeComplete(String user){
        showToast("Password Updated");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}