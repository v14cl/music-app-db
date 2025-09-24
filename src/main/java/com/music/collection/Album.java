package com.music.collection;

public class Album {
    private int albumId;
    private String title;
    private int releaseYear;
    private int artistId;

    public Album(int albumId, String title, int releaseYear, Integer artistId) {
        this.albumId = albumId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.artistId = artistId;
    }

    public Album(String title, int releaseYear, int artistId) {
        this(0, title, releaseYear, artistId);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + albumId +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", artistId=" + artistId +
                '}';
    }
}