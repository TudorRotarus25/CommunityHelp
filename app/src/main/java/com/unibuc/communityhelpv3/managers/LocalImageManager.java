package com.unibuc.communityhelpv3.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Luci on 07/04/2016.
 */
public class LocalImageManager {
    private Context context;
    private static final String PREF_NAME = "SHARED_PREFS";

    public LocalImageManager(Context context){
        this.context = context;
    }

    public static void storeProfilePicture(Bitmap bmp){
        File sdCardDirectory = Environment.getExternalStorageDirectory();

        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ComunityHelp/");
        if (!f.exists()){
            Boolean b = f.mkdirs();
            Log.e("!!!!", b.toString());
        }
        File f2 = new File(sdCardDirectory + "/ComunityHelp/", "profilepictures");
        if (!f2.exists()){
            Boolean b = f2.mkdir();
            Log.e("!!!!",b.toString());
        }

        File image = new File(sdCardDirectory + "/ComunityHelp/profilepictures/", "profile_picture.png");
        try {
            if (image.exists())
                image.delete();
            image.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean success = false;

        FileOutputStream outStream;
        try {
            outStream = new FileOutputStream(image);
            Log.e("!!!!", bmp.toString());
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);

            outStream.flush();
            outStream.close();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (success) {
            Log.e("LocalImageManager", "Image saved with success");
        } else {
            Log.e("LocalImageManager", "Error during image saving");
        }
    }

    public static Bitmap retrieveProfilePicture(){
        File pictureFile = new File(Environment.getExternalStorageDirectory() + "/ComunityHelp/profilepictures/profile_picture.png");
        Bitmap bitmap = null;
//        try {
//            InputStream is = new URL(Environment.getExternalStorageState() +  "/Licenta/profilepictures/profile_picture.png").openStream();
//            bitmap = BitmapFactory.decodeStream(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/ComunityHelp/profilepictures/profile_picture.png");

        Log.e("LocalImageManager", "retried local profile picture");
        return bitmap;
    }

    public void storeProfilePictureUrl(String url){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE).edit();
        editor.putString("profilePictureUrl", url);
        editor.commit();
    }

    public String getProfilePictureUrl(){
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        String url = prefs.getString("profilePictureUrl", "");
        return url;
    }
}
