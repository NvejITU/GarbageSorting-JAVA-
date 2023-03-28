package dk.itu.garbagesorting;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ItemsDB extends ViewModel {
    private static ItemsDB sItemsDB;
    private List<Item> itemsDB= new ArrayList<>();

    public ItemsDB(Context context) { fillItemsDB(context); }

    public static void initialize(Context context) {
        if (sItemsDB == null) sItemsDB= new ItemsDB(context);
    }

    public static ItemsDB get() {
        if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDB;
    }

    public void addItem(String what, String where){
        itemsDB.add(new Item(what, where));
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

        for (Item existingItem : itemsDB) {
            if (existingItem.equals(item)){
                itemPlace = existingItem.getWhere();
            }
        }
        return itemPlace;
    }

    public List<Item> getList(){
        return itemsDB;
    }

    public int size() {
        return itemsDB.size();
    }

    public String listItems() {
        String r= "";
        for (Item item: itemsDB)
            r= r+"\n Buy "+item.getWhat() + " in: "  + item.getWhere();
        return r;
    }

    public void removeItem(String what) {
        for (Item existingItem : itemsDB) {
            if (existingItem.equals(what)) {
                itemsDB.remove(existingItem);
                break;
            }
        }
    }
}
