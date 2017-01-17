package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 11/11/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class watchlist extends Fragment implements FragmentLifecycle{
    ListView lv;
    private static final String TAG = "TESTFRAGMENT";

    public SharedPref sharedPreference;

    public ArrayList<String> name=new ArrayList<>();
    public ArrayList<String> nav=new ArrayList<>();
    public ArrayList<String> code=new ArrayList<>();
    public ArrayList<String> cha=new ArrayList<>();
    public ArrayList<String> date=new ArrayList<>();



    public static PortfolioAdapter adapter;


    final String[] itemname1 = {
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser"

    };
    final String[] itemname2 = {
            "1",
            "2",
            "3",
            "4",
            "5"
    };

    public watchlist() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wwatchlist, container, false);


        sharedPreference = new SharedPref();
        ArrayList<Product> p=sharedPreference.getFavorites(getActivity().getApplicationContext());
        name=new ArrayList<>();
        nav=new ArrayList<>();
        code=new ArrayList<>();
        cha=new ArrayList<>();
        date=new ArrayList<>();
        if(p!=null) {
            System.out.println(p.size());
            for (int i = 0; i < p.size(); i++) {
                PortfolioInfo pi=new PortfolioInfo();
                System.out.println(p.get(i).getName());
                System.out.println(p.get(i).getNav());
                System.out.println("Changes="+p.get(i).gethowmuchChange());

                name.add(p.get(i).getName());
                nav.add(p.get(i).getNav());
                code.add(p.get(i).getCode());
                cha.add(p.get(i).gethowmuchChange());
                date.add(p.get(i).getDate());


            }
            //recList.setAdapter(padapter);

            downloadUrl(code);

            System.out.println(name.size());
            System.out.println(nav.size());
            lv = (ListView) view.findViewById(R.id.listView_watchlist);
            // lv.setAdapter(new CustomAdapter(getActivity(), name.toArray(new String[0]), name.toArray(new String[0])));
            adapter = new PortfolioAdapter(getActivity(), name.toArray(new String[0]), nav.toArray(new String[0]),cha.toArray(new String[0]),date.toArray(new String[0]));
            // CustomAdapter adapter = new CustomAdapter(getActivity(), itemname1, itemname2);
            System.out.println(adapter);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent newActivity = new Intent(getActivity(), mfdetailActivity1.class);
                    newActivity.putExtra("code", code.get(position));
                    newActivity.putExtra("Name", name.get(position));
                    newActivity.putExtra("Nav", nav.get(position));
                    newActivity.putExtra("date", date.get(position));
                    newActivity.putExtra("Button", "false");
                    startActivity(newActivity);
                }
            });



            return view;
        }
        else{
            View view1=inflater.inflate(R.layout.fragment_two,container,false);
            return view1;
        }
        //return inflater.inflate(R.layout.fragment_two, container, false);


    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent newActivity = new Intent(getActivity(),mfdetailActivity1.class);
        newActivity.putExtra("code", code.get(position));
        newActivity.putExtra("Name", name.get(position));
        newActivity.putExtra("Nav", nav.get(position));
        startActivity(newActivity);

    }
    @Override
    public void onPauseFragment() {
        Log.i(TAG, "onPauseFragment()");
        Toast.makeText(getActivity(), "onPauseFragment():" + TAG, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResumeFragment() {
        Log.i(TAG, "onResumeFragment()");
        Toast.makeText(getActivity(), "onResumeFragment():" + TAG, Toast.LENGTH_SHORT).show();
    }
    private List<ContactInfo> createList(int size) {

        List<ContactInfo> result = new ArrayList<ContactInfo>();
        for (int i=1; i <= size; i++) {
            ContactInfo ci = new ContactInfo();
            ci.name = ContactInfo.NAME_PREFIX + i;
            ci.surname = ContactInfo.SURNAME_PREFIX + i;
            ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;
    }
    public HashMap<String,String> downloadUrl(ArrayList<String> code)  {
        HashMap<String,String> hm=new HashMap<>();
        JSONObject obj=null;
        if(code.size()!=0){
        System.out.println("INSIDE DOWNLOADURL" + code);
           MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONArray array1=new JSONArray();
            for(int i=0;i<code.size();i++){
                array1.put(code.get(i));}
            obj=new JSONObject();
            obj.put("code",array1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url="http://127.0.0.1:8988/mf/updatewatchlist";

        PostTask p=new PostTask();
        String result = null;
        try {
            result = p.execute(url,obj.toString()).get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(JsonPath.read(result,"$.[0].name"));
        // text.setText(result.toString());

        try {
            JSONObject json = new JSONObject(result);
            Iterator keysToCopyIterator = json.keys();
            List<String> keysList = new ArrayList<String>();
            System.out.println("teamwork");
            while(keysToCopyIterator.hasNext()) {

                String key = (String) keysToCopyIterator.next();
                keysList.add(key);
                System.out.println("TEAMWORK" + json.getString("date"));
                System.out.println("TEAMWORK -1" + date.get(0));

                if(!(json.getString("date").equals(""))){
                    System.out.println("hello date");
                    if(key.equals("date")){
                        continue;
                    }
                    else {
                        sharedPreference.updateshare(this.getActivity().getApplicationContext(), key, json.getString(key));
                    }

                }
                hm.put(key, json.getString(key));

            }
        }
        catch (Exception e){

        }}
        return  hm;




    }
    public void updateSharePref(HashMap<String,String> hm){
        ArrayList<Product> p1=sharedPreference.getFavorites(this.getActivity().getApplicationContext());


    }


}
