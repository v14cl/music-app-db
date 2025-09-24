package com.music.collection;

import java.sql.*;

public class AlbumDAO {
    private final String url = "jdbc:sqlite:Music.db";

    public int insert(Album album) {
        String sql = """
                INSERT INTO albums(album_id, title, releaseYear, artist_id)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, album.getAlbumId());
            pstmt.setString(2, album.getTitle());
            pstmt.setInt(3, album.getReleaseYear());
            pstmt.setInt(4, album.getArtistId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No rows affected.");
            }

            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Album getById(int id) {
        String sql = "SELECT album_id, title, releaseYear, artist_id FROM albums WHERE album_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Album(
                            rs.getInt("album_id"),
                            rs.getString("title"),
                            rs.getInt("releaseYear"),
                            rs.getInt("artist_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int id, Album album) {
        String sql = """
                UPDATE albums
                SET album_id = ?, title = ?, releaseYear = ?, artist_id = ?
                WHERE album_id = ?
                """;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, album.getAlbumId());
            pstmt.setString(2, album.getTitle());
            pstmt.setInt(3, album.getReleaseYear());
            pstmt.setInt(4, album.getArtistId());
            pstmt.setInt(5, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM albums WHERE album_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
