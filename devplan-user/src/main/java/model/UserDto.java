package model;

import org.hibernate.criterion.Projections;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.EntityResult;

/**
 * Created by meruzhan.gasparyan on 27-Jan-17.
 */

@Projection(name = "userDto", types = {User.class})
public interface UserDto {

    Long getId();

    String getFirstName();

    String getLastName();

    String getRole();

}
