package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by srapion.hunanyan on 12/1/2016.
 */

@Entity
@Table(name = "events")
@Component
public class Event implements Serializable {
    private Long id;
    private Date startDate;
    private Date endDate;
    private String title;
    private String description;
    private Long authorId;
    private List<Subscriber> subscribers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "author_Id")
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USEREVENT",joinColumns = @JoinColumn(name = "EVENT_ID",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @JsonIgnore
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }


    public void setSubscribers(List<Subscriber> subScribers) {
        this.subscribers = subScribers;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title=" + title +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
