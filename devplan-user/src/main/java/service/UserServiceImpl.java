package service;

import dao.UserDao;
import exception.ConcurrentUserNameException;
import filter.Filter;
import model.User;
import model.UserDto;
import model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.JwtUtil;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by meruzhan.gasparyan on 29-Nov-16.
 */

@Service("userService")
@Repository
@Transactional
@PropertySource("classpath:token.config.properties")
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private Filter<User> filterUser;
    @Autowired
    private Environment properties;

    public List<UserDto> loadAll() {
        return userDao.findAllUserDtoBy();
    }

    public List<UserDto> loadUserDtosById(Iterable<Long> ids) {
        return getUsersDtoByIds(ids);
    }

    public List<User> loadAllUsersByCriteriyes(Map<String, String> criterion) {
        filterUser.setFilters(criterion);
        return userDao.findAll(filterUser);
    }

    public UserDto loadById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return userDao.findUserDtoById(id);
    }

    public UserDto loadByFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("The given User Name must not be null");
        }
        return userDao.findByFirstName(firstName);
    }

    public Map<String, Object> signIn(String userName, String password) {
        User user = userDao.findByUserName(userName);
        if (user == null || password == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        map.put("token", JwtUtil.generateToken(user,properties.getProperty("token.singining.key")));
        return map;

    }

    public User loadUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }


    public User loadUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return userDao.findOne(id);
    }

    public List<UserEvent> getEvents(Long userId) {
        return userDao.findOne(userId).getEvents();
    }

    public void delete(UserDto user) {
        if (user == null) {
            throw new NullPointerException();
        }
        userDao.deleteUserDtoBy(user);
    }


    public User save(@Valid User user) throws ConcurrentUserNameException {
        if (user.getUserName() == null) {
            throw new NullPointerException();
        }
        if (userDao.findByUserName(user.getUserName()) != null) {
            throw new ConcurrentUserNameException(user.getUserName() + " User Name is exist");
        }
        return userDao.save(user);
    }

    private List<UserDto> getUsersDtoByIds(Iterable<Long> ids) {
        List<UserDto> userDtos = new ArrayList<UserDto>();
        if (ids == null || !ids.iterator().hasNext()) {
            return Collections.emptyList();
        }
        for (Long id : ids) {
            userDtos.add(userDao.findUserDtoById(id));
        }
        return userDtos;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setFilterUser(Filter<User> filterUser) {
        this.filterUser = filterUser;
    }
}
