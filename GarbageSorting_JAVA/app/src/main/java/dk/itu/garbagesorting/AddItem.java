package dk.itu.garbagesorting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// test
public class AddItem extends AppCompatActivity {

    //Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        itemsDB = ItemsDB.get();

        //Text Fields
        TextView newWhat = findViewById(R.id.which_item);
        TextView newWhere = findViewById(R.id.where_to_place);

        Button addItem = findViewById(R.id.add_button);
        // adding a new thing
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS = newWhat.getText().toString().trim();
                String whereS = newWhere.getText().toString().trim();
                if ((whatS.length() > 0) && (whereS.length() > 0)) {
                    itemsDB.addItem(whatS, whereS);
                    newWhat.setText("");
                    newWhere.setText("");
                }
            }
        });
    }
}
