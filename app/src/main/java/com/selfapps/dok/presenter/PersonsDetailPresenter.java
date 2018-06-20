package com.selfapps.dok.presenter;

import android.widget.ImageView;

import com.selfapps.dok.R;
import com.selfapps.dok.model.DetailsModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;

public class PersonsDetailPresenter extends BaseDetailsPresenter<DetailsModel<Person>> {

    public PersonsDetailPresenter(DetailsModel<Person> model, String id) {
        super(model, id);
    }


    @Override
    public void onAdditionalScreenSelected() {
        view.showAdditionalScreen(id, DataType.PERSON);
    }

    @Override
    public void onMapSelected() {

    }

    @Override
    public void onPlaceSelected() {

    }

    @Override
    public void viewIsReady() {
       model.loadImage(view.getImageContainer());
       view.showName(model.getContent().getName());
       view.updateFab(R.drawable.additional_content_img);
       view.showTextContent(model.getContent().getContent());
    }
}
