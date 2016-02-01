package com.blueino.android.unist.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blueino.android.unist.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TerminalFragment extends BaseFragment {

    private TextView top_text;
    private View mFragmentView;

    TextView arg_1;
    TextView ppm;
    TextView conc;
    private TextView terminal;
    private boolean isLogging = false;

    public TerminalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_terminal, container, false);
        terminal = (TextView)mFragmentView.findViewById(R.id.terminalTextView);
        final Button button = (Button)mFragmentView.findViewById(R.id.logButton);
        conc = (TextView)mFragmentView.findViewById(R.id.text_conc);
        ppm = (TextView)mFragmentView.findViewById(R.id.ppm_text);
        arg_1 = (TextView)mFragmentView.findViewById(R.id.arg_1);
        top_text = (TextView)mFragmentView.findViewById(R.id.top_text);

        if(isLogging){
            button.setText("Stop Logging");
        }else{
            button.setText("Start Logging");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isLogging) {

                }else{
                }
            }
        });
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
        int A = Integer.parseInt(text);

        if(A>300){
            top_text.setTextColor(Color.rgb(204, 61, 61));
            top_text.setText("You should not drive");
        }else{
             top_text.setTextColor(Color.rgb(71, 200, 62));
            top_text.setText("You may drive");
        }
        terminal.setText(text);
    }

}
