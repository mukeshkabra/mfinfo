package mf.andorid.com.mfinfo.network;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;

/**
 * Created by 8398 on 30/12/16.
 */
public class RequestBuilder {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /*//Login request body
    public static RequestBody LoginBody(String username, String password, String token) {
        return RequestBody.create(JSON,
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }*/

    public static HttpUrl buildURL() {
        return new HttpUrl.Builder()
                .scheme("https") //http
                .host("www.somehostname.com")
                .addPathSegment("pathSegment")//adds "/pathSegment" at the end of hostname
                .addQueryParameter("param1", "value1") //add query parameters to the URL
                .addEncodedQueryParameter("encodedName", "encodedValue")//add encoded query parameters to the URL
                .build();
        /**
         * The return URL:
         *  https://www.somehostname.com/pathSegment?param1=value1&encodedName=encodedValue
         */
    }

}
