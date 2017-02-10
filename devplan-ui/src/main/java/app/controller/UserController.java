package app.controller;

import exception.ConcurrentUserNameException;
import model.User;
import model.UserDto;
import model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by zaven.chilingaryan on 12/2/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UserDto>> loadUsers() {
        return new ResponseEntity<List<UserDto>>(userService.loadAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserDto> loadUserById(@PathVariable Long id) {

        UserDto userDto = userService.loadById(id);

        if (userDto == null) {
            return new ResponseEntity<UserDto>((UserDto) null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/search/{criterion}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<User>> loadSearchUser(@PathVariable Map<String, String> criterion) {
        List<User> user = userService.loadAllUsersByCriteriyes(criterion);
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestBody UserDto user) {
        try {
            userService.delete(user);
        } catch (NullPointerException e) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        ResponseEntity<String> resp;
        try {
            resp = new ResponseEntity<String>("" + (userService.save(user)).getId(), HttpStatus.OK);
        } catch (ConcurrentUserNameException e) {
            resp = new ResponseEntity<String>("", HttpStatus.CONFLICT);
        } catch (Throwable e) {
            resp = new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
        return resp;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/events", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserEvent>> getEventByUserId(@RequestParam(name = "userId") Long userId) {
        try {
            return new ResponseEntity<List<UserEvent>>(userService.getEvents(userId), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<List<UserEvent>>((List<UserEvent>) null, HttpStatus.BAD_REQUEST);
        }
    }

}
