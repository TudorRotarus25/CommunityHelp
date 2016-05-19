package com.unibuc.communityhelpv3.pojos.interfaces;

/**
 * Created by Tudor on 13.05.2016.
 */
public interface AddLocationListener {
    int TYPE_HOME = 1;
    int TYPE_WORK = 2;
    void onAddLocationSuccess(int type);
    void onAddLocationFailed();
}
