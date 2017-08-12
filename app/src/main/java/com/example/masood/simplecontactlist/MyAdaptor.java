package com.example.masood.simplecontactlist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masood.simplecontactlist.database.MyContact;
import com.example.masood.simplecontactlist.database.contactModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masood on 8/7/17.
 */

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {
    private List<MyContact> values;

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

    public MyAdaptor(List<MyContact> values) {
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
    public void onBindViewHolder(final MyAdaptor.ViewHolder holder, int position) {
        final MyContact m = values.get(position);
        holder.contactName.setText(m.getContactName());
        holder.details.setText(m.getContactDetails());
        holder.userNumber.setText(m.getContactNumber());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
