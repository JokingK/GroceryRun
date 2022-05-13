package com.example.groceryrunv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.TextView;

//import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class resultsPage extends AppCompatActivity {

    ArrayList<Item> store = new ArrayList<>(100);
    ArrayList<Item> target = new ArrayList<>(100);
    ArrayList<Item> walmart = new ArrayList<>(100);
    ArrayList<Item> stopandshop = new ArrayList<>(100);
    //-----------------------------------------------------------
    ArrayList<Item> bag = new ArrayList<>();
    static String[] totals = new String[10]; //needs only to be 0,1,2
    //static String[] totals = {"14.99", "12.99", "34.99"};
    ArrayList<Item> targetBag = new ArrayList<>();
    ArrayList<Item> walmartBag = new ArrayList<>();
    ArrayList<Item> stopandshopBag = new ArrayList<>();

    ListView listView;
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> storeList;
    HashMap<String, List<String>> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        Log.d("TAG", "onCreate Started");

        readStoreData();

        //Item i1 = new Item("dins", "milk", "$5.99");
        //Item i2 = new Item("idns", "bread", "$2.99");
        //store.add(i1);
        //store.add(i2);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> requests = extras.getStringArrayList("groceryList");

        requests.add("eggs");
        //ListView listView = (ListView) findViewById(R.id.ListView);
        bag = findItems(requests, store);
        //ItemListAdapter adapter = new ItemListAdapter(this, R.layout.adapter_view_layout, bag);
        //listView.setAdapter(adapter);

        for(int i = 0; i < totals.length; i++){
            double temp1 = findTotalPrice(bag);
            String tot = "$" + Double.toString(temp1);
            totals[i] = tot;
        }

        //-------------------------------------------------------
        expandableListView = findViewById(R.id.listview);
        showList();
        listViewAdapter = new ExpandableListViewAdapter(this, storeList, itemList);
        expandableListView.setAdapter(listViewAdapter);

    }

    private void readStoreData() {
        InputStream is = getResources().openRawResource(R.raw.teststoredata);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String ln;
        try {
            while ((ln = reader.readLine()) != null) {
                String[] tokens = ln.split(",");
                Item item = new Item();
                item.setImage(tokens[0]);
                item.setName(tokens[1]);
                item.setPrice(tokens[2]);
                store.add(item);

                Log.d("resultsPage", "Just Created: " + item);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Item> findItems(ArrayList<String> requests, ArrayList<Item> store){
        ArrayList<Item> bag = new ArrayList<Item>();

        Item current = null;

        for(int i = 0; i < requests.size(); i++){
            for(int j = 0; j < store.size(); j++){
                current = store.get(j);
                if(current.name.contains(requests.get(i))){
                    bag.add(current);
                }
            }
        }
        return bag;
    }

    private void showList() {
        storeList = new ArrayList<>();
        itemList = new HashMap<String, List<String>>();

        storeList.add("Target");
        storeList.add("Walmart");
        storeList.add("Stop&Shop");

        //These can be replaced by lists made from csv data

        List<String> itemSet1 = new ArrayList<>();
        for(int i = 0; i < bag.size(); i++){
            itemSet1.add(bag.get(i).getName() + " | " + bag.get(i).getPrice());
        }
        //itemSet1.add("Milk");
        //itemSet1.add("Eggs");
        //itemSet1.add("Bread");

        List<String> itemSet2 = new ArrayList<>();
        itemSet2.add("Milk");
        itemSet2.add("Eggs");
        itemSet2.add("Bread");

        List<String> itemSet3 = new ArrayList<>();
        itemSet3.add("Milk");
        itemSet3.add("Eggs");
        itemSet3.add("Bread");

        itemList.put(storeList.get(0), itemSet1);
        itemList.put(storeList.get(1), itemSet2);
        itemList.put(storeList.get(2), itemSet3);


    }

    public static double findTotalPrice(ArrayList<Item> bag){
        double totalPrice = 0.0;
        double price = 0.0;
        Item current = null;

        for(int i = 0; i < bag.size(); i++){
            current = bag.get(i);
            try{
                String pri = current.price.substring(1);    //only counts numbers from "$4.55"
                System.out.println(pri);
                price = Double.parseDouble(pri);
            }catch(NumberFormatException e){
                price = 0;
            }

            totalPrice += price;
        }

        return totalPrice;
    }
}