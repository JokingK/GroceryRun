package com.example.groceryrunv1;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.groceryrunv1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> storeList;
    private HashMap<String, List<String>> itemList;

    private int[] images = {R.drawable.targetlogo, R.drawable.walmartlogo, R.drawable.stopshoplogo};
    public static String[] toto = resultsPage.totals;

    public ExpandableListViewAdapter(Context context, List<String> storeList, HashMap<String, List<String>> itemList) {
        this.context = context;
        this.storeList = storeList;
        this.itemList = itemList;
    }

    @Override
    public int getGroupCount() {
        return this.storeList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.itemList.get(this.storeList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.storeList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.itemList.get(this.storeList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String storeL = (String) getGroup(groupPosition);

        if ( convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.store, null);
        }
        TextView storeTv = convertView.findViewById(R.id.storesList);
        storeTv.setText(storeL);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.ImageView);
        imageView.setImageResource(images[groupPosition]);

        TextView totalPrice = convertView.findViewById(R.id.TotalPrice);
        totalPrice.setText(toto[groupPosition]);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String itemL = (String) getChild(groupPosition, childPosition);

        if ( convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemlist, null);
        }
        TextView itemTv = convertView.findViewById(R.id.itemsList);
        itemTv.setText(itemL);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}