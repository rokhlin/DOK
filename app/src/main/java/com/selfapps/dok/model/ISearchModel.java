package com.selfapps.dok.model;

import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.IModel;

import java.util.ArrayList;

public interface ISearchModel extends IModel {
    ArrayList<ExpListGroup> getResultSet(String query);
}
