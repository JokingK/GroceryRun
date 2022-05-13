package com.example.groceryrunv1;
import java.io.Serializable;

public class Item {

    public String image;
    public String name;
    public String price;

    public Item(){

    }
    public Item(String i, String n, String p){
        this.image = i;
        this.name = n;
        this.price = p;
    }

    public void setImage(String i){
        this.image = i;
    }
    public String getImage(){
        return this.image;
    }

    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return this.name;
    }

    public void setPrice(String p){
        this.price = p;
    }
    public String getPrice(){
        return this.price;
    }

    public String toString(){
        String ln = "Image: " + this.getImage() + "Name: " + this.getName() + "PriceL " + this.getPrice();
        return ln;
    }
}
