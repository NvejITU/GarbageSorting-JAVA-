package dk.itu.garbagesorting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import dk.itu.garbagesorting.ItemsDB;
import dk.itu.garbagesorting.R;

public class UIFragment extends Fragment {
    // Model: Database of items
    private static ItemsDB itemsDB;
// hjk
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);

        //Text Fields
        TextView input = v.findViewById(R.id.what_text);

        Button addItem = v.findViewById(R.id.add_item_button);

        Button findPlace = v.findViewById(R.id.where_button);
        findPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS = input.getText().toString().trim();
                String place = itemsDB.searchForItem(whatS);

                input.setText(whatS + " should be placed in: " + place);
            }
        });

        Button addItems = v.findViewById(R.id.add_item_button);
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UIFragment.this, AddItem.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
