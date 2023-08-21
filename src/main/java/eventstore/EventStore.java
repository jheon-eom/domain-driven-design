package eventstore;

import java.util.List;

/**
 * 이벤트를 저장하고 조회하는 인터페이스를 제공
 */
public interface EventStore {

    void save(Object event);

    List<EventEntry> get(long offset, long limit);
}
