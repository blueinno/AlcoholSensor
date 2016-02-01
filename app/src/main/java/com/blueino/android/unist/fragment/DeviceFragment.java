package com.blueino.android.unist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blueino.android.unist.MainActivity;
import com.blueino.android.unist.R;

public class DeviceFragment extends Fragment implements View.OnClickListener {

    public DeviceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_devices, container, false);
        Button connectButton = (Button)myFragmentView.findViewById(R.id.connect_button);
        connectButton.setOnClickListener(this);

        return myFragmentView;
    }

    //  =======================================================================================

    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).setCurrentItem(4);
/*        if( "n".equals(item) ) {

            button.setText("Connect");
        } else if( "Connect".equals(item) ) {
            ((MainActivity)getActivity()).connect();
            button.setText("DisConnect");
        } else if( "DisConnect".equals(item) ) {
            ((MainActivity)getActivity()).disConnect();
            button.setText("Scan");
        }*/
    }

}
