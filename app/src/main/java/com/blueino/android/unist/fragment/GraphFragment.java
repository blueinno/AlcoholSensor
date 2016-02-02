package com.blueino.android.unist.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueino.android.unist.R;
import com.blueino.android.unist.util.PreferenceUtil;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

public class GraphFragment extends BaseFragment {

    private View myFragmentView;
    private GraphView graph;

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private Viewport viewport;
    private int lastX = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.fragment_graph, container, false);
        initialize();
        return myFragmentView;
    }

    @Override
    public void update(final byte[] data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if( getActivity() != null ) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Float f = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
//                            String temp = String.format("%", d);
                            addEntry( f );
                        }
                    });
                }
            }
        }).start();
    }

    public void setMinMax(int min, int max) {
        graph.removeAllSeries();
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setScrollable(true);
        viewport.setMaxY(max);
        viewport.setMinY(min);
    }

    //  ===========================================================================================

    private void initialize() {
        createChildren();
    }

    private void createChildren() {
        graph = (GraphView) myFragmentView.findViewById(R.id.graphView);
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setScrollable(true);

        String max = PreferenceUtil.get(getActivity(), PreferenceUtil.PREFERENCE_MAX_Y_SCALE);
        String min = PreferenceUtil.get(getActivity(), PreferenceUtil.PREFERENCE_MIN_Y_SCALE);

        if( max == null )
            max = "300";

        if( min == null )
            min = "0";

        viewport.setMaxY(Integer.valueOf(max));
        viewport.setMinY(Integer.valueOf(min));

//        draw();
    }

    private void draw() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if( getActivity() != null ) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                addEntry();
                            }
                        });

                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private void addEntry(double value) {
        series.appendData(new DataPoint(lastX++, value), true, 10);
    }

    private int getColor(int ctr){
        switch(ctr){
            case 0:
                return Color.BLUE;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.CYAN;
            case 5:
                return 0xffbb33;
            default:
                return Color.MAGENTA;
        }
    }


}
