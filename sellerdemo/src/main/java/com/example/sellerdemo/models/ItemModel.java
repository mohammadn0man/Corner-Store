package com.example.sellerdemo.models;

public class ItemModel {
    public String item_name;
    public String item_rate;
    public String item_measure;
    public String item_description;
    public String item_status;
    public String item_stock_availability;

    public ItemModel() {
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_rate() {
        return item_rate;
    }

    public void setItem_rate(String item_rate) {
        this.item_rate = item_rate;
    }

    public String getItem_measure() {
        return item_measure;
    }

    public void setItem_measure(String item_measure) {
        this.item_measure = item_measure;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    public String getItem_stock_availability() {
        return item_stock_availability;
    }

    public void setItem_stock_availability(String item_stock_availability) {
        this.item_stock_availability = item_stock_availability;
    }
}
