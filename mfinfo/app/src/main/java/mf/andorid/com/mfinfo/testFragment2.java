package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 11/11/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class testFragment2 extends Fragment{
    TextView text,vers;
    String data;
    ArrayList<String> mdataName=new ArrayList<String>();
    ArrayList<String> mReturn=new ArrayList<String>();
    private ListView myList;
    DecimalFormat df = new DecimalFormat("0.00");
    public testFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //String code=getArguments().get("key1").toString();
        //System.out.println("Code ="+code);
        data=downloadUrl("139562");
        System.out.println(data);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ArrayList<String> list = new ArrayList();
        ArrayList<String> list1 = new ArrayList();

        //for simplicity we will add the same name for 20 times to populate the list view
        for (int i = 0; i < mdataName.size(); i++){
            String str=mdataName.get(i).replace("Nov","11");
            String st1=str.replace("Oct","10");
            list.add(st1);
            Double d1=Double.parseDouble(mReturn.get(i));
            String returnPer=df.format(d1)+"%";
            list1.add(returnPer);
        }




        View view = inflater.inflate(R.layout.fragment_test, container, false);

        //TableLayout tl = (TableLayout)view.findViewById(R.id.tableLayout1de);
        myList = (ListView)view.findViewById(R.id.list);

        myList.setAdapter(new MyCustomAdapter(this.getActivity(),list,list1));



        return view;
    }
    public String downloadUrl(String code) {
        String url="http://127.0.0.1:8988/mf/navhistory?mcode="+code;
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
            System.out.println("Size="+array.length());
            JSONObject obj=array.getJSONObject(0);
            Iterator it=obj.keys();
            while(it.hasNext()){
                String keyvalue=it.next().toString();
                if(keyvalue.contains("weekly")) {
                    System.out.println("Size=" + keyvalue);

                    mdataName.add(keyvalue.toString());
                    mReturn.add(obj.getString(keyvalue.toString()));
                }

            }


            System.out.println("Hellos");
            System.out.println(obj.toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result.toString();
    }


}
