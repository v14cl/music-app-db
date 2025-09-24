package com.music.collection;

import java.sql.*;

public class TrackDAO {
    private final String url = "jdbc:sqlite:Music.db";

    public int insert(Track track) {
        String sql = """
                INSERT INTO tracks(track_id, title, duration, album_id)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, track.getTrackId());
            pstmt.setString(2, track.getTitle());
            pstmt.setInt(3, track.getDuration());
            pstmt.setInt(4, track.getAlbumId());

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

    public Track getById(int id) {
        String sql = "SELECT track_id, title, duration, album_id FROM tracks WHERE track_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Track(
                            rs.getInt("track_id"),
                            rs.getString("title"),
                            rs.getInt("duration"),
                            rs.getInt("album_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int id, Track track) {
        String sql = """
                UPDATE tracks
                SET track_id = ?, title = ?, duration = ?, album_id = ?
                WHERE track_id = ?
                """;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, track.getTrackId());
            pstmt.setString(2, track.getTitle());
            pstmt.setInt(3, track.getDuration());
            pstmt.setInt(4, track.getAlbumId());
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
        String sql = "DELETE FROM tracks WHERE track_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
