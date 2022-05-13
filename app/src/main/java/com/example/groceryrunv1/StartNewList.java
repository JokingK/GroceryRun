package com.example.groceryrunv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

//import java.lang.reflect.Array;
import java.io.Serializable;
import java.util.ArrayList;

public class StartNewList extends AppCompatActivity {

    public ArrayList<String> groceryList = new ArrayList<>(100);
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;
    Button findStores;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_list);

        groceryList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_layout, groceryList);

        listView = findViewById(R.id.ListView);
        listView.setAdapter(arrayAdapter);

        editText = findViewById(R.id.EditText);

        add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groceryList.add(editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        findStores = (Button) findViewById(R.id.findStores);
        findStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        });

    }

    public ArrayList<String> getList(){
        return groceryList;
    }


    public void addItemToList(View view){
        groceryList.add(editText.getText().toString());
        arrayAdapter.notifyDataSetChanged();
        editText.setText("");
    }

    public void openResultsPage(){
        Intent intent = new Intent(this, resultsPage.class);
        intent.putStringArrayListExtra("groceryList", groceryList);
        startActivity(intent);
    }
}