package com.example.eduin.simplecontactlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduin.simplecontactlist.database.MyContact;

import java.util.ArrayList;
import java.util.List;


public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> implements Filterable {
    private List<MyContact> values;
    CustomFilter filter;
    private List<MyContact> filterValues;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName;
        public TextView details;
        public ImageView userImage;
        public TextView userNumber;
        public View layout;
        public ImageView deleteContact;
        public ImageView editContact;
//        public ImageView sendMessage;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            contactName = (TextView) v.findViewById(R.id.contact_name_place);
            details = (TextView) v.findViewById(R.id.contact_details_place);
            userImage = (ImageView) v.findViewById(R.id.place_holder_image_view);
            userNumber = (TextView) v.findViewById(R.id.user_number_tv);
            deleteContact = (ImageView) v.findViewById(R.id.delete_contact_iv);
            editContact = (ImageView) v.findViewById(R.id.edit_contact_iv);
        }

    }

    public MyAdaptor(List<MyContact> values, Context context) {
        this.values = MyContact.listAll(MyContact.class);
        this.context = context;
        this.filterValues = (ArrayList<MyContact>) values;
    }

    private long id;
    private Context context;


    @NonNull
    @Override
    public MyAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
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
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {

            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence != null && charSequence.length() > 0) {
                charSequence = charSequence.toString().toUpperCase();
                ArrayList<MyContact> filters = new ArrayList<MyContact>();
                for (int i = 0; i < filterValues.size(); i++) {
                    if (filterValues.get(i).getContactName().toUpperCase().contains(charSequence)) {
                        filters.add(filterValues.get(i));
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterValues.size();
                results.values = filterValues;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            values = (List<MyContact>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    public void removeAt(int position, long id) {
        MyContact.findById(MyContact.class, id).delete();
        values = MyContact.listAll(MyContact.class);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, values.size());
    }

}
