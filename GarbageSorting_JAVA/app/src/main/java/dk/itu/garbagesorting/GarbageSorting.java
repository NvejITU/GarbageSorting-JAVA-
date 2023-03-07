package dk.itu.garbagesorting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GarbageSorting extends AppCompatActivity {

    //Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage_sorting_portrait);

        ItemsDB.initialize(GarbageSorting.this);
        itemsDB = ItemsDB.get();


    }
}