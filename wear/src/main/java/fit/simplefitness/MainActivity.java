package fit.simplefitness;

/**
 * Created by daltonellis on 5/3/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;

import java.util.ArrayList;
import java.util.List;

import fit.simplefitness.listview.ListViewAdapter;
import fit.simplefitness.listview.ListViewItem;
import jp.android.a.akira.library.okwear.OkWear;
import jp.android.a.akira.library.okwear.listener.NodeChangeListener;
import jp.android.a.akira.library.okwear.listener.SendResultListener;
import jp.android.a.akira.library.okwear.util.ParseByteArray;
import jp.android.a.akira.library.okwear.util.Payload;


public class MainActivity extends Activity implements WearableListView.ClickListener, View.OnClickListener{

    private List<ListViewItem> viewItemList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mSendButton;
    private OkWear mOkWear;
    private int mPayload = 0;

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

        mOkWear = new OkWear(this);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        //Log.d("watchstube", "first");
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                //Log.d("watchstube","second");
                mSendButton = (Button) stub.findViewById(R.id.activity_main_send_button);
                mSendButton.setOnClickListener(MainActivity.this);
            }
        });

    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
       // Toast.makeText(this, "Click on " + viewItemList.get(viewHolder.getPosition()).text, Toast.LENGTH_SHORT).show();
        sampleSendMessageAllAsync();
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

    @Override
   public void onClick(View v) {
       //sampleSendData();
        //sampleSendMessageAll();
        //sampleSendMessageAllAsync();
    }

    private void sampleSendMessage() {
        final byte[] bs = ParseByteArray.fromInt(100);

        mOkWear.getNodeList(new NodeChangeListener() {
            @Override
            public void onReceiveNodes(List<Node> nodes) {
                mOkWear.sendMessage(nodes.get(0), bs, null, null);
            }
        });
    }

    private void sampleSendMessageAll() {
        mOkWear.sendMessageAll("hello message", null);
    }

    private void sampleSendMessageAllAsync() {
        final byte[] bs = ("hello message").getBytes();
        mOkWear.sendMessageAllAsync(bs, null, new SendResultListener<MessageApi.SendMessageResult>() {
            @Override
            public void onResult(MessageApi.SendMessageResult result) {
                Log.v(TAG, "Status: " + result.getStatus());
            }
        });
    }

    private void sampleSyncData() {
        final Payload payload =
                new Payload.Builder(OkWear.DEFAULT_DATA_API_PATH)
                        .addPayload(OkWear.DEFAULT_DATA_API_KEY, mPayload++)
                        .addPayload("my key", "hello")
                        .build();

        mOkWear.syncData(payload, new SendResultListener<DataApi.DataItemResult>() {
            @Override
            public void onResult(DataApi.DataItemResult result) {
                Log.v(TAG, "Status: " + result.getStatus());
            }
        });
    }

    private void sampleSyncData2() {

        mOkWear.syncData(OkWear.DEFAULT_DATA_API_PATH,
                OkWear.DEFAULT_DATA_API_KEY,
                "hello");
    }


}