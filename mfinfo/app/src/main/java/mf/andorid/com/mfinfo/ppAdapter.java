package mf.andorid.com.mfinfo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 8398 on 25/12/16.
 */
public class ppAdapter extends RecyclerView.Adapter<ppAdapter.ContactViewHolder> {
    private List<PortfolioInfo> contactList;

    public ppAdapter(List<PortfolioInfo> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        PortfolioInfo ci = contactList.get(i);
        contactViewHolder.vName.setText("Nav : "+ci.mNav);
        String mUnits = String.format("%.2f", ci.mUnit);
        contactViewHolder.vEmail.setText("Units : "+mUnits);
        //contactViewHolder.vEmail.setText("Units : "+ci.mUnit);
        contactViewHolder.vSurname.setText(Html.fromHtml("<b>Amount Invested</b>")+" : " + ci.mAmountInvested);
        contactViewHolder.vEmail.setTypeface(null, Typeface.BOLD);
        contactViewHolder.vTitle.setText(ci.mName);
        String s = String.format("%.2f", ci.mtotalValue);
        contactViewHolder.vtotalamount.setText("Total amount : " + s);
        if(ci.mAmountInvested>ci.mtotalValue){
            contactViewHolder.upIcon.setVisibility(View.GONE);
        }
        else{
            //contactViewHolder.vtotalamount.setTextColor(Color.GREEN);
            contactViewHolder.downIcon.setVisibility(View.GONE);
        }


        contactViewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
                System.out.print("finally");
            }
        });
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview, viewGroup, false);

        return new ContactViewHolder(itemView);
    }



    public  class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        protected TextView vtotalamount;
        protected ImageView upIcon;
        protected ImageView downIcon;
        private ItemClickListener mListener;


        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
            vtotalamount=(TextView) v.findViewById(R.id.txttotalAmount);
            upIcon=(ImageView)v.findViewById(R.id.upIcon);
            downIcon=(ImageView)v.findViewById(R.id.downIcon);
            //itemView.setOnClickListener(this);
           v.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View v) {


                   PortfolioInfo ci = contactList.get(getAdapterPosition());
                   Intent newActivity = new Intent(v.getContext().getApplicationContext(), mfdetailActivity1.class);
                   newActivity.putExtra("code", ci.mCode);
                   newActivity.putExtra("Name", ci.mName);
                   newActivity.putExtra("Nav", Double.toString(ci.mNav));
                   newActivity.putExtra("date",ci.mdate);
                   newActivity.putExtra("Button", "false");
                   v.getContext().startActivity(newActivity);

               }
           });

        }

        public void setClickListener(ItemClickListener listener) {
            this.mListener = listener;
        }

        @Override
        public void onClick(View view) {
            mListener.onClickItem(getLayoutPosition());
        }


    }
    public interface ItemClickListener {
        void onClickItem(int pos);

    }

}
