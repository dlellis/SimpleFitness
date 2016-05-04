package fit.simplefitness;

/**
 * Created by daltonellis on 5/3/16.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fit.simplefitness.listview.ListViewAdapter;
import fit.simplefitness.listview.ListViewItem;


public class MainActivity extends Activity implements WearableListView.ClickListener{

    private List<ListViewItem> viewItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WearableListView wearableListView = (WearableListView) findViewById(R.id.wearable_list_view);

        viewItemList.add(new ListViewItem(R.drawable.ic_basketball, "Basketball"));
        viewItemList.add(new ListViewItem(R.drawable.ic_baseball, "Baseball"));
        viewItemList.add(new ListViewItem(R.drawable.ic_running, "Running"));
        viewItemList.add(new ListViewItem(R.drawable.ic_skateboard, "Skateboard"));

        wearableListView.setAdapter(new ListViewAdapter(this, viewItemList));
        wearableListView.setClickListener(this);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Toast.makeText(this, "Click on " + viewItemList.get(viewHolder.getPosition()).text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTopEmptyRegionClick() {

    }
}