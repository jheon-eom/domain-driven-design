package eventstore;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 일정 주기로 EventStore에서 이벤트를 읽어와 이벤트 핸들러에 전달한다.
 */
@Component
public class EventForwarder {
    private static final int DEFAULT_LIMIT_SIZE = 100;

    private final EventStore eventStore;
    private final OffsetStore offsetStore;
    private final EventSender eventSender;

    public EventForwarder(EventStore eventStore, OffsetStore offsetStore, EventSender eventSender) {
        this.eventStore = eventStore;
        this.offsetStore = offsetStore;
        this.eventSender = eventSender;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = 1000L)
    public void getAndSend() {
        long nextOffset = getNextOffset();
        List<EventEntry> events  = eventStore.get(nextOffset, DEFAULT_LIMIT_SIZE);
        if (!events.isEmpty()) {
            int processedCount = sendEvent(events);
            if (processedCount > 0) {
                saveNextOffset(nextOffset + processedCount);
            }
        }
    }

    private long getNextOffset() {
        return offsetStore.get();
    }

    private int sendEvent(List<EventEntry> events) {
        int processedCount = 0;
        try {
            for (EventEntry event : events) {
                eventSender.send(event);
                processedCount++;
            }
        } catch (Exception e) {
            // 로깅 처리
        }
        return processedCount;
    }

    private void saveNextOffset(long nextOffset) {
        offsetStore.update(nextOffset);
    }
}
