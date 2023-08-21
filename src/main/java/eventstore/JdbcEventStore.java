package eventstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

import static org.apache.tomcat.jni.SSL.getTime;

/**
 * JDBC를 이용한 EventStore 구현 클래스
 */
@Component
public class JdbcEventStore implements EventStore {
    private final ObjectMapper objectMapper;
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventStore(ObjectMapper objectMapper, JdbcTemplate jdbcTemplate) {
        this.objectMapper = objectMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Object event) {
        EventEntry entry = new EventEntry(event.getClass().getName(), "application/json", toJson(event));
        jdbcTemplate.update("""
                    INSERT INTO (type, content_type, payload, timestamp)
                    VALUES (?, ?, ?, ?, ?);
                """,
                ps -> {
                    ps.setString(1, entry.getType());
                    ps.setString(2, entry.getContentType());
                    ps.setString(3, entry.getPayload());
                    ps.setTimestamp(4, new Timestamp(entry.getTimestamp()));
                });
    }

    @Override
    public List<EventEntry> get(long offset, long limit) {
        return jdbcTemplate.query("""
                    SELECT *
                    FROM evententry
                    ORDER BY id ASC
                    LIMIT ?, ?
                """,
                ps -> {
                    ps.setLong(1, offset);
                    ps.setLong(2, limit);
                },
                (rs, rowNum) -> new EventEntry(
                        rs.getLong("id"),
                        rs.getString("type"),
                        rs.getString("content_type"),
                        rs.getString("payload"),
                        rs.getTimestamp("timestamp", getTime())
                );
    }

    private String toJson(Object event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new PayloadConvertException(e);
        }
    }
}
