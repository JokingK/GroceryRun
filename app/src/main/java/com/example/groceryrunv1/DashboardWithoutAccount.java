package com.example.groceryrunv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardWithoutAccount extends AppCompatActivity {
    private Button startNewListButton;
    private Button mapButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_without_account);

        startNewListButton = findViewById(R.id.button2);
        startNewListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewList();
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