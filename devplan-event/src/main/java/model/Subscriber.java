package model;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by meruzhan.gasparyan on 08-Feb-17.
 */


@Entity
@Table(name = "users")
@Component
public class Subscriber {

    private Long id;
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 2, max = 60)
    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 2, max = 60)
    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
