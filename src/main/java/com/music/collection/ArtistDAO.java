package com.music.collection;

import java.sql.*;

public class ArtistDAO {
    private final String url = "jdbc:sqlite:Music.db";

    public int insert(Artist artist) {
        String sql = """
                INSERT INTO artists(artist_id, name, country, formedYear)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, artist.getArtistId());
            pstmt.setString(2, artist.getName());
            pstmt.setString(3, artist.getCountry());
            pstmt.setInt(4, artist.getFormedYear());

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

    public Artist getById(int id) {
        String sql = "SELECT artist_id, name, country, formedYear FROM artists WHERE artist_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Artist(
                            rs.getInt("artist_id"),
                            rs.getString("name"),
                            rs.getString("country"),
                            rs.getInt("formedYear"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int id, Artist artist) {
        String sql = """
                UPDATE artists
                SET artist_id = ?, name = ?, country = ?, formedYear = ?
                WHERE artist_id = ?
                """;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, artist.getArtistId());
            pstmt.setString(2, artist.getName());
            pstmt.setString(3, artist.getCountry());
            pstmt.setInt(4, artist.getFormedYear());
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
        String sql = "DELETE FROM artists WHERE artist_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
