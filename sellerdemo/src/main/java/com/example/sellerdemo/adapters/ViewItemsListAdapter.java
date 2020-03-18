package com.example.sellerdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sellerdemo.R;
import com.example.sellerdemo.models.ItemModel;

import java.util.List;

import javax.xml.transform.Templates;

public class ViewItemsListAdapter extends ArrayAdapter<ItemModel> {

    public ViewItemsListAdapter(@NonNull Context context, int resource, @NonNull List<ItemModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_items_list_layout, null);

        TextView item_name = (TextView) view.findViewById(R.id.item_name);

        ItemModel itemModel = getItem(position);

        item_name.setText(itemModel.getItem_name());

        return super.getView(position, convertView, parent);
    }
}
