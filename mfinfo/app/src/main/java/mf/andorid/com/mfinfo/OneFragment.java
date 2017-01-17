package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 11/11/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class OneFragment extends Fragment{
    TextView text,vers;
    String data;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        data=downloadUrl();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        text=(TextView)view.findViewById(R.id.AndroidOs);
        text.setText(" Mutual funds are in the form of Trust \n that manages the  pool of money \n collected from various investors \n for investment in various classes of assets to achieve certain financial goals.  We can say that Mutual Fund is trusts which  pool the savings of large number of investors and then reinvests those funds for earning profits and then distribute the dividend among the investors.    In return for such services,  Asset Management Companies charge small fees.    Every Mutual Fund / launches different schemes, each  with a specific objective.   Investors who share the same objectives invests in that particular Scheme.   Each Mutual Fund Scheme is  managed by a Fund Manager with the help of his team of professionals (One Fund Manage may be managing more than one scheme also).   ");
        return view;
    }
    public String downloadUrl() {
        String url = "http://127.0.0.1:8988/mf/getAllmutualFund";
        OkHttpHandler handler = new OkHttpHandler();
        String result = null;
        try {
            result = handler.execute(url).get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);
       // text.setText(result.toString());
        try {
            JSONArray array=new JSONArray(result.toString());
            System.out.println("Hellos");
            System.out.println(array.length());
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result.toString();
    }

}
