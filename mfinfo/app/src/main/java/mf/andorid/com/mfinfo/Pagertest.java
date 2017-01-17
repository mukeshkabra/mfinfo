package mf.andorid.com.mfinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8398 on 19/12/16.
 */
public class Pagertest extends Fragment {
        ListView lv;
        Context context;
        ArrayList prgmName;
        ArrayList<String> name=new ArrayList<>();
        ArrayList<String> nav=new ArrayList<>();
        ArrayList<String> code=new ArrayList<>();


        @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View vu = inflater.inflate(R.layout.test, container, false);
           /* ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
            ViewPager viewPager = (ViewPager) vu.findViewById(R.id.viewpager_fr);
            testFragment t1=new testFragment();
            testFragment2 t2=new testFragment2();
            adapter.addFragment(t1, "History");
            adapter.addFragment(t2, "Returns");
            viewPager.setAdapter(adapter);
            System.out.println("HelloMukesh");*/
            return vu;
        }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewpager_fr);
        ViewPagerAdapter adapter =new ViewPagerAdapter(getChildFragmentManager());
        testFragment t1=new testFragment();
        testFragment2 t2=new testFragment2();
        adapter.addFragment(t1, "History");
        adapter.addFragment(t2, "Returns");
        mViewPager.setAdapter(adapter);
    }



    public String downloadUrl(int pid)  {
        System.out.println("PID" + pid);
        String url = "http://127.0.0.1:8988/mf/getsubMutualFund?pId="+Integer.toString(pid);

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
            for(int i=0;i<array.length();i++){
                JSONObject c=array.getJSONObject(i);
                code.add(c.getString("code"));
                JSONObject info=c.getJSONObject("info");
                String temp=info.get("name")+"-"+info.getString("typemf");
                name.add(temp);
                nav.add(info.getString("nav"));

            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result.toString();
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

