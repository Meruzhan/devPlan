package model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * Created by meruzhan.gasparyan on 16-Mar-17.
 */



@Entity
@Table(name = "userevent")
@Component
public class EventUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "event_Id")
    private Long eventIds;

    @Column(name = "user_Id")
    private Long userIds;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getEventIds() {
        return eventIds;
    }

    public void setEventIds(Long eventIds) {
        this.eventIds = eventIds;
    }

    public Long getUserIds() {
        return userIds;
    }

    public void setUserIds(Long userIds) {
        this.userIds = userIds;
    }
}
