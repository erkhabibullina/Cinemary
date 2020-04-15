package com.example.android.cinemary.models;

/**
 * Object which holds information about a Video (clip/trailer) for a Movie or TV-show.
 */
public class Video {
    private String _id, title, type, webUrl;

    public Video(String _id, String title, String type, String webUrl) {
        this._id = _id;
        this.title = title;
        this.type = type;
        this.webUrl = webUrl;
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type.toLowerCase();
    }

    public String getWebUrl() {
        return webUrl;
    }

    @Override
    public String toString() {
        return "Video{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
