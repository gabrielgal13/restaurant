package com.lucas.restaurante.dao;

import android.content.Context;
import com.lucas.restaurante.storage.StateElementsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        loadArray((ArrayList<Food>) StateElementsManager.loadState(ct,"purchaseList",true ));
    }

    public ArrayList<Food> getArray() {
        return this.list;
    }

    public void loadArray (ArrayList<Food> arrayList){
        if (list.isEmpty()){
            list.addAll(arrayList);
        }
    }
    public void addToArray(Food value, Context ct) {
        list.add(value);
        Map<Object, Integer> price = new HashMap<>();
        price = list.stream()
                .collect(Collectors.groupingBy(food -> food.getTitle(),
                        Collectors.summingInt(food->food.getQuantity())));

        HashMap<String,Food> purchaseHash = new HashMap<>();
        for (Food food : list) {
            food.setQuantity(price.get(food.getTitle()));
            purchaseHash.put(food.getTitle(),food);
        }
        list = new ArrayList<Food>(purchaseHash.values());
        StateElementsManager.saveState(ct, list, "purchaseList");
    }
    //Add element to array
    public void cleanArray(Context ct) {
        list.clear();
        StateElementsManager.saveState(ct, list, "purchaseList");
    }

    public Double getSum () {
        return  list.stream().mapToDouble(Food::getPrice).sum() * list.stream().mapToDouble(Food::getQuantity).sum();
    }

    public Integer  getCount () {
        return  list.isEmpty() ? 0 : list.size();
    }
}
