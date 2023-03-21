package dk.itu.garbagesorting;

import android.content.Context;
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
        // adding a new thing
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddItem.class);
                startActivity(intent);
            }
        });

        Button findPlace = v.findViewById(R.id.where_button);
        findPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what = input.getText().toString().trim();
                String place = itemsDB.searchForItem(what);
                input.setBackgroundColor(Color.parseColor("#FFFFFF"));
                input.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text

                input.setText(what + " should be placed in: " + place);
            }
        });

        Button deleteItem = v.findViewById(R.id.delete_item_button);
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what = input.getText().toString().trim();
                input.setBackgroundColor(Color.parseColor("#FFFFFF"));
                input.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                itemsDB.removeItem(what);
            }
        });

        return v;
    }
}
