package dk.itu.garbagesorting;

import android.content.ClipData;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

import dk.itu.garbagesorting.ItemsDB;
import dk.itu.garbagesorting.R;

public class ListFragment extends Fragment {
    ItemsViewModel itemsDB;
    private TextView listThings;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v= inflater.inflate(R.layout.fragment_list, container, false);
        itemsDB = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);

        RecyclerView itemList = v.findViewById(R.id.listItems);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemAdapter mAdapter = new ItemAdapter();
        ItemList.setAdapter(mAdapter);
        itemsDB.getValue().observe(getActivity(),itemsDB -> mAdapter.notifyDataSetChanged());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Button backButton = v.findViewById(R.id.back_button);
            backButton.setOnClickListener(view ->
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(androidx.leanback.R.id.main_fragment ,new UIFragment()).commit());
        }

        return v;
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView mWhatTextView, mNoView;

        public ItemHolder(View ItemView) {
            super(itemView);
            mNoView = itemView.findViewById(R.id.item_no);
            mWhatTextView = itemView.findViewById(R.id.what_text);
            itemView.setOnClickListener(this);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.one_row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
                Item item = itemsDB.getList().get(position);
                holder.bind(item, position);
            }

            @Override
                    public int getItemCount(){
                    return itemsDB.size();
            }
        }
    }
}
