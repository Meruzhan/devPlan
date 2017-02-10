package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by meruzhan.gasparyan on 12-Dec-16.
 */

@Entity
@Table(name = "events")
public class UserEvent implements Serializable {

    private Long id;
    private String title;
    private Date startDate;
    private Date EndDate;
//    private String discation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "start_Date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_Date")
    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return "SimplEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", EndDate=" + EndDate +
                 +
                '}';
    }

}
