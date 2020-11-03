package com.gurudattdahare.photos.Modal;

public class Guru {
    private String URL;
    private int downloads;
    private int likes;

    public Guru(String URL, int downloads, int likes) {
        this.URL = URL;
        this.downloads = downloads;
        this.likes = likes;
    }

    public Guru() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
