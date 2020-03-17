package com.example.cornerstores.DBhelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cornerstores.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerInfo extends AppCompatActivity {
    EditText store_nme , pro_id, pro_description, pro_group,price;
    Button submit;
    DatabaseReference reff;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_seller_data);
        store_nme = (EditText) findViewById(R.id.store_text1);
        pro_id = (EditText)findViewById(R.id.p_id);
        pro_description = (EditText)findViewById(R.id.description);
        pro_group = (EditText) findViewById(R.id.group);
        price = (EditText) findViewById(R.id.price);
        submit = (Button)findViewById(R.id.submit);

        final SellerData sellerData = new SellerData();
        reff= FirebaseDatabase.getInstance().getReference().child("Seller_Info");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String s_name = store_nme.getText().toString().trim();
             String pro_des = pro_description.getText().toString().trim();
             String pro_g = pro_group.getText().toString().trim();
             Integer product_id = Integer.parseInt(pro_id.getText().toString().trim());
             Float product_pri = Float.parseFloat(price.getText().toString().trim());
             sellerData.setPrice(product_pri);
             sellerData.setStore_name(s_name);
             sellerData.setProduct_description(pro_des);
             sellerData.setProduct_group(pro_g);
             sellerData.setProduct_id(product_id);

             reff.push().setValue(sellerData);
                Toast.makeText(SellerInfo.this, "Seller Data Inserted Successfully", Toast.LENGTH_LONG).show();


            }
        });


    }
}
