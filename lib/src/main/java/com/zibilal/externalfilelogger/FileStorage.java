package com.zibilal.externalfilelogger;

import android.content.Context;
import android.os.Environment;
//import android.util.Log;

import com.zibilal.externalfilelogger.Message;
import com.zibilal.externalfilelogger.Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by bilalmuhammad on 9/22/14.
 */
public class FileStorage implements Storage {

    private static final String TAG="FileStorage";

    private Context context;
    private File file;

    public FileStorage(Context context, String dir, String filename) {
        this.context=context;

        String fdir = "/" + dir ;
        if(isExternalStorageWritable()) {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fdir);
            if(!file.exists())
                file.mkdirs();

            String ffile = "/" + filename + ".txt";
            file = new File(file + ffile);
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
            return true;
        else
            return false;
    }

    @Override
    public void save(Message message) {
        BufferedWriter writer = null;
        try {
            if(file != null) {
                writer = new BufferedWriter(new FileWriter(file, true));
                //Log.d(TAG, message.getMessage());
                writer.write(message.getMessage());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
