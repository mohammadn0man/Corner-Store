package com.example.sellerdemo.sellerhome.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sellerdemo.R;
import com.example.sellerdemo.models.ItemModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {

    private static final String TAG = "MyAddItem";
    ViewHolder viewHolder = new ViewHolder();
    ItemModel itemModel = new ItemModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        viewHolder.itemName = (EditText) findViewById(R.id.item_name);
        viewHolder.itemMeasure = (EditText) findViewById(R.id.item_measure);
        viewHolder.itemRate = (EditText) findViewById(R.id.item_rate);
        viewHolder.itemStatus = (EditText) findViewById(R.id.item_status);
        viewHolder.itemStockAvailable = (EditText) findViewById(R.id.item_stock_available);
        viewHolder.itemDescription = (EditText) findViewById(R.id.item_description);
        viewHolder.addItemButton = (Button) findViewById(R.id.add_item_button);

        viewHolder.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Seller_info").child("Store_name").child("itemModelArrayList");

                itemModel.setItem_name(viewHolder.itemName.getText().toString().trim());
                itemModel.setItem_rate(viewHolder.itemRate.getText().toString().trim());
                itemModel.setItem_measure(viewHolder.itemMeasure.getText().toString().trim());
                itemModel.setItem_status(viewHolder.itemStatus.getText().toString().trim());
                itemModel.setItem_description(viewHolder.itemDescription.getText().toString().trim());
                itemModel.setItem_stock_availability(viewHolder.itemStockAvailable.getText().toString().trim());

                Log.e(TAG, "name " + viewHolder.itemName.getText().toString().trim());

                databaseReference.push().setValue(itemModel);

                Toast.makeText(AddItem.this, itemModel.getItem_name() + " Added sucessfully", Toast.LENGTH_LONG).show();

                finish();

            }
        });



    }

    class ViewHolder {
        EditText itemName;
        EditText itemRate;
        EditText itemMeasure;
        EditText itemDescription;
        EditText itemStockAvailable;
        EditText itemStatus;
        Button addItemButton;
        public ViewHolder() {
        }
    }
}
