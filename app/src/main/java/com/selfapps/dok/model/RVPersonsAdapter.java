package com.selfapps.dok.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.Language;
import com.selfapps.dok.model.entity.PersonContent;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.network.Communicator;

import java.util.ArrayList;

public class RVPersonsAdapter extends RecyclerView.Adapter<RVPersonsAdapter.PersonsViewHolder> {
    private ArrayList<Person> persons;

    public RVPersonsAdapter(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public RVPersonsAdapter.PersonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_persons, parent, false);
        return new PersonsViewHolder(view);
    }


    public void onBindViewHolder(@NonNull RVPersonsAdapter.PersonsViewHolder holder, int position) {
        PersonContent content = getContentByLanguage(persons.get(position));
        holder.name.setText(content.getName());

        String imgName = null;
        if (persons.get(position).getImageList()!=null ||
                persons.get(position).getImageList().size()!=0 )
            imgName = persons.get(position).getImageList().get(0);

        loadImage(holder.logo, imgName);

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

    private PersonContent getContentByLanguage(Person item) {
        switch (getCurrentLanguage()){
            case En:
                return item.getPersonData().getEn();
            case Ru:
                return item.getPersonData().getRu();
            default:
                return null;
        }
    }

    private Language getCurrentLanguage() {
        //TODO Create language change method
        return Language.Ru;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonsViewHolder extends RecyclerView.ViewHolder{
        TextView name, details;
        ImageView logo;

        public PersonsViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tw_name1);
            details =(TextView) itemView.findViewById(R.id.btn_details);
            logo = (ImageView) itemView.findViewById(R.id.img_photo1);
        }
    }
}
