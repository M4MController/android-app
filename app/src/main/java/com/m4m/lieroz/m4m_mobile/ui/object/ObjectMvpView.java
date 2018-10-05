package com.m4m.lieroz.m4m_mobile.ui.object;

import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.MvpView;

import java.util.List;

public interface ObjectMvpView extends MvpView {

    void update(List<UserRelationsResponse.Sensor> sensors);
}
