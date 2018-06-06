package com.selfapps.dok.view;

import com.selfapps.dok.model.entity.Entity;

import java.util.List;

public interface MvpView {
    void showList(List<Entity> entities);
}
