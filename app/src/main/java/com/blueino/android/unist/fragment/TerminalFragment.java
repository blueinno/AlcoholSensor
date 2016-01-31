package com.blueino.android.unist.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blueino.android.unist.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TerminalFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView top_text;
    private View mFragmentView;
    private LinearLayout top;
    private int ctr = 0;

    TextView arg_1;
    TextView ppm;
    TextView conc;
    private TextView terminal;
    SharedPreferences sharedPref;
    private boolean isLogging = false;

    static TerminalFragment fragment;

    public static TerminalFragment newInstance(String param1, String param2) {
        fragment = new TerminalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TerminalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharedPref =  PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_terminal, container, false);
        terminal = (TextView)mFragmentView.findViewById(R.id.terminalTextView);
        final Button button = (Button)mFragmentView.findViewById(R.id.logButton);
        conc = (TextView)mFragmentView.findViewById(R.id.text_conc);
        ppm = (TextView)mFragmentView.findViewById(R.id.ppm_text);
        arg_1 = (TextView)mFragmentView.findViewById(R.id.arg_1);
        top = (LinearLayout)mFragmentView.findViewById(R.id.terminal_top);
        top_text = (TextView)mFragmentView.findViewById(R.id.top_text);

        if(isLogging){
            button.setText("Stop Logging");
        }else{
            button.setText("Start Logging");
        }
        /*sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);
                builder.setTitle("Enter Message");
                // Set up the input
                final EditText input = new EditText(getActivity());
                final CheckBox checkbox = new CheckBox(getActivity());
                checkbox.setText("Send line endings");
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                layout.addView(input);
                layout.addView(checkbox);
                builder.setView(layout);
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //start file logging
                        String message = input.getText().toString();
                        Boolean lineEndings = checkbox.isChecked();
                        ((MainActivity)getActivity()).sendMessage(message,lineEndings);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isLogging) {
                    //check for timestamp settings
                    final boolean timestampOn = sharedPref.getBoolean("pref_autoNaming", false);
                    final boolean append = sharedPref.getBoolean("pref_timestampAdditionalName", false);
                    if(timestampOn && !append){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                        String currentTimeStamp = dateFormat.format(new Date());
//                        ((MainActivity) getActivity()).initFileWrite(currentTimeStamp+".txt");
                        isLogging = !isLogging;
                        button.setText("Stop Logging");
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Enter Filename");
                        // Set up the input
                        final EditText input = new EditText(getActivity());
                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);
                        // Set up the buttons
                        builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //start file logging
                                String fileName = input.getText().toString();
                                if(append){
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                                    String currentTimeStamp = dateFormat.format(new Date());
                                    fileName += currentTimeStamp;
                                }
//                                ((MainActivity) getActivity()).initFileWrite(fileName + ".txt");
                                isLogging = !isLogging;
                                button.setText("Stop Logging");
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                }else{
//                    ((MainActivity)getActivity()).stopFileWrite();
                    button.setText("Start Logging");
                    isLogging = !isLogging;
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "The logfile is located in your external root folder.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        return mFragmentView;
    }

    public void updateText(String text){
        int tmp = Integer.parseInt(sharedPref.getString("pref_lastX", "400"));

        if(ctr >= tmp){
            String t = terminal.getText().toString();
            if(t.length()>=tmp) {
                terminal.setText(t.substring(t.length() - (tmp/2)));
            }
            ctr = 0;
        }

        int A = Integer.parseInt(text);

        if(A>300){
            top_text.setTextColor(Color.rgb(204, 61, 61));
            top_text.setText("You should not drive");
        }else{
            //terminal.setTextColor(Color.rgb(71, 200, 62));
            top_text.setTextColor(Color.rgb(71, 200, 62));
            top_text.setText("You may drive");
        }
        terminal.setText(text);
        ctr++;
    }

}
