package mf.andorid.com.mfinfo;

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

public class PortfolioAdapter extends BaseAdapter{
    String [] result;
    Context context;
    String [] imageId;
    String[] changes;
    String[] date;
    private static LayoutInflater inflater=null;
    public PortfolioAdapter(Activity mfactivity, String[] prgmNameList, String[] prgmImages,String[] mchanges,String[] mdate) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mfactivity.getApplicationContext();
        imageId=prgmImages;
        changes=mchanges;
        date=mdate;
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
        TextView change;
        TextView date;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.portfoliolistview, null);
        holder.tv=(TextView) rowView.findViewById(R.id.submfnamep);
        holder.img=(TextView) rowView.findViewById(R.id.mfNavp);
        holder.change=(TextView)rowView.findViewById(R.id.mfchange);
        holder.tv.setText(result[position]);
        holder.date=(TextView) rowView.findViewById(R.id.mfwdate);
        holder.date.setText("As on "+date[position]);
        float nav =Float.valueOf(imageId[position]);
        String s = String.format("%.2f", nav);

       /* Random ran = new Random();
        int x = ran.nextInt(6) + 5;
        holder.change.setText(Integer.toString(x)+"%");
        if(x>8){
         holder.change.setTextColor(Color.RED);}
        else{
            holder.change.setTextColor(Color.GREEN);
        }*/
        holder.change.setText(changes[position]);

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

    public void refereshData(String[] prgmNameList, String[] prgmImages,String[] mchanges,String[] mdate) {
        result=prgmNameList;
        imageId=prgmImages;
        changes=mchanges;
        date=mdate;
        notifyDataSetChanged();
    }
}
