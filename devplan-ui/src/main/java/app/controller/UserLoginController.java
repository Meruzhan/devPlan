package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Map;

/**
 * Created by meruzhan.gasparyan on 06-Feb-17.
 */

@RestController
@RequestMapping("/login")
public class UserLoginController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userName) {
        try {
            return new ResponseEntity<Map<String, Object>>(userService.signIn(userName.get("userName"), userName.get("password")), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<Map<String, Object>>((Map) null, HttpStatus.BAD_REQUEST);
        }
    }
}
