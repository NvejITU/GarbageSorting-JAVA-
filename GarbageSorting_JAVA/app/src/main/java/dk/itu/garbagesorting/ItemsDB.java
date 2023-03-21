package dk.itu.garbagesorting;

import android.content.Context;
import android.database.Observable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ItemsDB extends ViewModel {
    private static ItemsDB sItemsDB;
    private Map<String, String> itemsDB= new HashMap<>();

    public ItemsDB(Context context) { fillItemsDB(context); }

    public static void initialize(Context context) {
        if (sItemsDB == null) sItemsDB= new ItemsDB(context);
    }

    public static ItemsDB get() {
        if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDB;
    }

    public void addItem(String what, String where){
        itemsDB.put(what, where);

    }

    public void fillItemsDB(Context context) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("garbage.txt")));
            String line = reader.readLine();
            while (line != null) {
                String[] listItem = line.split(", ");
                addItem(listItem[0], listItem[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public String searchForItem(String item){
        String itemPlace = "not found";

        for (Map.Entry<String, String> existingItem : itemsDB.entrySet()) {
            if (existingItem.getKey().equals(item)){
                itemPlace = existingItem.getValue();
            }
        }
        return itemPlace;
    }

    public Map<String, String> getMap(){
        return itemsDB;
    }

    public String listItems() {
        String r= "";
        for (HashMap.Entry <String, String> item: itemsDB.entrySet())
            r= r+"\n Buy "+item.getKey() + " in: "  + item.getValue();
        return r;
    }

    public void removeItem(String what) {
        for (Map.Entry<String, String> existingItem : itemsDB.entrySet()) {
            if (existingItem.getKey().equals(what)) {
                itemsDB.remove(existingItem);
                break;
            }
        }
    }
}
