package com.selfapps.dok.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.PersonContent;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.utils.Utils;
import com.selfapps.dok.view.activity.PersonDetailActivity;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RVPersonsAdapter extends RecyclerView.Adapter<RVPersonsAdapter.PersonsViewHolder> {
    private static final String TAG = RVPersonsAdapter.class.getSimpleName();
    private ArrayList<Person> persons;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(App.getContext(), PersonDetailActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Constants.CONTENT_ID_TAG, v.getTag().toString());
            App.getContext().startActivity(intent);
        }
    };

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
        String id = "";
        Person person = null;
        try {
            person = persons.get(position);
            if(person==null) return;
            PersonContent content = getContentByLanguage(person);
            id =person.getId();
            //Name set up
            holder.name.setText(content.getName());
        } catch (NullPointerException e) {
            Log.e(TAG,"Person is empty. Content error "+e.getMessage());
        }

        //Logo set up
        try{
            String imgName = null;
            if (person.getImageList()!=null ||
                    person.getImageList().size()!=0 )
                imgName = person.getImageList().get(0);

            loadImage(holder.logo, imgName);
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG,"Logo is empty. Image loading error "+e.getMessage());
        }

        //Button set up
        holder.cv.setTag(id);
        holder.cv.setOnClickListener(listener);
        holder.details.setTag(id);
        holder.details.setOnClickListener(listener);

    }

    private void loadImage(ImageView logo, String imgName) {
        if (imgName == null) return;
        Communicator.loadImageFilterSepia(logo,imgName,R.drawable.siluet);
    }

    private PersonContent getContentByLanguage(Person item) {
        switch (Utils.getCurrentLanguage()){
            case En:
                return item.getPersonData().getEn();
            case Ru:
                return item.getPersonData().getRu();
            default:
                return null;
        }
    }



    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonsViewHolder extends RecyclerView.ViewHolder{
        TextView name, details;
        ImageView logo;
        CardView cv;

        public PersonsViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tw_name1);
            details =(TextView) itemView.findViewById(R.id.btn_details);
            logo = (ImageView) itemView.findViewById(R.id.img_photo1);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}
