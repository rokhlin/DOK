package com.selfapps.dok.presenter;

import com.selfapps.dok.R;
import com.selfapps.dok.model.IDetailsModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;

public class PersonsDetailPresenter extends BaseDetailsPresenter<IDetailsModel<Person>> {

    public PersonsDetailPresenter(IDetailsModel<Person> model, String id) {
        super(model, id);
    }


    @Override
    public void onAdditionalScreenSelected() {
        view.showAdditionalScreen(id, DataType.PERSON);
    }

    @Override
    public void onMapSelected() {
        //Don't use here
    }

    @Override
    public void onPlaceSelected() {
        //Don't use here
    }

    @Override
    public void viewIsReady() {
       model.loadImage(view.getImageContainer());
       view.showName(model.getContent().getName());
       view.updateFab(R.drawable.additional_content_img);
       view.showTextContent(model.getContent().getContent());
    }
}
