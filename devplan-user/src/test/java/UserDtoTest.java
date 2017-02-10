import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.config.UserConfig;
import exception.ConcurrentUserNameException;
import model.Role;
import model.User;
import model.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by meruzhan.gasparyan on 27-Jan-17.
 */

public class UserDtoTest {

    public static void main(String[] args) throws JsonProcessingException, ConcurrentUserNameException {
//        SpringApplication.run(UserDtoTest.class);

        AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(UserConfig.class);
        UserService userService = cxt.getBean("userService", UserService.class);
//        User user = new User();
//        user.setFirstName("firstName6");
//        user.setLastName("lastName6");
//        user.setRole(Role.ROLE_USER.name());
//        user.setPassword("password6");
//        user.setUserName(null);

        List<Long> ids = new ArrayList<Long>();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);
        ids.add(4l);
//            System.out.println(userService.save(user));
//        userService.delete(null);
//        User user1 = userService.loadUserByUserName(null);
//        System.out.println(user1);
//        System.out.println(userService.loadUser(25l));
        System.out.println(userService.loadUser(1l));
//        System.out.println(userService.loadById(20l));
//        List<UserDto> userDtos = userService.loadAll();
//
//        for (UserDto us : userDtos) {
//            System.out.println(us);

//        }

    }
}

