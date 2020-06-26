package com.lucas.restaurante.dao;

import java.util.ArrayList;

public class SingletonPurchaseList  {

    private static SingletonPurchaseList mInstance;
    private ArrayList<Food> list = null;

    public static SingletonPurchaseList getInstance() {
        if(mInstance == null)
            mInstance = new SingletonPurchaseList();

        return mInstance;
    }

    private SingletonPurchaseList() {
        list = new ArrayList<Food>();
    }
    // retrieve array from anywhere
    public ArrayList<Food> getArray() {
        return this.list;
    }
    //Add element to array
    public void addToArray(Food value) {
        list.add(value);
    }

    public void cleanArray() {
        list.clear();
    }
}
