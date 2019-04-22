package com.udayshakya.sqlitedatabase;

/**
 * Created by User on 1/24/2019.
 */

public class Products
{
    String name;
    int id,qty,price;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Products(int id, int qty, int price, String name)
    {
        this.id = id;
        this.qty = qty;
        this.price = price;
        this.name = name;
    }



    public Products()
    {

    }



}
