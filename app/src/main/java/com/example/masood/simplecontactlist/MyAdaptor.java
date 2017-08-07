package com.example.masood.simplecontactlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masood.simplecontactlist.database.contactModel;

import java.util.ArrayList;

/**
 * Created by masood on 8/7/17.
 */

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {
    private ArrayList<contactModel> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName;
        public TextView details;
        public ImageView userImage;
        public TextView userNumber;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            contactName = (TextView) v.findViewById(R.id.contact_name_place);
            details = (TextView) v.findViewById(R.id.contact_details_place);
            userImage = (ImageView) v.findViewById(R.id.place_holder_image_view);
            userNumber = (TextView) v.findViewById(R.id.user_number_tv);
        }

    }

    public MyAdaptor(ArrayList<contactModel> values) {
        this.values = values;
    }

    @Override
    public MyAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdaptor.ViewHolder holder, int position) {
        final contactModel m = values.get(position);
        holder.contactName.setText(m.getContactName());
        holder.details.setText(m.getContactDetails());
        holder.userNumber.setText(m.getContactNumber());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
