package com.blueino.android.unist.manager;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TextFileManager {

    private static final String FILE_NAME = "unist_log";
    private Context mContext;

    public TextFileManager(Context context) {
        mContext = context;
    }

    public void save(String data) {
        if( data == null || "".equals(data) ) {
            return;
        }

        FileOutputStream stream = null;

        try {
            stream = mContext.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            stream.write( data.getBytes() );
            stream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String load() {
        try {
            FileInputStream stream = mContext.openFileInput(FILE_NAME);
            byte[] data = new byte[stream.available()];
            while (stream.read(data) != -1) {
                return new String(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void delete() {
        mContext.deleteFile(FILE_NAME);
    }

}
