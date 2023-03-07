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
        TextView itemWhat = v.findViewById(R.id.what_text);

        Button addItem = v.findViewById(R.id.add_item_button);
        // adding a new thing
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UIFragment.this, AddItem.class);
                startActivity(intent);
            }
        });

        Button findItems = v.findViewById(R.id.where_button);
        findItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what= itemWhat.getText().toString().trim();
                itemWhat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
            }
        });

        return v;
    }
}
