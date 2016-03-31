package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.LoginPostBody;

/**
 * Created by Tudor on 31.03.2016.
 */
public interface LoginListener {
    void onLoginSuccess(LoginPostBody response);
    void onLoginFailed();
}
