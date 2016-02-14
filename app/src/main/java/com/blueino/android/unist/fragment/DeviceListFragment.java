package com.blueino.android.unist.fragment;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.blueino.android.unist.MainActivity;
import com.blueino.android.unist.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DeviceListFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private HashMap<String, BluetoothDevice> map = new HashMap<String, BluetoothDevice>();
    private ArrayAdapter adapter;

    private Button scanButton;

    //  =======================================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_list, container, false);
        listView= (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, new ArrayList());
        listView.setAdapter(adapter);
        adapter.clear();

        scanButton = (Button) view.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);

        return view;
    }

    //  =======================================================================================

    public void setUp(BluetoothDevice data) {
        if( data == null )
            return;

        if( !map.containsKey(data.getAddress()) ) {
            String item = data.getName() + "\n" + data.getAddress();
            map.put(data.getAddress(), data);
            adapter.add(item);
        }
    }

    //  =======================================================================================

    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).scan();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*
        BluetoothDevice device = (BluetoothDevice) adapter.getItem(position);
        String message = device.getName() + "에 연결을 시도합니다.";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
*/
        ((MainActivity)getActivity()).connect();

    }

}
