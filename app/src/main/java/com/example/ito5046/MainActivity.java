package com.example.ito5046;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username, password, rePassword, emailText, reEmailText;
    private Button signUp, signIn;
    private String user, pass, repass, email, reEmail;
    private User account, checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.repassword);
        signUp = findViewById(R.id.buttonSignUp);
        signIn = findViewById(R.id.buttonSignIn);
        emailText = findViewById(R.id.email);
        reEmailText = findViewById(R.id.reemail);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user = username.getText().toString();
                        pass = password.getText().toString();
                        repass = rePassword.getText().toString();
                        email = emailText.getText().toString();
                        reEmail = reEmailText.getText().toString();

                        if (user.isEmpty()|| pass.isEmpty() || repass.isEmpty()
                        ||email.isEmpty() || reEmail.isEmpty()){
                            showToast("Please enter all fields");
                        } else {
                            if (pass.equals(repass)){
                                if (email.equals(reEmail)) {
                                    checker = Database.getINSTANCE(getApplicationContext()).userDao().findUsername(user);
                                    if (checker == null) {
                                        account = new User(user, pass, email);
                                        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
                                        insertAsyncTask.execute(account);
                                        registered(user);
                                    } else {
                                        showToast("User Already Exists");
                                    }
                                } else {
                                    showToast("Emails do not match");
                                }
                            } else {
                                showToast("Passwords do not match");
                            }
                        }
                    }
                });
                thread.start();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    class InsertAsyncTask extends AsyncTask<User,Void,Void> {

        @Override
        protected Void doInBackground(User... users) {
            Database.getINSTANCE(getApplicationContext()).userDao().insertUser(users[0]);
            return null;
        }
    }

    public void registered(String user){
        showToast("Registered");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        Singleton.addValue(user);
        startActivity(intent);
    }

    public void signIn(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void showToast(final String toast)
    {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show());
    }
}