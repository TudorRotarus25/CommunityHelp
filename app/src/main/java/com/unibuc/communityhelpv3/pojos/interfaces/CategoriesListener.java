package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.CategoriesGetBody;

/**
 * Created by Tudor on 31.03.2016.
 */
public interface CategoriesListener {
    void onCategoryGetSuccess(CategoriesGetBody response);
    void onCategoryGetFailed();
}
