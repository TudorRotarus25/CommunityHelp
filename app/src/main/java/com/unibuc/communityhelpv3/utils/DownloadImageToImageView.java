package com.unibuc.communityhelpv3.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.unibuc.communityhelpv3.managers.LocalImageManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Luci on 07/04/2016.
 */
public class DownloadImageToImageView {
    public void downloadImageToIV(String url, ImageView iv){
        ImageDownloadAsyncTask asyncTask = new ImageDownloadAsyncTask(iv);
        asyncTask.execute(url);
    }

    static class ImageDownloadAsyncTask extends AsyncTask<String, Integer, Bitmap> {
        ImageView imageView;
        Bitmap bmp;

        public ImageDownloadAsyncTask(ImageView iv){
            imageView = iv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            LocalImageManager.storeProfilePicture(bitmap);
            //LocalImageManager.saveImage(bmp);
            imageView.setImageBitmap(bmp);
        }

        @Override
        protected  Bitmap doInBackground(String... params) {
            try {
                bmp = downloadUrl(params[0]);
                return bmp;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        private Bitmap downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            int len = 500;
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                //int response = conn.getResponseCode();
                //Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
    }
}
