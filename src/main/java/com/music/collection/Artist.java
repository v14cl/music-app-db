package com.music.collection;

public class Artist {
    private int artistId;
    private String name;
    private String country;
    private int formedYear;

    public Artist(int artistId, String name, String country, int formedYear) {
        this.artistId = artistId;
        this.name = name;
        this.country = country;
        this.formedYear = formedYear;
    }

    public Artist(String name, String country, int formedYear) {
        this(0, name, country, formedYear);
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFormedYear() {
        return formedYear;
    }

    public void setFormedYear(int formedYear) {
        this.formedYear = formedYear;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + artistId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", formedYear=" + formedYear +
                '}';
    }
}
