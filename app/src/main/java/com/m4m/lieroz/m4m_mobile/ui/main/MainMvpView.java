package com.m4m.lieroz.m4m_mobile.ui.main;

import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void update(List<UserRelationsResponse.Object> objects);
}
