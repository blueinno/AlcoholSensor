package com.blueino.android.unist.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blueino.android.unist.R;
import com.blueino.android.unist.manager.TextFileManager;
import com.blueino.android.unist.util.PreferenceUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TerminalFragment extends BaseFragment implements View.OnClickListener {

    private TextView top_text;
    private View mFragmentView;
    private TextView terminal;
    private Button logButton;

    private StringBuilder logBuilder;
    private boolean isLoging = false;

    public TerminalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logBuilder = new StringBuilder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_terminal, container, false);
        terminal = (TextView)mFragmentView.findViewById(R.id.terminalTextView);
        logButton = (Button) mFragmentView.findViewById(R.id.logButton);
        logButton.setOnClickListener(this);
        top_text = (TextView)mFragmentView.findViewById(R.id.top_text);
        return mFragmentView;
    }

    @Override
    public void update(byte[] data) {
        super.update(data);
        Float f = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        int value = f.intValue();
        updateText( String.valueOf(value) );
    }

    //  ========================================================================================

    public void updateText(String text){
        int max = Integer.parseInt(text);

        String limit = PreferenceUtil.get(getActivity(), PreferenceUtil.PREFERENCE_MAX_Y_SCALE);

        if( limit == null )
            limit = "300";

        if (max > Integer.valueOf(limit) ) {
            top_text.setTextColor(Color.rgb(204, 61, 61));
            top_text.setText("You should not drive");
        }else{
            top_text.setTextColor(Color.rgb(71, 200, 62));
            top_text.setText("You may drive");
        }
        terminal.setText(text);

        if( isLoging ) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateFormat.format(new Date());
            logBuilder.append( text + " , " + time );
        }
    }

    //  =======================================================================================

    @Override
    public void onClick(View v) {
        if (isLoging) {
            logButton.setText("Stop Logging");
            isLoging = true;
        } else {
            logButton.setText("Start Logging");
            isLoging = false;
            TextFileManager manager = new TextFileManager(getActivity());
            manager.save(logBuilder.toString());
        }
    }



}
