package com.example.groceryrunv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button signup;
    private Button continueWithout;

    private String emailInput;
    private String passwordInput;

    private EditText emailField;
    private EditText passwordField;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.username);

        passwordField = findViewById(R.id.password);

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSignupPage();
            }
        });

        continueWithout = findViewById(R.id.withoutAccount);
        continueWithout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openDashboardWithoutAccount();
            }
        });

        auth = FirebaseAuth.getInstance(); //Firebase object
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                emailInput = emailField.getText().toString().trim();
                passwordInput = passwordField.getText().toString().trim();
                loginUser(emailInput, passwordInput);
            }
        });

    }

    //If they login correctly
    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(emailInput, passwordInput).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        auth.signInWithEmailAndPassword(emailInput, passwordInput).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (emailInput == null) {
                    Toast.makeText(MainActivity.this, "Email cannot be empty. Try again.", Toast.LENGTH_SHORT).show();
                } else if (passwordInput == null) {
                    Toast.makeText(MainActivity.this, "Password cannot be empty. Try again.", Toast.LENGTH_SHORT).show();
                } else if (emailInput == null && passwordInput == null) {
                    Toast.makeText(MainActivity.this, "Both fields are empty. Try again.", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
            }
        });

        //Fix when fields are empty: https://stackoverflow.com/questions/62772217/app-crashes-during-login-when-fields-are-empty
    }

    //If they want to sign up
    public void openSignupPage(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }

    //If they login without an account
    public void openDashboardWithoutAccount(){
        Intent intent = new Intent(this, DashboardWithoutAccount.class);
        startActivity(intent);
    }
}
