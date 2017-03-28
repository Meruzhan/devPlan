package service;

import model.Event;
import model.EventUser;
import model.Subscriber;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by srapion.hunanyan on 12/1/2016.
 */
public interface EventService {

    Page<Event>  loadPageEvents(int page, int size);

    Event loadEventById(long id);

    List<Event> loadByStartDate(Date startDate);

    List<Event> loadByTitle(String title);

    void deleteEvent(Event event);

    Event saveEvent(Event event);

    Page<Event> loadSearchEvent(int page, int size, String searchField) ;

    /**
     *
     * @param eventId
     * @return
     * @throws NullPointerException if {@code eventId} is not exist
     */
    List<Subscriber> loadEventSubscriber(Long eventId);

    Long loadSubscribersCount(Long eventId);

    Boolean loadSubscriber(Long eventId, Long userId);

    Boolean deleteSubscriber(Long eventId,Long subscriberId);

    EventUser saveSubscribe(Long eventId, Long userId);
}
