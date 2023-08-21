package eventstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JdbcTemplate {
    private Connection connection; // Assume you have a connection instance

    public JdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    public void update(String sql, Consumer<PreparedStatement> psSetter) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            psSetter.accept(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EventEntry> query(String sql, Object... args) throws SQLException {
        List<EventEntry> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EventEntry entry = new EventEntry(
                            rs.getLong("id"),
                            rs.getString("type"),
                            rs.getString("content_type"),
                            rs.getString("payload"),
                            rs.getTimestamp("timestamp").toInstant()
                    );
                    result.add(entry);
                }
            }
        }
        return result;
    }
}
