package eventstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API를 이용해서 이벤트 목록을 제공하는 컨트롤러
 */
@RestController
public class EventApi {
    private final EventStore eventStore;

    public EventApi(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @GetMapping("/api/events")
    public List<EventEntry> list(@RequestParam("offset") Long offset, @RequestParam("limit") Long limit) {
        return eventStore.get(offset, limit);
    }
}
