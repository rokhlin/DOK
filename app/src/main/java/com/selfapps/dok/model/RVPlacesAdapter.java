package com.selfapps.dok.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.R;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.model.entity.Language;
import com.selfapps.dok.model.entity.POIContent;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.utils.Utils;


import java.util.ArrayList;


public class RVPlacesAdapter extends RecyclerView.Adapter<RVPlacesAdapter.PlacesViewHolder> {
    private static final String TAG = RVPlacesAdapter.class.getSimpleName();
    private ArrayList<Place> places;

    public RVPlacesAdapter(ArrayList<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_places, parent, false);
        return new PlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHolder holder, int position) {
        POIContent content = getPoiContentByLanguage(places.get(position));
        holder.name.setText(content.getName());
        holder.address.setText(content.getAddress());
    try{
        String imgName = null;
        if (places.get(position).getImageList()!=null ||
                places.get(position).getImageList().size()!=0 )
        imgName = places.get(position).getImageList().get(0);

        loadImage(holder.logo, imgName);
    } catch (IndexOutOfBoundsException e) {
        Log.d(TAG,"Logo is empty. Image loading error "+e.getMessage());
    }
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO create open intent
            }
        });

    }

    private void loadImage(ImageView logo, String imgName) {
        if (imgName == null) return;
        Communicator.loadUsingGlide(logo,imgName);
       // Communicator.loadImageFromCache(logo, imgName);
    }

    private POIContent getPoiContentByLanguage(Place place) {
        switch (Utils.getCurrentLanguage()){
            case En:
                return place.getPlaceData().getEn();
            case Ru:
                return place.getPlaceData().getRu();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder{
        TextView name, address;
        Button details;
        ImageView logo;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tw_name);
            address = (TextView) itemView.findViewById(R.id.tw_address);
            details =(Button) itemView.findViewById(R.id.btn_details);
            logo = (ImageView) itemView.findViewById(R.id.img_photo);
        }
    }
}
