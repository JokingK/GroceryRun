package com.example.groceryrunv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SelectStores extends AppCompatActivity {
    private Spinner dropDownStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stores);

        String[] stores = {" ", "Walmart", "Costco"};
        dropDownStores = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectStores.this, android.R.layout.simple_spinner_item,stores);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownStores.setAdapter(adapter);

    }
}