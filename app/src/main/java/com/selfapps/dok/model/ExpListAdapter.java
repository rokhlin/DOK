package com.selfapps.dok.model;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.App;
import com.selfapps.dok.MyClickListener;
import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Tag;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Utils;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private static final String TAG =" ExpListAdapter" ;
    private ArrayList<ExpListGroup> groups;
    private MyClickListener listener;


    public ExpListAdapter(ArrayList<ExpListGroup> groups, MyClickListener listener) {
        this.groups = groups;
        this.listener = listener;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).size();
    }

    @Override
    public ExpListGroup getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        switch (groups.get(groupPosition).getType()){
            case ROUTE:
                return groups.get(groupPosition).getPlaces().get(childPosition);
            case PERSON:
                return groups.get(groupPosition).getPersons().get(childPosition);
            case PLACE:
                return groups.get(groupPosition).getPlaces().get(childPosition);
            case IMAGE:
                return groups.get(groupPosition).getImageList().get(childPosition);
            default:
                return null;
        }

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText(getGroupName(groupPosition));

        return convertView;
    }

    private String getGroupName(int groupPosition) {
        //TODO separate to different language support
        switch (Utils.getCurrentLanguage()){
            case Ru:
                return getRuGroupName(groupPosition);
            case En:
                return groups.get(groupPosition).getType().toString() +"S";
            default:
                return groups.get(groupPosition).getType().toString() +"S";
        }
    }

    private String getRuGroupName(int groupPosition) {
        switch (groups.get(groupPosition).getType()){
            case PERSON:
                return App.getContext().getResources().getString(R.string.persons);
            case ROUTE:
                return App.getContext().getResources().getString(R.string.routes);
            case PLACE:
                return App.getContext().getResources().getString(R.string.places);
            case IMAGE:
                return App.getContext().getResources().getString(R.string.images);
            default:
                return App.getContext().getResources().getString(R.string.name_undefined);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DataType itemType =  groups.get(groupPosition).getType();
        ImageView imageView;
        TextView zoom;
        String imgPath = "";
        LayoutInflater inflater = (LayoutInflater)  App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Loading Image strategy
        if(itemType.equals(DataType.IMAGE)){
            imgPath = groups.get(groupPosition).getImageList().get(childPosition);
            final Tag tag = new Tag(itemType.name(),imgPath);

            assert inflater != null;
            convertView = inflater.inflate(R.layout.list_item_image, null);
            imageView = convertView.findViewById(R.id.img_photo);
            zoom = convertView.findViewById(R.id.tv_zoom);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(tag);
                }
            };
            zoom.setOnClickListener(clickListener);
            imageView.setOnClickListener(clickListener);
            Communicator.loadImage(imageView,imgPath,R.drawable.place_holder);

        }else {//Loading Entity strategy
            assert inflater != null;
            convertView = inflater.inflate(R.layout.list_item_persons, null);
            imageView = convertView.findViewById(R.id.img_photo1);
            TextView name = convertView.findViewById(R.id.tw_name1);
            TextView strType = convertView.findViewById(R.id.tv_type_marker);
            TextView button = convertView.findViewById(R.id.btn_details);

            Entity entity = (Entity) getChild(groupPosition,childPosition);
            imgPath = entity.getImageList().get(0);
            //strType.setText(itemType.name().toLowerCase());
            strType.setText(App.getContext().getString(R.string.places));
            name.setText(entity.getName());

            final Tag tag = new Tag(itemType.name(),entity.getId());
            convertView.setOnClickListener(this);
            convertView.setTag(tag);
            Communicator.loadImageFilterSepia(imageView,imgPath,R.drawable.place_holder);
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onClick(View v) {
        Tag tag = (Tag) v.getTag();
        listener.onClick(tag);
    }

    public void notifyDataSetChanged(ArrayList<ExpListGroup> groups){
        this.groups = groups;
        this.notifyDataSetChanged();
    }
}
