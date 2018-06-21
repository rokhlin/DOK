package com.selfapps.dok.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Tag;
import com.selfapps.dok.network.Communicator;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    ArrayList<ExpListGroup> groups;

    public ExpListAdapter(ArrayList<ExpListGroup> groups) {
        this.groups = groups;
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
        textGroup.setText(groups.get(groupPosition).getType().name() +"S");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DataType itemType =  groups.get(groupPosition).getType();
        Tag tag;

        LayoutInflater inflater = (LayoutInflater)  App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView imageView;
        String imgPath = "";
        if(itemType.equals(DataType.IMAGE)){
            imgPath = groups.get(groupPosition).getImageList().get(childPosition);
            tag = new Tag(itemType.name(),imgPath);
            convertView = inflater.inflate(R.layout.list_item_image, null);

            imageView = convertView.findViewById(R.id.img_photo);

        }else {
            convertView = inflater.inflate(R.layout.list_item_persons, null);
            imageView = convertView.findViewById(R.id.img_photo1);
            TextView name = convertView.findViewById(R.id.tw_name1);
            TextView strType = convertView.findViewById(R.id.tv_type_marker);
            TextView button = convertView.findViewById(R.id.btn_details);

            Entity entity = (Entity) getChild(groupPosition,childPosition);
            imgPath = entity.getImageList().get(0);
            strType.setText(itemType.name().toLowerCase());
            name.setText(entity.getName());
            tag = new Tag(itemType.name(),entity.getId());
            button.setOnClickListener(this);
            button.setTag(tag);

        }

        Communicator.loadUsingGlide(imageView,imgPath);

        convertView.setTag(tag);
        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onClick(View v) {
        Tag tag = (Tag) v.getTag();
    }
}
