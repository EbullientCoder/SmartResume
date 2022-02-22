package com.example.smartcard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcard.R;
import com.example.smartcard.interfaces.PortfolioCallback;
import com.example.smartcard.model.ProjectModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder> {

    List<ProjectModel> mdata;
    PortfolioCallback listener;

    public PortfolioAdapter(List<ProjectModel> mdata, PortfolioCallback listener) {
        this.mdata = mdata;
        this.listener = listener;
    }


    @NonNull
    @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_portfolio,parent,false) ;

        return new PortfolioViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, int position) {
        holder.setProjectImage(mdata.get(position).getImageLink());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class PortfolioViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePhoto;

        public PortfolioViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePhoto = itemView.findViewById(R.id.item_portfolio_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPortfolioItemClick(getAdapterPosition());
                }
            });
        }

        //Set Image
        public void setProjectImage(String link){
            Picasso.get().load(link).error(R.drawable.userspace).into(profilePhoto);
        }
    }
}
