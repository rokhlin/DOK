package com.selfapps.dok.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.Language;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.model.entity.RouteContent;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.utils.Utils;
import com.selfapps.dok.view.activity.PlaceDetailActivity;
import com.selfapps.dok.view.activity.RoutesDetailActivity;

import java.util.ArrayList;

public class RVRoutesAdapter extends RecyclerView.Adapter<RVRoutesAdapter.RoutesViewHolder> {
    private static final String TAG ="RVRoutesAdapter" ;
    private ArrayList<Route> routes;

    public RVRoutesAdapter(ArrayList<Route> routes) {
        this.routes = routes;
    }

    @NonNull
    @Override
    public RVRoutesAdapter.RoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_routes, parent, false);
        return new RVRoutesAdapter.RoutesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVRoutesAdapter.RoutesViewHolder holder, int position) {
        Route route = null;
        try {
            route = routes.get(position);
            RouteContent content = getContentByLanguage(route);

            holder.name.setText(content.getName());
            holder.placesCount.setText(getPlacesCount(route));

            holder.details.setTag(route.getId());
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(App.getContext(), RoutesDetailActivity.class);
                    intent.putExtra(Constants.CONTENT_ID_TAG, v.getTag().toString());
                    App.getContext().startActivity(intent);
                }
            });

            String imgName = null;
            if (route.getImageList()!= null ||
                    route.getImageList().size()!=0 )
                imgName = route.getImageList().get(0);

            loadImage(holder.logo, imgName);


        } catch (IndexOutOfBoundsException e) {
                Log.e(TAG,"Logo is empty. Image loading error "+e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG,"RouteContent is empty. Skipping this Item... \nRouteContent error "+e.getMessage());
        }
    }

    private String getPlacesCount(Route route) {
        int count = route.getPoiIdList().size();
        switch (Utils.getCurrentLanguage()){
            case En:
                return getEngString(count);
            case Ru:
                return getRusString(count);
            default:
                return null;
        }
    }

    private String getRusString(int count) {
        String suffix = "мест";

        if(count == 1) suffix = "место";
        else if(count>1 && count < 5) suffix = "места";

        return count+" "+ suffix;
    }

    private String getEngString(int count) {
        return count > 1? count+" places" : count+" place";
    }

    private void loadImage(ImageView logo, String imgName) {
        if (imgName == null) return;
        Communicator.loadUsingGlide(logo,imgName);
        // Communicator.loadImageFromCache(logo, imgName);
    }

    private RouteContent getContentByLanguage(@NonNull Route route) throws NullPointerException {
            switch (Utils.getCurrentLanguage()){
                case En:
                    return route.getData().getEn();
                case Ru:
                    return route.getData().getRu();
                default:
                    return null;
            }
    }



    @Override
    public int getItemCount() {
        return routes.size();
    }

    public class RoutesViewHolder extends RecyclerView.ViewHolder{
        TextView name, placesCount;
        Button details;
        ImageView logo;

        public RoutesViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tw_name);
            placesCount = (TextView) itemView.findViewById(R.id.tv_paces_count);
            details =(Button) itemView.findViewById(R.id.btn_details);
            logo = (ImageView) itemView.findViewById(R.id.img_photo);
        }
    }
}
