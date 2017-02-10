package app.controller;

import model.Event;
import model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EventService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Event>> loadAllEvents() {
        return new ResponseEntity<List<Event>>(eventService.loadAllEvents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Event>> searchEvent(@RequestParam Map<String, String> criterionSherch) {
        List<Event> events = eventService.loadSearchEvent(criterionSherch);
        if (events.isEmpty()) {
            return new ResponseEntity<List<Event>>((List) null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/subscribers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Subscriber>> loadSubscribers(@RequestParam(name = "eventId") Long eventsId) {
        try {
            return new ResponseEntity<List<Subscriber>>(eventService.loadEventSubscriber(eventsId), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<List<Subscriber>>((List) null, HttpStatus.BAD_REQUEST);
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

            return new ResponseEntity<Event>((eventService.saveEvent(event)), HttpStatus.OK);
        }catch (Throwable e){
            return new ResponseEntity<Event>((Event) null, HttpStatus.BAD_REQUEST);
        }
    }


}
