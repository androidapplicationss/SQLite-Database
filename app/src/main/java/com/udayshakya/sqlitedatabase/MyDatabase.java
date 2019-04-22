package com.udayshakya.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 1/24/2019.
 */

public class MyDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;   //using capital words bcoz in java every predefined
    // final variables is always used in capitalletters
    public static final String DATABASE_NAME = "mydb";  //extesion file of the database is always tension of db i.e mydb.db

    //creating tables in databse

    public static final String TABLE = "made_in_india";

    //defining columns of the table

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String QTY = "qty";

    public MyDatabase(Context context) {
        //it is uesd to create databse in your phone
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String query = "CREATE TABLE " + TABLE + "(" + ID + " NUMBER PRIMARY KEY," + NAME + " TEXT, " + QTY + " NUMBER," + PRICE + " NUMBER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);

    }

    public void insertProducts(Products products) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        //using value method to get different paramenters using getters
        values.put(ID, products.getId());
        values.put(NAME, products.getName());
        values.put(QTY, products.getQty());
        values.put(PRICE, products.getPrice());


        db.insert(TABLE, null, values);
        //if u  want to put any columns as
        // null then we have to replace null with that particular column name
    }

    //to read a single record
    public Products getSingleProduct(int id) {
        SQLiteDatabase db = getReadableDatabase();

        //in query wehave to pass table name of string type, column name of string array type,
        // String selection, String array selection argumnet,String having,String orderby
        Cursor cursor = db.query(TABLE, new String[]{ID, NAME, QTY, PRICE},
                ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new Products(cursor.getInt(0), cursor.getInt(1),
                cursor.getInt(2), cursor.getString(3));

    }

    //to Read ALl Products Available in Database
    public ArrayList<Products> getAllProducts()

    {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Products> list = new ArrayList<Products>();


        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null); //secomd null column is a select argument

        if (cursor.moveToFirst()) {
            do {
                Products products = new Products();
                products.setId(cursor.getInt(0));
                products.setName(cursor.getString(1));
                products.setQty(cursor.getInt(2));
                products.setPrice(cursor.getInt(3));

                //set list to arraylist

                list.add(products);

            }
            while (cursor.moveToNext());
        }
        return list;

    }

    public void updateProducts(Products products) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        //using value method to get different paramenters using getters
        values.put(NAME, products.getName());
        values.put(QTY, products.getQty());
        values.put(PRICE, products.getPrice());

        db.update(TABLE, values, ID + "=?", new String[]{String.valueOf(products.getId())});
    }


    public void deleteProduct(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE, ID + "=?", new String[]{String.valueOf(id)});
    }

}
