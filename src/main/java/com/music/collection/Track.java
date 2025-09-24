package com.music.collection;

public class Track {
    private int trackId;
    private String title;
    private int duration;
    private int albumId;

    public Track(int trackId, String title, int duration, int albumId) {
        this.trackId = trackId;
        this.title = title;
        this.duration = duration;
        this.albumId = albumId;
    }

    public Track(String title, int duration, int albumId) {
        this(0, title, duration, albumId);
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + trackId +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", albumId=" + albumId +
                '}';
    }
}

