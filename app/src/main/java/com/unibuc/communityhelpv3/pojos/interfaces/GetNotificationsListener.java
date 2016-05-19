package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.NotificationsGetBody;

/**
 * Created by Serban Theodor on 19-May-16.
 */
public interface GetNotificationsListener {
    void onGetMyNotificationsSuccess(NotificationsGetBody response);
    void onGetMyNotificationFailed();
}
