package mf.andorid.com.mfinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by 8398 on 11/11/16.
 */
public class mfactivity extends AppCompatActivity{
    ListView lv;
    Context context;
    ArrayList prgmName;
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> nav=new ArrayList<>();
    ArrayList<String> code=new ArrayList<>();
    ArrayList<String> date=new ArrayList<>();

    public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};
    public static String [] prgmImages={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        int pId = (Integer) intent.getSerializableExtra("pId");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mfactivity);
        Toolbar t=(Toolbar) findViewById(R.id.toolbar1);
        t.setTitle("Scheme Name");
        downloadUrl(pId);
        context=this;
        lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, name.toArray(new String[0]), nav.toArray(new String[0])));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newActivity = new Intent(mfactivity.this,mfdetailActivity1.class);
                newActivity.putExtra("code",code.get(position));
                newActivity.putExtra("Name",name.get(position));
                newActivity.putExtra("Nav",nav.get(position));
                newActivity.putExtra("date",date.get(position));
                newActivity.putExtra("Button","true");
                startActivity(newActivity);
            }
        });

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
                date.add(c.getString("navdate"));
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

}
