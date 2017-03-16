package model;

import app.config.EventConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.EventService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meruzhan.gasparyan on 15-Mar-17.
 */
public class testMain {

    public static void main(String[] args)   {
//        SpringApplication.run(UserDtoTest.class);

        AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(EventConfig.class);
        EventService userService = cxt.getBean("eventService", EventService.class);

//        userService.saveSubscribe((long)1,(long)2);
//        User user = new User();
//        user.setFirstName("firstName6");
//        user.setLastName("lastName6");
//        user.setRole(Role.ROLE_USER.name());
//        user.setPassword("password6");
//        user.setUserName(null);

//        List<Long> ids = new ArrayList<Long>();
//        ids.add(1l);
//        ids.add(2l);
//        ids.add(3l);
//        ids.add(4l);
//            System.out.println(userService.save(user));
//        userService.delete(null);
//        User user1 = userService.loadUserByUserName(null);
//        System.out.println(user1);
//        System.out.println(userService.loadUser(25l));
//        System.out.println(userService.loadUser(1l));
//        System.out.println(userService.loadById(20l));
//        List<UserDto> userDtos = userService.loadAll();
//
//        for (UserDto us : userDtos) {
//            System.out.println(us);

//        }

    }
}
