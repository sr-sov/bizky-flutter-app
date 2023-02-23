package uplb.ics.cmsc191.bizky;

import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable{
    int id;
    private String name;
    private float price;
    private String date;
    //private int imageResourceId;

    public Invoice(int id, String name, float price, String s){
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = s;
        //this.imageResourceId = imageResourceId;
    }

    public int id(){
        return id;
    }

    public float getPrice(){
        return price;
    }

    public String getDate() { return date; }

    public String getName(){
        return name;
    }

    public String toString(){
        return this.name;
    }
}
