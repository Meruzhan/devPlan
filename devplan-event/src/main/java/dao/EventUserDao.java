package dao;

import model.EventUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by meruzhan.gasparyan on 16-Mar-17.
 */

@Repository
public interface EventUserDao extends CrudRepository<EventUser,Long> {
}
