package mf.andorid.com.mfinfo;

import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by 8398 on 02/01/17.
 */
public class PostTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    OkHttpClient client = new OkHttpClient();
    MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    JSONObject obj;

    public void PostTask(JSONObject obj){
        this.obj=obj;
    }




    protected String doInBackground(String... urls) {
        try {
            Request.Builder builder = new Request.Builder();
            builder.url(urls[0]);
            System.out.println(urls[1]);
            String getResponse = post(urls[0],urls[1]);
            return getResponse;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String getResponse) {
        System.out.println(getResponse);
    }
    private String post(String url, String json) throws IOException {

        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()

                .url(url)

                .post(body)

                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();

    }

}







