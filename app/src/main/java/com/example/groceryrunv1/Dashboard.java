package com.example.groceryrunv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    private Button startNewListButton;
    private Button previousListsButton;
    private Button mapButton;
    private Button exitButton;
    private Button logout;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        startNewListButton = findViewById(R.id.button2);
        startNewListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewList();
            }
        });

        previousListsButton = findViewById(R.id.button3);
        previousListsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        mapButton = findViewById(R.id.button4);
        mapButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openMap();
            }
        });

        exitButton = findViewById(R.id.button5);
        exitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                closeApp();
            }
        });

        auth = FirebaseAuth.getInstance(); //Firebase object
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                auth.signOut();;
                Toast.makeText(Dashboard.this, "Log Out Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this, MainActivity.class));
            }
        });
    }

    public void openNewList(){
        Intent intent = new Intent(this, StartNewList.class);
        startActivity(intent);
    }

    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void closeApp(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
