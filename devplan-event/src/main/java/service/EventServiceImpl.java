package service;

import dao.EventDao;
import filter.Filter;
import model.Event;
import model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by srapion.hunanyan on 12/1/2016.
 */
@Service("eventService")
@Repository
@Transactional
public class EventServiceImpl implements EventService {
    private EventDao eventDao;
    private Filter<Event> criteriaFilter;

    @Autowired
    public void setCriteriaFilter(Filter<Event> criteriaFilter) {
        this.criteriaFilter = criteriaFilter;
    }

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<Event> loadAllEvents() {
        return (List<Event>) eventDao.findAll();
    }

    public Event loadEventById(long id) {
        return eventDao.findOne(id);
    }

    public List<Event> loadByStartDate(Date startDate) {
        return eventDao.findEventByStartDate(startDate);
    }

    public List<Event> loadByTitle(String title) {
        return eventDao.findEventsByTitle(title);
    }

    public void deleteEvent(Event event) {
        eventDao.delete(event);
    }

    public Event saveEvent(Event event) {
        return eventDao.save(event);
    }

    public List<Event> loadSearchEvent(Map<String, String> criterion) {
        criteriaFilter.setFilters(criterion);
        return eventDao.findAll(criteriaFilter);
    }

    public List<Subscriber> loadEventSubscriber(Long eventId) {
        return eventDao.findOne(eventId).getSubscribers();
    }
}
