package com.udayshakya.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    MyDatabase db;

    EditText edtId,edtName,edtQty,edtPrice;
    Button btnSave,btnRead,btnReadAll,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new MyDatabase(this);
        edtId=findViewById(R.id.editTextpid);
        edtName=findViewById(R.id.editTextpName);
        edtQty=findViewById(R.id.editTextpQty);
        edtPrice=findViewById(R.id.editTextpPricee);

        btnSave=findViewById(R.id.buttonSave);
        btnRead=findViewById(R.id.button);
        btnReadAll=findViewById(R.id.button2);
        btnUpdate=findViewById(R.id.button3);
        btnDelete=findViewById(R.id.button4);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int id=Integer.parseInt(edtId.getText().toString());
                String name=edtName.getText().toString();
                int qty=Integer.parseInt(edtQty.getText().toString());
                int price=Integer.parseInt(edtPrice.getText().toString());

                Products products=new Products(id,qty,price,name);

               db.insertProducts(products);


                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

                edtId.setText("");
                edtName.setText("");
                edtQty.setText("");
                edtPrice.setText("");
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String data=edtId.getText().toString();

                if(data.equals(""))
                {
                    edtId.setError("Please Enter Id");
                }
                else
                {
                    int id=Integer.parseInt(data);

                    Products obj=db.getSingleProduct(id);

                    Toast.makeText(MainActivity.this, " ID:-"+obj.getId()+"\n Name:-"+obj.getName()
                            +"\nQuantity:-"+obj.getQty()+"\nPrice:-"+obj.getPrice(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ArrayList<Products> list=db.getAllProducts();

                for(Products obj:list)
                {
                    Toast.makeText(MainActivity.this, " ID:-"+obj.getId()+"\n Name:-"+obj.getName()
                            +"\nQuantity:-"+obj.getQty()+"\nPrice:-"+obj.getPrice(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int id=Integer.parseInt(edtId.getText().toString());
                String name=edtName.getText().toString();
                int qty=Integer.parseInt(edtQty.getText().toString());
                int price=Integer.parseInt(edtPrice.getText().toString());


                Products products=new Products(id,qty,price,name);

                db.updateProducts(products);

                edtId.setText("");
                edtName.setText("");
                edtQty.setText("");
                edtPrice.setText("");

                Toast.makeText(MainActivity.this, "Records Updated...", Toast.LENGTH_SHORT).show();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String data=edtId.getText().toString();
                {
                    if(data.equals(""))
                    {
                        edtId.setError("Please enter a valid id");
                    }
                    else
                    {
                        int id=Integer.parseInt(data);
                        db.deleteProduct(id);
                        Toast.makeText(MainActivity.this, "Product Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
