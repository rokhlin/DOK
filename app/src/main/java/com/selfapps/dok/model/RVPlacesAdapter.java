package com.selfapps.dok.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.model.entity.POIContent;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.utils.Utils;
import com.selfapps.dok.view.activity.PlaceDetailActivity;


import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class RVPlacesAdapter extends RecyclerView.Adapter<RVPlacesAdapter.PlacesViewHolder> {
    private static final String TAG = RVPlacesAdapter.class.getSimpleName();
    private ArrayList<Place> places;

    public RVPlacesAdapter(ArrayList<Place> places) {
        this.places = places;
    }
    private View.OnClickListener listener = new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(App.getContext(), PlaceDetailActivity.class);
                                                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                                    intent.putExtra(Constants.CONTENT_ID_TAG, v.getTag().toString());
                                                    App.getContext().startActivity(intent);
                                                }
                                            };

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_places, parent, false);
        return new PlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHolder holder, int position) {
        if(places.get(position)==null) return;

        POIContent content = getPoiContentByLanguage(places.get(position));
        holder.name.setText(content.getName());
        holder.address.setText(content.getAddress());
        String id ="";
    try{
        String imgName = null;
        if (places.get(position).getImageList()!=null ||
                places.get(position).getImageList().size()!=0 )
        imgName = places.get(position).getImageList().get(0);

        id = places.get(position).getId();

        loadImage(holder.logo, imgName);
    } catch (IndexOutOfBoundsException e) {
        Log.d(TAG,"Logo is empty. Image loading error "+e.getMessage());
    }
        holder.cv.setTag(id);
        holder.cv.setOnClickListener(listener);
        holder.details.setTag(id);
        holder.details.setOnClickListener(listener);

    }

    private void loadImage(ImageView logo, String imgName) {
        if (imgName == null) return;
        Communicator.loadImageFilterSepia(logo,imgName,R.drawable.place_holder);
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
        CardView cv;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tw_name);
            address = (TextView) itemView.findViewById(R.id.tw_address);
            details =(Button) itemView.findViewById(R.id.btn_details);
            logo = (ImageView) itemView.findViewById(R.id.img_photo);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}
