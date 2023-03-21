package dk.itu.garbagesorting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dk.itu.garbagesorting.ItemsDB;

import android.os.Bundle;

public class GarbageSorting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage_sorting);
        ItemsDB.initialize(GarbageSorting.this);
        setUpFragments();

        ItemsDB itemsDB = new ViewModelProvider(this).get(ItemsDB.class);
    }

    private void setUpFragments() {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentUI = fm.findFragmentById(R.id.container_ui);
        Fragment fragmentList = fm.findFragmentById(R.id.container_list);

        if (fragmentUI == null && fragmentList == null) {
            fragmentUI = new UIFragment();
            fragmentList = new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_ui, fragmentUI)
                    .add(androidx.leanback.R.id.container_list, fragmentList)
                    .commit();
        }

    }
}