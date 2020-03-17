package com.example.cornerstores.tempforpractice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cornerstores.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;

public class DatabaseTest extends AppCompatActivity {

    private static final String TAG = "DatabaseTest";
    ViewHolder viewHolder = new ViewHolder();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModelTemp userModelTemp = dataSnapshot.child(Integer.toString(32)).getValue(UserModelTemp.class);

                viewHolder.textView.setText(userModelTemp.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadCanceled", databaseError.toException());
                Toast.makeText(DatabaseTest.this, "Failed to load data", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.tempforpracticedatabase);

        viewHolder.button = (Button) findViewById(R.id.button);
        viewHolder.textView = (TextView) findViewById(R.id.textView);
        viewHolder.editText = (EditText) findViewById(R.id.editText);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("user");

                UserModelTemp userModelTemp = new UserModelTemp();
                UserAddressModel userAddressModel = new UserAddressModel();
                userAddressModel.setAddressId(234);
                userAddressModel.setHouseNo("23");
                userAddressModel.setLine1("234");
                userAddressModel.setState("up");
                userAddressModel.setZipCode("234222");
                ArrayList<UserAddressModel> addressModelArrayList = new ArrayList<>();
                addressModelArrayList.add(userAddressModel);
                addressModelArrayList.add(userAddressModel);
                userModelTemp.setAddressModel(addressModelArrayList);
                userModelTemp.setName(viewHolder.editText.getText().toString());
                userModelTemp.setUserId(32);
                userModelTemp.setEmail("2324");
                userModelTemp.setPassword("asdd");
                userModelTemp.setContactNo("324423523");
                myRef.child(Integer.toString(userModelTemp.getUserId())).setValue(userModelTemp);
//                myRef.setValue(viewHolder.editText.getText().toString());

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        UserModelTemp value = (UserModelTemp) dataSnapshot.child(Integer.toString(32)).getValue(UserModelTemp.class);

                        viewHolder.textView.setText(value.getContactNo());

                        Log.d(TAG, "Value is: " + value.getName());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
        });


    }

    class ViewHolder {
        public TextView textView;
        public EditText editText;
        public Button button;
    }
}
