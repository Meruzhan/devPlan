package dao;

import model.User;
import model.UserDto;
import model.UserEvent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by meruzhan.gasparyan on 29-Nov-16.
 */

@Repository
@RepositoryRestResource(excerptProjection = UserDto.class)
public interface UserDao extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {


    List<UserDto> findAllUserDtoBy();

    UserDto findUserDtoById(Long id);

    UserDto findByFirstName(@Param("firstName") String firstName);

    User findByUserName(@Param("userName") String userName);

    void deleteUserDtoBy(UserDto userDto);


}
