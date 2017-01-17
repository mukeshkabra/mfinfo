package mf.andorid.com.mfinfo.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mf.andorid.com.mfinfo.R;

/**
 * Created by 8398 on 14/12/16.
 */
public class mfHistorylistFragment extends BaseAdapter {
    private ArrayList<String> mListItems;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mValue;

    public mfHistorylistFragment(Context context, ArrayList<String> arrayList, ArrayList<String> value){

        mListItems = arrayList;
        mValue=value;
        //get the layout inflater
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //getCount() represents how many items are in the list
        return mListItems.size();
    }

    @Override
    //get the data of an item from a specific position
    //i represents the position of the item in the list
    public Object getItem(int i) {
        return null;
    }

    @Override
    //get the position id of the item from the list
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int position, View view, ViewGroup viewGroup) {

        // create a ViewHolder reference
        ViewHolder holder;

        //check to see if the reused view is null or not, if is not null then reuse it
        if (view == null) {
            holder = new ViewHolder();

            view = mLayoutInflater.inflate(R.layout.attrix_row, viewGroup, false);

            // get all views you need to handle from the cell and save them in the view holder
            holder.itemName = (TextView) view.findViewById(R.id.TextView1row1a);
            holder.itemPrice=   (TextView) view.findViewById(R.id.TextView2row1a);

            // save the view holder on the cell view to get it back latter
            view.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }

        //get the string item from the position "position" from array list to put it on the TextView
        String stringItem = mListItems.get(position);
        String itemprice=mValue.get(position);
        if (stringItem != null) {
            //set the item name on the TextView
            holder.itemName.setText(stringItem);
            holder.itemPrice.setText(itemprice);

        } else {
            // make sure that when you have an if statement that alters the UI, you always have an else that sets a default value back, otherwise you will find that the recycled items will have the same UI changes
            holder.itemName.setText("Unknown");
        }

        //this method must return the view corresponding to the data at the specified position.
        return view;

    }

    /**
     * Used to avoid calling "findViewById" every time the getView() method is called,
     * because this can impact to your application performance when your list is large
     */
    private class ViewHolder {

        protected TextView itemName;
        protected TextView itemPrice;

    }
}
