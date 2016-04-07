package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.UserGetBody;

/**
 * Created by Tudor on 07.04.2016.
 */
public interface ProfileListener {
    void onProfileSuccess(UserGetBody user);
    void onProfileFailed();
}
