package service;

        import dao.EventDao;
        import dao.EventUserDao;
        import filter.EventFilter;
        import model.Event;
        import model.EventUser;
        import model.Subscriber;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.stereotype.Repository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.stream.Collectors;
/**
 * Created by srapion.hunanyan on 12/1/2016.
 */
@Service("eventService")
@Repository
@Transactional
public class EventServiceImpl implements EventService {
    private EventDao eventDao;
    private EventFilter<Event> criteriaEventFilter;

    @Autowired
    private EventUserDao eventUserDao;

    @Autowired
    public void setCriteriaEventFilter(EventFilter<Event> criteriaEventFilter) {
        this.criteriaEventFilter = criteriaEventFilter;
    }

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Page<Event> loadPageEvents(int page,int size) {
        Pageable pages = new PageRequest(page,size);
        return  eventDao.findAll(pages);
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

    public Page<Event> loadSearchEvent(int page, int size, String searchField) {
        Map<String,String> criteriaMap = new HashMap<>();
        criteriaMap.put("title", searchField);
        Pageable pages = new PageRequest(page,size);
        criteriaEventFilter.setFilters(criteriaMap);
        return eventDao.findAll(criteriaEventFilter,pages);
    }

    public List<Subscriber> loadEventSubscriber(Long eventId) {
        return eventDao.findOne(eventId).getSubscribers();
    }

    public Long loadSubscribersCount(Long eventId) {
        return (long) eventDao.findOne(eventId).getSubscribers().size();
    }

    public Boolean loadSubscriber(Long eventId, final Long userId) {
        List<Subscriber> subscribers = eventDao.findOne(eventId).getSubscribers().stream().filter(sub -> userId.equals(sub.getId())).collect(Collectors.toList());
        return subscribers.isEmpty();
    }

    @Override
    public Boolean deleteSubscriber(Long eventId, Long subscriberId) {
       Event events =  eventDao.findOne(eventId);
        Subscriber subscriber = events.getSubscribers().stream().filter(sub -> sub.getId().equals(subscriberId )).findAny().orElse(null);
        events.getSubscribers().remove(subscriber);
        return true;
    }

    @Override
    public EventUser saveSubscribe(Long eventId, Long subscriberId){
        EventUser eventUser = new EventUser();
        eventUser.setEventIds(eventId);
        eventUser.setUserIds(subscriberId);
        return eventUserDao.save(eventUser);
    }

}
