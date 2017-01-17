package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 11/11/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import mf.andorid.com.mfinfo.Activity.mfactivity;

public class TwoFragment1 extends ListFragment {
    ArrayList<String> al=new ArrayList<String>();
    String[] AndroidOS = new String[] { "Cupcake","Donut","Eclair","Froyo","Gingerbread","Honeycomb","Ice Cream SandWich","Jelly Bean","KitKat" };
    String[] Version = new String[]{"1.5","1.6","2.0-2.1","2.2","2.3","3.0-3.2","4.0","4.1-4.3","4.4"};
    HashMap<String,Integer> hm=new HashMap<>();
    TreeMap<String,Integer> tm=new TreeMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            downloadUrl();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.mfactivity, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, al.toArray(new String[0]));
        setListAdapter(adapter);

        return view;

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), al.get(position), Toast.LENGTH_LONG).show();
        Intent newActivity = new Intent(getActivity(), mfactivity.class);
        newActivity.putExtra("pId", hm.get(al.get(position)));
        startActivity(newActivity);

    }


    public String downloadUrl() throws JSONException {
        String url = "http://127.0.0.1:8988/mf/getAllmutualFund";

        OkHttpHandler handler = new OkHttpHandler();
        String result = null;
        try {
            result = handler.execute(url).get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(JsonPath.read(result,"$.[0].name"));
        // text.setText(result.toString());


        try {
            JSONArray array=new JSONArray(result.toString());
            System.out.println("Hellos");
            for(int i=0;i<array.length();i++){
                JSONObject c=array.getJSONObject(i);
                al.add(c.getString("name"));
                hm.put(c.getString("name"),c.getInt("id"));
            }
            Collections.sort(al);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result.toString();
    }

}
