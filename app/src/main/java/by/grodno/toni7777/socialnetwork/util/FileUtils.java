package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

//    private Context mContext;
//
//    public FileUtils(Context context) {
//        mContext = context;
//    }

    public static String writeFileStorage(Context context, Bitmap image) {
        String fileName = Long.toString(System.currentTimeMillis()) + ".png";
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    public static Bitmap readFileStorage(Context context, String fileName) {
        Bitmap bitmap = null;
        FileInputStream inputStream = null;
        try {
            inputStream = context.openFileInput(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    public static File getAbsolutePathFile(Context context, String fileName) {
        return context.getFileStreamPath(fileName);
    }

//    getFileStreamPath("new.xml");
}
