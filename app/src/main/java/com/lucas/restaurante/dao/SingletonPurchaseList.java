package com.lucas.restaurante.dao;

import android.content.Context;
import com.lucas.restaurante.storage.StateElementsManager;

import java.util.ArrayList;

public class SingletonPurchaseList  {

    private static SingletonPurchaseList mInstance;
    private ArrayList<Food> list = null;

    public static SingletonPurchaseList getInstance(Context ct) {
        if(mInstance == null)
            mInstance = new SingletonPurchaseList(ct);

        return mInstance;
    }

    private SingletonPurchaseList(Context ct) {
        list = new ArrayList<Food>();
        list = (ArrayList<Food>) StateElementsManager.loadState(ct,"purchaseList",true );
    }
    // retrieve array from anywhere
    public ArrayList<Food> getArray() {
        return this.list;
    }
    //Add element to array

    public void loadArray (ArrayList<Food> arrayList){
        if (list.isEmpty()){
            list.addAll(arrayList);
        }
    }
    public void addToArray(Food value, Context ct) {
        list.add(value);
        StateElementsManager.saveState(ct, list, "purchaseList");
    }
    //Add element to array
    public void cleanArray(Context ct) {
        list.clear();
        StateElementsManager.saveState(ct, list, "purchaseList");
    }
}
