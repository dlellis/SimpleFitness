package fit.simplefitness;

/**
 * Created by daltonellis on 5/3/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;

import java.util.ArrayList;
import java.util.List;

import fit.simplefitness.listview.ListViewAdapter;
import fit.simplefitness.listview.ListViewItem;
import jp.android.a.akira.library.okwear.OkWear;
import jp.android.a.akira.library.okwear.listener.SendResultListener;
import jp.android.a.akira.library.okwear.listener.WearReceiveListener;


public class MainActivity extends Activity implements WearReceiveListener, WearableListView.ClickListener {

    private List<ListViewItem> viewItemList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();


    private OkWear mOkWear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        WearableListView wearableListView = (WearableListView) findViewById(R.id.wearable_list_view);

        viewItemList.add(new ListViewItem(R.drawable.ic_running, "Friday Workout Group"));
        viewItemList.add(new ListViewItem(R.drawable.ic_running, "P90x"));
        viewItemList.add(new ListViewItem(R.drawable.ic_running, "Cross-fit"));
        viewItemList.add(new ListViewItem(R.drawable.ic_running, "Leg Day"));
        wearableListView.setAdapter(new ListViewAdapter(this, viewItemList));
        wearableListView.setClickListener(this);

        mOkWear = new OkWear(this);

    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        // Toast.makeText(this, "Click on " + viewItemList.get(viewHolder.getPosition()).text, Toast.LENGTH_SHORT).show();
        sampleSendMessageAllAsync(viewItemList.get(viewHolder.getPosition()).text.getBytes());
    }

    @Override
    public void onTopEmptyRegionClick() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mOkWear.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mOkWear.disconnect();
    }



    private void sampleSendMessageAllAsync(byte[] bs) {

        mOkWear.sendMessageAllAsync(bs, null, new SendResultListener<MessageApi.SendMessageResult>() {
            @Override
            public void onResult(MessageApi.SendMessageResult result) {
                Log.v(TAG, "Status: " + result.getStatus());
            }
        });
    }


    @Override
    public void onReceiveMessage(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals(OkWear.DEFAULT_MESSAGE_API_PATH)) {
            final String messagePayload = new String(messageEvent.getData());
            Log.v(TAG, messagePayload);
        }
    }
    @Override
    public void onReceiveDataApi(DataEventBuffer dataEventBuffer) {
        for (DataEvent event : dataEventBuffer) {
            final DataMap dataMap = DataMap.fromByteArray(event.getDataItem().getData());
            //final int data1 = dataMap.getInt("key1");
            final ArrayList<String> data2 = dataMap.getStringArrayList("key2");
            //Log.v(TAG, "data(int): " + data1);
            Log.v(TAG, "data(string): " + data2.get(0));
        }
    }

}