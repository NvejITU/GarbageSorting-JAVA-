package dk.itu.garbagesorting;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.Map;

public class ItemsViewModel extends ViewModel {
        private static MutableLiveData<ItemsDB> items;

        public ItemsViewModel() {
            items= new MutableLiveData<>();
            items.setValue(ItemsDB.get());
        }

        public MutableLiveData<ItemsDB> getValue() { return items; }

        public void addItem(String what, String where){
            ItemsDB temp = items.getValue();
            temp.addItem(what, where);
            items.setValue(temp);
        }

        public void removeItem(String what){
            ItemsDB temp= items.getValue();
            temp.removeItem(what);
            items.setValue(temp);
        }
        public List<Item> getList() {
            return items.getValue().getMap();
        }

        public int size() {
            return items.getValue().getMap().size();
        }
    }
