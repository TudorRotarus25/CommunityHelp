package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.LocationsGetBody;

/**
 * Created by Tudor on 23.05.2016.
 */
public interface LocationsListener {
    void onGetLocationsSuccess(LocationsGetBody response);
    void onGetLocationsFailed();
}
