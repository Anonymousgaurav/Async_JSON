package com.example.async_task;

import android.util.Log;

public class NewsData {

    String name;
    String title;
    String urlToImage;
    String author;

//    public NewsData(String name, String title, String urlToImage, String author) {
//        this.name = name;
//        this.title = title;
//        this.urlToImage = urlToImage;
//        this.author = author;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        Log.d("getAuthor: ",author);
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}