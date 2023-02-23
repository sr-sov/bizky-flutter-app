package uplb.ics.cmsc191.bizky;

import java.io.Serializable;

public class Product implements Serializable{
    int id;
    private String name;
    private float price;
    private int quantity;
    private int maxQuantity;
    private int cartQuantity;
    //private int imageResourceId;

    public static Product[] product = {
            new Product(1, "Nestea",(float) 15.5, 2),
            new Product(2, "Bread",(float) 1.0, 5)
    };

    public Product(int id, String name, float price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.maxQuantity = quantity;
        this.cartQuantity = 0;
        //this.imageResourceId = imageResourceId;
    }

    public int id(){
        return id;
    }

    public float getPrice(){
        return price;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int q){ quantity = q; }

    public void setCartQuantity(int q){ cartQuantity = maxQuantity-q; }

    public int getMaxQuantity(){ return maxQuantity; }

    public int getCartQuantity(){ return cartQuantity; }

    public String getName(){
        return name;
    }

    /*public int getImageResourceId(){
        return imageResourceId;
    }*/

    public String toString(){
        return this.name;
    }
}
