package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 11/11/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mf.andorid.com.mfinfo.sharedPref.portfolio;
import mf.andorid.com.mfinfo.sharedPref.portfolioPref;


public class TwoFragment extends Fragment implements FragmentLifecycle{
    ListView lv;
    private static final String TAG = "TESTFRAGMENT";

    public portfolioPref sharedPreference;

    public ArrayList<String> name=new ArrayList<>();
    public ArrayList<String> nav=new ArrayList<>();
    public ArrayList<String> code=new ArrayList<>();
    public ArrayList<String> cha=new ArrayList<>();
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

    public TwoFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.portfolio,container,false);
        RecyclerView recList = (RecyclerView)view.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
       // ContactAdapter ca = new ContactAdapter(createList(30));
        //recList.setAdapter(ca);

        sharedPreference = new portfolioPref();
        ArrayList<portfolio> p=sharedPreference.getFavorites(getActivity().getApplicationContext());
        List<PortfolioInfo> resultPortfolio = new ArrayList<PortfolioInfo>();

        if(p!=null) {
            System.out.println(p.size());
            for (int i = 0; i < p.size(); i++) {
                PortfolioInfo pi=new PortfolioInfo();
                pi.mName=p.get(i).getName();
                pi.mNav=p.get(i).getCurrentNav();
                pi.mAmountInvested=p.get(i).getAmoutInv();
                pi.mUnit=p.get(i).getUnits();
                pi.mtotalValue=pi.mUnit*pi.mNav;
                pi.mCode=p.get(i).getCode();
                pi.mdate=p.get(i).getdate();
                resultPortfolio.add(pi);


            }
            ppAdapter padapter=new ppAdapter(resultPortfolio);
            recList.setAdapter(padapter);


            System.out.println(name.size());
            System.out.println(nav.size());
            lv = (ListView) view.findViewById(R.id.listView);
            // lv.setAdapter(new CustomAdapter(getActivity(), name.toArray(new String[0]), name.toArray(new String[0])));
            //adapter = new PortfolioAdapter(getActivity(), name.toArray(new String[0]), nav.toArray(new String[0]),cha.toArray(new String[0]));
            // CustomAdapter adapter = new CustomAdapter(getActivity(), itemname1, itemname2);
            //System.out.println(adapter);

            //lv.setAdapter(adapter);

           /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent newActivity = new Intent(getActivity(), mfdetailActivity1.class);
                    newActivity.putExtra("code", code.get(position));
                    newActivity.putExtra("Name", name.get(position));
                    newActivity.putExtra("Nav", nav.get(position));
                    newActivity.putExtra("Button","false");
                    startActivity(newActivity);
                }
            });*/



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


}
