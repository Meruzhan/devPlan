package app.controller;

import model.Event;
import model.EventUser;
import model.Subscriber;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import service.EventService;
import service.UserService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Event>> loadAllEvents() {
        return new ResponseEntity<List<Event>>(eventService.loadAllEvents(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Event>> searchEvent(@RequestParam Map<String, String> criterionSherch) {
        List<Event> events = eventService.loadSearchEvent(criterionSherch);
        if (events.isEmpty()) {
            return new ResponseEntity<List<Event>>((List) null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/subscribers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Subscriber>> loadSubscribers(@PathVariable(name = "id") Long eventsId) {
        try {
            return new ResponseEntity<List<Subscriber>>(eventService.loadEventSubscriber(eventsId), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<List<Subscriber>>((List) null, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/subscribers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> loadSubscribers(@RequestParam(name = "eventId") Long eventsId,@RequestParam(name = "userId")Long userId) {
        try {
            return new ResponseEntity<>(eventService.loadSubscriber(eventsId,userId), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>( false, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/subscribers", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<EventUser> saveSubscriber(@RequestBody Map<String,Long> ids) {
        try {
            return new ResponseEntity<>(eventService.saveSubscribe(ids.get("eventId"), ids.get("subscriberId")), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>((EventUser) null, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/subscribers", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Boolean> deleteSubscriber(@RequestBody Map<String,Long> ids) {
        try {
            return new ResponseEntity<>(eventService.deleteSubscriber(ids.get("eventId"),ids.get("subscriberId")), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/subscribers/count", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Long> loadSubscriberCount(@RequestParam(name = "eventId") Long eventId) {
        try {
            return new ResponseEntity<Long>(eventService.loadSubscribersCount(eventId), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<Long>((Long) null, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Event loadEventById(@PathVariable Long id) {
        return eventService.loadEventById(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> deleteEvent(@RequestBody Event event) {
        eventService.deleteEvent(event);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        try {

            return new ResponseEntity<Event>(eventService.saveEvent(event), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<Event>((Event) null, HttpStatus.BAD_REQUEST);
        }
    }


}
