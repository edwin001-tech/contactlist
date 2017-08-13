package com.example.masood.simplecontactlist;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        public ImageView deleteContact;
        public ImageView editContact;
        public ImageView sendMessage;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            contactName = (TextView) v.findViewById(R.id.contact_name_place);
            details = (TextView) v.findViewById(R.id.contact_details_place);
            userImage = (ImageView) v.findViewById(R.id.place_holder_image_view);
            userNumber = (TextView) v.findViewById(R.id.user_number_tv);
            deleteContact = (ImageView) v.findViewById(R.id.delete_contact_iv);
            editContact = (ImageView) v.findViewById(R.id.edit_contact_iv);
            sendMessage = (ImageView) v.findViewById(R.id.delete_contact_iv);
        }

    }

    public MyAdaptor(List<MyContact> values, Context context) {
        this.values = MyContact.listAll(MyContact.class);
        this.context = context;
    }

    private long id;
    private Context context;

    @Override
    public MyAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyAdaptor.ViewHolder holder, final int position) {
        final MyContact m = values.get(position);
        holder.contactName.setText(m.getContactName());
        holder.details.setText(m.getContactDetails());
        holder.userNumber.setText(m.getContactNumber());
        holder.editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = AddContactActivity.getActivityIntent(context, m.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Toast.makeText(context, "id:"+ m.getId(), Toast.LENGTH_SHORT).show();
                context.startActivity(i);
            }
        });
        holder.deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(position, m.getId());
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            //starting a phone call
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:"+m.getContactNumber()));
                context.startActivity(callIntent);
            }
        });
        holder.sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a message editor
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.setData(Uri.parse("sms:" + m.getContactNumber()));
                smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public void removeAt(int position, long id) {
        MyContact.findById(MyContact.class,id).delete();
        values = MyContact.listAll(MyContact.class);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, values.size());
    }
}
