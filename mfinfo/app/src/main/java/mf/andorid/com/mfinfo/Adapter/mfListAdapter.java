package mf.andorid.com.mfinfo.Adapter;

/**
 * Created by 8398 on 11/11/16.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mf.andorid.com.mfinfo.R;

public class mfListAdapter extends BaseAdapter{
    String [] result;
    Context context;
    String [] imageId;
    private static LayoutInflater inflater=null;
    public mfListAdapter(Activity mfactivity, String[] prgmNameList, String[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mfactivity.getApplicationContext();
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.customlistview, null);
        holder.tv=(TextView) rowView.findViewById(R.id.submfname);
        holder.img=(TextView) rowView.findViewById(R.id.mfNav);
        holder.tv.setText(result[position]);
        float nav =Float.valueOf(imageId[position]);
        String s = String.format("%.2f", nav);

        holder.img.setText(s);
        /*rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                Intent newActivity = new Intent(context,mfdetailActivity.class);
                newActivity.putExtra("code","116873");
                context.getApplicationContext().startActivity(newActivity);
            }
        });*/
        return rowView;
    }

    public void refereshData(String[] prgmNameList, String[] prgmImages) {
        result=prgmNameList;
        imageId=prgmImages;
        notifyDataSetChanged();
    }
}
