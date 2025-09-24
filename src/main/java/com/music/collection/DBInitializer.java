package com.music.collection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInitializer {
    public static void initialize() {
        File dbFile = new File("Music.db");

        if (dbFile.exists()) {
            dbFile.delete();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Music.db");
                Statement stmt = conn.createStatement()) {

            String createArtists = """
                        CREATE TABLE IF NOT EXISTS artists (
                            artist_id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            country TEXT NOT NULL,
                            formedYear INTEGER NOT NULL
                        );
                    """;

            String createAlbums = """
                        CREATE TABLE IF NOT EXISTS albums (
                            album_id INTEGER PRIMARY KEY AUTOINCREMENT,
                            title TEXT NOT NULL,
                            releaseYear INTEGER NOT NULL,
                            artist_id INTEGER,
                            FOREIGN KEY (artist_id) REFERENCES artists(artist_id)
                        );
                    """;

            String createTracks = """
                        CREATE TABLE IF NOT EXISTS tracks (
                            track_id INTEGER PRIMARY KEY AUTOINCREMENT,
                            title TEXT NOT NULL,
                            duration INTEGER NOT NULL,
                            album_id INTEGER NOT NULL,
                            FOREIGN KEY (album_id) REFERENCES albums(album_id)
                        );
                    """;

            stmt.execute(createArtists);
            stmt.execute(createAlbums);
            stmt.execute(createTracks);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
