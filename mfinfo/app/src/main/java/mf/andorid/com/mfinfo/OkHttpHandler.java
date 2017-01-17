package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 11/11/16.
 */

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpHandler extends AsyncTask<String, Void, String> {

    OkHttpClient client = new OkHttpClient();



    @Override
    protected String doInBackground(String... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Request request = builder.build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
        }

        return null;
    }

}