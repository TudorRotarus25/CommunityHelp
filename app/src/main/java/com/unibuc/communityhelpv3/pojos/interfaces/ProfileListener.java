package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.UserGetBody;

/**
 * Created by Luci on 07/04/2016.
 */
public interface ProfileListener {
    void onProfileSuccess(UserGetBody response);
    void onProfileFailed();
}
