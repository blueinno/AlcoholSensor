package com.blueino.android.unist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blueino.android.unist.R;

public class DevicesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    static DevicesFragment fragment;
    private View myFragmentView;

    public static DevicesFragment newInstance(String param1, String param2) {
        fragment = new DevicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DevicesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public boolean isOpen(){
        return fragment.isHidden();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.fragment_devices, container, false);
        final Button connectButton = (Button)myFragmentView.findViewById(R.id.conn_button);
        if(connectButton != null) {
            connectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    ((MainActivity) getActivity()).disconnect();
                    Intent intent = new Intent(getActivity().getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);*/
                }
            });
        }
/*        final Button disconnectButton = (Button) myFragmentView.findViewById(R.id.disconnectButton);
*//*

        if(!((MainActivity)getActivity()).isConnected()) {
            disconnectButton.setVisibility(View.INVISIBLE);
        }
*//*

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)getActivity()).disconnect();
                disconnectButton.setVisibility(View.INVISIBLE);
            }
        });*/
        return myFragmentView;
    }

}
