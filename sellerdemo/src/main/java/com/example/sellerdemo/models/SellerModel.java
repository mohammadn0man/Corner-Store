package com.example.sellerdemo.models;

import java.util.ArrayList;

public class SellerModel {
    String store_name;
    String store_address;
    String store_contact_no;
    ArrayList<ItemModel> itemModelArrayList;

    public SellerModel() {
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_contact_no() {
        return store_contact_no;
    }

    public void setStore_contact_no(String store_contact_no) {
        this.store_contact_no = store_contact_no;
    }

    public ArrayList<ItemModel> getItemModelArrayList() {
        return itemModelArrayList;
    }

    public void setItemModelArrayList(ArrayList<ItemModel> itemModelArrayList) {
        this.itemModelArrayList = itemModelArrayList;
    }
}
