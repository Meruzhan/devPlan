package service;

import model.Event;
import model.Subscriber;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by srapion.hunanyan on 12/1/2016.
 */
public interface EventService {

    List<Event> loadAllEvents();

    Event loadEventById(long id);

    List<Event> loadByStartDate(Date startDate);

    List<Event> loadByTitle(String title);

    void deleteEvent(Event event);

    Event saveEvent(Event event);

    List<Event> loadSearchEvent(Map<String,String> criterion);


    /**
     *
     * @param eventId
     * @return
     * @throws NullPointerException if {@code eventId} is not exist
     */
    List<Subscriber> loadEventSubscriber(Long eventId);
}
