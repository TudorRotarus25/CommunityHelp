package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class TaskGetBody {
    String title;

    public TaskGetBody(String content)
    {
        this.title = content;
    }


    public String getContent() {
        return title;
    }

    public void setContent(String content) {
        this.title = content;
    }




}
