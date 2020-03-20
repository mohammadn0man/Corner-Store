package com.example.sellerdemo.sellerhome.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sellerdemo.R;
import com.example.sellerdemo.adapters.ViewItemsListAdapter;
import com.example.sellerdemo.models.ItemModel;
import com.example.sellerdemo.models.SellerModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewItems extends AppCompatActivity {
    private static final String TAG = "myViewList";
    public ListView listView;
    ViewItemsListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_items);
        listView = (ListView) findViewById(R.id.view_items_listview);

        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Seller_info").child("Store_name").child("itemModelArrayList");

        Log.d(TAG, "about to query");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    itemModelArrayList.add(ds.getValue(ItemModel.class));
                }
                Log.d(TAG, itemModelArrayList.get(0).getItem_name());


                adapter = new ViewItemsListAdapter(ViewItems.this, R.layout.view_items_list_layout, itemModelArrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
// SEARCH BAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
