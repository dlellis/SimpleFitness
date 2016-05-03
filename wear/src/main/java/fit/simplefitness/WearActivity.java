package fit.simplefitness;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WearActivity extends Activity implements WearableListView.ClickListener {

    // Sample dataset for the list
    String[] elements = { "List Item 1", "List Item 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_item);

            // Get the list component from the layout of the activity
            WearableListView listView =
            (WearableListView) findViewById(R.id.wearable_list);

            // Assign an adapter to the list
            Log.d("newcharacter", elements[1]);
            listView.setAdapter(new WearAdapter(this, elements));
            Log.d("adapt", "after");
            // Set a click listener
            listView.setClickListener(this);


            }



    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {
            Integer tag = (Integer) v.itemView.getTag();
            // use this data to complete some action ...
            }

    @Override
    public void onTopEmptyRegionClick() {
            }







    private static final class WearAdapter extends WearableListView.Adapter {
        public String[] mDataset;
        public final Context mContext;
        public final LayoutInflater mInflater;

        // Provide a suitable constructor (depends on the kind of dataset)
        public WearAdapter(Context context, String[] dataset) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataset = dataset;
        }

        // Provide a reference to the type of views you're using
        public static class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textView;
            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textView = (TextView) itemView.findViewById(R.id.name);
            }
        }

        // Create new views for list items
        // (invoked by the WearableListView's layout manager)
        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
            // Inflate our custom layout for list items
            return new ItemViewHolder(mInflater.inflate(R.layout.list_item, null));
        }

        // Replace the contents of a list item
        // Instead of creating new views, the list tries to recycle existing ones
        // (invoked by the WearableListView's layout manager)
        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder,
                                     int position) {
            // retrieve the text view
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            TextView view = itemHolder.textView;
            // replace text contents
            view.setText(mDataset[position]);
            // replace list item's metadata
            holder.itemView.setTag(position);
        }

        // Return the size of your dataset
        // (invoked by the WearableListView's layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }


}

