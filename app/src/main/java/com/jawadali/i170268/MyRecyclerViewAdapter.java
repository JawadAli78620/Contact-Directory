package com.jawadali.i170268;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private ArrayList<ContactDetailsModel> contactDataList;

    private OnContactItemListener mOnContactItemListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder newViewHolder = new MyViewHolder(view, mOnContactItemListener);
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Set Datasets in view holder
        TextView name = holder.name;
        TextView phone = holder.phoneNumber;
        CircularImageView image = holder.image;

        Uri imgUri = Uri.parse(contactDataList.get(position).getImgUri());
        name.setText(contactDataList.get(position).getName());
        phone.setText(contactDataList.get(position).getPhone());
        image.setImageURI(imgUri);

        //Use Piccaso library to set image
    }

    @Override
    public int getItemCount() {
        return contactDataList.size();
    }

    //Constructor
    public MyRecyclerViewAdapter(ArrayList<ContactDetailsModel> contactDataList, OnContactItemListener onContactItemListener){
        this.contactDataList = new ArrayList<>();
        this.contactDataList = contactDataList;
        this.mOnContactItemListener = onContactItemListener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, phoneNumber;
        CircularImageView image;

        OnContactItemListener onContactItemListener;

        public MyViewHolder(@NonNull View itemView, final OnContactItemListener onContactItemListener) {
            super(itemView);

            name = itemView.findViewById(R.id.item_nameTv);
            phoneNumber = itemView.findViewById(R.id.item_phoneTv);
            image = itemView.findViewById(R.id.item_profileIV);

            this.onContactItemListener = onContactItemListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onContactItemListener.onContactClick(getAdapterPosition());
                }
            });
        }

    }

    public interface OnContactItemListener {
        public void onContactClick(int position);
    }
}
