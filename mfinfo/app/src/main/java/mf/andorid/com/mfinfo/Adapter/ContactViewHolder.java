package mf.andorid.com.mfinfo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mf.andorid.com.mfinfo.R;

/**
 * Created by 8398 on 25/12/16.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder{
    protected TextView vName;
    protected TextView vSurname;
    protected TextView vEmail;
    protected TextView vTitle;

    public ContactViewHolder(View v) {
        super(v);
        vName =  (TextView) v.findViewById(R.id.txtName);
        vSurname = (TextView)  v.findViewById(R.id.txtSurname);
        vEmail = (TextView)  v.findViewById(R.id.txtEmail);
        vTitle = (TextView) v.findViewById(R.id.title);
        v.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                System.out.println("Onclick");
            }
        });

    }

}
