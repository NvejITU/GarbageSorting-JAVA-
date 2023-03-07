package dk.itu.garbagesorting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GarbageSorting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage_sorting_portrait);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentUI = fm.findFragmentById(R.id.container_ui);
        Fragment fragmentList = fm.findFragmentById(androidx.leanback.R.id.container_list);

        if (fragmentUI == null && fragmentList == null) {
            fragmentUI = new UIFragment();
            fragmentList = new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_ui, fragmentUI)
                    .commit();
            fm.beginTransaction()
                    .add(androidx.leanback.R.id.container_list, fragmentList)
                    .commit();
        }

    }
}