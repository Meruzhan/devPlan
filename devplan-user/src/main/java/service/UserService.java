package service;

import exception.ConcurrentUserNameException;
import model.User;
import model.UserDto;
import model.UserEvent;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by meruzhan.gasparyan on 29-Nov-16.
 */


public interface UserService {

    /**
     * load all UserDto
     * @return List UserDto
     */
    List<UserDto> loadAll();

    /**
     * return userDto List by id
     * @param ids
     * @return
     */
    List<UserDto> loadUserDtosById(Iterable<Long> ids);


    /**
     * load users by given criterion
     * @param criterion
     * @return Users by {@code criterion}
     */
    List<User> loadAllUsersByCriteriyes(Map<String, String> criterion);

    /**
     * load UserDto by Id
     * @param id
     * @return User or {@literal null} if the given {@code id} is not exist
     */
    UserDto loadById(Long id);

    /**
     * load userDto by given first name
     *
     * @param firstName
     * @return if the given {@code firstName} is not exist return {@literal null} else return userDto
     */
    UserDto loadByFirstName(String firstName);


    /**
     * the to check is if there is a user generated token for the user and return a user and token
     *
     * @param userName
     * @param password
     * @return
     * @throws IllegalArgumentException if {@code userName} or {@code password} is {@literal null} or {@code userName} or {@code password} is not exist
     */
    Map<String, Object> signIn(String userName, String password);

    /**
     * load user by given userName
     *
     * @param userName
     * @return User or {@literal null} if the given {@code username} is not exist
     */
    User loadUserByUserName(String userName);

    /**
     * @param id
     * @return User or {@literal null} if the given {@code id} is not exist
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    User loadUser(Long id);

    List<UserEvent> getEvents(Long userId);

    /**
     * @param user
     * @throws NullPointerException if {@code userDto} is {@Literal null}
     */
    void delete(UserDto user);

    /**
     * @param user
     * @return
     * @throws NullPointerException    if {@code userName} is {@literal null} or
     * @throws ConcurrentUserNameException if {@code userName of user} is exist
     */
    User save(@Valid User user) throws ConcurrentUserNameException;


}
