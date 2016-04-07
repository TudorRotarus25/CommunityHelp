package com.unibuc.communityhelpv3.pojos;

import java.util.ArrayList;

/**
 * Created by Tudor on 31.03.2016.
 */
public class CategoriesGetBody {

    ArrayList<Category> categories;

    public CategoriesGetBody(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public class Category {
        int id;
        String title;
        String text;
        int type;
        String picture_url;

        public Category(int id, String title, String text, String picture_url) {
            this.id = id;
            this.title = title;
            this.text = text;
            this.picture_url = picture_url;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
