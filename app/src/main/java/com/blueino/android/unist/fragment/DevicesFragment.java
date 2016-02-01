package com.blueino.android.unist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blueino.android.unist.MainActivity;
import com.blueino.android.unist.R;

public class DevicesFragment extends Fragment implements View.OnClickListener {

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
        Button connectButton = (Button)myFragmentView.findViewById(R.id.connect_button);
        connectButton.setOnClickListener(this);
        return myFragmentView;
    }

    //  =======================================================================================

    @Override
    public void onClick(View v) {
        Button button = ((Button) v);
        String item = button.getText().toString();
        if( "Scan".equals(item) ) {
            ((MainActivity)getActivity()).scan();
            button.setText("Connect");
        } else if( "Connect".equals(item) ) {
            ((MainActivity)getActivity()).connect();
            button.setText("DisConnect");
        } else if( "DisConnect".equals(item) ) {
            ((MainActivity)getActivity()).disConnect();
            button.setText("Scan");
        }
    }

}
